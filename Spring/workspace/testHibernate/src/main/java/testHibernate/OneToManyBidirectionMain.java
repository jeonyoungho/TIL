package testHibernate;

import java.io.Serializable;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneToManyBidirectionMain {

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
		product1.setName("notebook1");
		product1.setPrice(2000);
		product1.setDescription("Awesome notebook1!!!");
		product1.setCategory(category1);
		
		category1.getProducts().add(product1); // <--
		
		Product product2 = new Product();
		product2.setName("notebook2");
		product2.setPrice(20000);
		product2.setDescription("Awesome notebook2!!!");
		product2.setCategory(category1);
		
		category1.getProducts().add(product2); // <--
		
		Product product3 = new Product();
		product3.setName("sonata");
		product3.setPrice(20000);
		product3.setDescription("Popular Car!!!");
		product3.setCategory(category2);
		
		category2.getProducts().add(product3); // <--
		
		Session session = sessionFactory.openSession();
		Serializable catId1 = 0;
		try {
			Transaction tx = session.beginTransaction();
			
			catId1 = session.save(category1); //category 저장 후 그와 연관된 product들도 저장하게 되있음
			session.save(category2);
			
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
		}
		
		
		Session session1 = sessionFactory.openSession();
		Transaction tx1 = session1.beginTransaction();
		
		Category aCategory = session1.get(Category.class, catId1);
		//FetchType이 Lazy이기에 category를 읽을때 이 category에 해당하는 products를 읽어들이지 않는다
		
		
		Set<Product> products = aCategory.getProducts();
		// 실제로 접근할때 그때 가져온다
		
		for(Product p: products)
			System.out.println(p.getName());
		
		tx1.commit();
		session1.close();
		
		sessionFactory.close();
		
	}

}
