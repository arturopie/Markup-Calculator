package arturop.markup;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
	public static final String DRUGS = "drugs";
	public static final String ELECTRONICS = "electronics";
	public static final String FOOD = "food";

	private LabourMarkup labourMarkup;
	private MaterialMarkup materialMarkup;

	public static double computePrice(double basePrice, int numberPeople, String materialType){
		Job job = new Job(numberPeople, materialType);

		Map<String, Double> metrialMarkups = new HashMap<String, Double>();
		metrialMarkups.put(DRUGS, 0.075);
		metrialMarkups.put(ELECTRONICS, 0.02);
		metrialMarkups.put(FOOD, 0.13);

		// TODO: extracts percentages to constants
		long finalPrice = new Calculator(new LabourMarkup(0.012), new MaterialMarkup(metrialMarkups)).compute(toPennies(basePrice), job);
		return toDollars(finalPrice);
	}

	private Calculator(LabourMarkup labourMarkup, MaterialMarkup materialMarkup){
		this.labourMarkup = labourMarkup;
		this.materialMarkup = materialMarkup;
	}

	// TODO: document this method. Explain basePrice is in pennies.
	private long compute(long basePrice, Job job){
		validateArguments(basePrice, job);

		long flatPrice = Math.round(basePrice + basePrice * 0.05);
		long labourCharge = labourMarkup.compute(flatPrice, job);
		long materialCharge = materialMarkup.compute(flatPrice, job);

		return flatPrice + labourCharge + materialCharge;
	}

	private void validateArguments(long basePrice, Job job) {
		if (basePrice < 0) throw new IllegalArgumentException("Base Price must be greater or equal to 0.");
	}

	private static long toPennies(double dollars) {
		return Math.round(dollars * 100);
	}

	private static double toDollars(long pennies) {
		return ((double) pennies) / 100;
	}
}
