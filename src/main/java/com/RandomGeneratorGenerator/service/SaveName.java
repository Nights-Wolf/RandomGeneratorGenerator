package com.RandomGeneratorGenerator.service;

import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.repository.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveName {

    private final NameRepository nameRepository;

    @Autowired
    public SaveName(NameRepository nameRepository) {
        this.nameRepository = nameRepository;

    }

       public Name newName(Name name) {
        return nameRepository.save(name);
       }

}
