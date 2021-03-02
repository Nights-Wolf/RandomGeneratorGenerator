package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.event.ListSelectionEvent;

@Component
public class ListSelectionListener implements javax.swing.event.ListSelectionListener {

    private final GUI gui;

    @Autowired
    public ListSelectionListener(GUI gui) {
        this.gui = gui;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getGeneratedNames().addListSelectionListener(this);
        gui.getSetsList().addListSelectionListener(this);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        Object src = e.getSource();
        if (src == gui.getGeneratedNames()) {
            int selectedIndex = gui.getGeneratedNames().getSelectedIndex();
            if (gui.getGeneratedNames().isSelectedIndex(selectedIndex)) {
                gui.getRemoveGeneratedName().setVisible(true);
            } else {
                gui.getRemoveGeneratedName().setVisible(false);
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

      /*int selectedIndex = gui.getGeneratedNames().getSelectedIndex();
      int selectedSetIndex = gui.getSetsList().getSelectedIndex();
if (gui.getGeneratedNames().isSelectedIndex(selectedIndex)) {
    gui.getRemoveGeneratedName().setVisible(true);
}else if (gui.getSetsList().isSelectedIndex(selectedSetIndex)) {
    gui.getRemoveSet().setVisible(true);
} else {
    gui.getRemoveGeneratedName().setVisible(false);
    gui.getRemoveSet().setVisible(false); */



}
}
