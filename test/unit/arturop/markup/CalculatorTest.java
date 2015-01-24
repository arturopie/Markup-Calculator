package arturop.markup;

import org.junit.Test;

public class CalculatorTest {

	@Test(expected=IllegalArgumentException.class)
	public void throwExceptionWhenNegativeBasePrice() {
		Calculator.computePrice(-1, 1, Calculator.FOOD);
	}

	@Test(expected=IllegalArgumentException.class)
	public void throwExceptionWhenNegativeNumberOfPeople() {
		Calculator.computePrice(1, -1, Calculator.FOOD);
	}
}
