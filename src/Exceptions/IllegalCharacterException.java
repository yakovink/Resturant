package Exceptions;



public class IllegalCharacterException extends InputException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalCharacterException(String nodeName) {
		super(nodeName+" contains one or more from the characters: ' ' / - ! , $  \\  %");

	}


}
