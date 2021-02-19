package com.RandomGeneratorGenerator.service;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Tag;
import com.RandomGeneratorGenerator.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SaveTag {

    private final TagRepository tagRepository;
    private final GUI gui;

    @Autowired
    public SaveTag(TagRepository tagRepository, GUI gui) {
        this.tagRepository = tagRepository;
        this.gui = gui;
    }

    public Tag newTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addTagsToBox() {
        String none = "-None-";
        ArrayList<String> tagsFromDb = new ArrayList<>();
        long tagIdCount = tagRepository.count();

        for (long i = 1; i <= tagIdCount; i++) {
            String myTags = tagRepository.getTagsNames(i);
            tagsFromDb.add(myTags);
        }

        gui.getFirstTags().addItem(none);
        gui.getSecondTags().addItem(none);
        gui.getThirdTags().addItem(none);
        gui.getFourthTags().addItem(none);

        for (String s : tagsFromDb) {
            gui.getFirstTags().addItem(s);
            gui.getSecondTags().addItem(s);
            gui.getThirdTags().addItem(s);
            gui.getFourthTags().addItem(s);
        }
    }

}
