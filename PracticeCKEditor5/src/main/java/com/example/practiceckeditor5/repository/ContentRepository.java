package com.example.practiceckeditor5.repository;

import com.example.practiceckeditor5.entity.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository interface 로 하면 스프링 부트가 알아서 관리해서 굳이 붙일 필요가 없다
public interface ContentRepository extends JpaRepository<ContentEntity, Integer> {

    ContentEntity findById(int id);
}
