# MultipleBagFetchException

- JPA의 N+1문제에 대한 해결책으로 Fetch Join을 사용하다보면 자주 만나는 문제가 있다. 바로 MultipleBagFetchException이다.
- 이 문제는 <b>2개 이상의 OneToMany 자식 테이블에 Fetch Join을 선언햇을때</b> 발생한다.
    - OneToOne, ManyToOne과 같이 단일 관계의 자식 테이블에는 Fetch Join을 써도 된다.
- 이 문제에 대한 해결책으로는 보통 2가지를 언급한다.
    - 자식 테이블 하나에만 Fetch Join을 걸고 나머진 Lazy Loading
    - 모든 자식 테이블을 다 Lazy Loading으로
- 이럴 경우 성능상 이슈가 아무래도 해결되는게 아니므로 아래와 같은 방법을 사용하는게 좋다.

### 문제 상항
- OneToMany 관계의 엔티티들이 있다.
- Store.java
~~~
package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Store {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addProduct(Product product) {
        this.products.add(product);
        product.updateStore(this);
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.updateStore(this);
    }

}
~~~

- Product.java
~~~
package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private long price;

    @ManyToOne
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_STORE"))
    private Store store;

    public Product(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public void updateStore(Store store) {
        this.store = store;
    }
}

~~~

- Employee.java
~~~
package com.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(name = "store_id", foreignKey = @ForeignKey(name = "FK_EMPLOYEE_STORE"))
    private Store store;

    public Employee(String name, LocalDate hireDate) {
        this.name = name;
        this.hireDate = hireDate;
    }

    public void updateStore(Store store) {
        this.store = store;
    }

}
~~~

- StoreRepository.java
~~~
package com.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
~~~

- StoreService.java
~~~
package com.example.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    // Lazy Loading 발동을 위해 get필드 사용
    @Transactional(readOnly = true)
    public long find() {
        System.out.println("========================");
        List<Store> stores = storeRepository.findAll();

        long totalPrice = 0;
        for(Store store:stores) {
            List<Product> products = store.getProducts();
            for(Product product:products) {
                totalPrice += product.getPrice();
            }

            List<Employee> employees = store.getEmployees();
            for(Employee employee:employees) {
                String name = employee.getName();
                System.out.println("employee: " + name);
            }
        }

        System.out.println("========================");
        return totalPrice;
    }

}
~~~

- find메소드에서 Store 엔티티의 자식들(Product/Employee)을 모두 가져와야한다.
- 기능은 단순하다.
    - 1)전체 Store를 가져온다
    - 2)각 Store의 Product와 Employee를 가져와 전체 상품의 가격을 계산하고 직원들의 이름을 모두 출력한다
- 테스트 코드는 아래와 같다.(StoreServiceTest.java)
~~~
package com.example.domain;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class StoreServiceTest {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreService storeService;

    @BeforeEach
    void setUp() {
        Store store1 = new Store("서점1", "서울시 강남구");
        store1.addProduct(new Product("책1_1", 10000L));
        store1.addProduct(new Product("책1_2", 20000L));
        store1.addEmployee(new Employee("직원1_1", LocalDate.now()));
        store1.addEmployee(new Employee("직원1_2", LocalDate.now()));
        storeRepository.save(store1);

        Store store2 = new Store("서점2", "서울시 강남구");
        store2.addProduct(new Product("책2_1", 10000L));
        store2.addProduct(new Product("책2_2", 20000L));
        store2.addEmployee(new Employee("직원2_1", LocalDate.now()));
        store2.addEmployee(new Employee("직원2_2", LocalDate.now()));
        storeRepository.save(store2);
    }

    @AfterEach
    void tearDown() {
        storeRepository.deleteAll();
    }

    @Test
    public void NO_Repository_의_BatchSize () throws Exception {
        long size = storeService.find();
        assertThat(size, is(60000L));
    }
}
~~~

- 위의 테스트 코드를 실행해서 발생한 쿼리들을 보면 아래의 그림과 같다.
~~~
Hibernate: 
    select
        store0_.id as id1_2_,
        store0_.address as address2_2_,
        store0_.name as name3_2_ 
    from
        store store0_ # 1) Store 전체 쿼리

Hibernate: 
    select
        products0_.store_id as store_id4_1_0_,
        products0_.id as id1_1_0_,
        products0_.id as id1_1_1_,
        products0_.name as name2_1_1_,
        products0_.price as price3_1_1_,
        products0_.store_id as store_id4_1_1_ 
    from
        product products0_ 
    where
        products0_.store_id=? # 2) Store 1번의 Product 자식들 조회 쿼리

Hibernate: 
    select
        employees0_.store_id as store_id4_0_0_,
        employees0_.id as id1_0_0_,
        employees0_.id as id1_0_1_,
        employees0_.hire_date as hire_dat2_0_1_,
        employees0_.name as name3_0_1_,
        employees0_.store_id as store_id4_0_1_ 
    from
        employee employees0_ 
    where
        employees0_.store_id=? # 3) Store 1번의 Employee 자식들 조회 쿼리

employee: 직원1_1
employee: 직원1_2

