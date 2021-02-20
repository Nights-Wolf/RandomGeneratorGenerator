package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.generator.RandomGenerator;
import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.repository.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        Object src = e.getSource();
        if (src == gui.getGenerateNumber()) {
            ArrayList<String> names = new ArrayList<>();

            for (Long i : randomGenerator.generateNumber()) {
                names.add(nameRepository.getNamesById(i));
            }
            gui.getGeneratedNames().setListData(names.toArray());

            if (names.size() != 0) {
                gui.getGeneratedNames().setVisible(true);
                gui.getSaveSet().setVisible(true);
            } else {
                gui.getGeneratedNames().setVisible(false);
                gui.getSaveSet().setVisible(false);
            }
        }
    }
}
