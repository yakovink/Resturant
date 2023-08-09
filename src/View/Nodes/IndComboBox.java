package View.Nodes;

import Exceptions.BlankFieldException;

import application.App;
import javafx.scene.control.ComboBox;

/*an indictive comboBox for short list choice.
 * checks if the value is null**/

public class IndComboBox<T> extends ComboBox<T>{

	public IndComboBox() {
		super();

		this.valueProperty().addListener(e1->{
			try {
				this.isLegal();
				App.setBorderColor(this, "GREEN");
				
			} catch (BlankFieldException e21) {
				App.setBorderColor(this, "RED");
			}
			});
			App.setBorderColor(this, "RED");

		// TODO Auto-generated constructor stub
	}
	
	public void isLegal() throws BlankFieldException {
		if(this.getSelectionModel().isEmpty()) {
			throw new BlankFieldException();
		}
	}
	
	public void checkStyle(String s) {
		this.setStyle(s);
		try {
			isLegal();
			App.setBorderColor(this, "GREEN");

		} catch (BlankFieldException e1) {
			App.setBorderColor(this, "RED");
		}
	}
	
}
