package Exceptions;

public class ToYoungException extends LegalActionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1116331410112756636L;

	public ToYoungException() {
		super("This customer is to young and cannot work here or get service from us without his parents!");
		// TODO Auto-generated constructor stub
	}

}
