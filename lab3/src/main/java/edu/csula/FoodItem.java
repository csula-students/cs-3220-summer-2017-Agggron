package edu.csula;

public class FoodItem {
	public final int id;
	public final String name;
	public final String description;
	public final String imgURL;
	public final float price;

	public FoodItem (int id, String name, String description, String imgURL, float price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imgURL = imgURL;
		this.price = price;
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

	public float getPrice() {
		return price;
	}
}
