package com.example.practiceckeditor5.controller;

import com.example.practiceckeditor5.dto.SaveDTO;
import com.example.practiceckeditor5.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SaveController {

    private final ContentService contentService;

    @PostMapping("/save")
    public String saveFunc(SaveDTO saveDTO) {

        contentService.saveContent(saveDTO);

        return "redirect:/";
    }
}
