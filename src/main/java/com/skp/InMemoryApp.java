package com.skp;

import com.skp.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InMemoryApp {

    @Autowired
    UserController userController;

    public static void main(String[] args) {
        SpringApplication.run(InMemoryApp.class, args);
    }
}
