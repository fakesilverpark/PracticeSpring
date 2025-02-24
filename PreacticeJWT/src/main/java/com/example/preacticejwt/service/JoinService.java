package com.example.preacticejwt.service;

import com.example.preacticejwt.dto.JoinDTO;
import com.example.preacticejwt.entity.UserEntity;
import com.example.preacticejwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO){

        String username = joinDTO.getUsername();
        String password = bCryptPasswordEncoder.encode(joinDTO.getPassword());

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist){

            System.out.println("해당 유저 이름이 이미 존재합니다.");
            return;
        }

        UserEntity data = new UserEntity(username, password, "ROLE_ADMIN");

        userRepository.save(data);

    }
}
