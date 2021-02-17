package com.RandomGeneratorGenerator;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
@Getter
public class GUI implements Runnable {

    private final JButton saveButton = new JButton("Save name");
    private final JButton tagButton = new JButton("Save tag");
    private final JButton contentButton = new JButton("Save content");
    private final JButton generateNumber = new JButton("Generate Name!");
    private final JTextArea generatedNames = new JTextArea(10, 10);
    private final JPanel tagPanel = new JPanel();
    private final JComboBox<String> firstTags = new JComboBox<>();
    private final JComboBox<String> secondTags = new JComboBox<>();
    private final JComboBox<String> thirdTags = new JComboBox<>();
    private final JComboBox<String> fourthTags = new JComboBox<>();
    private final JTabbedPane tabs = new JTabbedPane();
    private final JPanel moreTagsPanel = new JPanel();
    private final JButton saveSet = new JButton("Save set");
    private final JTextField quantityToGenerate = new JTextField("10", 3);

    private Thread runner = null;

    public GUI() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    @Override
    public void run() {
            JFrame frame = new JFrame("Random Generator Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setVisible(true);

        JPanel generatingPan = new JPanel();
        generatingPan.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(generatingPan);

        JPanel savingPanel = new JPanel();
        savingPanel.setLayout(new FlowLayout());
        frame.add(savingPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        frame.add(contentPanel);

        tabs.addTab("Generating", generatingPan);
        tabs.addTab("Saving", savingPanel);
        tabs.addTab("Content", contentPanel);
        frame.add(tabs);

        tagPanel.setLayout(new BorderLayout());
        tagPanel.add(generateNumber, BorderLayout.SOUTH);
        generatingPan.add(tagPanel);
        moreTagsPanel.setLayout(new FlowLayout());
        moreTagsPanel.add(firstTags);
        moreTagsPanel.add(secondTags);
        moreTagsPanel.add(thirdTags);
        moreTagsPanel.add(fourthTags);
        tagPanel.add(moreTagsPanel, BorderLayout.CENTER);
        generatingPan.add(quantityToGenerate);
        generatingPan.add(saveSet);
        generatedNames.setEditable(false);
        generatingPan.add(generatedNames);

        savingPanel.add(saveButton);
        savingPanel.add(tagButton);
        savingPanel.add(contentButton);
    }
}
