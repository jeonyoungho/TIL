# JPA vs Hibernate

![image](https://user-images.githubusercontent.com/44339530/113241981-cdf01d00-92ea-11eb-9bb7-108cc557ca17.png)
- JPA(Java Persistence API): 구현물이 없는 인터페이스
- Hibernate: JPA의 구현체
- JPA만 가지고는 프로그래밍할 순 없고 Hibernate, EclipseLink, DataNucleus와 같은 구현체를 가지고 사용해야한다.<br>

<img width="454" alt="스크린샷 2021-04-01 오후 1 21 36" src="https://user-images.githubusercontent.com/44339530/113243035-317b4a00-92ed-11eb-8298-56f9ba4c48b7.png"><br>

- SessionFactory(Hibernate): JPA에 정의된 EntityManager Factory를 상속 받은 인터페이스이고 이를 실제 구현한게 SessionFactoryImplementation 클래스
- Session(Hibernate): JPA에 정의된 Entity Manager를 상속하고 있고 이를 실제 구현한게 SessionImpl클래스


## Library
1. Java-persistence-api
~~~
<dependency>
    <groupId>javax.persistence</groupId>
    <artifactId>javax.persistence-api</artifactId>
    <version>2.2</version>
</dependency>
~~~

2. hibernate-core
- hibernate-core만 추가해도 의존하는 Java-persistence-api도 같이 다운 받게됨
~~~
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>5.4.10.Final</version>
</dependency>
~~~

## 활용 방법
1. JPA + JPA Provider(Hibernate)
- 애플리케이션이 JPA를 사용해서 jdbc를 호출해서 사용하는 방법
- <b>장점은 hibernate를 사용하다 EclipseLink, DataNucleus와 같은 다른 구현체로 쉽게 바꿀 수 있음(호환성이 뛰어남)</b>

2. only Hibernate
- 애플리케이션이 JPA를 거치지 않고 바로 hibernate를 사용해서 jdbc를 호출해서 사용하는 방법
- <b>장점은 hibernate가 JPA의 인터페이스를 구현한거 외에 자체적으로 존재하는 native 구현체들을 사용할 수 있다.(JPA외에도 추가적인 특징을 사용 가능하다)</b>
- 사용하는 목적에 따라 적절하게 활용하면 됨

