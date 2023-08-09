package Model;



import java.time.LocalDate;



import Utils.Gender;

/*here i added email and phone, and check methods for validate the values before setting them.
 * the ID now is real ID and needs check digit validate.
 * I decided that the restaurant won't employee or sell for teens below 18 year's old**/

public abstract class Person {
	protected int id;
	protected String firstName;
	protected String lastName;
	protected LocalDate birthDay;
	protected Gender gender;
	protected String email;
	protected String phoneNumber;
	
	public Person(int id, String firstName, String lastName, LocalDate birthDay, Gender gender, String email, String phoneNumber) {
		super();
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setGender(gender);
		this.setBirthDay(birthDay);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
	}
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber){
		
		this.phoneNumber = phoneNumber;
	}

	public Person(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
		
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay){

			this.birthDay = birthDay;

	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id+ " "+firstName + " " + lastName;
	}

	

	
	
	
	
	
}
