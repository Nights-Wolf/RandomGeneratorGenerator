package com.RandomGeneratorGenerator.Listeners;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.names.SaveName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class NameListener implements ActionListener {

    private final GUI gui;
    private final SaveName saveName;

    @Autowired
    public NameListener(GUI gui, SaveName saveName) {
        this.gui = gui;
        this.saveName = saveName;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addListener() {
        gui.getSaveButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == gui.getSaveButton()) {
            Name name = new Name();
            name.setName("Artur");
            saveName.newName(name);
        }
    }
}
