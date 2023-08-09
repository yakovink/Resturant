package Exceptions;


public class SensitiveException extends LegalActionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2068869081318301519L;

	public SensitiveException(String customerName, String dishName) {
		super("Customer " + customerName + " is sensitive to one of the components in the dish " + dishName + "!");
		
	}
	
}
