package com.example.practiceckeditor5.controller;

import com.example.practiceckeditor5.dto.SaveDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaveController {

    @PostMapping("/save")
    public String saveFunc(SaveDTO saveDTO) {

        System.out.println(saveDTO);

        return "redirect:/";
    }
}
