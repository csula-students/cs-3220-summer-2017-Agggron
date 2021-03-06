public class JDBCFoodItem {
	public final int id;
	public final String name;
	public final String description;
	public final String imgURL;
	public final double price;
	public int quantity;

	public JDBCFoodItem (int id, String name, String description, String imgURL, double price) {
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
