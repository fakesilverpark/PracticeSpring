package com.example.practicesecurity.repository;

import com.example.practicesecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    // 동일한 이름이 존재하면 true 존재하지않으면 false
    // 이건 custom jpa 구문
    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
