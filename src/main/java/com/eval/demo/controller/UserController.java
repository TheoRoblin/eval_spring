package com.eval.demo.controller;

import com.eval.demo.dao.UserDao;
import com.eval.demo.models.User;
import com.eval.demo.security.AppUserDetailsService;
import com.eval.demo.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    AppUserDetailsService appUserDetailsService;

    @Autowired
    AuthenticationProvider daoAuthenticationProvider;

    @Autowired
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody User user) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.save(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        //UserDetails appUserDetails = appUserDetailsService.loadUserByUsername(user.getEmail());

        try {
            UserDetails userDetails =(UserDetails) daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getPseudo(),
                    user.getPassword()
            )).getPrincipal();
            return jwtUtils.generateJwt(userDetails);
        }catch (Exception ex){
            return null;
        }

    }



}
