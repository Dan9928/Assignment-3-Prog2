package exceptions;

public class InvalidSinLength extends Exception {
	
	public InvalidSinLength() {
		super("Sin must be 10 digits");
	}
}
