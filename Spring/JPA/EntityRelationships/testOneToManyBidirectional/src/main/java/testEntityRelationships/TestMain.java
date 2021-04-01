package testEntityRelationships;


import java.io.Serializable;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestMain {

	private static SessionFactory sessionFactory;
	
	public static void main(String[] args) {

		/*
		 * Configuration conf = new org.hibernate.cfg.Configuration();
		 * conf.configure("hibernate.cfg.xml");
		 * sessionFactory = conf.buildSessionFactory();
		 */
		
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
		
		category1.getProducts().add(product1);
		
		Product product2 = new Product();
		product2.setName("notebook2");
		product2.setPrice(20000);
		product2.setDescription("Awesome notebook2!!!");
		product2.setCategory(category1);
		
		category1.getProducts().add(product2);
		
		Product product3 = new Product();
		product3.setName("sonata");
		product3.setPrice(30000);
		product3.setDescription("Popular Car!!!");
		product3.setCategory(category2);
		
		category2.getProducts().add(product3);
		
		Session session = sessionFactory.openSession(); // DB와 커넥션을 위한 session 생성
		Transaction tx = null;
		Serializable catId1 = null;
		try {
		
			tx = session.beginTransaction(); // create transaction
			
			// parent인 category를 먼저 저장 후 child인 product저장
			// DB의 외래키 참조하는 값이 없는데 insert 수행시 오류나는 것과 연관시켜 위와 같은 수행순서를 생각하면 됨
			
			catId1 = session.save(category1);
			session.save(category2);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			tx.commit(); // commit transaction
			session.close();
		}
		
		
		// 디버깅을 통한 FetchType비교
		Session session1 = sessionFactory.openSession();
		Transaction tx1 = session1.beginTransaction();
		
		// category1을 조회했는데 products의 fetchType을 Lazy라면 products를 읽어들이지 않는다.
		Category aCategory = session1.get(Category.class, catId1);
		Set<Product> products = aCategory.getProducts();
		
		// products의 fetchType이 Lazy라면 products에 접근하는 아래코드에서 읽어들인다.
		for(Product p: products) {
			System.out.println(p.getName());
		}
		
		tx1.commit();
		session1.close();
		
		sessionFactory.close();
	}

}
