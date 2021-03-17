package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.model.Used;
import com.RandomGeneratorGenerator.repository.NameRepository;
import com.RandomGeneratorGenerator.repository.UsedRepository;
import com.RandomGeneratorGenerator.service.SaveUsed;
import lombok.Getter;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Component
@Getter
public class UsedTagListener implements ActionListener {

    private final GUI gui;
    private final SaveUsed saveUsed;
    private final NameRepository nameRepository;
    private final UsedRepository usedRepository;
    private final JButton removeFromBinButton = new JButton("Remove");
    private final JButton removeAllFromBinButton = new JButton("Remove all");
    private final JList usedTagsList = new JList();
    private JDialog dialog;

    public UsedTagListener(GUI gui, SaveUsed saveUsed, NameRepository nameRepository, UsedRepository usedRepository) {
        this.gui = gui;
        this.saveUsed = saveUsed;
        this.nameRepository = nameRepository;
        this.usedRepository = usedRepository;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getAddToUsedTags().addActionListener(this);
        gui.getUsedTagsButton().addActionListener(this);
        removeAllFromBinButton.addActionListener(this);
        removeFromBinButton.addActionListener(this);
        gui.getAddAllToUsedTagsButton().addActionListener(this);
    }


    public ArrayList<String> usedTags() {
        ArrayList<String> usedTags = new ArrayList<>();
        ArrayList<Long> usedTagsId = usedRepository.getUsedTags();
        for (Long i : usedTagsId) {
            usedTags.add(nameRepository.getNamesById(i));
        }
        return usedTags;
    }

    public void tagThrash(Point p) {
        JPanel usedTagsPane = new JPanel();
        usedTagsPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        usedTagsPane.setBackground(new Color(231, 188, 13));
        DefaultListModel model = new DefaultListModel();
        model.addAll(usedTags());
        usedTagsList.setModel(model);
        usedTagsList.setVisibleRowCount(10);
        usedTagsList.setBackground(new Color(224,131,0));
        usedTagsList.setForeground(Color.BLACK);
        JScrollPane scroller = new JScrollPane(usedTagsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        usedTagsPane.add(scroller);
        JPanel removeFromBinPane = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 1, 2, 10);
        removeFromBinPane.setLayout(gridLayout);
        removeFromBinPane.setBackground(new Color(231, 188, 13));
        removeFromBinButton.setBackground(new Color(224,131,0));
        removeFromBinButton.setForeground(Color.BLACK);
        removeAllFromBinButton.setBackground(new Color(224,131,0));
        removeAllFromBinButton.setForeground(Color.BLACK);
        removeFromBinPane.add(removeAllFromBinButton);
        removeFromBinPane.add(removeFromBinButton);
        removeFromBinButton.setVisible(false);
        usedTagsPane.add(removeFromBinPane);
        dialog = new JDialog();
        dialog.setLocation(p);
        dialog.setTitle("Oblivion");
        dialog.add(usedTagsPane);
        dialog.setSize(300, 300);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(usedTagsPane);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == gui.getAddToUsedTags()) {
            DefaultListModel<ListModel> model = (DefaultListModel) gui.getGeneratedNames().getModel();
            int selectedIndex = gui.getGeneratedNames().getSelectedIndex();
            String nameToAdd = String.valueOf(model.getElementAt(selectedIndex));
                long nameId = nameRepository.getNameIdByName(nameToAdd);
            if (usedRepository.getUsedTags().contains(nameId)) {
                JOptionPane.showMessageDialog(new JFrame(), "Name already sent into Oblivion");
            } else {
                Used used = new Used();
                used.setName_id(nameId);
                saveUsed.saveUsed(used);

                if (dialog.isVisible()) {
                    Point point = dialog.getLocationOnScreen();
                    dialog.dispose();
                    tagThrash(point);
                }
            }
        }
        if (src == gui.getUsedTagsButton()) {
            Point p = new Point(0,0);
           tagThrash(p);
        }
        if (src == removeFromBinButton) {
            DefaultListModel model = (DefaultListModel) usedTagsList.getModel();
            int selectedIndex = usedTagsList.getSelectedIndex();
            String selectedUsedTag = String.valueOf(model.getElementAt(selectedIndex));
           long idToRemove = nameRepository.getNameIdByName(selectedUsedTag);
            usedRepository.deleteUsedTag(idToRemove);
            model.removeElementAt(selectedIndex);
        }
        if (src == removeAllFromBinButton) {
            usedRepository.deleteAllUsedTag();
            DefaultListModel model = (DefaultListModel) usedTagsList.getModel();
            model.removeAllElements();
        }
        if (src == gui.getAddAllToUsedTagsButton()) {
            DefaultListModel model = (DefaultListModel) gui.getGeneratedNames().getModel();
            for (int i = 0; i < model.getSize(); i++) {
                long namesId = nameRepository.getNameIdByName(String.valueOf(model.getElementAt(i)));
                if (usedRepository.getUsedTags().contains(namesId)) {
                    continue;
                } else {
                    Used used = new Used();
                    used.setName_id(namesId);
                    saveUsed.saveUsed(used);
                }
            }
                if (dialog.isVisible()) {
                    Point point = dialog.getLocationOnScreen();
                    dialog.dispose();
                    tagThrash(point);
                }
        }
    }
}
