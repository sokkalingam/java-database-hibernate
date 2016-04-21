package dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		
		for (int i = 0; i < 3; i++)
			addUser(UserDetails.generate());
		
		List<UserDetails> list = readAll();
		System.out.println(list);
		System.out.println(list.size());
		
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
