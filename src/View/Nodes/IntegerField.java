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

public class IntegerField extends TextField{

	public IntegerField() {
		super();
		this.textProperty().addListener(e->{
			checkColor();
		});
		App.setBorderColor(this, "RED");
	}

	public IntegerField(String text) {
		super();
		this.textProperty().addListener(e->{
			checkColor();
		});
		this.setText(text);
	}
	
	private void isNumeric() throws NumberFormatException, LieException, BlankFieldException, NotNumberException{
		
		for(Character c:this.getText().toCharArray()) {
			if(!(Character.isDigit(c))) {
				throw new NotNumberException();
			}
		}
		if(Integer.parseInt(this.getText())<0) {
			throw new LieException();}
		
		
		
	}
	
	public void checkColor() {
		try {
			isLegal();
			App.setBorderColor(this, "GREEN");
		} catch (NumberFormatException | BlankFieldException | IllegalCharacterException | LieException | NotNumberException e) {
			App.setBorderColor(this, "RED");
		}
	}
	
	public void checkStyle(String s) {
		this.setStyle(s);
		checkColor();
	}
	
	public void isLegal() throws BlankFieldException, IllegalCharacterException, NumberFormatException, LieException, NotNumberException {
		App.checkLegalCharacters(this);
		this.isNumeric();
	}
}
