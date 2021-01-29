package com.RandomGeneratorGenerator;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RandomGeneratorGeneratorApplicationTests {

	@BeforeClass
	public static void setupHeadlessMode() {
		System.setProperty("java.awt.headless", "false");
	}

	@Test
	void contextLoads() {
	}

}
