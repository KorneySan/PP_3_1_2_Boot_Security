package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nobody")
public class NobodyController {
    @GetMapping()
    public String getView() {
        return "nobody/nobody";
    }
}
