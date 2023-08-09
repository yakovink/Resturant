package View.Nodes;

/*this text field desinged to validate if the value is valid phone number.
 *it wont valid if there is more than 10 or less that 9 digits, or if the preNumber is legal in israel.
 *the field is indictive **/

import java.util.Arrays;
import java.util.HashSet;

import Exceptions.BlankFieldException;
import Exceptions.IllegalCharacterException;
import Exceptions.InvalidPhoneException;
import Exceptions.LieException;
import Exceptions.NotNumberException;
import application.App;

public class PhoneField extends IntegerField{

	private static final HashSet<String> preNums10=new HashSet<>(Arrays.asList("050","051","052","053","054","055","058","071","072","073","074","076","077","079"));
	private static final HashSet<String> preNums9=new HashSet<>(Arrays.asList("02","03","04","08","09"));
	
	public PhoneField() {
		super();
		this.textProperty().addListener(e->{
			try {
				isLegalPhone();
					App.setBorderColor(this, "GREEN");

			} catch (NumberFormatException | LieException | BlankFieldException | IllegalCharacterException | InvalidPhoneException | NotNumberException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		App.setBorderColor(this, "RED");
	}

	public PhoneField(String text) {
		super();
		this.textProperty().addListener(e->{
			try {
				isLegalPhone();
					App.setBorderColor(this, "GREEN");

			} catch (NumberFormatException | LieException | BlankFieldException | IllegalCharacterException | InvalidPhoneException | NotNumberException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		try {
			isLegalPhone();
				App.setBorderColor(this, "GREEN");

		} catch (NumberFormatException | LieException | BlankFieldException | IllegalCharacterException | InvalidPhoneException | NotNumberException e1) {
			App.setBorderColor(this, "RED");
		}
		this.setText(text);
	}
	
	public void checkStyle(String s) {
		this.setStyle(s);
		try {
			isLegalPhone();
			App.setBorderColor(this, "GREEN");

		} catch (BlankFieldException | NumberFormatException | LieException | IllegalCharacterException | InvalidPhoneException | NotNumberException e1) {
			App.setBorderColor(this, "RED");
		}
	}
	
	public void isPhone() throws InvalidPhoneException {
		String phoneNumber=this.getText();
		if(phoneNumber.length()!=9&&phoneNumber.length()!=10) {
			throw new InvalidPhoneException();
		}
		String pre=phoneNumber.substring(0,phoneNumber.length()-7);
		if(!(preNums10.contains(pre)||(preNums9.contains(pre)))){
			throw new InvalidPhoneException();
		}
	}
	
	public void isLegalPhone() throws NumberFormatException, LieException, BlankFieldException, IllegalCharacterException, InvalidPhoneException, NotNumberException {
		super.isLegal();
		isPhone();
	}
}
