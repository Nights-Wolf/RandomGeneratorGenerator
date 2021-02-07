package com.RandomGeneratorGenerator.service;

import com.RandomGeneratorGenerator.model.Tag;
import com.RandomGeneratorGenerator.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveTag {

    private final TagRepository tagRepository;

    @Autowired
    public SaveTag(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag newTag(Tag tag) {
        return tagRepository.save(tag);
    }
}
