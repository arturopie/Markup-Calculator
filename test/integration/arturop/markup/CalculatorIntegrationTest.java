package arturop.markup;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorIntegrationTest {

	@Test
	public void testInput1FromProblemDescription() {
		double price = Calculator.computePrice(1299.99, 3, MaterialType.FOOD);
		assertEquals(1591.58, price, 0.001);
	}

}
