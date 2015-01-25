package arturop.markup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
	public static final String DRUGS = "drugs";
	public static final String ELECTRONICS = "electronics";
	public static final String FOOD = "food";

	// CONFIGURATION STARTS
	private static final double FLAT_MARKUP = 0.05;
	private static final double PER_PERSON_MARKUP = 0.012;
	private static final Map<String, Double> MATERIAL_MARKUPS;
	static {
		Map<String, Double> markup = new HashMap<String, Double>();
		markup.put(DRUGS, 0.075);
		markup.put(ELECTRONICS, 0.02);
		markup.put(FOOD, 0.13);
		MATERIAL_MARKUPS = Collections.unmodifiableMap(markup);
	}
	// CONFIGURATION ENDS

	private MarkupCalculator flatMarkup;
	private MarkupCalculator labourMarkup;
	private MarkupCalculator materialMarkup;

	/**
	 * Facade to Markup Calculator. It computes final Price for a job
	 *
	 * @param basePrice     Base price in dollars. It must be greater or equals to 0.
	 * @param numberPeople  Number of personnel required for this job. It must be greater or equals to 0.
	 * @param materialType  Type of materials involved.
	 * @return              Final price after applying all markups.
	 */
	public static double computePrice(double basePrice, int numberPeople, String materialType){
		validateBasePrice(basePrice);

		Calculator calculator = new Calculator(
			new FlatMarkup(FLAT_MARKUP),
			new LabourMarkup(PER_PERSON_MARKUP),
			new MaterialMarkup(MATERIAL_MARKUPS)
		);
		Job job = new Job(numberPeople, materialType);

		return computePrice(calculator, basePrice, job);
	}

	private static double computePrice(Calculator calculator, double basePrice, Job job) {
		long finalPrice = calculator.compute(toPennies(basePrice), job);

		return toDollars(finalPrice);
	}

	private Calculator(MarkupCalculator flatMarkup, MarkupCalculator labourMarkup, MarkupCalculator materialMarkup){
		this.flatMarkup = flatMarkup;
		this.labourMarkup = labourMarkup;
		this.materialMarkup = materialMarkup;
	}

	/**
	 * Computes final Price for a job
	 *
	 * @param basePrice  Base price in pennies since Java's double/float are not suitable for representing money
	 * @param job        Contains information about the job used by calculator to compute final price.
	 * @return           Final price in pennies after applying all markups
	 */
	private long compute(long basePrice, Job job){
		long flatPrice = flatMarkup.compute(basePrice, job);
		long labourCharge = labourMarkup.compute(flatPrice, job);
		long materialCharge = materialMarkup.compute(flatPrice, job);

		return flatPrice + labourCharge + materialCharge;
	}

	private static void validateBasePrice(double basePrice) {
		if (basePrice < 0) throw new IllegalArgumentException("Base Price must be greater or equal to 0.");
	}

	private static long toPennies(double dollars) {
		return Math.round(dollars * 100);
	}

	private static double toDollars(long pennies) {
		return ((double) pennies) / 100;
	}
}
