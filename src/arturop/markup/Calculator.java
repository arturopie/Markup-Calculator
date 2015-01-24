package arturop.markup;

public class Calculator {
	public static final String DRUGS = "drugs";
	public static final String ELECTRONICS = "electronics";
	public static final String FOOD = "food";

	private LabourMarkup labourMarkup;

	public static double computePrice(double basePrice, int numberPeople, String material){
		long finalPrice = new Calculator(new LabourMarkup(0.012)).compute(toPennies(basePrice), numberPeople, material);
		return toDollars(finalPrice);
	}

	public Calculator(LabourMarkup labourMarkup){
		this.labourMarkup = labourMarkup;
	}

	// TODO: document this method. Explain basePrice is in pennies.
	private long compute(long basePrice, int numberPeople, String material){
		validateArguments(basePrice, numberPeople);

		long flatPrice = Math.round(basePrice + basePrice * 0.05);
		long labourCharge = labourMarkup.compute(flatPrice, numberPeople);

		double materialMarkup;
		if(material.equals(DRUGS))
			materialMarkup = 0.075;
		else if(material.equals(ELECTRONICS))
			materialMarkup = 0.02;
		else if(material.equals(FOOD))
			materialMarkup = 0.13;
		else
			materialMarkup = 0;

		long materialCharge = Math.round(flatPrice * materialMarkup);
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
