package com.example.practiceckeditor5.service;

import com.example.practiceckeditor5.dto.SaveDTO;
import com.example.practiceckeditor5.entity.ContentEntity;
import com.example.practiceckeditor5.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    public void saveContent(SaveDTO saveDTO){

        String title = saveDTO.getTitle();
        String content = saveDTO.getContent();

        ContentEntity contentData = new ContentEntity(title, content);

        contentRepository.save(contentData);

        System.out.println("Saved Content");
    }

    public List<ContentEntity> selectContent(){

        return contentRepository.findAll();
    }

    public ContentEntity selectContentById(int id){

        return contentRepository.findById(id);
    }

    public void deleteContentById(int id){

        contentRepository.deleteById(id);
    }

    public void updateContentById(int id, SaveDTO saveDTO){

        ContentEntity data = new ContentEntity(id, saveDTO.getTitle(), saveDTO.getContent());
        contentRepository.save(data);
    }
}
