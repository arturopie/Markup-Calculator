package arturop.markup;

public class LabourMarkup {

	private double labourMarkup;

	public LabourMarkup(double labourMarkup){
		this.labourMarkup = labourMarkup;
	}

	public long compute(long price, Job job) {
		validateArguments(job);

		return Math.round(price * job.numberPeople * labourMarkup);
	}

	private void validateArguments(Job job) {
		if (job.numberPeople < 0) throw new IllegalArgumentException("Number of people must be greater or equal to 0.");
	}
}
