package View.Nodes;

import java.util.LinkedList;

/*this is text field that desined to check real human ID number by its last digit.**/

import Exceptions.BlankFieldException;
import Exceptions.IllegalCharacterException;
import Exceptions.InvalidIDException;
import Exceptions.LieException;
import Exceptions.NotNumberException;
import application.App;

public class PersonIdField extends IntegerField{

	public PersonIdField() {
		super();
		this.textProperty().addListener(e->{
			try {
				isLegalID();
				App.setBorderColor(this, "GREEN");

			} catch (NumberFormatException | LieException | BlankFieldException | IllegalCharacterException | InvalidIDException | NotNumberException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		App.setBorderColor(this, "RED");
	}

	public PersonIdField(String text) {
		super();
		this.textProperty().addListener(e->{
			try {
				isLegalID();
				App.setBorderColor(this, "GREEN");

			} catch (NumberFormatException | LieException | BlankFieldException | IllegalCharacterException | InvalidIDException | NotNumberException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		try {
			isLegalID();
			App.setBorderColor(this, "GREEN");

		} catch (NumberFormatException | LieException | BlankFieldException | IllegalCharacterException | InvalidIDException | NotNumberException e1) {
			App.setBorderColor(this, "RED");
		}
		this.setText(text);
	}
	
	public boolean isId() throws NumberFormatException, InvalidIDException{
		
		int iddemo=Integer.parseInt(this.getText()),sum=0;
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
		return true;
	}
	
	public void checkStyle(String s) {
		this.setStyle(s);
		try {
			isLegalID();
			App.setBorderColor(this, "GREEN");

		} catch (BlankFieldException | NumberFormatException | LieException | InvalidIDException | IllegalCharacterException | NotNumberException e1) {
			App.setBorderColor(this, "RED");
		}
	}

	public void isLegalID() throws NumberFormatException, LieException, InvalidIDException, IllegalCharacterException, BlankFieldException, NotNumberException {

		super.isLegal();
		isId();
	}

}
