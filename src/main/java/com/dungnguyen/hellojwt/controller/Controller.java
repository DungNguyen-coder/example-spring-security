package com.dungnguyen.hellojwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping({ "/users" })
    public String usersPage() {
        return "Hello ! users can access this page";
    }

    @RequestMapping({"/admins"})
    public String adminsPage(){
        return "Hello ! admins can access this page";
    }

    @RequestMapping({"/home"})
    public String homepage(){
        return "this is homepage";
    }

}
