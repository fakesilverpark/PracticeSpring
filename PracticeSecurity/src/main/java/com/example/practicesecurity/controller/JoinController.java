package com.example.practicesecurity.controller;

import com.example.practicesecurity.dto.JoinDTO;
import com.example.practicesecurity.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String joinP() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {

        System.out.println("username: " + joinDTO.getUsername());
        System.out.println("password: " + joinDTO.getPassword());

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
