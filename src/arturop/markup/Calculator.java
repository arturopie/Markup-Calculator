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
		return 0.0;
	}
}