Hibernate: 
    select
        products0_.store_id as store_id4_1_0_,
        products0_.id as id1_1_0_,
        products0_.id as id1_1_1_,
        products0_.name as name2_1_1_,
        products0_.price as price3_1_1_,
        products0_.store_id as store_id4_1_1_ 
    from
        product products0_ 
    where
        products0_.store_id=? # 4) Store 2번의 Product 자식들 조회 쿼리

Hibernate: 
    select
        employees0_.store_id as store_id4_0_0_,
        employees0_.id as id1_0_0_,
        employees0_.id as id1_0_1_,
        employees0_.hire_date as hire_dat2_0_1_,
        employees0_.name as name3_0_1_,
        employees0_.store_id as store_id4_0_1_ 
    from
        employee employees0_ # 5) Store 2번의 Employee 자식들 조회 쿼리
    where
        employees0_.store_id=?
~~~

- 총 <b>5번의 쿼리가 수행되었다.<b>
    - Store 조회 쿼리 실행(id 1,2인 엔티티 반환) - 1번 수행
    - 1번 Store의 Product, Employee 조회 각각 발생 - 2번, 3번 수행
    - 2번 Store의 Product, Employee 조회 각각 발생 - 4번, 5번 수행
- <b>조회된 부모의 수만큼 자식 테이블의 쿼리가 추가 발생하는 현상을 JPA의 [N+1](https://github.com/jeonyoungho/TIL/blob/master/Spring/JPA/N%2B1%EB%AC%B8%EC%A0%9C/N%2B1%EB%AC%B8%EC%A0%9C.md)문제라고 한다.</b>

- 이 문제를 해결하기 위해 Product / Employee 조회에 <b>Fetch Join</b>을 적용한다.
~~~
public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s " +
            "FROM Store s " +
            "JOIN FETCH s.products " +
            "JOIN FETCH s.employees")
    List<Store> findAllByFetchJoin ();
}
~~~

- 하지만 이렇게 <b>1:N 관계의 자식 테이블 여러 곳에 Fetch Join을 사용하면 아래와 같이 에러가 발생한다.<br>
<img width="1114" alt="스크린샷 2021-06-13 오후 9 37 07" src="https://user-images.githubusercontent.com/44339530/121807544-89401500-cc8f-11eb-9c6b-8bb9d69b7d8c.png"><br>

- JPA에서 Fetch Join의 조건은 다음과 같다.
    - <b>To One은 몇개든 사용 가능하다</b>
    - <b>ToMany는 1개만 사용 가능하다.<b>
- 어떻게 하면 <b>MultipleBagFetchException</b> 에러 없이 N+1문제를 최대한 회피할 수 있을까?

### 해결책 - Hibernate default_batch_fetch_size
- 해결책은 hibernate의 <b>default_batch_fetch_size</b>옵션에 있다.
- 다시 한 번 JPA의 N+1문제를 바라보자.<br>
<img width="847" alt="1" src="https://user-images.githubusercontent.com/44339530/121807639-fa7fc800-cc8f-11eb-8776-6c78479052ea.png"><br>

- <b>N+1 문제란 결국 부모 엔티티와 연관 관계가 있는 자식 엔티티들의 조회 쿼리가 문제이다.</b> 부모 엔티티의 Key 하나하나를 자식 엔티티 조회로 사용하기 때문이다.
- <b>만약 1개씩 사용되는 조건문을 in절로 묶어서 조회하면 어떨까?</b><br>
<img width="1045" alt="2" src="https://user-images.githubusercontent.com/44339530/121807643-fd7ab880-cc8f-11eb-9418-aca2a252c860.png"><br>

- 바로 이 개념으로 사용되는 것이 바로 hibernate.default_batch_fetch_size 옵션이다.
- 해당 옵션은 <b>지정된 수만큼 in절에 부모 key를 사용</b>하게 해준다.
- 즉, 1000개를 옵션값으로 지정하면 1000개 단위로 in절에 부모 key가 넘어가서 자식 엔티티 들이 조회되는 것이다. 단순하게 생각해도 <b>쿼리 수행수가 1/1000</b>이 되는거다.
- 위의 옵션을 적용하여 다시 한 번 Test코드를 수행해보자
~~~
package com.example.domain;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@TestPropertySource(properties = "spring.jpa.properties.hibernate.default_batch_fetch_size=1000") // 옵션 적용
class StoreServiceTest {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreService storeService;

    @BeforeEach
    void setUp() {
        Store store1 = new Store("서점1", "서울시 강남구");
        store1.addProduct(new Product("책1_1", 10000L));
        store1.addProduct(new Product("책1_2", 20000L));
        store1.addEmployee(new Employee("직원1_1", LocalDate.now()));
        store1.addEmployee(new Employee("직원1_2", LocalDate.now()));
        storeRepository.save(store1);

        Store store2 = new Store("서점2", "서울시 강남구");
        store2.addProduct(new Product("책2_1", 10000L));
        store2.addProduct(new Product("책2_2", 20000L));
        store2.addEmployee(new Employee("직원2_1", LocalDate.now()));
        store2.addEmployee(new Employee("직원2_2", LocalDate.now()));
        storeRepository.save(store2);
    }

    @AfterEach
    void tearDown() {
        storeRepository.deleteAll();
    }

    @Test
    public void NO_Repository_의_BatchSize () throws Exception {
        long size = storeService.find();
        assertThat(size, is(60000L));
    }
}
~~~

#### 출처
- https://jojoldu.tistory.com/457