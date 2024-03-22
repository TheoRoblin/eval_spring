package com.eval.demo.controller;

import com.eval.demo.dao.OperationDao;
import com.eval.demo.dao.UserDao;
import com.eval.demo.models.Chantier;
import com.eval.demo.models.Operation;
import com.eval.demo.models.User;
import com.eval.demo.views.OperationView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class OperationController {

    @Autowired
    OperationDao operationDao;

    @Autowired
    UserDao userDao;

    @GetMapping("/operation/list")
    @Secured({"ROLE_OUVRIER","ROLE_ADMIN"})
    @JsonView(OperationView.class)
    public List<Operation> liste() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> user = userDao.findByPseudo(username);

        List<Integer> operationIds = user.get().getOperationList().stream().map(
                Operation::getId
        ).collect(Collectors.toList());


        return operationDao.findAllById(operationIds);
    }

    @GetMapping("operation/{id}")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> get(@PathVariable int id) {
        Optional<Operation> optionalOperation = operationDao.findById(id);

        if (optionalOperation.isPresent()) {
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/operation/{id}")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> delete(@PathVariable int id) {
        Optional<Operation> optionalOperation = operationDao.findById(id);

        if (optionalOperation.isPresent()) {
            operationDao.deleteById(id);
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/operation")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> create(@RequestBody @Valid Operation Operation) {
        Operation.setId(null);
        operationDao.save(Operation);
        return new ResponseEntity<>(Operation, HttpStatus.CREATED);
    }

    @PutMapping("/operation/{id}")
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> update(@RequestBody @Valid Operation Operation, @PathVariable int id) {
        Operation.setId(id);
        Optional<Operation> optionalOperation = operationDao.findById(id);

        if (optionalOperation.isPresent()) {
            operationDao.save(Operation);
            return new ResponseEntity<>(Operation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
