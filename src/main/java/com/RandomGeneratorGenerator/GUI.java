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

    private Thread runner = null;

    public GUI() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    @Override
    public void run() {
            JFrame frame = new JFrame("Main Frame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setVisible(true);
        JPanel pan = new JPanel();
        pan.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(pan);

        pan.add(saveButton);
        pan.add(tagButton);
        pan.add(contentButton);

    }
}
