package Exceptions;

public class NotNumberException extends InputException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2649918303405718593L;

	public NotNumberException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public NotNumberException() {
		super("please enter a number");
		// TODO Auto-generated constructor stub
	}

}
