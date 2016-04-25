package test;

import resources.GemResource;

public class GemTest {
	
	public static void main(String[] args) {
		
//		GemDatabase gemDatabase = new GemDatabase();
//		
//		for (int i = 0 ; i < 10; i ++)
//			gemDatabase.addGem(Gem.generateModel());
//		
//		List<Gem> gems = gemDatabase.readGems();
//		
//		Gem gem = gems.get(0);
//		
//		gem.setName("Boom Man");
//		
//		gemDatabase.updateGem(gem);
//		
//		gems = gemDatabase.readGems();
//		
//		ReviewDatabase reviewDatabase = new ReviewDatabase();
//		
//		reviewDatabase.addReview(gem.getId(), new Review(5, "Awesome Review", "Sokka"));
//		reviewDatabase.addReview(gem.getId(), new Review(1, "Bad Review", "Jon"));
//		
//		gems = gemDatabase.readGems();
//		List<Review> reviews = reviewDatabase.getReviews(gem.getId());
//		
//		Review review = reviews.get(0);
//		
//		reviewDatabase.deleteReview(gem.getId(), review);
//		
//		reviews = reviewDatabase.getReviews(gem.getId());
//		
//		gems = gemDatabase.readGems();
//		
//		gemDatabase.closeSessionFactory();
		
		new GemResource().populate(10, 2);
	}
}
