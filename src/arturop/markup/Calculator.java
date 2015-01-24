package arturop.markup;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
	public static final String DRUGS = "drugs";
	public static final String ELECTRONICS = "electronics";
	public static final String FOOD = "food";

	private LabourMarkup labourMarkup;
	private MaterialMarkup materialMarkup;

	public static double computePrice(double basePrice, int numberPeople, String material){
		// TODO: extracts Job concept
		Map<String, Double> metrialMarkups = new HashMap<String, Double>();
		metrialMarkups.put(DRUGS, 0.075);
		metrialMarkups.put(ELECTRONICS, 0.02);
		metrialMarkups.put(FOOD, 0.13);

		long finalPrice = new Calculator(new LabourMarkup(0.012), new MaterialMarkup(metrialMarkups)).compute(toPennies(basePrice), numberPeople, material);
		return toDollars(finalPrice);
	}

	private Calculator(LabourMarkup labourMarkup, MaterialMarkup materialMarkup){
		this.labourMarkup = labourMarkup;
		this.materialMarkup = materialMarkup;
	}

	// TODO: document this method. Explain basePrice is in pennies.
	private long compute(long basePrice, int numberPeople, String material){
		validateArguments(basePrice, numberPeople);

		long flatPrice = Math.round(basePrice + basePrice * 0.05);
		long labourCharge = labourMarkup.compute(flatPrice, numberPeople);
		long materialCharge = materialMarkup.compute(flatPrice, material);

		return flatPrice + labourCharge + materialCharge;
	}

	private void validateArguments(long basePrice, int numberPeople) {
		if (basePrice < 0) throw new IllegalArgumentException("Base Price must be greater or equal to 0.");
	}

	private static long toPennies(double dollars) {
		return Math.round(dollars * 100);
	}

	private static double toDollars(long pennies) {
		return ((double) pennies) / 100;
	}
}
