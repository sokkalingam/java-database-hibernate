package services;

import java.util.Map;

import models.review.GemReview;
import models.review.Review;

public class ReviewService {

	private GemService gemService = new GemService();
	private static Integer reviewCounter = 0;

	public GemReview getGemReview(Integer gemId) {
		if (gemService.isGemPresent(gemId)) {
			return gemService.getGem(gemId).getGemReview();
		}
		return null;
	}

	public Review getReview(Integer gemId, Integer reviewId) {
		if (gemId == null || reviewId == null)
			return null;
		Map<Integer, Review> reviewMap = getReviewMap(gemId);
		if (reviewMap == null)
			return null;
		return reviewMap.get(reviewId);
	}

	public Review addReview(Integer gemId, Review review) {
		if (gemId == null || review == null)
			return null;
		Map<Integer, Review> reviewMap = getReviewMap(gemId);
		if (reviewMap == null)
			return null;
		review.setId(reviewCounter++);
		reviewMap.put(review.getId(), review);
		return review;
	}

	public Review updateReview(Integer gemId, Integer reviewId, Review review) {
		if (gemId == null || reviewId == null || review == null)
			return null;
		Map<Integer, Review> reviewMap = getReviewMap(gemId);
		if (reviewMap != null && reviewMap.containsKey(reviewId)) {
			review.setId(reviewId);
			reviewMap.put(reviewId, review);
			return review;
		}
		return null;
	}

	public Review deleteReview(Integer gemId, Integer reviewId) {
		if (gemId == null || reviewId == null)
			return null;
		Map<Integer, Review> reviewMap = getReviewMap(gemId);
		if (reviewMap == null)
			return null;
		return reviewMap.remove(reviewId);
	}

	public Map<Integer, Review> getReviewMap(Integer gemId) {
		if (gemService.isGemPresent(gemId))
			return gemService.getGem(gemId).getGemReview().getReviewMap();
		return null;
	}

}
