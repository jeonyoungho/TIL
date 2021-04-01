package testEntityRelationships;


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
		
		Category category1 = new Category();
		category1.setName("컴퓨터");
		
		Category category2 = new Category();
		category2.setName("자동차");
		
		Product product1 = new Product();
		product1.setName("notebook1");
		product1.setPrice(2000);
		product1.setDescription("Awesome notebook1!!!");
		product1.setCategory(category1);
		
		Product product2 = new Product();
		product2.setName("notebook2");
		product2.setPrice(20000);
		product2.setDescription("Awesome notebook2!!!");
		product2.setCategory(category1);
		
		Product product3 = new Product();
		product3.setName("sonata");
		product3.setPrice(30000);
		product3.setDescription("Popular Car!!!");
		product3.setCategory(category2);
		
		Session session = sessionFactory.openSession(); // DB와 커넥션을 위한 session 생성
		Transaction tx = null;
		try {
		
			tx = session.beginTransaction(); // create transaction
			
			// instead of sql statement
			session.save(product1); 
			session.save(product2); 
			session.save(product3);
			
			/*
			 * 1. product를 제거하면 cascading타입에 따라 category도 같이 제거되거나 냅둬지게됨
			 * 
			 * 2. 만약 product1,2 모두 category1을 가리키고 cascading type이 ALL일때 만약에 product1을 삭제시키게 되면
			 * product2는 없는 category를 가리키기 때문에 hibernate에 의해 exception이 발생하게됨
			 * => 해결책: product1의 category를 null로 설정해서 중간의 assocation을 끊어주면 됨
			 */ 
			product1.setCategory(null);
			session.delete(product1);  
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			tx.commit(); // commit transaction
			session.close();
			sessionFactory.close();
		}
	}

}
