package View.Nodes;

/*that password field desined to check the level of the password.
 * the record calculate by log(2) of number of possablity options. the password bits, actually.
 * field have 4 levels: below 64 bits - red. between 64 to 96 - orange. between 96 to 128 - yellow, above 128 - green
 * field is indictive.**/

import java.util.HashMap;

import Exceptions.BlankFieldException;
import Exceptions.IllegalCharacterException;
import Exceptions.LowPasswordException;
import application.App;
import javafx.scene.control.PasswordField;

public class IndPasswordField extends PasswordField{
	
	public IndPasswordField() {
		super();
		String[] colors= {"RED","#CC6C1D","YELLOW","GREEN"};
		this.textProperty().addListener(e->{
			int level=passLevel();
			try {
				isLegal();
				App.setBorderColor(this, colors[level]);
	
			} catch (BlankFieldException | IllegalCharacterException | LowPasswordException e1) {
				App.setBorderColor(this, "RED");
			}
		});
	}

	/*(2^n)^m=2^nm**/
	
	public int passLevel() {
		String s=this.getText();
		HashMap<String,Integer> req=new HashMap<>();
		req.put("lowerCase", 0);
		req.put("upperCase", 0);
		req.put("digit", 0);
		req.put("signChar", 0);
		int count=0;
		for(Character c:s.toCharArray()) {
			if(Character.isUpperCase(c)) {
				req.put("upperCase", req.get("upperCase")+1);
			}
			else if(Character.isLowerCase(c)) {
				req.put("lowerCase", req.get("lowerCase")+1);
			}
			else if(Character.isDigit(c)) {
				req.put("digit", req.get("digit")+1);
			}
			else {
				req.put("signChar", req.get("signChar")+1);
			}
			count++;
		}

		int options=0;
		if(req.get("upperCase")>0) {
			options+=26;
		}
		if(req.get("lowerCase")>0) {
			options+=26;
		}
		if(req.get("digit")>0) {
			options+=10;
		}
		if(req.get("signChar")>0) {
			options+=21;
		}
		Double level=0.0;
		
		level=Math.log(count)/Math.log(2)*options;
		
		if(level<64) {
			return 0;
		}
		if(level<96) {
			return 1;
		}
		if(level<128) {
			return 2;
		}
		return 3;
	}
	
	public void checkStyle(String s) {
		this.setStyle(s);
		try {
			isLegal();
			App.setBorderColor(this, "GREEN");

		} catch (BlankFieldException | IllegalCharacterException | LowPasswordException e1) {
			App.setBorderColor(this, "RED");
		}
	}
	
	public void isLegal() throws BlankFieldException, IllegalCharacterException, LowPasswordException {
		App.checkLegalCharacters(this);
		if(passLevel()==0) {
			throw new LowPasswordException();
		}
	}

}
