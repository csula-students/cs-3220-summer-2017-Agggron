public class Card {
	public final int id;
	public final String suit;
	public final int value;
	public final String imgURL;

	public Card (int id, String suit, int value, String imgURL) {
		this.id = id;
		this.suit = suit;
		this.value = value;
		this.imgURL = imgURL;
	}

	public int getId() {
		return id;
	}

	public String getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	public String getImgURL() {
		return imgURL;
	}
}