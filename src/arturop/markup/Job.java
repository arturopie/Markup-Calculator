package arturop.markup;

public class Job {
	private int numberPeople;
	private String materialType;

	public Job(int numberPeople, String materialType) {
		this.numberPeople = numberPeople;
		this.materialType = materialType;
	}

	public int getNumberPeople() {
		return numberPeople;
	}

	public String getMaterialType() {
		return materialType;
	}
}
