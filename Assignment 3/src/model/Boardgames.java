package model;

public class Boardgames extends Toy{
	
	private String numOfPlayers;
	private String designer;

	/**
	Constructor for Boardgames toy class.
	@param sn is the SerIal number
	@param name is the name of the toy
	@param brand is the brand of the toy
	@param price is how much the toy costs
	@param count is how many of the toy there is available
	@param age is the recommended age range of the toy
	@param numOfPlayers is the number of players that can play
	@param designer is the designers of the board game
	*/
	public Boardgames(String sn, String name, String brand, double price, int count, int age, String numOfPlayers, String designer) throws Exception {
        super(sn, name, brand, price, count, age);
        this.numOfPlayers = numOfPlayers;
        this.designer = designer;
    }
	/**
 	Displays toString of the Boardgames classes objects.
     */
	@Override
	public String toString() {
	        return "Name: "+ getName() +" Brand: "+ getBrand() +" Serial Number: "+ getSn() +" Price: $"+ getPrice() +" Available count: "+ getCount() +
	        " Age Required: "+ getAge() + " Number of Players: "+ getNumOfPlayers() +" Designer: "+getDesigner();
	    }
	/**
 	Format for printing Boardgames information to file.
     */
	@Override
	public String format() {
		return getSn()+";"+getName()+";"+getBrand()+";"+getPrice()+";"+getCount()+";"+getAge()+";"+getNumOfPlayers()+";"+getDesigner();
	}
	/**
 	Returns the number of players that can play the board game.
 	@return number of players
     */
	public String getNumOfPlayers() {
		return numOfPlayers;
	}
	/**
 	Sets the number of players that can play the board game.
 	@param numOfPlayers number of players who can play
     */
	public void setNumOfPlayers(String numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	/**
 	Returns the designers of the board game.
 	@return board game designers
     */
	public String getDesigner() {
		return designer;
	}
	/**
 	Sets the designers of the board game.
 	@param designer designers of the game
     */
	public void setDesigner(String designer) {
		this.designer = designer;
	}

}
