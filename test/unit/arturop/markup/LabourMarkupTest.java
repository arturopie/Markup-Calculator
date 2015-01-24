package arturop.markup;

import static org.junit.Assert.*;

import org.junit.Test;

public class LabourMarkupTest {

	@Test(expected=IllegalArgumentException.class)
	public void computeThrowsExceptionWhenNegativeNumberOfPeople() {
		LabourMarkup markup = new LabourMarkup(2);
		markup.compute(1, build_job(-2));
	}

	@Test
	public void computeAppliesLabourMarkup() {
		LabourMarkup markup = new LabourMarkup(0.5);

		long charge = markup.compute(100, build_job(1));

		assertEquals(50, charge);
	}

	@Test
	public void computeAppliesLabourMarkupPerPerson() {
		LabourMarkup markup = new LabourMarkup(0.5);

		long charge = markup.compute(100, build_job(5));

		assertEquals(50 * 5, charge);
	}

	@Test
	public void computeRoundsToNearestInteger() {
		LabourMarkup markup = new LabourMarkup(0.5);

		long charge = markup.compute(5, build_job(1));

		assertEquals(3, charge);
	}

	private Job build_job(int numberPeople) {
		return new Job(numberPeople, "MaterialType");
	}
}
