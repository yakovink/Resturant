package Model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import Exceptions.InvalidEmailException;
import Exceptions.InvalidIDException;
import Exceptions.InvalidPhoneException;
import Exceptions.ToYoungException;
import Utils.Gender;

public abstract class Person {
	protected int id;
	protected String firstName;
	protected String lastName;
	protected LocalDate birthDay;
	protected Gender gender;
	protected String email;
	protected String phoneNumber;
	private static final HashSet<String> preNums10=new HashSet<>(Arrays.asList("050","051","052","053","054","055","058","071","072","073","074","076","077","079"));
	private static final HashSet<String> preNums9=new HashSet<>(Arrays.asList("02","03","04","08","09"));
	
	public Person(int id, String firstName, String lastName, LocalDate birthDay, Gender gender, String email, String phoneNumber) throws ToYoungException, InvalidIDException, UnknownHostException, InvalidEmailException, InvalidPhoneException {
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

	public void setEmail(String email) throws InvalidEmailException {
		String[] parts=email.split("@");
		if(parts.length!=2) {
			throw new InvalidEmailException();
		}
		InetAddress domain;
		try {
			domain = InetAddress.getByName(parts[1]);
			domain.isReachable(2000);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			throw new InvalidEmailException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new InvalidEmailException();
		}
		
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws InvalidPhoneException{
		
		if(phoneNumber.length()!=9&&phoneNumber.length()!=10) {
			throw new InvalidPhoneException();
		}
		String pre=phoneNumber.substring(0,phoneNumber.length()-7);
		if(!(preNums10.contains(pre)||(preNums9.contains(pre)))){
			throw new InvalidPhoneException();
		}
		for(char c:phoneNumber.toCharArray()) {
			if (Character.isAlphabetic(c)){
				throw new InvalidPhoneException();
			}
		}
		this.phoneNumber = phoneNumber;
	}

	public Person(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) throws InvalidIDException {
		int iddemo=id,sum=0;
		LinkedList<Integer> nums=new LinkedList<>();
		while(iddemo!=0) {
			nums.addFirst(iddemo%10);
			iddemo=iddemo/10;
		}
		if(nums.size()>9) {
			throw new InvalidIDException();
		}
		else if(nums.size()<9) {
			int a=nums.size();
			for(int i=0;i<9-a;i++)
				nums.addFirst(0);
		}
		for(int i=0;i<nums.size()-1;i++) {
			int bonus=((i%2==0)?nums.get(i):nums.get(i)*2);
			if(bonus>=10) {
				bonus=bonus/10+bonus%10;
			}
			sum+=bonus;
		}
		if(10-sum%10!=nums.getLast()) {
			throw new InvalidIDException();
		}
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
	public void setBirthDay(LocalDate birthDay) throws ToYoungException{
		LocalDate now=LocalDate.now();
		if(now.getYear()-birthDay.getDayOfYear()<16||(now.getYear()-birthDay.getYear()==16&&now.getMonthValue()<birthDay.getMonthValue())||(now.getYear()-birthDay.getYear()==16&&now.getMonthValue()==birthDay.getMonthValue()&&now.getDayOfMonth()>birthDay.getDayOfMonth())) {
			throw new ToYoungException();
		}
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
