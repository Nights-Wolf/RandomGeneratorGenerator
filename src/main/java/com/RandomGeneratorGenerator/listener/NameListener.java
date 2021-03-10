package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.repository.NameRepository;
import com.RandomGeneratorGenerator.service.SaveName;
import com.RandomGeneratorGenerator.table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class NameListener implements ActionListener {

    private final SaveName saveName;
    private final GUI gui;
    private final Table table;
    private final NameRepository nameRepository;

    @Autowired
    public NameListener(SaveName saveName, GUI gui, Table table, NameRepository nameRepository) {
        this.saveName = saveName;
        this.gui = gui;
        this.table = table;
        this.nameRepository = nameRepository;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getSaveButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getSaveButton()) {
            String nameToAdd = gui.getAddNewName().getText();
            if (nameRepository.getNames().contains(nameToAdd)) {
                JOptionPane.showMessageDialog(new JFrame(), "Name already exists!");
            } else  {
                Name name = new Name();
                name.setName_name(gui.getAddNewName().getText());
                saveName.newName(name);

                DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
                String[] addRow = {gui.getAddNewName().getText()};
                model.addRow(addRow);
            }
        }
    }
}
