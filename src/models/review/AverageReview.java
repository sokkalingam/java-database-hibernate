package models.review;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AverageReview {

	private Double averageReview = 0.00;
	private Map<Integer, Integer> starsToUsersMap = new HashMap<Integer, Integer>();

	@Override
	public String toString() {
		return "AverageReview [averageReview=" + averageReview + ", starsToUsersMap=" + starsToUsersMap + "]";
	}

	public Double getAverageReview() {
		return averageReview;
	}

	public void setAverageReview(Double averageReview) {
		this.averageReview = averageReview;
	}

	public Map<Integer, Integer> getStarsToUsersMap() {
		return starsToUsersMap;
	}

	public void setStarsToUsersMap(Map<Integer, Integer> starsToUsersMap) {
		this.starsToUsersMap = starsToUsersMap;
	}

	public static AverageReview getAverageReview(List<Review> reviews) {

		if (reviews == null || reviews.size() == 0)
			return new AverageReview();

		AverageReview averageReview = new AverageReview();
		Map<Integer, Integer> starsToUsersMap = new HashMap<Integer, Integer>();

		Integer sumOfStars = 0;

		for (Review review : reviews) {

			Integer stars = review.getStars();
			sumOfStars += stars;

			if (starsToUsersMap.containsKey(stars))
				starsToUsersMap.put(stars, starsToUsersMap.get(stars) + 1);
			else
				starsToUsersMap.put(stars, 1);

		}

		Double avgReview = ((double) sumOfStars / (double) reviews.size());
		averageReview.setAverageReview(Double.valueOf(new DecimalFormat("#.##").format(avgReview)));
		averageReview.setStarsToUsersMap(starsToUsersMap);

		return averageReview;
	}

}
