package Model;


import java.time.LocalDate;


import Utils.Gender;
import Utils.SpecificTime;

/*I added here 2 fields for crm handeling. that new class is father class of cook and delivery person,
 *and it inherence for them both an indicator if they busy or not and last busy status change**/

public abstract class Worker extends Person {

	protected Boolean isBusy;
	protected SpecificTime lastCheck;
	
	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
		this.check();
	}

	public SpecificTime getLastCheck() {
		return lastCheck;
	}
	
	public Worker(int id, String firstName, String lastName, LocalDate birthDay, Gender gender, String email,
			String phoneNumber, Double salary) {
		super(id, firstName, lastName, birthDay, gender, email, phoneNumber);

		this.setBusy(false);
		// TODO Auto-generated constructor stub
	}
	
	public Worker(int id) {
		super(id);
	}


	public void check() {
		lastCheck=SpecificTime.now();
	}

}
