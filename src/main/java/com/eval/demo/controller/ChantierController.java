package com.eval.demo.controller;

import com.eval.demo.dao.ChantierDao;
import com.eval.demo.dao.UserDao;
import com.eval.demo.models.Chantier;
import com.eval.demo.models.Operation;
import com.eval.demo.models.Tache;
import com.eval.demo.models.User;
import com.eval.demo.views.ChantierView;
import com.eval.demo.views.OperationView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ChantierController {

    @Autowired
    ChantierDao chantierDao;

    @Autowired
    UserDao userDao;

    @GetMapping("/chantier/list")
    @Secured({"ROLE_OUVRIER","ROLE_ADMIN"})
    @JsonView(ChantierView.class)
    public List<Chantier> liste() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userDao.findByPseudo(username);

        List<Integer> chantierIds = user.get().getChantierOuvList().stream().map(
                Chantier::getId
        ).collect(Collectors.toList());


        return chantierDao.findAllById(chantierIds);
    }

    @GetMapping("chantier/{id}")
    @JsonView(OperationView.class)
    public ResponseEntity<Chantier> get(@PathVariable int id) {
        Optional<Chantier> chantierOptional = chantierDao.findById(id);

        if (chantierOptional.isPresent()) {
            return new ResponseEntity<>(chantierOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("chantier/time/{id}")
    @JsonView(ChantierView.class)
    public ResponseEntity<Integer> getByTime(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userDao.findByPseudo(username);

        List<Integer> chantierIds = user.get().getChantierOuvList().stream().map(
                Chantier::getId
        ).collect(Collectors.toList());


        if (optionalChantier.isPresent()) {
           List<Operation> operationList = optionalChantier.get().getOperationList();

           List<Tache> tacheList = operationList.stream().map(Operation::getTache).collect(Collectors.toList());

           int time = tacheList.stream().mapToInt(Tache::getTemps).sum();


           return new ResponseEntity<>(time, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/chantier/{id}")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> delete(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.deleteById(id);
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/chantier")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> create(@RequestBody @Valid Chantier Chantier) {
        Chantier.setId(null);
        chantierDao.save(Chantier);
        return new ResponseEntity<>(Chantier, HttpStatus.CREATED);
    }

    @PutMapping("/chantier/{id}")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> update(@RequestBody @Valid Chantier Chantier, @PathVariable int id) {
        Chantier.setId(id);
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.save(Chantier);
            return new ResponseEntity<>(Chantier, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
