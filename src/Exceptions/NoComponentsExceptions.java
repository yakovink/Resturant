package Exceptions;

import Model.Dish;

public class NoComponentsExceptions extends LegalActionException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6640146900888630413L;

	public NoComponentsExceptions(Dish dish) {
		super("The dish "+ dish + " is not include components!");
		
	}
	
}
