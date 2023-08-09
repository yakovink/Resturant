package Model;


import java.net.UnknownHostException;

import java.time.LocalDate;


import Exceptions.InvalidEmailException;
import Exceptions.InvalidIDException;
import Exceptions.InvalidPhoneException;
import Exceptions.ToYoungException;
import Utils.Gender;
import Utils.Neighberhood;

public class Customer extends Person{
	
	private static int idCounter = 1;
	private Neighberhood neighberhood;
	private boolean isSensitiveToLactose;
	private boolean isSensitiveToGluten;
	private Account account;
	private String image;
	
	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String email,String phone,String username,String password) throws UnknownHostException, ToYoungException, InvalidIDException, InvalidEmailException, InvalidPhoneException {
		super(idCounter++, firstName, lastName, birthDay, gender, email, phone);
		this.setNeighberhood(neighberhood);
		this.setSensitiveToGluten(isSensitiveToGluten);
		this.setSensitiveToLactose(isSensitiveToLactose());
		this.account=new Account(username,password);
		this.image="file:images/custDefault";
	}
	
	public Customer(int id,String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String email,String phone,String username,String password, String image) throws UnknownHostException, ToYoungException, InvalidIDException, InvalidEmailException, InvalidPhoneException{
		super(id, firstName, lastName, birthDay, gender, email, phone);
		this.setNeighberhood(neighberhood);
		this.setSensitiveToGluten(isSensitiveToGluten);
		this.setSensitiveToLactose(isSensitiveToLactose());
		this.account=new Account(username,password);
		this.image=image;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Account getAccount() {
		return account;
	}

	public Customer(int id) {
		super(id);
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Customer.idCounter = idCounter;
	}

	public Neighberhood getNeighberhood() {
		return neighberhood;
	}

	public void setNeighberhood(Neighberhood neighberhood) {
		this.neighberhood = neighberhood;
	}

	public boolean isSensitiveToLactose() {
		return isSensitiveToLactose;
	}

	public void setSensitiveToLactose(boolean isSensitiveToLactose) {
		this.isSensitiveToLactose = isSensitiveToLactose;
	}

	public boolean isSensitiveToGluten() {
		return isSensitiveToGluten;
	}

	public void setSensitiveToGluten(boolean isSensitiveToGluten) {
		this.isSensitiveToGluten = isSensitiveToGluten;
	}

	@Override
	public String toString() {
		return super.toString()+" " + (isSensitiveToLactose?"lactose":"") + " " + (isSensitiveToGluten?"gluten":"");
	}
}
