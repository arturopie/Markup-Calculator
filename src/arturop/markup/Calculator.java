package arturop.markup;

// TODO: move MaterialType to another file
enum MaterialType{
	ELECTRONICS,
	FOOD,
	PHARMACEUTICALS
}

public class Calculator {
	public static double computePrice(double basePrice, int numberPeople, MaterialType material){
		return new Calculator().compute(basePrice, numberPeople, material);
	}

	public double compute(double basePrice, int numberPeople, MaterialType material){
		validateArguments(basePrice, numberPeople);
		return 0.0;
	}

	private void validateArguments(double basePrice, int numberPeople) {
		if (basePrice < 0) throw new IllegalArgumentException("Base Price must be greater or equal to 0.");
		if (numberPeople < 0) throw new IllegalArgumentException("Number of people must be greater or equal to 0.");
	}
}
