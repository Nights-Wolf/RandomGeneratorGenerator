package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.KitsName;
import com.RandomGeneratorGenerator.repository.KitsContentRepository;
import com.RandomGeneratorGenerator.repository.KitsNameRepository;
import com.RandomGeneratorGenerator.repository.NameRepository;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ShowSetListener implements ActionListener {

    private final GUI gui;
    private final KitsNameRepository kitsNameRepository;
    private final KitsContentRepository kitsContentRepository;
    private final NameRepository nameRepository;

    @Autowired
    public ShowSetListener(GUI gui, KitsNameRepository kitsNameRepository, KitsContentRepository kitsContentRepository, NameRepository nameRepository) {
        this.gui = gui;
        this.kitsNameRepository = kitsNameRepository;
        this.kitsContentRepository = kitsContentRepository;
        this.nameRepository = nameRepository;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getChoseSet().addActionListener(this);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addSetsToComboBox() {
        String none = "-None-";
        ArrayList<String> setsFromDb = new ArrayList<>();
        ArrayList<Long> setsIdCount = kitsNameRepository.getKitsNameId();

        for (Long i : setsIdCount) {
            String mySets = kitsNameRepository.getKitsNames(i);
            setsFromDb.add(mySets);
            }
        setsFromDb.removeAll(Collections.singleton(null));

        gui.getSetsName().addItem(none);

        for (String s : setsFromDb) {
            gui.getSetsName().addItem(s);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
try {
        if (src == gui.getChoseSet()) {

            ArrayList<Long> setsNameIdValue;
            ArrayList<String> sets = new ArrayList<>();

            String getChosenSet = String.valueOf(gui.getSetsName().getSelectedItem());
            long setId = kitsNameRepository.getKitsNameIdByName(getChosenSet);
            setsNameIdValue = kitsContentRepository.getSetsById(setId);

            for (Long set : setsNameIdValue) {
                sets.add(nameRepository.getNamesById(set));
            }
            DefaultListModel model = new DefaultListModel();
            model.addAll(sets);
            gui.getSetsList().setModel(model);

            DefaultListCellRenderer renderer = new DefaultListCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            gui.getSetsList().setCellRenderer(renderer);
        }
} catch (AopInvocationException exception) {
    JOptionPane.showMessageDialog(new JFrame("Warning!"),
            "Choose at least one set!");
}
    }
}
