package testHibernate;


import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class TestMain {

	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {

		/*
		 * Configuration conf = new org.hibernate.cfg.Configuration();
		 * conf.configure("hibernate.cfg.xml");
		 * sessionFactory = conf.buildSessionFactory();
		 */
		
		Serializable id;
		
		// classpath에 존재하기에 configure()메소드 호출시 인자 없이 호출해도 상관 없음
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Product product = new Product();
		product.setName("notebook2");
		product.setPrice(2000);
		product.setDescription("Awesome notebook!!!");
		
		Product product2 = new Product();
		product2.setName("notebook1");
		product2.setPrice(20000);
		product2.setDescription("Awesome notebook2!!!");
		
		Session session = sessionFactory.openSession(); // DB와 커넥션을 위한 session 생성
		
		try {
		
			Transaction tx = session.beginTransaction(); // create transaction
			
			// id  = session.save(product); // instead of sql statement
			session.save(product); // instead of sql statement
			session.save(product2); // instead of sql statement
			
			/*
			 * Product savedProduct = session.get(Product.class, id);
			 * System.out.println(savedProduct);
			 */
			
			Query<Product> theQuery = session.createQuery("from Product order by name", Product.class); // HQL
			List<Product> products = theQuery.getResultList();
			System.out.println(products);
			
			tx.commit(); // commit transaction
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
