package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.KitsContent;
import com.RandomGeneratorGenerator.model.KitsName;
import com.RandomGeneratorGenerator.repository.KitsNameRepository;
import com.RandomGeneratorGenerator.repository.NameRepository;
import com.RandomGeneratorGenerator.service.SaveSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Component
public class SaveSetListener implements ActionListener {

    private final GUI gui;
    private final SaveSet saveSet;
    private final KitsNameRepository kitsNameRepository;
    private final NameRepository nameRepository;
    private final  ShowSetListener showSetListener;

    @Autowired
    public SaveSetListener(GUI gui, SaveSet saveSet, KitsNameRepository kitsNameRepository, NameRepository nameRepository, ShowSetListener showSetListener) {
        this.gui = gui;
        this.saveSet = saveSet;
        this.kitsNameRepository = kitsNameRepository;
        this.nameRepository = nameRepository;
        this.showSetListener = showSetListener;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getSaveSet().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Long> kit = new ArrayList<>();

        try {
            String s = JOptionPane.showInputDialog(
                    new JFrame(),
                    "Set name: ",
                    "Save set",
                    JOptionPane.PLAIN_MESSAGE);

        while (s.length() <= 0) {
            JOptionPane.showMessageDialog(new JFrame(), "Empty name!");
            s = JOptionPane.showInputDialog(
                    new JFrame(),
                    "Set name: ",
                    "Save set",
                    JOptionPane.PLAIN_MESSAGE);
            continue;
        }

        if (s != null && s.length() > 0) {
            KitsName kitsName = new KitsName();
            kitsName.setKitsName_name(s);
            saveSet.saveKitsName(kitsName);

            for (int i = 0; i < gui.getGeneratedNames().getModel().getSize(); i++) {
                kit.add(nameRepository.getNameIdByName(String.valueOf(gui.getGeneratedNames().getModel().getElementAt(i))));
            }

            for (Long aLong : kit) {
                try {
                    long kitsNameId = kitsNameRepository.getKitsNameIdByName(s);
                    KitsContent kitsContent = new KitsContent();
                    kitsContent.setKitsName_id(kitsNameId);
                    kitsContent.setName_id(aLong);
                    saveSet.saveKitsContent(kitsContent);
                } catch (IncorrectResultSizeDataAccessException exception) {
                        JOptionPane.showConfirmDialog(new JFrame(), "Set already exists!", "Warning!", JOptionPane.WARNING_MESSAGE, JOptionPane.CLOSED_OPTION);
                        int yesOption = JOptionPane.CLOSED_OPTION;
                        if (yesOption != 0) {
                            break;
                        }
                }
            }
            gui.getSetsName().removeAllItems();
            showSetListener.addSetsToComboBox();
        }
        } catch (NullPointerException exception) {
        }
    }
}
