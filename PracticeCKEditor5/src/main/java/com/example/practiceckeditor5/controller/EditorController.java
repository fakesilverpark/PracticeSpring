package com.example.practiceckeditor5.controller;

import com.example.practiceckeditor5.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class EditorController {

    private final ContentService contentService;

    @GetMapping("/editor")
    public String editorPage() {

        return "editor";
    }

    @GetMapping("/editor/{id}")
    public String updatePage(@PathVariable int id, Model model){

        model.addAttribute("data", contentService.selectContentById(id));

        return "editor";
    }
}
