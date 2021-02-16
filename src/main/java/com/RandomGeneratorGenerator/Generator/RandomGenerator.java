package com.RandomGeneratorGenerator.Generator;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.repository.ContentRepository;
import com.RandomGeneratorGenerator.repository.TagRepository;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
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
        ArrayList<Long> firstTagNamesValue = new ArrayList<>();
        ArrayList<Long> secondTagNamesValue = new ArrayList<>();
        ArrayList<Long> thirdTagNamesValue = new ArrayList<>();
        ArrayList<Long> fourthTagNamesValue = new ArrayList<>();

        String selectedTagFirst = String.valueOf(gui.getFirstTags().getSelectedItem());
        try {
            long firstTagIdValue = tagRepository.getTagByName(selectedTagFirst);
            firstTagNamesValue = contentRepository.getNameById(firstTagIdValue);
        } catch (AopInvocationException e) {
        }

        try {
            if (firstTagNamesValue.size() > 0) {
                String selectedTagSecond = String.valueOf(gui.getSecondTags().getSelectedItem());
                long secondTagIdValue = tagRepository.getTagByName(selectedTagSecond);
                for (Long tag : firstTagNamesValue) {
                    secondTagNamesValue.add(contentRepository.filterName(secondTagIdValue, tag));
                }
            }
        } catch (AopInvocationException e) {
        }
try {
    if (secondTagNamesValue.size() > 0) {
        String selectedTagThird = String.valueOf(gui.getThirdTags().getSelectedItem());
        long thirdTagIdValue = tagRepository.getTagByName(selectedTagThird);
        for (Long tag : secondTagNamesValue) {
            thirdTagNamesValue.add(contentRepository.filterName(thirdTagIdValue, tag));
        }
    }
        } catch (AopInvocationException e) {
        }
try {
    if (thirdTagNamesValue.size() > 0) {
        String selectedTagFourth = String.valueOf(gui.getFourthTags().getSelectedItem());
        long fourthTagIdValue = tagRepository.getTagByName(selectedTagFourth);
        for (Long tag : thirdTagNamesValue) {
            fourthTagNamesValue.add(contentRepository.filterName(fourthTagIdValue, tag));
        }
    }
        } catch (AopInvocationException e) {
        }

    if (secondTagNamesValue.size() <= 0) {
        for (Long e : firstTagNamesValue) {
            namesFromDb.add(e);
            Collections.shuffle(namesFromDb);
        }
    } if (thirdTagNamesValue.size() <= 0 && fourthTagNamesValue.size() <= 0) {
            for (Long e : secondTagNamesValue) {
                namesFromDb.add(e);
                Collections.shuffle(namesFromDb);
            }
        }
        if (fourthTagNamesValue.size() <= 0) {
            for (Long e : thirdTagNamesValue) {
                namesFromDb.add(e);
                Collections.shuffle(namesFromDb);
            }
        }
        if (fourthTagNamesValue.size() > 0) {
            for (Long e : fourthTagNamesValue) {
                namesFromDb.add(e);
                Collections.shuffle(namesFromDb);
            }
        }

            long[] chosenNames = new long[10];
try {

    for (int i = 0; i < chosenNames.length; ++i) {
        if (namesFromDb.size() < chosenNames.length) {
            JOptionPane.showMessageDialog(new JFrame("Warning!"),
                    "Not enough records in database (max. " + namesFromDb.size() + " results)");
            chosenNames = new long[namesFromDb.size()];
            chosenNames[i] = namesFromDb.get(i);
        }
        chosenNames[i] = namesFromDb.get(i);
    }
}catch (IndexOutOfBoundsException e) {
    JOptionPane.showMessageDialog(new JFrame("Warning!"),
            "Choose at least one tag!");
}
            return chosenNames;
        }
    }
//panel wyboru liczby generowanych imion