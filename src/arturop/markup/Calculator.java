package arturop.markup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
	public static final String DRUGS = "drugs";
	public static final String ELECTRONICS = "electronics";
	public static final String FOOD = "food";

	private static final double FLAT_MARKUP = 0.05;
	private static final double PER_PERSON_MARKUP = 0.012;
	private static final Map<String, Double> materialMarkups;
    static {
        Map<String, Double> markup = new HashMap<String, Double>();
        markup.put(DRUGS, 0.075);
        markup.put(ELECTRONICS, 0.02);
        markup.put(FOOD, 0.13);
        materialMarkups = Collections.unmodifiableMap(markup);
    }

    private MarkupCalculator flatMarkup;
	private MarkupCalculator labourMarkup;
	private MarkupCalculator materialMarkup;

	public static double computePrice(double basePrice, int numberPeople, String materialType){
		Calculator calculator = new Calculator(new FlatMarkup(FLAT_MARKUP), new LabourMarkup(PER_PERSON_MARKUP), new MaterialMarkup(materialMarkups));
		Job job = new Job(numberPeople, materialType);
		long finalPrice = calculator.compute(toPennies(basePrice), job);

		return toDollars(finalPrice);
	}

	private Calculator(MarkupCalculator flatMarkup, MarkupCalculator labourMarkup, MarkupCalculator materialMarkup){
		this.flatMarkup = flatMarkup;
		this.labourMarkup = labourMarkup;
		this.materialMarkup = materialMarkup;
	}

	// TODO: document this method. Explain basePrice is in pennies.
	private long compute(long basePrice, Job job){
		validateArguments(basePrice);

		long flatPrice = flatMarkup.compute(basePrice, job);
		long labourCharge = labourMarkup.compute(flatPrice, job);
		long materialCharge = materialMarkup.compute(flatPrice, job);

		return flatPrice + labourCharge + materialCharge;
	}

	private void validateArguments(long basePrice) {
		if (basePrice < 0) throw new IllegalArgumentException("Base Price must be greater or equal to 0.");
	}

	private static long toPennies(double dollars) {
		return Math.round(dollars * 100);
	}

	private static double toDollars(long pennies) {
		return ((double) pennies) / 100;
	}
}
