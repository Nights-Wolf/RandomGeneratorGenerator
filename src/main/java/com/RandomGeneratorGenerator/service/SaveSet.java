package com.RandomGeneratorGenerator.service;

import com.RandomGeneratorGenerator.model.KitsContent;
import com.RandomGeneratorGenerator.model.KitsName;
import com.RandomGeneratorGenerator.repository.KitsContentRepository;
import com.RandomGeneratorGenerator.repository.KitsNameRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveSet {

    private final KitsNameRepository kitsNameRepository;
    private final KitsContentRepository kitsContentRepository;

    public SaveSet(KitsNameRepository kitsNameRepository, KitsContentRepository kitsContentRepository) {
        this.kitsNameRepository = kitsNameRepository;
        this.kitsContentRepository = kitsContentRepository;
    }

    public KitsName saveKitsName(KitsName kitsName) {
        return kitsNameRepository.save(kitsName);
    }

    public KitsContent saveKitsContent(KitsContent kitsContent) {
        return kitsContentRepository.save(kitsContent);
    }
}
