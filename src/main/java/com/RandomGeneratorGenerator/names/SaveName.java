package com.RandomGeneratorGenerator.names;

import com.RandomGeneratorGenerator.RandomGeneratorGeneratorApplication;
import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.repository.NamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Service
public class SaveName implements ActionListener {

    private final NamesRepository namesRepository;
    private final RandomGeneratorGeneratorApplication gui;

    @Autowired
    public SaveName(NamesRepository namesRepository, RandomGeneratorGeneratorApplication gui) {
        this.namesRepository = namesRepository;
        this.gui = gui;
    }
        @EventListener(ApplicationReadyEvent.class)
       public void addListener() {
        gui.getSaveButton().addActionListener(this);
       }

       public Name newName(Name name) {
        return namesRepository.save(name);
       }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == gui.getSaveButton()) {
            Name newName = new Name();
            newName.setName("Artur");
            newName(newName);
        }
    }
}
