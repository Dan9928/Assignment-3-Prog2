package exceptions;

public class MissingInput extends Exception {
	public MissingInput() {
		super("Some inputs are missing");
	}
}
