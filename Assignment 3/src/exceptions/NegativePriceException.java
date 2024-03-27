package exceptions;

public class NegativePriceException extends Exception {
	
	public NegativePriceException() {
		super("The input price cannot be negative.");
	}
}
