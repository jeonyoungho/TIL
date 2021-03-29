package testHibernate;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OnetoManyUnidirectionMain {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {

//		Configuration conf = new Configuration();
//		conf.configure("hibernate.cfg.xml");
//		sessionFactory = conf.buildSessionFactory();
		
		Serializable id;
		sessionFactory = new Configuration().configure().buildSessionFactory();

		Category category1 = new Category();
		category1.setName("컴퓨터");
		
		Category category2 = new Category();
		category2.setName("자동차");
		
		Product product1 = new Product();
		product1.setName("notebook2");
		product1.setPrice(2000);
		product1.setDescription("Awesome notebook1!!!");
		product1.setCategory(category1);
		
		Product product2 = new Product();
		product2.setName("notebook1");
		product2.setPrice(20000);
		product2.setDescription("Awesome notebook2!!!");
		product2.setCategory(category1);
		
		Product product3 = new Product();
		product3.setName("sonata");
		product3.setPrice(20000);
		product3.setDescription("Popular Car!!!");
		product3.setCategory(category2);
		
		Session session = sessionFactory.openSession();

		try {
			Transaction tx = session.beginTransaction();
			
			session.save(product1); // instead of sql statement
			session.save(product2);
			session.save(product3);
			
			product1.setCategory(null);
			session.delete(product1); // product1 -> category1, product2 -> category1
			//product1제거시 category1도 제거가 된다
			//그렇게 되면 product2는 존재하지 않는 레코드를 가리키게 되므로 Exception이 발생한다
			//따라서 이와 같은 경우에는 product1만 제거하고 category1은 제거하지 않는게 바람직하다
			//product1.setCategory(null); 이렇게 하여 product1과 category1의 연결을 먼저 끊어주고 제거하는게 바람직하다
			
			/*
			 * Product savedProduct = session.get(Product.class, id);
			 * System.out.println("저장된 product -> " +savedProduct);
			 */
			
			/*
			 * Query<Product> theQuery =
			 * session.createQuery("from Product order by name",Product.class); // HQL
			 * List<Product> products = theQuery.getResultList();
			 * System.out.println(products);
			 */
			 
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			session.close();
			sessionFactory.close();
		}
		
		
	}

}
