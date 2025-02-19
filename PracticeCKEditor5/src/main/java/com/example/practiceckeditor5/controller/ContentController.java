package com.example.practiceckeditor5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/content")
    public String contentPage() {

        return "content";
    }
}
