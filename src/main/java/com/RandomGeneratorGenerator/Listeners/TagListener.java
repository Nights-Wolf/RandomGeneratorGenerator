package com.RandomGeneratorGenerator.Listeners;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Tag;
import com.RandomGeneratorGenerator.service.SaveTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class TagListener implements ActionListener {

    private final GUI gui;
    private final SaveTag saveTag;

    @Autowired
    public TagListener(GUI gui, SaveTag saveTag) {
        this.gui = gui;
        this.saveTag = saveTag;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getTagButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getTagButton()) {
            Tag tag = new Tag();
            tag.setTag_name("Names");
            saveTag.newTag(tag);
        }
        saveTag.addTagsToBox();
    }
}
