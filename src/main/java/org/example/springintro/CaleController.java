package org.example.springintro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaleController {


    // Get/ Add?a=10&b=5 =>15


    @GetMapping("/Add")
    public double add(@RequestParam double a , @RequestParam double b){
        return a + b;
    }
    @GetMapping("/")
    public String hello(){
        return "Hello";
    }
}
