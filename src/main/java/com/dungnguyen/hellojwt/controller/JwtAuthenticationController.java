package com.dungnguyen.hellojwt.controller;

import com.dungnguyen.hellojwt.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.dungnguyen.hellojwt.service.JwtUserDetailsService;


import com.dungnguyen.hellojwt.configure.JwtTokenUtil;
import com.dungnguyen.hellojwt.model.JwtRequest;
import com.dungnguyen.hellojwt.model.JwtResponse;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerNewUser(@RequestBody User user){
        System.out.println("register new user");
//        System.out.println(userDetailsService.findUserById(1l));
        System.out.println(user);
        return userDetailsService.addNewUser(user);
    }

    @GetMapping("/user")
    public User getUserById(@PathParam("id") Long id){
        return userDetailsService.findUserById(id);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        System.out.println("Authenticate when client login");
//        authenticate username and password
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.ok().body(
                    new JwtResponse("error", "cant authentication", "")
            );
        }


        final User user = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(user);

        return ResponseEntity.ok().body(
                new JwtResponse("oke", "successful", token)
        );
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            System.out.println("before authenticate");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("after authenticate");
        } catch (DisabledException e) {
            System.out.println("DisabledException");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            System.out.println("BadCredentialsException");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}