package com.example.projectSpring.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @GetMapping("/")
    public String index(){
        return "Api de oscar jundt-schmitter";
    }
}
