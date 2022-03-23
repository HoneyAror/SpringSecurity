package com.example.firstapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "SPRING SECURITY ROCKS!";
    }


    @GetMapping("/honey")
    public String hellohoney(){
        return "WELCOME HONEY TO SPRING SECURITY!";
    }
}
