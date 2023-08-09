package Exceptions;

public class LowPasswordException extends LegalActionException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8370537695251054830L;

	public LowPasswordException() {
		super("password level is to low!");
		// TODO Auto-generated constructor stub
	}

}
