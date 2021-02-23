package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.repository.KitsContentRepository;
import com.RandomGeneratorGenerator.repository.KitsNameRepository;
import com.RandomGeneratorGenerator.repository.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class RemoveListener implements ActionListener {

    private final GUI gui;
    private final KitsContentRepository kitsContentRepository;
    private final NameRepository nameRepository;
    private final KitsNameRepository kitsNameRepository;
    private final ShowSetListener showSetListener;

    @Autowired
    public RemoveListener(GUI gui, KitsContentRepository kitsContentRepository, NameRepository nameRepository, KitsNameRepository kitsNameRepository, ShowSetListener showSetListener) {
        this.gui = gui;
        this.kitsContentRepository = kitsContentRepository;
        this.nameRepository = nameRepository;
        this.kitsNameRepository = kitsNameRepository;
        this.showSetListener = showSetListener;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getRemoveGeneratedName().addActionListener(this);
        gui.getRemoveSet().addActionListener(this);
        gui.getClearWholeSet().addActionListener(this);
    }

    public void removeSetFromList() {
        DefaultListModel<ListModel> model = (DefaultListModel) gui.getSetsList().getModel();
        int selectedIndex = gui.getSetsList().getSelectedIndex();
        long nameId = nameRepository.getNameIdByName(gui.getSetsList().getSelectedValue().toString());
        kitsContentRepository.removeContentFromSet(nameId);
        model.removeElementAt(selectedIndex);
    }


    public void removeNameFromList() {
            DefaultListModel<ListModel> model = (DefaultListModel) gui.getGeneratedNames().getModel();
            int selectedIndex = gui.getGeneratedNames().getSelectedIndex();
            model.removeElementAt(selectedIndex);
    }

    public void clearWholeSet() {
        long setId = kitsNameRepository.getKitsNameIdByName(String.valueOf(gui.getSetsName().getSelectedItem()));
        kitsContentRepository.removeWholeSet(setId);
        kitsNameRepository.removeSetName(setId);
        gui.getSetsName().removeAllItems();
        showSetListener.addSetsToComboBox();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getRemoveGeneratedName()) {
            removeNameFromList();
        } else if (src == gui.getRemoveSet()) {
            removeSetFromList();
        } else if (src == gui.getClearWholeSet()) {
            clearWholeSet();
        }
    }
}
