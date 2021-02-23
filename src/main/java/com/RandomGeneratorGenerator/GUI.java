package com.RandomGeneratorGenerator;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
@Getter
public class GUI implements Runnable {

    private final ImageIcon icon = new ImageIcon("src/main/resources/logo2Bigger.png");
    private final JLabel iconLabel = new JLabel();
    private final JButton saveButton = new JButton("Save name");
    private final JButton tagButton = new JButton("Save tag");
    private final JButton contentButton = new JButton("Save content");
    private final JButton generateNumber = new JButton("Generate Name!");
    private final JList generatedNames = new JList();
    private final JPanel tagPanel = new JPanel();
    private final JComboBox<String> firstTags = new JComboBox<>();
    private final JComboBox<String> secondTags = new JComboBox<>();
    private final JComboBox<String> thirdTags = new JComboBox<>();
    private final JComboBox<String> fourthTags = new JComboBox<>();
    private final JTabbedPane tabs = new JTabbedPane();
    private final JPanel moreTagsPanel = new JPanel();
    private final JButton saveSet = new JButton("Save set");
    private final JButton removeGeneratedName = new JButton("Remove");
    private final JButton removeSet = new JButton("Remove");
    private final JButton clearWholeSet = new JButton("Remove set");
    private final JPanel generatedNamesPanel = new JPanel();
    private final JTextField quantityToGenerate = new JTextField("10", 3);
    private final JPanel generatingPan = new JPanel();
    private final JScrollPane scroller = new JScrollPane();
    private final JPanel setsChoosingPanel = new JPanel();
    private final JComboBox<String> setsName = new JComboBox<>();
    private final JButton choseSet = new JButton("Show set");
    private final JList setsList = new JList();

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
            frame.getContentPane().setBackground(new Color(235, 94, 40));
            frame.setIconImage(icon.getImage());
            frame.setDefaultLookAndFeelDecorated(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setVisible(true);

        generatingPan.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(generatingPan);

        JPanel savingPanel = new JPanel();
        savingPanel.setLayout(new FlowLayout());
        frame.add(savingPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBackground(new Color(231, 188, 13));
        frame.add(contentPanel);
        setsChoosingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        setsChoosingPanel.add(setsName);
        setsChoosingPanel.add(choseSet);
        setsChoosingPanel.add(clearWholeSet);
        setsChoosingPanel.setBackground(new Color(231, 188, 13));
        contentPanel.add(setsChoosingPanel);
        scroller.add(setsList);
        contentPanel.add(setsList);
        removeSet.setVisible(false);
        contentPanel.add(removeSet);

        tabs.addTab("Generating", generatingPan);
        tabs.addTab("Saving", savingPanel);
        tabs.addTab("Content", contentPanel);
        tabs.setBackgroundAt(0, new Color(224,131,0));
        tabs.setBackgroundAt(1, new Color(204,119,0));
        tabs.setBackgroundAt(2, new Color(184,107,0));
        tabs.setForeground(Color.BLACK);
        frame.add(tabs);

        tagPanel.setLayout(new BorderLayout());
        tagPanel.setBackground(new Color(231, 188, 13));
        generateNumber.setBackground(new Color(224,131,0));
        generateNumber.setForeground(Color.BLACK);
        tagPanel.add(generateNumber, BorderLayout.SOUTH);
        generatingPan.add(iconLabel);
        generatingPan.add(tagPanel);
        moreTagsPanel.setLayout(new FlowLayout());
        firstTags.setBackground(new Color(224,131,0));
        firstTags.setForeground(Color.BLACK);
        moreTagsPanel.add(firstTags);
        secondTags.setBackground(new Color(224,131,0));
        secondTags.setForeground(Color.BLACK);
        moreTagsPanel.add(secondTags);
        thirdTags.setBackground(new Color(224,131,0));
        thirdTags.setForeground(Color.BLACK);
        moreTagsPanel.add(thirdTags);
        fourthTags.setBackground(new Color(224,131,0));
        fourthTags.setForeground(Color.BLACK);
        moreTagsPanel.add(fourthTags);
        moreTagsPanel.setBackground(new Color(231, 188, 13));
        tagPanel.add(moreTagsPanel, BorderLayout.CENTER);
        iconLabel.setIcon(icon);
        generatedNamesPanel.setLayout(new BorderLayout());
        generatedNamesPanel.setBackground(new Color(231, 188, 13));
        JPanel quantityPanel = new JPanel();
        quantityPanel.setBackground(new Color(231, 188, 13));
        quantityToGenerate.setHorizontalAlignment(JTextField.CENTER);
        quantityToGenerate.setBackground(new Color(224,131,0));
        quantityToGenerate.setForeground(Color.BLACK);
        quantityPanel.add(quantityToGenerate);
        generatedNamesPanel.add(quantityPanel, BorderLayout.NORTH);
        JPanel saveSetPanel = new JPanel();
        saveSetPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        saveSetPanel.setBackground(new Color(231, 188, 13));
        saveSet.setBackground(new Color(224,131,0));
        saveSet.setForeground(Color.BLACK);
        saveSet.setVisible(false);
        saveSetPanel.add(saveSet);
        generatedNamesPanel.add(saveSetPanel, BorderLayout.WEST);
        JPanel removeNamePanel = new JPanel();
        removeNamePanel.setLayout(new FlowLayout());
        removeNamePanel.setBackground(new Color(231, 188, 13));
        removeGeneratedName.setBackground(new Color(224,131,0));
        removeGeneratedName.setForeground(Color.BLACK);
        removeGeneratedName.setVisible(false);
        removeNamePanel.add(removeGeneratedName);
        generatedNamesPanel.add(removeNamePanel, BorderLayout.EAST);
        generatedNames.setLayoutOrientation(JList.VERTICAL);
        generatedNames.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        generatedNames.setVisible(false);
        generatedNames.setBackground(new Color(224,131,0));
        generatedNames.setForeground(Color.BLACK);
        generatedNamesPanel.add(generatedNames, BorderLayout.CENTER);
        generatingPan.add(generatedNamesPanel);
        generatingPan.setBackground(new Color(231, 188, 13));

        savingPanel.add(saveButton);
        savingPanel.add(tagButton);
        savingPanel.add(contentButton);
    }
}
