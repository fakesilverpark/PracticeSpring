package com.example.preacticejwt.controller;

import com.example.preacticejwt.dto.JoinDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){

        return "ok";
    }
}
