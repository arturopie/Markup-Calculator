package arturop.markup;

import static org.junit.Assert.*;
import org.junit.Test;

public class FlatMarkupTest {

	@Test
	public void computeAppliesFlatMarkup() {
		FlatMarkup markup = new FlatMarkup(0.5);

		assertEquals(10 + 5, markup.compute(10, null));
	}

	@Test
	public void computeRoundsToNearestInteger() {
		FlatMarkup markup = new FlatMarkup(0.5);

		assertEquals(5 + 3, markup.compute(5, null));
	}
}
