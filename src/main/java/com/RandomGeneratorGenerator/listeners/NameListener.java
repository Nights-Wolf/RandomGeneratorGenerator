package com.RandomGeneratorGenerator.listeners;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.service.SaveName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class NameListener implements ActionListener {

    private final SaveName saveName;
    private final GUI gui;

    @Autowired
    public NameListener(SaveName saveName, GUI gui) {
        this.saveName = saveName;
        this.gui = gui;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getSaveButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getSaveButton()) {
            Name name = new Name();
            name.setName_name("Kamila");
            saveName.newName(name);
        }
    }
}
