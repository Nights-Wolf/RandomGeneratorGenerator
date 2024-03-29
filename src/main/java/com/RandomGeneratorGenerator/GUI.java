package com.RandomGeneratorGenerator;

import com.RandomGeneratorGenerator.table.Table;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Component
@Getter
public class GUI implements Runnable {

    private final Table table;
    private String selectedName;
    //Frame
    private BufferedImage image;
    private BufferedImage imageOnContent;
    private final JLabel iconLabel = new JLabel();
    private final JLabel iconLabelOnContent = new JLabel();
    private final JTabbedPane tabs = new JTabbedPane();
    //Generating Tab
    private final JButton generateNumber = new JButton("Generate Name!");
    private final JList generatedNames = new JList();
    private final JPanel tagPanel = new JPanel();
    private final JComboBox<String> firstTags = new JComboBox<>();
    private final JComboBox<String> secondTags = new JComboBox<>();
    private final JComboBox<String> thirdTags = new JComboBox<>();
    private final JComboBox<String> fourthTags = new JComboBox<>();
    private final JPanel moreTagsPanel = new JPanel();
    private final JButton saveSet = new JButton("Save set");
    private final JButton removeGeneratedName = new JButton("Remove");
    private final JButton usedTagsButton = new JButton("Oblivion");
    private final JButton addToUsedTags = new JButton("Yeet into Oblivion");
    private final JButton addAllToUsedTagsButton = new JButton("Yeet all into Oblivion");
    private final JPanel generatedNamesPanel = new JPanel();
    private final JTextField quantityToGenerate = new JTextField("10", 2);
    private final JPanel generatingPan = new JPanel();
    private final JScrollPane scroller = new JScrollPane(generatedNames, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    //Saving Tab
    private final JPanel savingPanel = new JPanel();
    private final JButton saveButton = new JButton("Save name");
    private final JButton tagButton = new JButton("Save tag");
    private final JButton deleteButton = new JButton("Delete");
    private final JTextField addNewName = new JTextField(5);
    private final JTextField addNewTag = new JTextField(5);
    private final JLabel savingIconLabel = new JLabel();
    private final JButton deleteTag = new JButton("Delete Tag");
    //Content Tab
    private final JPanel setsChoosingPanel = new JPanel();
    private final JComboBox<String> setsName = new JComboBox<>();
    private final JButton clearWholeSet = new JButton("Remove set");
    private final JButton removeSet = new JButton("Remove");
    private final JButton choseSet = new JButton("Show set");
    private final JList setsList = new JList();
    private final JScrollPane setsScroller = new JScrollPane(setsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    private final Thread runner;

    @Autowired
    public GUI(Table table) {
        this.table = table;
        runner = new Thread(this);
        runner.start();
    }

    @Override
    public void run() {
        try {
            File file = new File("images/logo.png");
            this.image = ImageIO.read(file);
        } catch(IOException exception) {
            exception.printStackTrace();
        }

        try {
            File file = new File("images/logo2Bigger.png");
            this.imageOnContent = ImageIO.read(file);
        } catch(IOException exception) {
            exception.printStackTrace();
        }

            JFrame frame = new JFrame("Random Generator Generator");
            frame.getContentPane().setBackground(new Color(235, 94, 40));
            frame.setLocationByPlatform(true);
            frame.setIconImage(new ImageIcon(image).getImage());
            JFrame.setDefaultLookAndFeelDecorated(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setVisible(true);

        generatingPan.setLayout(new BorderLayout());
        generatingPan.setBackground(new Color(231, 188, 13));
        frame.add(generatingPan);

        savingPanel.setLayout(new BorderLayout());
        savingPanel.setBackground(new Color(231, 188, 13));
        frame.add(savingPanel);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(231, 188, 13));
        frame.add(contentPanel);


        tabs.addTab("Generating", generatingPan);
        tabs.addTab("Database & Saving", savingPanel);
        tabs.addTab("Sets", contentPanel);
        tabs.setBackgroundAt(0, new Color(224,131,0));
        tabs.setBackgroundAt(1, new Color(204,119,0));
        tabs.setBackgroundAt(2, new Color(184,107,0));
        tabs.setForeground(Color.BLACK);
        frame.add(tabs);

        tagPanel.setLayout(new BorderLayout());
        tagPanel.setBackground(new Color(231, 188, 13));
        JPanel generateNumberButtonPane = new JPanel();
        generateNumberButtonPane.setBackground(new Color(231, 188, 13));
        generateNumberButtonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        generateNumber.setBackground(new Color(224,131,0));
        generateNumber.setForeground(Color.BLACK);
        generateNumberButtonPane.add(generateNumber);
        tagPanel.add(generateNumberButtonPane, BorderLayout.SOUTH);
        iconLabel.setIcon(new ImageIcon(imageOnContent));
        JPanel iconPane = new JPanel();
        iconPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        iconPane.setBackground(new Color(231, 188, 13));
        iconPane.add(iconLabel);
        tagPanel.add(iconPane, BorderLayout.NORTH);
        generatingPan.add(tagPanel, BorderLayout.NORTH);
        moreTagsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
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
        JPanel removeNamePanel = new JPanel();
        removeNamePanel.setLayout(new FlowLayout());
        removeNamePanel.setBackground(new Color(231, 188, 13));
        removeGeneratedName.setBackground(new Color(224,131,0));
        removeGeneratedName.setForeground(Color.BLACK);
        removeGeneratedName.setVisible(false);
        usedTagsButton.setBackground(new Color(224,131,0));
        usedTagsButton.setForeground(Color.BLACK);
        addAllToUsedTagsButton.setBackground(new Color(224,131,0));
        addAllToUsedTagsButton.setForeground(Color.BLACK);
        addAllToUsedTagsButton.setVisible(false);
        addToUsedTags.setBackground(new Color(224,131,0));
        addToUsedTags.setForeground(Color.BLACK);
        addToUsedTags.setVisible(false);
        removeNamePanel.add(usedTagsButton);
        removeNamePanel.add(addAllToUsedTagsButton);
        removeNamePanel.add(addToUsedTags);
        removeNamePanel.add(removeGeneratedName);
        generatedNames.setLayoutOrientation(JList.VERTICAL);
        generatedNames.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        generatedNames.setVisible(false);
        generatedNames.setBackground(new Color(224,131,0));
        generatedNames.setForeground(Color.BLACK);
        generatedNames.setSelectionBackground(new Color(231, 188, 13));
        generatedNames.setSelectionForeground(Color.BLACK);
        scroller.setVisible(false);
        JPanel listPane = new JPanel();
        listPane.setLayout(new GridLayout());
        listPane.setBackground(new Color(231, 188, 13));
        listPane.add(saveSetPanel);
        listPane.add(scroller);
        listPane.add(removeNamePanel);
        generatedNamesPanel.add(listPane, BorderLayout.CENTER);
        generatingPan.add(generatedNamesPanel, BorderLayout.CENTER);
        JLabel uselessLabel = new JLabel("by Dawid Całkowski");
        uselessLabel.setForeground(new Color(231, 188, 13));
        generatingPan.add(uselessLabel, BorderLayout.SOUTH);

        table.getTable().getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getTable().getSelectedRow();
            if (table.getTable().isRowSelected(selectedRow)) {
                selectedName = String.valueOf(table.getTable().getValueAt(selectedRow, 0));
                deleteButton.setVisible(true);
            } else {
                deleteButton.setVisible(false);
            }
        });
        JScrollPane tableScroller = new JScrollPane(table.getTable());
        tableScroller.setPreferredSize(new Dimension(450, 400));
        savingPanel.add(tableScroller, BorderLayout.CENTER);
        JPanel addNamePanel = new JPanel();
        addNamePanel.setLayout(new FlowLayout());
        addNamePanel.setBackground(new Color(231, 188, 13));
        savingIconLabel.setIcon(new ImageIcon(image));
        addNamePanel.add(savingIconLabel);
        addNewTag.setBackground(new Color(224,131,0));
        addNewTag.setForeground(Color.BLACK);
        addNamePanel.add(addNewTag);
        tagButton.setBackground(new Color(224,131,0));
        tagButton.setForeground(Color.BLACK);
        addNamePanel.add(tagButton);
        addNewName.setBackground(new Color(224,131,0));
        addNewName.setForeground(Color.BLACK);
        addNamePanel.add(addNewName);
        saveButton.setBackground(new Color(224,131,0));
        saveButton.setForeground(Color.BLACK);
        addNamePanel.add(saveButton);
        JPanel deletePanel = new JPanel();
        BoxLayout box = new BoxLayout(deletePanel, BoxLayout.Y_AXIS);
        deletePanel.setLayout(box);
        deletePanel.setBackground(new Color(231, 188, 13));
        deleteButton.setBackground(new Color(224,131,0));
        deleteButton.setForeground(Color.BLACK);
        deletePanel.add(deleteButton);
        deleteButton.setVisible(false);
        deleteTag.setBackground(new Color(224,131,0));
        deleteTag.setForeground(Color.BLACK);
        deletePanel.add(deleteTag);
        deleteTag.setVisible(false);
        addNamePanel.add(deletePanel);
        savingPanel.add(addNamePanel, BorderLayout.SOUTH);

        setsChoosingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        listPanel.setBackground(new Color(231, 188, 13));
        setsName.setBackground(new Color(224,131,0));
        setsName.setForeground(Color.BLACK);
        iconLabelOnContent.setIcon(new ImageIcon(image));
        setsChoosingPanel.add(iconLabelOnContent);
        setsChoosingPanel.add(setsName);
        choseSet.setBackground(new Color(224,131,0));
        choseSet.setForeground(Color.BLACK);
        setsChoosingPanel.add(choseSet);
        clearWholeSet.setBackground(new Color(224,131,0));
        clearWholeSet.setForeground(Color.BLACK);
        setsChoosingPanel.add(clearWholeSet);
        setsChoosingPanel.setBackground(new Color(231, 188, 13));
        contentPanel.add(setsChoosingPanel, BorderLayout.NORTH);
        setsList.setBackground(new Color(224,131,0));
        setsList.setForeground(Color.BLACK);
        setsList.setSelectionBackground(new Color(231, 188, 13));
        setsList.setSelectionForeground(Color.BLACK);
        setsScroller.setPreferredSize(new Dimension(100, 300));
        listPanel.add(setsScroller);
        removeSet.setVisible(false);
        removeSet.setBackground(new Color(224,131,0));
        removeSet.setForeground(Color.BLACK);
        listPanel.add(removeSet);
        contentPanel.add(listPanel, BorderLayout.CENTER);
    }
}
