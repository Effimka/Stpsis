package com.example.demo.Controller;

import com.example.demo.Lib.EmploeeService;
import com.example.demo.Models.Emploee;
import com.example.demo.Types.loginTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("login")
public class Login {

    public final EmploeeService emploeeService;

    @Autowired
    public Login(EmploeeService emploeeService) {
        this.emploeeService = emploeeService;
    }

    @GetMapping
    public String init() {
        return "Post login and password";
    }

    @PostMapping("/entred")
    public Emploee login(@RequestBody loginTemplate data) {
        return emploeeService.findEmploeeByLoginData(data);
    }
}
