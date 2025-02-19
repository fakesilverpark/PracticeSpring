package com.example.practiceckeditor5.controller;

import com.example.practiceckeditor5.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @GetMapping("/content/{id}")
    public String contentPage(@PathVariable int id, Model model) {

        model.addAttribute("Content", contentService.selectContentById(id));

        return "content";
    }
}
