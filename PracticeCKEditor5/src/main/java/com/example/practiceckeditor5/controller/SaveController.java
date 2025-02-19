package com.example.practiceckeditor5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveController {

    @PostMapping("/save")
    public String saveFunc() {

        return "redirect:/";
    }
}
