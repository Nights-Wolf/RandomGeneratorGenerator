package com.RandomGeneratorGenerator.service;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Used;
import com.RandomGeneratorGenerator.repository.UsedRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveUsed {

    private final UsedRepository usedRepository;
    private final GUI gui;

    public SaveUsed(UsedRepository usedRepository, GUI gui) {
        this.usedRepository = usedRepository;
        this.gui = gui;
    }

    public Used saveUsed(Used used) {
       return usedRepository.save(used);
    }
}
