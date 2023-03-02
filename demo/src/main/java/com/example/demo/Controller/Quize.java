package com.example.demo.Controller;

import com.example.demo.Lib.EmploeeService;
import com.example.demo.Models.Emploee;
import com.example.demo.Types.quizeTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quize")
public class Quize {

    public final EmploeeService emploeeService;

    @Autowired
    public Quize(EmploeeService emploeeService) {
        this.emploeeService = emploeeService;
    }

    @GetMapping
    public String init() {
        return "quize -> fetch (id, bio, moral, intellectual, )";
    }

    @PostMapping("/pass")
    public Emploee passQuize(@RequestBody quizeTemplate data) {
        return emploeeService.FillEmploeeRisk(data);
    }
}
