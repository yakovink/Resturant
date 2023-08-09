package View.Nodes;

/*this is numbers text field. it checks if the value is valid number.
 * because there isn't should be negative numerical values, its also checks they are posetive.
 * enable partials.**/

import Exceptions.BlankFieldException;
import Exceptions.IllegalCharacterException;
import Exceptions.LieException;
import Exceptions.NotNumberException;
import application.App;
import javafx.scene.control.TextField;

public class IndNumField extends TextField{
	public IndNumField() {
		super();
		this.textProperty().addListener(e->{
			checkColor();
		});
		App.setBorderColor(this, "RED");
	}

	public IndNumField(String text) {
		super();
		this.textProperty().addListener(e->{
			checkColor();
		});
		this.setText(text);
	}
	
	private void isNumeric() throws NumberFormatException, LieException, BlankFieldException, NotNumberException{
		
		boolean wasDote=false;
		for(Character c:this.getText().toCharArray()) {
			if(!(Character.isDigit(c)||(c.equals('.')&&!wasDote))) {
				throw new NotNumberException();
			}
			if(c.equals('.')) {
				wasDote=true;
			}
		}
		if(Double.parseDouble(this.getText())<0) {
			throw new LieException();}
		
		
		
	}
	
	public void checkColor() {
		try {
			isLegal();
			App.setBorderColor(this, "GREEN");
		} catch (NumberFormatException | LieException | BlankFieldException | IllegalCharacterException | NotNumberException e) {
			App.setBorderColor(this, "RED");
		}
	}
	
	public void checkStyle(String s) {
		this.setStyle(s);
		checkColor();
	}
	
	public void isLegal() throws LieException, BlankFieldException, IllegalCharacterException, NotNumberException {
		App.checkLegalCharacters(this);
		this.isNumeric();
	}
}
