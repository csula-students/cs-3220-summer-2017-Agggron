package jaxrs.models;

public class FoodItem {
	public final int id;
	public final String name;
	public final String description;
	public final String imgURL;
	public final double price;
	public int quantity;

	// solo constructor (without arguments) needed for the JAXRS POST request, which converts JSON into a FoodItem object (presumably it has to use a solo constructor?)
	public FoodItem() {
		this.id = 0;
		this.name = "";
		this.description = "";
		this.imgURL = "";
		this.price = 0;
		this.quantity = 1;
	}
	public FoodItem (int id, String name, String description, String imgURL, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imgURL = imgURL;
		this.price = price;
		this.quantity = 1;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImgURL() {
		return imgURL;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
