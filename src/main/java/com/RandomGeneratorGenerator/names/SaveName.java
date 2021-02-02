package com.RandomGeneratorGenerator.names;

import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.repository.NamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveName {

    private final NamesRepository namesRepository;

    @Autowired
    public SaveName(NamesRepository namesRepository) {
        this.namesRepository = namesRepository;

    }

       public Name newName(Name name) {
        return namesRepository.save(name);
       }

}
