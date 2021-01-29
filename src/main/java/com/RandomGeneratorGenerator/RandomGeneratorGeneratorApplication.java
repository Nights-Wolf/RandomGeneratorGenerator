package com.RandomGeneratorGenerator;


import lombok.Getter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
@Getter
public class RandomGeneratorGeneratorApplication implements CommandLineRunner {

	private final JButton saveButton = new JButton("Save");

	public static void main(String[] args) {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(RandomGeneratorGeneratorApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		SwingUtilities.invokeLater(() -> {

					JFrame mainFrame = new JFrame("Main Frame");

					mainFrame.setSize(500, 350);
					mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					mainFrame.setVisible(true);

					JPanel pan = new JPanel();
					pan.setLayout(new FlowLayout(FlowLayout.CENTER));
					mainFrame.add(pan);

					mainFrame.add(saveButton);
			});
		}
}