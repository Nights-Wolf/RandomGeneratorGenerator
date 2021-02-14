package com.RandomGeneratorGenerator.Generator;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.repository.ContentRepository;
import com.RandomGeneratorGenerator.repository.NameRepository;
import com.RandomGeneratorGenerator.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class RandomGenerator {

    private final GUI gui;
    private final ContentRepository contentRepository;
    private final TagRepository tagRepository;

    @Autowired
    public RandomGenerator(GUI gui, ContentRepository contentRepository, TagRepository tagRepository) {
        this.gui = gui;
        this.contentRepository = contentRepository;
        this.tagRepository = tagRepository;
    }

    public long[] generateNumber() {
        ArrayList<Long> namesFromDb = new ArrayList<>();
        ArrayList<Long> selectedNamesFromDb;

        String selectedTag = String.valueOf(gui.getFirstTags().getSelectedItem());
        long selectedTagId = tagRepository.getTagByName(selectedTag);

        selectedNamesFromDb = contentRepository.getNameById(selectedTagId);

        long[] chosenNames = new long[10];

        for (long e = 1; e <= selectedNamesFromDb.size(); e++) {
            namesFromDb.add(e);
            Collections.shuffle(namesFromDb);
            }

        for (int i = 0; i < chosenNames.length; ++i) {
            chosenNames[i] = namesFromDb.get(i);
        }
        return chosenNames;
    }
}
