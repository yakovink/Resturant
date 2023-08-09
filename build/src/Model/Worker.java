package Model;

import java.net.UnknownHostException;
import java.time.LocalDate;

import Exceptions.IllegalSalaryException;
import Exceptions.InvalidEmailException;
import Exceptions.InvalidIDException;
import Exceptions.InvalidPhoneException;
import Exceptions.ToYoungException;
import Utils.Gender;
import Utils.SpecificTime;

public abstract class Worker extends Person {
	
	protected Double salary;
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
			String phoneNumber, Double salary) throws ToYoungException, InvalidIDException, UnknownHostException,
			InvalidEmailException, InvalidPhoneException, IllegalSalaryException {
		super(id, firstName, lastName, birthDay, gender, email, phoneNumber);
		this.setSalary(salary);
		this.setBusy(false);
		// TODO Auto-generated constructor stub
	}
	
	public Worker(int id) {
		super(id);
	}


	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) throws IllegalSalaryException{
		if (salary<5000) {
			throw new IllegalSalaryException();
		}
		this.salary = salary;
	}
	public void check() {
		lastCheck=SpecificTime.now();
	}

}
