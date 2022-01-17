package com.dungnguyen.hellojwt.service;

import java.util.Optional;

import com.dungnguyen.hellojwt.Entity.User;
import com.dungnguyen.hellojwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Load User By Username");
        Optional<User> user = Optional.ofNullable(repo.findByUsername(username));

        if(user.isPresent()){
            return user.get();
        }
        else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

//        if ("dungnguyen".equals(username)) {
//
//
////            return new User("dungnguyen", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",  // password
////                    new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
    }

    public ResponseEntity<User> addNewUser(User user){

        User oldUser = repo.findByUsername(user.getUsername());
        if (oldUser != null){
            try {
                throw new Exception("username is exist !");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("register new user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok().body(repo.saveAndFlush(user));
    }

    public User findUserById(Long id){
        return (repo.findById(id).get());
    }
}
