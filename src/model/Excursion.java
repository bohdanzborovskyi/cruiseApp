package model;

/**
 *  Entity class for excursion.
 */

public class Excursion {

	private String excursionID;
	private String city;
	private String description;
	private int price;
	private int count;
	
	public Excursion(String excursionID, String city, String description, int price, int count) {
		super();
		this.excursionID = excursionID;
		this.city = city;
		this.description = description;
		this.price = price;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Excursion() 
	{
		
	}

	public Excursion(String excursionID, String city, String description, int price) {
		super();
		this.excursionID = excursionID;
		this.city = city;
		this.description = description;
		this.price = price;
	}

	public String getExcursionID() {
		return excursionID;
	}

	public String getCity() {
		return city;
	}

	public String getDescription() {
		return description;
	}

	public int getPrice() {
		return price;
	}

	public void setExcursionID(String excursionID) {
		this.excursionID = excursionID;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
