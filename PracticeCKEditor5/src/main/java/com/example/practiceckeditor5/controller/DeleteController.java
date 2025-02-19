package com.example.practiceckeditor5.controller;

import com.example.practiceckeditor5.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class DeleteController {

    private final ContentService contentService;

    @GetMapping("/delete/{id}")
    public String deleteFunc(@PathVariable int id) {

        contentService.deleteContentById(id);

        return "redirect:/list";
    }
}
