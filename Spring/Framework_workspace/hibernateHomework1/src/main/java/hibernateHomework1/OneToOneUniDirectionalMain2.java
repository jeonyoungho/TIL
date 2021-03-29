package hibernateHomework1;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneToOneUniDirectionalMain2 {
	
	private static SessionFactory sessionFactory;
	
	public static void main2(String[] args) {
		
    sessionFactory = new Configuration().configure().buildSessionFactory();
		
	Person person1 = new Person(); 
	person1.setFirstName("Alice"); 
	person1.setLastName("Lee"); 
	
	License license1 = new License();
	license1.setLicenseNumber("123456");
	license1.setIssueDate(new Date()); 
	license1.setPerson(person1);
	
	Session session = sessionFactory.openSession();
	try {
		Transaction tx = session.beginTransaction();
		
		// save objects 구현
		session.save(license1);
		
		tx.commit();
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		session.close();
		sessionFactory.close();
	}
	}
}
