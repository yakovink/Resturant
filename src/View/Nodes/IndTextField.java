package View.Nodes;

import Exceptions.BlankFieldException;
import Exceptions.IllegalCharacterException;
import application.App;
import javafx.scene.control.TextField;

/*this is an regular text field that only check if there are characters which may distract to the server-client protocols.**/

public class IndTextField extends TextField{

	public IndTextField() {
		super();
		this.textProperty().addListener(e->{
			try {
				isLegal();
				App.setBorderColor(this, "GREEN");

			} catch (BlankFieldException | IllegalCharacterException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		App.setBorderColor(this, "RED");
	}

	public IndTextField(String text) {
		super();
		this.textProperty().addListener(e->{
			try {
				isLegal();
				App.setBorderColor(this, "GREEN");

			} catch (BlankFieldException | IllegalCharacterException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		try {
			isLegal();
			App.setBorderColor(this, "GREEN");

		} catch (BlankFieldException | IllegalCharacterException e1) {
			App.setBorderColor(this, "RED");
		}
		this.setText(text);
	}
	
	public void checkStyle(String s) {
		this.setStyle(s);
		try {
			isLegal();
			App.setBorderColor(this, "GREEN");

		} catch (BlankFieldException | IllegalCharacterException e1) {
			App.setBorderColor(this, "RED");
		}
	}
	
	public void isLegal() throws BlankFieldException, IllegalCharacterException {
		App.checkLegalCharacters(this);
	}

}
