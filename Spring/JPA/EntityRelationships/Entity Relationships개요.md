# Entity Relationships
- 총 4가지 타입이 존재
    - 1. @OneToOne
    - 2. @OneToMany, @ManyToOne	
    - 3. @ManyToMany

- 총 2가지 방향성이 존재
    - 1. bidirectional(양방향)
    - 2. unidirectional(단방향)

## Entity Relation 속성
1. Cascading: 연관된 개체에 같은 연산을 적용할 것인지의 여부
- CascadeType: ALL, PERSIST, MERGE, REMOVE, REFRESH
- 디폴트는 no-operation

2. Fetch: 관련된 개체를 회수하는 전략
- FetchType
    - LAZY: 연관된 개체가 필요할때 메모리에 로드
    - EAGER: 연관된 개체가 조기에 같이 로드
- 디폴트는 다음과 같음

|Mapping | Default Fetch Type|
|-----|-----|
|@OneToOne | FetchType.EAGER|
|@OneToMany | FetchType.LAZY|
|@ManyToOne | FetchType.EAGER|
|@ManyToMany | FetchType.LAZY|

- @OneToOne은 참조하는게 하나 밖에 없으므로 조기에 읽어들이는게 디폴트
- @OneToMany는 참조하는게 여러개이므로 나중에 읽어들이는게 디폴트
- @ManyToOne는 참조하는게 하나 밖에 없으므로 조기에 읽어들이는게 디폴트
- @ManyToMany는 참조하는게 여러개이므로 나중에 읽어들이는게 디폴트

