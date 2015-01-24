package arturop.markup;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabourMarkupTest {

	@Test(expected=IllegalArgumentException.class)
	public void computeThrowsExceptionWhenNegativeNumberOfPeople() {
		LabourMarkup markup = new LabourMarkup(2);
		markup.compute(1, -2);
	}

	@Test
	public void computeAppliesLabourMarkup() {
		LabourMarkup markup = new LabourMarkup(0.5);

		long charge = markup.compute(100, 1);

		assertEquals(50, charge);
	}

	@Test
	public void computeAppliesLabourMarkupPerPerson() {
		LabourMarkup markup = new LabourMarkup(0.5);

		long charge = markup.compute(100, 5);

		assertEquals(50 * 5, charge);
	}

	@Test
	public void computeRoundsToNearestInteger() {
		LabourMarkup markup = new LabourMarkup(0.5);

		long charge = markup.compute(5, 1);

		assertEquals(3, charge);
	}
}
