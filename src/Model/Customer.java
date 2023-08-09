package Model;




import java.time.LocalDate;

import Utils.Gender;
import Utils.Neighberhood;

public class Customer extends Person{
	/*I added here username and password for accound constracting**/
	
	
	private static int idCounter = 1;
	private Neighberhood neighberhood;
	private boolean isSensitiveToLactose;
	private boolean isSensitiveToGluten;
	private Account account;

	
	public Customer(String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String email,String phone,String username,String password)  {
		super(idCounter++, firstName, lastName, birthDay, gender, email, phone);
		this.setNeighberhood(neighberhood);
		this.setSensitiveToGluten(isSensitiveToGluten);
		this.setSensitiveToLactose(isSensitiveToLactose());
		this.account=new Account(username,password);

	}
	
	public Customer(int id,String firstName, String lastName, LocalDate birthDay, Gender gender,
			Neighberhood neighberhood,	boolean isSensitiveToLactose, boolean isSensitiveToGluten, String email,String phone,String username,String password){
		super(id, firstName, lastName, birthDay, gender, email, phone);
		this.setNeighberhood(neighberhood);
		this.setSensitiveToGluten(isSensitiveToGluten);
		this.setSensitiveToLactose(isSensitiveToLactose());
		this.account=new Account(username,password);

	}
	
	public void setAccount(Account account) {
		this.account = account;
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
