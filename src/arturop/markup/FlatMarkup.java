package arturop.markup;

public class FlatMarkup implements MarkupCalculator {

	private double flatMarkup;

	public FlatMarkup(double flatMarkup){
		this.flatMarkup = flatMarkup;
	}

	@Override
	public long compute(long price, Job job) {
		return Math.round(price + price * flatMarkup);
	}
}
