package Exceptions;

public class CommandWasNotFoundException extends InternalErrorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2409770022670448289L;

	public CommandWasNotFoundException(String cm) {
		super("Command "+cm+" was not found!");
		// TODO Auto-generated constructor stub
	}

}
