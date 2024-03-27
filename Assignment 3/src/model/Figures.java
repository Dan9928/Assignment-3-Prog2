package model;

public class Figures extends Toy{
    
	private String figclass;

	/**
	Constructor for Figures toy class.
	@param sn is the SerIal number
	@param name is the name of the toy
	@param brand is the brand of the toy
	@param price is how much the toy costs
	@param count is how many of the toy there is available
	@param age is the recommended age range of the toy
	@param figclass is the classification of the figure
	*/
	public Figures(String sn, String name, String brand, double price, int count, int age, String figclass) throws Exception {
        super(sn, name, brand, price, count, age);
        this.figclass = figclass;
	}
	/**
 	Displays toString of the Figures classes objects.
     */
	@Override
	public String toString() {
	        return "Name: "+ getName() +" Brand: "+ getBrand() +" Serial Number: "+ getSn() +" Price: $"+ getPrice() +" Available count: "+ getCount() +
	        " Age Required: "+ getAge() + " Figure Classification: "+ getFigclass();
	    }
	/**
 	Format for printing Figures information to file.
     */
	@Override
	public String format() {
		return getSn()+";"+getName()+";"+getBrand()+";"+getPrice()+";"+getCount()+";"+getAge()+";"+getFigclass();
	}
	/**
 	Returns the figures classification.
 	@return figures classification
     */
	public String getFigclass() {
		return figclass;
	}
	/**
 	Method for setting the figures class.
 	@param figclass the figures classification
     */
	public void setFigclass(String figclass) {
		this.figclass = figclass;
	}

}
