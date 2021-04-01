# HibernateWithSpring개요

## spring과 hibernate 연동을 위한 4가지 단계
#### 1. spring과 hibernate 연동을 위한 메이븐 디펜던시 설정(pom.xml)
~~~
<!– Spring에서 ORM을 지원하기 위해 디펜던시 -->
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-orm</artifactId>
  <version>${org.springframework-version}</version>
</dependency>

<!– Hibernate ->
<dependency>
  <groupId>org.hibernate</groupId>
  <artifactId>hibernate-core</artifactId>
  <version>5.4.10.Final</version>
</dependency>
~~~

#### 2. Hibernate 설정(dao-context.xml)
- hibernate 프레임워크에서 hibernate.cfg.xml파일을 통해 설정한다.
- <b>Spring과 hibernate를 통합한다면 hibernate.cfg.xml파일 대신 dao-context.xml을 통해 설정하면 된다.</b>
- session factory와 같은 Spring Bean 설정
- dao-context.xml 내용
    - 1. DataSource bean 설정 
    ~~~
    <context:property-placeholder location="/WEB-INF/props/jdbc.properties" />

    <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource"
            destroy-method="close">
    <property name="driverClassName" value="${jdbc.driverClassName}" />
    <property name="url" value="${jdbc.url}" />
    <property name="username" value="${jdbc.username}" />
    <property name="password" value="${jdbc.password}" />
    </bean>
    ~~~
    - 2. SessionFactory bean 설정  
    ~~~ 
    <bean id="sessionFactory"
        class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
    <property name="packagesToScan">
        <list>
        <!-- scan할 패키지 지정, @Entity라는 어노테이션을 쭉 스캔해서 해당 모델을 바탕으로 데이터베이스 테이블을 생성 --> 
        <value>kr.ac.hansung.cse.model</value>
            
        </list>
    </property>

    <property name="hibernateProperties">
        <props>
        <prop key="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</prop>
        <prop key="hibernate.hbm2ddl.auto">create</prop>
        <prop key="hibernate.show_sql">true</prop>
        <!-- sql fomatting 여부 -->
        <prop key="hibernate.format_sql">false</prop>
        </props>
    </property>
    </bean>
    ~~~
    - 3. TransactionManager bean 설정 (AOP를 기술을 활용해서 @Transactional 어노테이션 활성화)
    ~~~
    <tx:annotation-driven transaction-manager="transactionManager" />

        <bean id="transactionManager"
            class="org.springframework.orm.hibernate5.HibernateTransactionManager">
            <property name="sessionFactory" ref="sessionFactory"></property>
        </bean>
    ~~~
    - 4. DAO bean 설정
    ~~~
    <context:annotation-config></context:annotation-config>

    <context:component-scan base-package="kr.ac.hansung.cse.dao">
    </context:component-scan>
    ~~~

#### 3. table로 저장되는 Entity Bean (with annotation) 설정
<img width="320" alt="스크린샷 2021-04-01 오후 5 02 35" src="https://user-images.githubusercontent.com/44339530/113262868-0f44f480-930c-11eb-8a27-c0c96c746b44.png"><br>

- JPA Annotation
    - @Entity(javax.persistence.Entity): hibernate에 의해서 영속성을 갖는 entity bean이 됨
    - @Table(javax.peristence.Table): table 매핑을 정의하는데 사용되고 table명, 카테고리, 스키마도 지정해줄 수 있음
    - @Id(javax.persistence.Id): Primary key로 사용될 필드
    - @GeneratedValue(javax.persistence.GeneratedValue): primary key를 생성하는 전략을 나타냄(AUTO-default, IDENTITY, SEQUENCE, TABLE)
    - <b>MySQL 데이터베이스에서 @GeneratedValue(strategy = GenerationType.AUTO)일 경우에 Hibernate4와 5는 다음과 같은 차이가 있다.</b>
        - Hibernate4: GenerationType.IDENTITY(autoincremented 컬럼을 사용하게됨, 자체적으로 1씩 증가)
        - Hibernate5: GenerationType.TABLE(추가적으로 hibernate sequence테이블을 유지하는데 여기서 primary key를 생성하기 위한 정보를 저장, Next_val이라는 컬럼으로 다음에 할당할 번호를 레코드로 저장하고 sequence번호 자체를 테이블 간에 서로 공유)
    - @Column(javax.persistence.Column): 테이블 컬럼과 필드를 매핑(컬럼 길이 또는 Null허용여부, Unique여부를 지정 가능)
    - @Transient: 데이터베이스에 저장하고 싶지 않은 컬럼 지정

- Entity bean 예시
~~~
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="product_id")
    private int id;

    private String name;
    private String category;
    private double price;
    private String manufacturer;
    private int unitInStock;
    private String description;

    // 이미지는 별도로 DB에 저장하지 않고 별도의 디렉토리에 저장하기에 @Transient 어노테이션 등록
    @Transient
    private MultipartFile productImage;
    private String imageFilename;
}
~~~

#### 4. DAO layer 구현

~~~
@Repository
@Transactional
public class ProductDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    public Product getProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
	    Product product = (Product) session.get(Product.class, id);

        return product;
    }

    public List<Product> getProducts() {
	Session session = sessionFactory.getCurrentSession();
	String hql = ”from Product";

       Query<Product> query = session.createQuery(hql, Product.class);
       List<Product> productList =  query.getResultList();

        return productList;
    }

    public void addProduct(Product product) {
            Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(product);
            session.flush();
    }
    
    public void deleteProduct (Product product) {
            Session session = sessionFactory.getCurrentSession();
            session.delete(product);
            session.flush();
    }

    public void editProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(product);
            session.flush();
    }
}
~~~
