package com.RandomGeneratorGenerator.listeners;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Content;
import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.model.Tag;
import com.RandomGeneratorGenerator.service.SaveContent;
import com.RandomGeneratorGenerator.service.SaveName;
import com.RandomGeneratorGenerator.service.SaveTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ContentListener implements ActionListener {

    private final GUI gui;
    private final SaveName saveName;
    private final SaveTag saveTag;
    private final SaveContent saveContent;

    @Autowired
    public ContentListener(GUI gui, SaveName saveName, SaveTag saveTag, SaveContent saveContent) {
        this.gui = gui;
        this.saveName = saveName;
        this.saveTag = saveTag;
        this.saveContent = saveContent;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getContentButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getContentButton()) {
            Name name = new Name();
            Tag tag = new Tag();

            name.setName_name("Artur");
            tag.setTag_name("Names");

            saveName.newName(name);
            saveTag.newTag(tag);

            Content content = new Content();
            content.setName_id(name.getName_id());
            content.setTag_id(tag.getTag_id());

            saveContent.newContent(content);
        }
    }
}
