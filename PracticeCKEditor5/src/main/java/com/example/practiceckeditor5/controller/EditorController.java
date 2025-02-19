package com.example.practiceckeditor5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditorController {

    @GetMapping("/editor")
    public String editorPage() {

        return "editor";
    }
}
