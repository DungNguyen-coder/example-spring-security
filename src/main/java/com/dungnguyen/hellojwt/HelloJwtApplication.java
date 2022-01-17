package com.dungnguyen.hellojwt;

import com.dungnguyen.hellojwt.configure.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import(WebSecurityConfig.class)
public class HelloJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloJwtApplication.class, args);
    }



}
