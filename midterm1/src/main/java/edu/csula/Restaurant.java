package edu.csula;

import java.util.List;
import java.util.ArrayList;

public class Restaurant {
	public final String id;
	public final String name;
	public final String url;
	public final List<Integer> designRatings;
	public final List<Integer> tasteRatings;

	public Restaurant (String id, String name, String url, List<Integer> designRatings, List<Integer> tasteRatings) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.designRatings = designRatings;
		this.tasteRatings = tasteRatings;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public List<Integer> getDesignRatings() {
		return designRatings;
	}

	public List<Integer> getTasteRatings() {
		return tasteRatings;
	}

	public float getDesignRatingsAverage() {
		if (designRatings.size() == 0) {
			return 0;
		} 
		else {
			int designRatingsSum = 0;
			for (int i = 0; i < designRatings.size(); i++) {
				designRatingsSum += designRatings.get(i);
			}
			float designRatingsAverage = designRatingsSum / designRatings.size();
			return designRatingsAverage;
		}
	}

	public float getTasteRatingsAverage() {
		if (tasteRatings.size() == 0) {
			return 0;
		} 
		else {
			int tasteRatingsSum = 0;
			for (int i = 0; i < tasteRatings.size(); i++) {
				tasteRatingsSum += tasteRatings.get(i);
			}
			float tasteRatingsAverage = tasteRatingsSum / tasteRatings.size();
			return tasteRatingsAverage;
		}
	}

	public int getNumberReviewers() {
		return designRatings.size();
	}
}
