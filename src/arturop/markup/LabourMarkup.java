package arturop.markup;

public class LabourMarkup {

	private double labourMarkup;

	public LabourMarkup(double labourMarkup){
		this.labourMarkup = labourMarkup;
	}

	public long compute(long price, int numberPeople) {
		validateArguments(numberPeople);

		return Math.round(price * numberPeople * labourMarkup);
	}

	private void validateArguments(int numberPeople) {
		if (numberPeople < 0) throw new IllegalArgumentException("Number of people must be greater or equal to 0.");
	}
}
