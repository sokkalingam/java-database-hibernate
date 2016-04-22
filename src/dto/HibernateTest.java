package dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;

	public static void main(String[] args) {
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		// add
		for (int i = 0; i < 3; i++)
			addUser(UserDetails.generate());
		
		List<UserDetails> list = readAll();
		System.out.println(list);
		System.out.println(list.size());
		
		
		// delete
		deleteUser(list.get(0));
		list = readAll();
		System.out.println(list);
		System.out.println(list.size());
		
		// update
		UserDetails usd = list.get(0);
		usd.setListOfString(Arrays.asList("BlackMamba", "KobeBryant"));
		usd.setUserName("Sokkalingam");
		updateUser(usd);
		list = readAll();
		System.out.println(list);
		System.out.println(list.size());
		
		sessionFactory.close();
		
	}
	
	
	public static Integer addUser(UserDetails user) {
		session = sessionFactory.openSession();
		Integer empId = null;
		transaction = session.beginTransaction();
		empId = (Integer) session.save(user);
		transaction.commit();
		session.close();
		return empId;
	}
	
	public static void deleteUser(UserDetails user) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
		session.close();
	}
	
	public static void updateUser(UserDetails user) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public static List<UserDetails> readAll() {
		List<UserDetails> result = new ArrayList<UserDetails>();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		result = session.createCriteria(UserDetails.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		session.close();
		return result;
	}
}
