public class Dice {
	public final int id;
	public final int value;
	public final String imgURL;

	public Dice (int id, int value, String imgURL) {
		this.id = id;
		this.value = value;
		this.imgURL = imgURL;
	}

	public int getId() {
		return id;
	}
	
	public int getValue() {
		return value;
	}

	public String getImgURL() {
		return imgURL;
	}
}