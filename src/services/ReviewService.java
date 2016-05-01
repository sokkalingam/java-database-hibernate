package services;

import databases.ReviewDatabase;
import models.review.GemReview;
import models.review.Review;

public class ReviewService {

	private ReviewDatabase reviewDatabase = new ReviewDatabase();

	public GemReview getGemReview(Integer gemId) {
		return reviewDatabase.getGemReview(gemId);
	}

	public Review getReview(Integer gemId, Integer reviewId) {
		if (gemId == null || reviewId == null)
			return null;
		return reviewDatabase.getReview(reviewId);
	}

	public void addReview(Integer gemId, Review review) {
		if (gemId == null || review == null)
			return;
		reviewDatabase.addReview(gemId, review);
	}

	public void deleteReview(Integer gemId, Integer reviewId) {
		if (gemId == null || reviewId == null)
			return;
		reviewDatabase.deleteReview(gemId, new Review(reviewId));
	}

}
