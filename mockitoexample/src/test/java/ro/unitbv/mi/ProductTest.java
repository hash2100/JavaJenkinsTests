package ro.unitbv.mi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class ProductTest {
	Product prod;
	
	@BeforeEach
	void setUp() {
		prod = new Product();
	}
	
	@Test
	@DisplayName("Basic product test")
	void testProduct()	{
		assertEquals(56, prod.calculate(7, 8), "Basic product");
		assertEquals(56, prod.calculate(8, 7), "Basic product reversed");
	}
	
	@RepeatedTest(4)
	@DisplayName("Random tests")
	void testRandom() {
		int a = ThreadLocalRandom.current().nextInt(0, 500 + 1);
		int b = ThreadLocalRandom.current().nextInt(0, 500 + 1);
		assertEquals(a * b, prod.calculate(a, b), "Product " + a + " * " + b + " failed");
	}
	
	@Test
	void testExceptionThrowing() {
		Throwable exception = assertThrows(
				ArithmeticException.class,
				() -> prod.division(10, 0)
		);
	}
}
