package exceptions;

public class InvalidNumberOfPlayers extends Exception {
	
	public InvalidNumberOfPlayers() {
		super("The minimum number of players must be less than or equal to the maximum.");
	}
}
