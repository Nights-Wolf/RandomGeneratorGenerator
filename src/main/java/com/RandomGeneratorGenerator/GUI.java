package com.RandomGeneratorGenerator;

import com.RandomGeneratorGenerator.model.Name;
import com.RandomGeneratorGenerator.names.SaveName;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
@Getter
public class GUI implements Runnable {

    private final JButton saveButton = new JButton("Save");

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
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        JPanel pan = new JPanel();
        pan.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(pan);

        frame.add(saveButton);

    }
}
