package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class DemoApplicationTests {

	Calculator calc = new Calculator();
	@Test
	void itShouldAddNumbers() {

		int num1 = 20;
		int num2 = 30;

		int result = calc.add(num1,num2);

		int expected = 50;
		assertThat(result).isEqualTo(expected);
	}

	class Calculator{
		int add(int a, int b){ return a+b;}
	}

}
