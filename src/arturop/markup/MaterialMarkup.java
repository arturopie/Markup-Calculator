package arturop.markup;

import java.util.Map;

public class MaterialMarkup implements MarkupCalculator {

	private Map<String, Double> markups;

	public MaterialMarkup(Map<String, Double> markups) {
		this.markups = markups;
	}

	@Override
	public long compute(long price, Job job) {
		double markup = markupFor(job.getMaterialType());
		return Math.round(price * markup);
	}

	private double markupFor(String materialType) {
		Double markup = markups.get(materialType);
		return (markup == null) ? 0 : markup;
	}
}
