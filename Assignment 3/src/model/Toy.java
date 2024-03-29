package model;

public class Toy {

	private String sn;
	private String name;
	private String brand;
	private double price;
	private int count;
	private int age;
    
	/**
	Empty constructor for Toy class.
	*/
    public Toy() {
    	
    }
    /**
	Constructor for Toy class.
	@param sn is the SerIal number
	@param name is the name of the toy
	@param brand is the brand of the toy
	@param price is how much the toy costs
	@param count is how many of the toy there is available
	@param age is the recommended age range of the toy
	*/
    public Toy(String sn, String name, String brand, double price, int count, int age) throws Exception {
		this.sn = sn;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.count = count;
		this.age = age;
	}
    
    /**
 	Returns Serial number of toy.
    @return serial number
     */
    public String getSn() {
		return sn;
	}
    /**
 	Sets the Serial number of.
	@param sn Serial number of toy
     */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
 	Returns name of toy.
    @return toy's name
     */
	public String getName() {
		return name;
	}
	/**
 	Sets the toy's name.
 	@param name name of toy
     */
	public void setName(String name) {
		this.name = name;
	}
	/**
 	Returns Serial number of toy.
    @return serial number
     */
	public String getBrand() {
		return brand;
	}
	/**
 	Set the toy's brand.
 	@param brand of toy
     */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
 	Returns price of toy.
    @return toy's price
     */
	public double getPrice() {
		return price;
	}
	/**
 	Sets price of toy.
 	@param price cost of toy
     */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
 	Returns available count of toy.
    @return number of toy available
     */
	public int getCount() {
		return count;
	}
	/**
 	Sets the available count for a toy.
 	@param count how much is available of the toy
     */
	public void setCount(int count) {
		this.count = count;
	}
	/**
 	Returns the recommended age group for a toy.
    @return Recommended age
     */
	public int getAge() {
		return age;
	}
	/**
 	Sets recommended age group for a toy.
 	@param age recommended age of toy
     */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
 	Displays toString of the toy classes objects.
     */
	public String toString() {
        return "Name: "+ getName() +" Brand: "+ getBrand() +" Serial Number: "+ getSn() +" Price: $"+ getPrice() +" Available count: "+ getCount() +
        " Age Required: "+ getAge();
	}
	/**
 	Format for printing toy information to file.
     */
	public String format() {
		return sn+";"+name+";"+brand+";"+price+";"+count+";"+age;
	}
	
}
