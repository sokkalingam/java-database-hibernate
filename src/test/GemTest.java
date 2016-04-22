package test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Gem;

public class GemTest {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;
	
	public static void main(String[] args) {
		
		buildSessionFactory();
		
		addGem(Gem.generateModel());
		
		System.out.println(readGems());
		
		closeSessionFactory();
	}
	
	public static void addGem(Gem gem) {
		openSession();
		beginTransaction();
		
		session.save(gem);
		
		commit();
		closeSession();
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Gem> readGems() {
		List<Gem> gems = new ArrayList<Gem>();
		
		openSession();
		beginTransaction();
		
		gems = session.createCriteria(Gem.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		commit();
		closeSession();
		
		return gems;
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
