package Exceptions;

public class ConvertToExpressException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2972449867456861678L;

	public ConvertToExpressException() {
		super("This regular delivery contain one order, please replace it to express delivery");
	}
	
}
