package com.example.practicesecurity.service;

import com.example.practicesecurity.dto.JoinDTO;
import com.example.practicesecurity.entity.UserEntity;
import com.example.practicesecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO){

        //db 에 이미 동일한 username 을 가진 회원이 존재하는지 검정하는 메서드가 필요함
        boolean isUserExists = userRepository.existsByUsername(joinDTO.getUsername());
        if (isUserExists){
            System.out.println("User already exists");
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
