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
		session = sessionFactory.openSession();
		
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
		
		session.close();
		sessionFactory.close();
		
	}
	
	public static Integer addUser(UserDetails user) {
		Integer empId = null;
		transaction = session.beginTransaction();
		empId = (Integer) session.save(user);
		transaction.commit();
		
		return empId;
	}
	
	public static void deleteUser(UserDetails user) {
		transaction = session.beginTransaction();
		session.delete(user);
		transaction.commit();
	}
	
	public static void updateUser(UserDetails user) {
		transaction = session.beginTransaction();
		session.update(user);
		transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	public static List<UserDetails> readAll() {
		List<UserDetails> result = new ArrayList<UserDetails>();
		result = session.createCriteria(UserDetails.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return result;
	}
}
