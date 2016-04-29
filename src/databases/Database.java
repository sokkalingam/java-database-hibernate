package databases;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Gem;

public class Database {
	
	protected static SessionFactory sessionFactory;
	protected static Session session;
	protected static Transaction transaction;
	
	private static Map<Integer, Gem> gems = new ConcurrentHashMap<Integer, Gem>();
	private static List<Gem> gemsInCart = new ArrayList<Gem>();
	
	public Database() {
		buildSessionFactory();
	}

	public static Map<Integer, Gem> getGems() {
		return gems;
	}

	public static List<Gem> getGemsInCart() {
		return gemsInCart;
	}
	
	/*
	 * Session methods
	 */
	public void sessionStart(){
		openSession();
		beginTransaction();
	}
	
	public void sessionEnd() {
		commit();
		closeSession();
	}
	
	public void buildSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public void openSession() {
		session = sessionFactory.openSession();
	}
	
	public void beginTransaction() {
		transaction = session.beginTransaction();
	}
	
	public void commit() {
		transaction.commit();
	}
	
	public void closeSession() {
		session.close();
	}
	
	public void closeSessionFactory() {
		sessionFactory.close();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		Database.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		Database.session = session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		Database.transaction = transaction;
	}
}
