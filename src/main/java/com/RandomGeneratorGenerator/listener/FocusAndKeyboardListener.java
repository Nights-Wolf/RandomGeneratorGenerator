package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Component
public class FocusAndKeyboardListener {

    private final GUI gui;
    private JTextField tagField;
    private JTextField nameField;
    private final TagListener tagListener;
    private final NameListener nameListener;

    public FocusAndKeyboardListener(GUI gui, TagListener tagListener, NameListener nameListener) {
        this.gui = gui;
        this.tagListener = tagListener;
        this.nameListener = nameListener;

        tagField = gui.getAddNewTag();
        tagField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                tagListener.saveNewTag();
                            }
                        }
                    });

        nameField = gui.getAddNewName();
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    nameListener.saveNewName();
                }
            }
        });
    }
}
