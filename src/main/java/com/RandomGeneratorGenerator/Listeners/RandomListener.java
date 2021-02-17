package com.RandomGeneratorGenerator.Listeners;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.Generator.RandomGenerator;
import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.repository.NameRepository;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RandomListener implements ActionListener {

    private final NameRepository nameRepository;
    private final GUI gui;
    private final RandomGenerator randomGenerator;
    private final Name name;

    @Autowired
    public RandomListener(NameRepository nameRepository, GUI gui, RandomGenerator randomGenerator, Name name) {
        this.nameRepository = nameRepository;
        this.gui = gui;
        this.randomGenerator = randomGenerator;
        this.name = name;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getGenerateNumber().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.getGeneratedNames().setText("");
        Object src = e.getSource();
        if (src == gui.getGenerateNumber()) {

            for (Long i : randomGenerator.generateNumber()) {
                gui.getGeneratedNames().append(nameRepository.getNamesById(i) + "\n");
            }
        }
    }
}
