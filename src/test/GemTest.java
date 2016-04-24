package test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Gem;
import models.review.Review;

public class GemTest {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;
	
	public static void main(String[] args) {
		
		buildSessionFactory();
		
		
		for (int i = 0; i < 5; i++)
			addGem(Gem.generateModel());
		
		List<Gem> gems = readGems();
		
		Gem gem = getGem(1);
		
		deleteGem(gem);
		
		gems = readGems();
		
		gem = gems.get(0);
		gem.setName("Updated Gem");
		
		updateGem(gem);
		
		gems = readGems();
		
		Review review = new Review(5, "Awesome Bro", "Sokkalingam");
		
		addReview(gems.get(0).getId(), review);
		
		gems = readGems();
		
		gem = gems.get(0);
		
		List<Review> reviews = getReviews(gem.getId());
		
		deleteReview(gem.getId(), reviews.get(0));
		
		reviews = getReviews(gem.getId());
		
		closeSessionFactory();
	}
	
	public static void addGem(Gem gem) {
		sessionStart();
		
		session.save(gem);
		
		sessionEnd();
	}
	
	public static void deleteGem(Gem gem) {
		sessionStart();
		
		session.delete(gem);
		
		sessionEnd();
	}
	
	public static void updateGem(Gem gem) {
		sessionStart();
		
		session.update(gem);
		
		sessionEnd();
	}
	
	public static Gem getGem(Integer id) {
		Gem gem = null;
		
		sessionStart();
		gem = session.get(Gem.class, id);
		sessionEnd();
		
		return gem;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Gem> readGems() {
		List<Gem> gems = new ArrayList<Gem>();
		
		sessionStart();
		
		gems = session.createCriteria(Gem.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		sessionEnd();
		
		return gems;
	}
	
	public static void addReview(Integer gemId, Review review) {
		sessionStart();
		Gem gem = session.get(Gem.class, gemId);
		gem.getGemReview().getReviews().add(review);
		session.persist(gem);
		sessionEnd();
	}
	
	public static void deleteReview(Integer gemId, Review review) {
		sessionStart();
		Gem gem = session.get(Gem.class, gemId);
		gem.getGemReview().getReviews().remove(review);
		session.persist(gem);
		sessionEnd();
	}
	
	public static List<Review> getReviews(Integer gemId) {
		return getGem(gemId).getGemReview().getReviews();
	}
	
	
	
	
	/*
	 * Session methods
	 */
	public static void sessionStart(){
		openSession();
		beginTransaction();
	}
	
	public static void sessionEnd() {
		commit();
		closeSession();
	}
	
	public static void buildSessionFactory() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static void openSession() {
		session = sessionFactory.openSession();
	}
	
	public static void beginTransaction() {
		transaction = session.beginTransaction();
	}
	
	public static void commit() {
		transaction.commit();
	}
	
	public static void closeSession() {
		session.close();
	}
	
	public static void closeSessionFactory() {
		sessionFactory.close();
	}

}
