package arturop.markup;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorIntegrationTest {

	@Test
	public void testInput1FromProblemDescription() {
		double price = Calculator.computePrice(1299.99, 3, MaterialType.FOOD);
		assertEquals(1591.58, price, 0.001);
	}

	@Test
	public void testInput2FromProblemDescription() {
		double price = Calculator.computePrice(5432.00, 1, MaterialType.DRUGS);
		assertEquals(6199.81, price, 0.001);
	}

	@Test
	public void testInput3FromProblemDescription() {
		double price = Calculator.computePrice(12456.95, 4, MaterialType.BOOKS);
		assertEquals(13707.63, price, 0.001);
	}
}
