package arturop.markup;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class MaterialMarkupTest {

	@Test
	public void computeAppliesCorrectMarkupType() {
		Map<String, Double> markups = new HashMap<String, Double>();
		markups.put("TypeA", 0.2);
		markups.put("TypeB", 0.8);
		MaterialMarkup markup = new MaterialMarkup(markups);

		assertEquals(8, markup.compute(10, build_job("TypeB")));
	}


	@Test
	public void computeDoesNotApplyMarkupIfTypeNotDefined() {
		Map<String, Double> markups = new HashMap<String, Double>();
		markups.put("MyType", 0.5);
		MaterialMarkup markup = new MaterialMarkup(markups);

		assertEquals(0, markup.compute(2, build_job("SomethingElse")));
	}

	@Test
	public void computeRoundsToNearestInteger() {
		Map<String, Double> markups = new HashMap<String, Double>();
		markups.put("MyType", 0.5);
		MaterialMarkup markup = new MaterialMarkup(markups);

		assertEquals(3, markup.compute(5, build_job("MyType")));
	}

	private Job build_job(String materialType) {
		return new Job(1, materialType);
	}
}
