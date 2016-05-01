package databases;

import java.util.List;

import models.Gem;
import models.review.GemReview;
import models.review.Review;

public class ReviewDatabase extends Database {
	
	private GemDatabase gemDatabase = new GemDatabase();

	public void addReview(Integer gemId, Review review) {
		sessionStart();
		Gem gem = session.get(Gem.class, gemId);
		gem.getGemReview().getReviews().add(review);
		session.persist(gem);
		sessionEnd();
	}

	public void deleteReview(Integer gemId, Review review) {
		sessionStart();
		Gem gem = session.get(Gem.class, gemId);
		gem.getGemReview().getReviews().remove(review);
		session.persist(gem);
		sessionEnd();
	}
	
	public Review getReview(Integer reviewId) {
		Review review = null;
		sessionStart();
		review = session.get(Review.class, reviewId);
		sessionEnd();
		return review;
	}
	
	public GemReview getGemReview(Integer gemId) {
		Gem gem = gemDatabase.getGem(gemId);
		if (gem != null)
			return gemDatabase.getGem(gemId).getGemReview();
		return null;
	}

	public List<Review> getReviews(Integer gemId) {
		Gem gem = gemDatabase.getGem(gemId);
		if (gem != null)
			return gemDatabase.getGem(gemId).getGemReview().getReviews();
		return null;
	}

}
