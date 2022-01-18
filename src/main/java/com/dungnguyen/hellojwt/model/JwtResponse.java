package com.dungnguyen.hellojwt.model;

import com.dungnguyen.hellojwt.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String status;
    private final String message;
    private final String jwttoken;
    private final User user;

//    public JwtResponse(String jwttoken) {
//        this.jwttoken = jwttoken;
//    }

//    public String getToken() {
//        return this.jwttoken;
//    }
}