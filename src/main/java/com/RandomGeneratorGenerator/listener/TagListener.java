package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Tag;
import com.RandomGeneratorGenerator.repository.TagRepository;
import com.RandomGeneratorGenerator.service.SaveTag;
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
public class TagListener implements ActionListener {

    private final GUI gui;
    private final SaveTag saveTag;
    private final TagRepository tagRepository;
    private final Table table;

    @Autowired
    public TagListener(GUI gui, SaveTag saveTag, TagRepository tagRepository, Table table) {
        this.gui = gui;
        this.saveTag = saveTag;
        this.tagRepository = tagRepository;
        this.table = table;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getTagButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getTagButton()) {
            String tagToAdd = gui.getAddNewTag().getText();
            if (tagRepository.getTags().contains(tagToAdd)) {
                JOptionPane.showMessageDialog(new JFrame(), "Tag already exists!");
            } else if (tagToAdd.equals("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Insert tag!");
            } else {
                Tag tag = new Tag();
                tag.setTag_name(tagToAdd);
                saveTag.newTag(tag);

                DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
                model.addColumn(tagToAdd);

                gui.getAddNewTag().setText("");

                gui.getFirstTags().removeAllItems();
                gui.getSecondTags().removeAllItems();
                gui.getThirdTags().removeAllItems();
                gui.getFourthTags().removeAllItems();

                saveTag.addTagsToBox();
            }
        }
    }
}
