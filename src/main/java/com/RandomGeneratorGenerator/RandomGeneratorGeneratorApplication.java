package com.RandomGeneratorGenerator;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RandomGeneratorGeneratorApplication {


	public static void main(String[] args) {

		SpringApplicationBuilder builder = new SpringApplicationBuilder(RandomGeneratorGeneratorApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
		GUI gui = context.getBean(GUI.class);
	}
}