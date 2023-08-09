package View.Nodes;


/*that datePicker desined fo person's birthDay.
 * it checks if the date chosen past by below than 18 years and if the value is null**/

import java.time.LocalDate;
import java.time.LocalTime;

import Exceptions.BlankFieldException;
import Exceptions.ToLateException;
import Exceptions.ToYoungException;
import Utils.SpecificTime;
import application.App;
import javafx.scene.control.DatePicker;

public class IndDatePicker extends DatePicker{

	public IndDatePicker() {
		super();
		this.valueProperty().addListener(e->{
			try {
				isLegal();
				App.setBorderColor(this, "GREEN");

			} catch (ToYoungException | BlankFieldException | ToLateException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		App.setBorderColor(this, "RED");
	}

	public IndDatePicker(LocalDate localDate) {
		super();
		this.valueProperty().addListener(e->{
			try {
				isLegal();
				App.setBorderColor(this, "GREEN");

			} catch (ToYoungException | BlankFieldException | ToLateException e1) {
				App.setBorderColor(this, "RED");
			}
		});
		try {
			isLegal();
			App.setBorderColor(this, "GREEN");

		} catch (ToYoungException | BlankFieldException | ToLateException e1) {
			App.setBorderColor(this, "RED");
		}
		this.setValue(localDate);
	}
	
	public void checkStyle(String s) {
		this.setStyle(s);
		try {
			isLegal();
			App.setBorderColor(this, "GREEN");

		} catch (BlankFieldException | ToLateException | ToYoungException e1) {
			App.setBorderColor(this, "RED");
		}
	}
	
	public void isLegal() throws  BlankFieldException, ToLateException, ToYoungException {
		if(this.getValue()==null) {
			throw new BlankFieldException();
		}
		
		SpecificTime toDay=new SpecificTime(LocalDate.now(),LocalTime.now());
		SpecificTime then=new SpecificTime(this.getValue(),LocalTime.now());
		if(toDay.Distance(then).getYears()<18) {
			throw new ToYoungException();
		}
	}
	
}
