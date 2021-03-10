package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.event.ListSelectionEvent;

@Component
public class ListSelectionListener implements javax.swing.event.ListSelectionListener {

    private final GUI gui;
    private final UsedTagListener usedTagListener;
    private final Table table;

    @Autowired
    public ListSelectionListener(GUI gui, UsedTagListener usedTagListener, Table table) {
        this.gui = gui;
        this.usedTagListener = usedTagListener;
        this.table = table;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getGeneratedNames().addListSelectionListener(this);
        gui.getSetsList().addListSelectionListener(this);
        usedTagListener.getUsedTagsList().addListSelectionListener(this);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        Object src = e.getSource();
        if (src == gui.getGeneratedNames()) {
            int selectedIndex = gui.getGeneratedNames().getSelectedIndex();
            if (gui.getGeneratedNames().isSelectedIndex(selectedIndex)) {
                gui.getRemoveGeneratedName().setVisible(true);
                gui.getAddToUsedTags().setVisible(true);
            } else {
                gui.getRemoveGeneratedName().setVisible(false);
                gui.getAddToUsedTags().setVisible(false);
            }
        }
            if (src == gui.getSetsList()) {
                int selectedSetIndex = gui.getSetsList().getSelectedIndex();
                if (gui.getSetsList().isSelectedIndex(selectedSetIndex)) {
                    gui.getRemoveSet().setVisible(true);
                } else {
                    gui.getRemoveSet().setVisible(false);
                }
            }
            if (src == usedTagListener.getUsedTagsList()) {
                int selectedTagIndex = usedTagListener.getUsedTagsList().getSelectedIndex();
                if (usedTagListener.getUsedTagsList().isSelectedIndex(selectedTagIndex)) {
                    usedTagListener.getRemoveFromBinButton().setVisible(true);
                } else
                    usedTagListener.getRemoveFromBinButton().setVisible(false);
            }
}
}
