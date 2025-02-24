package com.example.preacticejwt.service;

import com.example.preacticejwt.dto.JoinDTO;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    public void joinProcess(JoinDTO joinDTO){

        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();


    }
}
