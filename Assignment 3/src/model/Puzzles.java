package model;

public class Puzzles extends Toy{
	
	private String puzzleType;

	/**
	Constructor for Puzzles toy class.
	@param sn is the SerIal number
	@param name is the name of the toy
	@param brand is the brand of the toy
	@param price is how much the toy costs
	@param count is how many of the toy there is available
	@param age is the recommended age range of the toy
	@param puzzleType specifies the kind of puzzle: Mechanical, Cryptic, Logic, Trivia, or Riddle
	*/
	public Puzzles(String sn, String name, String brand, double price, int count, int age, String puzzleType) throws Exception {
        super(sn, name, brand, price, count, age);
        this.puzzleType = puzzleType;
    }
	/**
 	Displays toString of the Puzzles classes objects.
     */
	@Override
	public String toString() {
	        return "Name: "+ getName() +" Brand: "+ getBrand() +" Serial Number: "+ getSn() +" Price: $"+ getPrice() +" Available count: "+ getCount() +
	        " Age Required: "+ getAge() + " Puzzle type: "+ getPuzzleType();
	    }
	/**
 	Format for printing toy information to file.
     */
	@Override
	public String format() {
		return getSn()+";"+getName()+";"+getBrand()+";"+getPrice()+";"+getCount()+";"+getAge()+";"+getPuzzleType();
	}
	/**
 	Returns the type of the puzzle.
 	@return the puzzles type
     */
	public String getPuzzleType() {
		return puzzleType;
	}
	/**
 	Method for setting the puzzles type.
 	@param puzzleType the kind of puzzle
     */
	public void setPuzzleType(String puzzleType) {
		this.puzzleType = puzzleType;
	}

}