- 예시<br>
![image](https://user-images.githubusercontent.com/44339530/113244808-b156e380-92f0-11eb-8d76-25cd8072f487.png)<br>
- 한 고객이 여러 주문을 할수 있기에 OneToMany
- Customer개체를 조회 할 때 연관하고 있는 Order개체도 같이 메모리상에 로드 할 것이냐 말 것인지 설정

## Entity Relationship의 구체적인 예시
### 1. OneToMany Unidirectional
- <b>foreign key를 'Many'에 속하는쪽에 두어야함</b>
    - category: parent 테이블(One-side), product: child 테이블 (Many-side)
    - 따라서 child테이블이 foreign key를 가지게됨
- @ManyToOne어노테이션을 통해 hibernate는 어떤 객체가 child객체인지 알게됨
- @OneToMany어노테이션을 통해 hibernate는 어떤 객체가 parent객체인지 알게됨
- @JoinColum어노테이션을 통해 hibernate는 join할때 사용되는 컬럼을 알게됨<br>

#### 예제
<img width="424" alt="스크린샷 2021-04-01 오후 1 57 12" src="https://user-images.githubusercontent.com/44339530/113245564-2d9df680-92f2-11eb-9234-9aa3695583ad.png"><br>

- 하나의 카테고리에 여러 개의 상품들이 존재할 수 있음
- Product쪽에서만 Category쪽을 참조하므로 Unidirectional(단방향)<br>

<img width="541" alt="스크린샷 2021-04-01 오후 1 59 02" src="https://user-images.githubusercontent.com/44339530/113245692-6b9b1a80-92f2-11eb-96bc-fda66cbad05a.png"><br>

-  @ManyToOne(cascade=CascadeType.ALL) -> Product 입장에서 봤을때 많은 상품들이 하나의 카테고리에 속하기 때문에 ManyToOne
- Category입장에서는 반대
- @JoinColumn(name="category_id") -> 나중에 foreign key로 매핑이되서 저장
- 객체 지향 에선 Reference를 가지고 매핑이 되는데 실제테이블에서 JoinColumn으로 매핑이된다
- 아래의 클라이언트 코드를 실행하면 cascading에 의해서 product를 저장할때 해당되는 category로 함께 저장이 된다.
~~~
sessionFactory = new Configuration().configure()
				.buildSessionFactory();

Category category1 = new Category("Computer");
Product product1 = new Product("Notebook",200,
				"Awesome notebook",category1);
Product product2 = new Product("Desktop", 150, 
				"Powerful PC", category1);

Category category2 = new Category("Car");
Product product2 = new Product("Sonata", 2000, 
				"Popular car",	category2);

Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

session.save(product1);
session.save(product2);
session.save(product3);

tx.commit();
session.close();
~~~

### 2. OneToMany Bidirectional
- 자바의 관점에서 두 개체가 서로 참조하고 있는 상태
- 데이터베이스의 관점에서 한 레코드가 다른 레코드를 foreign-key로 참조하고 있는 상태
- 자바의 관점에서 Many쪽에서 One쪽으로 참조할때는 레퍼런스 객체가 하나만 존재하게 됨
    - 즉, @ManyToOne은 참조하고 있는 개체가 단일 인스턴스
- 자바의 관점에서 One쪽에서 Many쪽으로 참조할때는 레퍼런스 객체가 여러개 존재하게 됨
    - 즉, @OneToMany는 참조하고 있는 개체가 복수 인스턴스
- <b>mappedBy속성값을 통해 child클래스의 parent클래스를 가리키는 레퍼런스를 지정해줘야함</b>
- <b>주어진 mappedBy값을 통해 hibernate는 외래키 컬럼을 사용하여 해당 collection을 로드하게됨</b>
- One쪽에 CascadeType과 FetchType을 명시(데이터베이스 관점에서 참조되는 입장이기에)

#### 예제
<img width="488" alt="스크린샷 2021-04-01 오후 2 18 43" src="https://user-images.githubusercontent.com/44339530/113247053-2af0d080-92f5-11eb-8197-197323ab572b.png"><br>

- Product는 Many쪽이므로 @ManyToOne이고 단일 인스턴스 타입
- Category는 One쪽이므로 @OneToMany이고 복수 인스턴스 타입
- Product쪽에서 JoinColumn을 지정
- <b>One(Category)쪽에서 @OneToMany어노테이션의 속성으로 mappedBy가 설정되야함</b>
- <b>mappedBy에 Many(Product)쪽의 JoinColum의 필드명을 설정해줘야함</b>
- 아래의 클라이언트 코드를 실행하면 cascading에 의해서 product도 함께 저장됨
~~~
sessionFactory = new Configuration().configure().buildSessionFactory();

// 1) Instantiate parent object
Category category1 = new Category("Computer"); 
// 2) Instantiate child objects and set the parent object in the child objects
Product product1 = new Product("Notebook", 200,"Awesome notebook", 
				category1);
Product product2 = new Product("Desktop", 150, "Powerful PC",category1);

// 3) Set the collection of child objects on the parent
category1.getProducts().add(product1); 
category1.getProducts().add(product2); 

Category category2 = new Category("Car");
Product product3 = new Product("Sonata", 2000, 
			"Popular car",category2);

category2.getProducts().add(product3); // category2 -> product3

Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

// 4) Save the parent
session.save(category1); 
session.save(category2);

tx.commit();
session.close();
~~~

### 3. OneToOne Unidirectional
- OneToMany와 동일하게 구현

#### 예제 
<img width="402" alt="스크린샷 2021-04-01 오후 2 37 10" src="https://user-images.githubusercontent.com/44339530/113248349-bec39c00-92f7-11eb-8b34-7b4b3a1d227d.png"><br>
<img width="496" alt="스크린샷 2021-04-01 오후 2 40 34" src="https://user-images.githubusercontent.com/44339530/113248583-37c2f380-92f8-11eb-9079-81eaa070d765.png"><br>

- optional=false: 값이 꼭 존재해야함
- unique=true: 1:1이기 때문에 unique해야만함
- 위와 같은 조건에 위배되면 hibernate에 의해 Exception이 발생함
- 아래의 클라이언트 코드를 실행하면 cascading에 의해서 person도 함께 저장됨
~~~
sessionFactory = 
	new Configuration().configure().buildSessionFactory();

Person person = new Person();
person.setFirstName("Namyun");
person.setLastName("Kim");

License license = new License();
license.setLicense_number("123456");
license.setIssue_date(new Date());

license.setPerson(person);

Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

session.save(license);

tx.commit();
session.close();
~~~

### 4. OneToOne bidirectional
- 아래의 그림과 같이 양방향이게 mappedBy속성을 지정해줘야함
- cascade를 Person쪽에 지정해줘야함<br>
<img width="424" alt="스크린샷 2021-04-01 오후 2 45 34" src="https://user-images.githubusercontent.com/44339530/113248985-eb2be800-92f8-11eb-84f6-f2509a406380.png"><br>

- 아래의 클라이언트 코드를 실행하면 cascading에 의해서 license도 함께 저장됨
~~~
sessionFactory = 
	new Configuration().configure().buildSessionFactory();

Person person = new Person();
person.setFirstName("Namyun");
person.setLastName("Kim");

License license = new License();
license.setLicense_number("123456");
license.setIssue_date(new Date());

license.setPerson(person);

person.setLicense(license);

Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

session.save(person);

tx.commit();
session.close();
~~~

### 5. ManyToMany Unidirectional


#### 예제
<img width="380" alt="스크린샷 2021-04-01 오후 2 51 13" src="https://user-images.githubusercontent.com/44339530/113249474-b53b3380-92f9-11eb-8666-ba96175d56a3.png"><br>

- 저자는 여러 책을 쓸수 있고 책은 여러 저자에 의해 쓰여질 수 있기에 ManyToMany관계
- 다음 그림과 같이 join table을 사용
    - 각각의 primary key를 가지고 있는 제3의 테이블
    - 테이블명: 테이블1_테이블2(author_book)

<img width="297" alt="스크린샷 2021-04-01 오후 2 54 31" src="https://user-images.githubusercontent.com/44339530/113249732-2b3f9a80-92fa-11eb-9eab-d7aa7b3007a5.png"><br>
<img width="482" alt="스크린샷 2021-04-01 오후 2 55 14" src="https://user-images.githubusercontent.com/44339530/113249778-45797880-92fa-11eb-91d6-15818ef75f34.png"><br>

- Book쪽에 @ManyToMany어노테이션을 사용
- @JoinTable어노테이션을 통해 join table을 명시
    - name: join table명
    - joinColumns: 참조하는 테이블의 조인 컬럼명(book_id)
    - inverseJoinColumns: 참조되는 테이블의 조인 컬럼명(author_id)
- 아래의 클라이언트 코드를 실행하면 cascading에 의해서 author도 함께 저장됨
~~~
Book book1 = new Book();
book1.setTitle("spring book");

Book book2 = new Book();
book2.setTitle("hibernate book");

Book book3 = new Book();
book3.setTitle("java book");


Author author1 = new Author();
author1.setName("alice");

Author author2 = new Author();
author2.setName("bob");

Author author3 = new Author();
author3.setName("trudy");

Set<Author> springAuthors = new HashSet<Author>();
Set<Author> hibernateAuthors = new HashSet<Author>();
Set<Author> htmlAuthors = new HashSet<Author>();

springAuthors.add(author1);
springAuthors.add(author2);
springAuthors.add(author3);

hibernateAuthors.add(author1);
hibernateAuthors.add(author2);

htmlAuthors.add(author2);
htmlAuthors.add(author3);

book1.setAuthors(springAuthors);
book2.setAuthors(hibernateAuthors);
book3.setAuthors(htmlAuthors);

sessionFactory = new Configuration().configure().
				buildSessionFactory();

Session session = sessionFactory.openSession();
Transaction tx = session.beginTransaction();

session.save(book1);
session.save(book2);
session.save(book3);

tx.commit();
session.close();
~~~

#### 참고
- https://ict-nroo.tistory.com/122
- https://velog.io/@conatuseus/%EC%97%B0%EA%B4%80%EA%B4%80%EA%B3%84-%EB%A7%A4%ED%95%91-%EA%B8%B0%EC%B4%88-1-i3k0xuve9i