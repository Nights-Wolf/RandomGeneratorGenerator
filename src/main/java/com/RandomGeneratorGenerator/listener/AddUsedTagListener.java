package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Used;
import com.RandomGeneratorGenerator.repository.UsedRepository;
import com.RandomGeneratorGenerator.service.SaveUsed;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Component
public class AddUsedTagListener implements ActionListener {

    private final GUI gui;
    private final SaveUsed saveUsed;
    private final UsedRepository usedRepository;

    public AddUsedTagListener(GUI gui, SaveUsed saveUsed, UsedRepository usedRepository) {
        this.gui = gui;
        this.saveUsed = saveUsed;
        this.usedRepository = usedRepository;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getAddToUsedTags().addActionListener(this);
        gui.getUsedTagsButton().addActionListener(this);
    }

    public void tagThrash() {
        JFrame usedTagsFrame = new JFrame("Tags bin");
        usedTagsFrame.setSize(200, 200);
        usedTagsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        usedTagsFrame.setVisible(true);
        JPanel usedTagsPane = new JPanel();
        usedTagsPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        usedTagsPane.setBackground(new Color(231, 188, 13));
        usedTagsFrame.add(usedTagsPane);
        ArrayList<String> usedTags = usedRepository.getUsedTags();
        DefaultListModel model = new DefaultListModel();
        model.addAll(usedTags);
        JList usedTagsList = new JList(model);
        usedTagsList.setVisibleRowCount(10);
        usedTagsList.setBackground(new Color(224,131,0));
        usedTagsList.setForeground(Color.BLACK);
        JScrollPane scroller = new JScrollPane(usedTagsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        usedTagsPane.add(scroller);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getAddToUsedTags()) {
            DefaultListModel<ListModel> model = (DefaultListModel) gui.getGeneratedNames().getModel();
            int selectedIndex = gui.getGeneratedNames().getSelectedIndex();
            String nameToAdd = String.valueOf(model.getElementAt(selectedIndex));
            Used used = new Used();
            used.setUsed_Tags_name(nameToAdd);
            saveUsed.saveUsed(used);
        }
        if (src == gui.getUsedTagsButton()) {
            tagThrash();
        }
    }
}
