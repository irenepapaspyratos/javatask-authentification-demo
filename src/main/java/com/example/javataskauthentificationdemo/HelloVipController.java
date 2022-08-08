package com.example.javataskauthentificationdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloVipController {

    @GetMapping("/helloVip")
    String helloVip(){
        return "Hello Vip";
    }
}
