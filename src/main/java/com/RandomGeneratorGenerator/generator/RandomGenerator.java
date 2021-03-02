package com.RandomGeneratorGenerator.generator;

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
        int generatedNamesQuantity = 0;

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
                    try {
                        secondTagNamesValue.add(contentRepository.filterName(secondTagIdValue, tag));
                    } catch (AopInvocationException f) {
                    }
                }
                if (secondTagNamesValue.size() <= 0) {
                    JOptionPane.showMessageDialog(new JFrame(), "No results in second selected tag \"" + selectedTagSecond + "\"");
                }
            }
        }catch (AopInvocationException e) {
        }

try {
    if (secondTagNamesValue.size() > 0) {
        String selectedTagThird = String.valueOf(gui.getThirdTags().getSelectedItem());
        long thirdTagIdValue = tagRepository.getTagByName(selectedTagThird);
        for (Long tag : secondTagNamesValue) {
            try {
                thirdTagNamesValue.add(contentRepository.filterName(thirdTagIdValue, tag));
            }catch (AopInvocationException f){}
        }
        if (thirdTagNamesValue.size() <= 0) {
            JOptionPane.showMessageDialog(new JFrame(), "No results in third selected tag \"" + selectedTagThird + "\"");
        }
    }
        } catch (AopInvocationException e) {
        }
try {
    if (thirdTagNamesValue.size() > 0) {
        String selectedTagFourth = String.valueOf(gui.getFourthTags().getSelectedItem());
        long fourthTagIdValue = tagRepository.getTagByName(selectedTagFourth);
        for (Long tag : thirdTagNamesValue) {
            try {
                fourthTagNamesValue.add(contentRepository.filterName(fourthTagIdValue, tag));
            } catch (AopInvocationException f) {
            }
        }
        if (fourthTagNamesValue.size() <= 0) {
            JOptionPane.showMessageDialog(new JFrame(), "No results in fourth selected tag \"" + selectedTagFourth + "\"");
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
try {
    if (Integer.parseInt(gui.getQuantityToGenerate().getText()) < 0) {
        JOptionPane.showMessageDialog(new JFrame(), "Incorrect number to generate!");
    } else {
        generatedNamesQuantity = Integer.parseInt(gui.getQuantityToGenerate().getText());
    }
}catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(new JFrame(), "Incorrect number to generate!");
}
    long[] chosenNames = new long[generatedNamesQuantity];

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
            "Choose first tag!");
}
            return chosenNames;
        }
    }