package com.yooli.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lx on 2018/9/25.
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public void controllerTest(){
        System.out.println("hello controller");
    }
}
