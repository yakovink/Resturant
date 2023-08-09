package View.Nodes;

/*that text field has a fuctionality to check if the value is valid mail address.
 * 3 option can be for false return:
 * a   if the build of username@domain is not valid
 * b   if dns cant found the domain address
 * c   if domain address cant be reached**/



import Exceptions.BlankFieldException;
import Exceptions.IllegalCharacterException;
import Exceptions.InvalidEmailException;
import application.App;

public class EmailField extends IndTextField{

	public EmailField() {
		super();
		App.setBorderColor(this, "RED");
		this.textProperty().addListener(e->{
			try {
				isLegalMail();
				App.setBorderColor(this, "GREEN");

			} catch (BlankFieldException | IllegalCharacterException | InvalidEmailException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		App.setBorderColor(this, "RED");
		
	}

	public EmailField(String text) {
		super();
		try {
			isLegalMail();
			App.setBorderColor(this, "GREEN");

		} catch (BlankFieldException | IllegalCharacterException | InvalidEmailException e1) {
			App.setBorderColor(this, "RED");
		}
		this.textProperty().addListener(e->{
			try {
				isLegalMail();
				App.setBorderColor(this, "GREEN");

			} catch (BlankFieldException | IllegalCharacterException | InvalidEmailException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		this.setText(text);
		
	}
	
	public void isMail() throws InvalidEmailException {
		
		String[] parts=this.getText().split("@");
		if(parts.length!=2) {
			throw new InvalidEmailException();
		}
		String[] sub=parts[1].split("\\.");
		if(sub.length<2) {
			throw new InvalidEmailException();
		}

	}
	
	
	public void checkStyle(String s) {
		this.setStyle(s);
		try {
			isLegalMail();
			App.setBorderColor(this, "GREEN");

		} catch (BlankFieldException | IllegalCharacterException | InvalidEmailException e1) {
			App.setBorderColor(this, "RED");
		}
	}
	
	public void isLegalMail() throws BlankFieldException, IllegalCharacterException, InvalidEmailException {
		super.isLegal();
		isMail();
	}

}
