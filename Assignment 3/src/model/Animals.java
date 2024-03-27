package model;

public class Animals extends Toy {
	
	private String material;
	private String size;
	
	
	/**
	Constructor for Animals toy class.
	@param sn is the SerIal number
	@param name is the name of the toy
	@param brand is the brand of the toy
	@param price is how much the toy costs
	@param count is how many of the toy there is available
	@param age is the recommended age range of the toy
	@param material is what the toy is made of
	@param size is the size of the toy : Small, Medium, Large
	*/
    public Animals(String sn, String name, String brand, double price, int count, int age, String material, String size) throws Exception {
        super(sn, name, brand, price, count, age);
        this.material = material;
        this.size = size;
    }
    /**
 	Displays toString of the Animals classes objects.
     */
	@Override
	public String toString() {
	        return "Name: "+ getName() +" Brand: "+ getBrand() +" Serial Number: "+ getSn() +" Price: $"+ getPrice() +" Available count: "+ getCount() +
	        " Age Required: "+ getAge() + " Material: "+ getMaterial() +" Size: "+getSize();
	    }
	/**
 	Format for printing Animals information to file.
     */
	@Override
	public String format() {
		return getSn()+";"+getName()+";"+getBrand()+";"+getPrice()+";"+getCount()+";"+getAge()+";"+getMaterial()+";"+getSize();
	}
	/**
 	Returns the material of the Animals toy.
 	@return toy's material
     */
	public String getMaterial() {
		return material;
	}
	/**
 	Sets the material the Animals toy is made of.
 	@param material type of material the toy is made of
     */
	public void setMaterial(String material) {
		this.material = material;
	}
	/**
 	Returns the size of the animal toy: Small, Medium, Large.
 	@return animal toys size
     */
	public String getSize() {
		return size;
	}
	/**
 	Sets the toy's size.
 	@param size size of the toy
     */
	public void setSize(String size) {
		this.size = size;
	}

	
}
