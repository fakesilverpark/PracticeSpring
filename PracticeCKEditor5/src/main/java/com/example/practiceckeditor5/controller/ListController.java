package com.example.practiceckeditor5.controller;

import com.example.practiceckeditor5.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ListController {

    private ContentService contentService;

    @GetMapping("/list")
    public String listPage() {

        contentService.selectContent();

        return "list";
    }
}
