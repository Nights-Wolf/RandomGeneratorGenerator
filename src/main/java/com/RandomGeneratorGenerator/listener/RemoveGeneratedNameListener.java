package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class RemoveGeneratedNameListener implements ActionListener {

    private final GUI gui;

    @Autowired
    public RemoveGeneratedNameListener(GUI gui) {
        this.gui = gui;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getRemoveGeneratedName().addActionListener(this);
    }

    public void removeFromList() {
        DefaultListModel<ListModel> model = (DefaultListModel) gui.getGeneratedNames().getModel();
        int selectedIndex = gui.getGeneratedNames().getSelectedIndex();
        model.removeElementAt(selectedIndex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getRemoveGeneratedName()) {
            removeFromList();
        }
    }
}
