package Exceptions;

public class IllegalCustomerException extends LegalActionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6549874382824009736L;

	public IllegalCustomerException() {
		super("The restaurant is in conflict with this customer so this customer does not will order a new order!");
	}

	
}
