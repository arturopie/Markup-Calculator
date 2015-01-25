package arturop.markup;

import java.util.Map;

public class MaterialMarkup implements MarkupCalculator {

	private Map<String, Double> markups;

	public MaterialMarkup(Map<String, Double> markups) {
		this.markups = markups;
	}

	@Override
	public long compute(long price, Job job) {
		Double value = markups.get(job.getMaterialType());
		double markup = (value == null) ? 0 : value;

		return Math.round(price * markup);
	}
}
