package Model;


/*account object is my tactic for best operation of login process.
 * the restaurant object have hashmap with accounts as keys and customers as values.
 * the account equals methods use both username and password, and by find account key we can login with O(1) actions.
 * the password have encrypt option. i dont use it when i send it to client or from it, but in the serializable function it saved encrypting**/


import Utils.Encryption;

public class Account {
	
	private String userName;
	private String password;
	
	public Account(String userName, String password){
		super();
		this.setUserName(userName);
		this.setPassword(password);
	}

	public Account(String userName) {
		super();
		this.setUserName(userName);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Account other = (Account) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}
	
	public String getDecryptPassword(){
		return Encryption.decrypt(password);
	}


	@Override
	public String toString() {
		return userName + " " + password;
	}

	public void setPassword(String password){
		this.password = Encryption.encrypt(password);
	}


	
	
}
