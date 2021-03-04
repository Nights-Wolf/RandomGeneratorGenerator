package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Used;
import com.RandomGeneratorGenerator.service.SaveUsed;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class AddUsedTagListener implements ActionListener {

    private final GUI gui;
    private final SaveUsed saveUsed;

    public AddUsedTagListener(GUI gui, SaveUsed saveUsed) {
        this.gui = gui;
        this.saveUsed = saveUsed;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getAddToUsedTags().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getAddToUsedTags()) {
            DefaultListModel<ListModel> model = (DefaultListModel) gui.getGeneratedNames().getModel();
            int selectedIndex = gui.getGeneratedNames().getSelectedIndex();
            String nameToAdd = String.valueOf(model.getElementAt(selectedIndex));
            Used used = new Used();
            used.setUsedTags_name(nameToAdd);
            saveUsed.saveUsed(used);
        }
    }
}
