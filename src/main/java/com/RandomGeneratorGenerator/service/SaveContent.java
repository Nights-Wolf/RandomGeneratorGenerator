package com.RandomGeneratorGenerator.service;

import com.RandomGeneratorGenerator.model.Content;
import com.RandomGeneratorGenerator.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveContent {

    private final ContentRepository contentRepository;

    @Autowired
    public SaveContent(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    public Content newContent(Content content) {
        return contentRepository.save(content);
    }
}
