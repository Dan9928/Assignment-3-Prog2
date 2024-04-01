package exceptions;

public class NegativeInputException extends Exception {
	
	public NegativeInputException() {
		super("The input price cannot be negative.");
	}
}
