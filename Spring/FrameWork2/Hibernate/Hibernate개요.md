# Hibernate 개요

### - ORM Framework (Object Relational Mapping)   
- Object Persistence 를 위한 프레임워크   
- 객체와 테이블 사이에는 mismatch가 발생한다. 이를 위해 mapping시켜주는 프레임워크  
<img width="844" alt="스크린샷 2020-06-29 오전 11 50 35" src="https://user-images.githubusercontent.com/44339530/85967989-bf44a480-b9fe-11ea-981f-691e6a994120.png">  
  
### - DB에 데이터 persist를 위한 3가지 방법  
1. JDBC  
2. Spring JDBC  
3. Hibernate (ORM Framework)  
  
### - Association  
1. Java -> 객체 참조  
2. RDBMS -> Foreign Key  
  
### - Hibernate의 이점  
- 저수준의 SQL문을 사용하여 JDBC코드의 양을 줄여준다!!  
  
### - Hibernate 3가지 구성  
1. SessionFactory -> session을 만들어서 DB와 실제 커뮤니케이션  
2. hibernate.cfg.xml -> 하이버네이트에 대한 설정파일  
3. *.hbm.xml class mappings -> 객체와 테이블 사이에 대한 매핑 정보  

### Hibernate 라이브러리 추가  
<img width="844" alt="스크린샷 2020-06-29 오후 1 58 17" src="https://user-images.githubusercontent.com/44339530/85974506-975e3c80-ba10-11ea-9d04-a2be2c687769.png"><br>

### - Hibernate 동작 원리  
  
<img width="844" alt="스크린샷 2020-06-29 오전 11 59 15" src="https://user-images.githubusercontent.com/44339530/85968385-f5365880-b9ff-11ea-95c7-8a98aeea4ddc.png">  

<img width="844" alt="스크린샷 2020-06-29 오후 12 14 20" src="https://user-images.githubusercontent.com/44339530/85969275-2021ac00-ba02-11ea-9b4c-d0a023a6efa7.png">  
  
<img width="844" alt="스크린샷 2020-06-29 오전 11 59 15" src="https://user-images.githubusercontent.com/44339530/85968385-f5365880-b9ff-11ea-95c7-8a98aeea4ddc.png">  
  
1. Hibernate에 대한 설정정보 및 매핑 정보를 읽어들여 Configuration클래스가 SessionFactory(하나만 존재)를 생성한다.  
- Configuration Object<br>
1)Database connection에 관련된 정보<br>  
<img width="844" alt="스크린샷 2020-06-29 오후 12 23 31" src="https://user-images.githubusercontent.com/44339530/85969690-59a6e700-ba03-11ea-8e9d-0ce89fbab429.png"><br>
- Connection 정보<br>
- Dialect -> 우리가 사용하는 DB 정보입력<br>
- show_sql -> hibernate가 콘솔에서 자동 생성하는 sql을 볼 것이냐 말것이냐 (true이면 생성하는 sql을 문을  볼 수 있음)<br>
- Table을 메뉴얼(수동)적으로 생성하는게 아니라 오토(자동)적으로 생성하게 한다 (create를 주면 애플리케이션 실행시 매번 테이블을 자동으로 생성하게 되어있음)<br>
- Mapping클래스 지정<br><br>

2)Mapping에 관련된 정보 (클래스와 DB테이블 사이의 연관성에 대한 정보)  
<img width="844" alt="스크린샷 2020-06-29 오후 12 34 22" src="https://user-images.githubusercontent.com/44339530/85970249-dc7c7180-ba04-11ea-848c-a5759e3396e0.png">  
  
- Entity클래스 -> 데이터베이스 테이블에 매핑이 되는 자바 클래스<br>
- Table네임 없으면 클래스 name하고 동일하게 설정 됨<br>
- 필드는 테이블의 컬럼에 매핑이 된다<br>
- @Column네임이 없을시 변수이름과 동일하게 설정됨<br>
- 여러 개의 Product가 하나의 Category에 매핑이 되므로 Product입장에선 @ManyToOne<br>
- JoinColumn으로 매핑 키 지정<br>
  
2. SessionFactory가 Session을 만들어서 결국 이 Session을 이용해서 JDBC를 사용하여 데이터베이스를 접근하게 된다.   
- SessionFactory의 역할
1. 세션 생성<br>
2. 멀티 스레딩 환경에서도 안전하게 사용할 수 있는 객체 (애플리케이션의 모든스레드에 의해 사용될 수 있다)<br>
3. 무거운 객체이며 애플리케이션이 시작될때 한 번 생성되서 계속 사용된다<br>
  
- SessionFactory Object<br>
1. SessionFactory.openSession() -> 세션을 하나 생성 (세션을 생성 후 close후 세션이 캐슁을 하기 때문에 그내용을 flush시켜줄 필요가 있음)<br>

2. SessionFactroy.getCurrentSession() -> 만들어진 세션이 없으면 새로 세션을 하나 생성해서라도 세션을 하나 얻어온다<br>
<br>
- Example)<br>
<img width="844" alt="스크린샷 2020-06-29 오후 12 42 09" src="https://user-images.githubusercontent.com/44339530/85970623-f4082a00-ba05-11ea-8cbd-9c19ad6d02df.png"><br>
※ SQL전혀 볼 수 없고 객체지향적으로 프로그래밍 하고 있는걸 알 수 있음!!! (get메서드로 Person에서 해당 id를 가진 레코드를 객체로 매핑시켜 얻어와서 update로 내용을 수정하여 저장)<br>
<img width="844" alt="스크린샷 2020-06-29 오후 12 48 05" src="https://user-images.githubusercontent.com/44339530/85970912-c8397400-ba06-11ea-83b3-89ae5b7b4ff4.png"><br>
※ @Transactional을 선언하여 Transaction을 생성하고 커밋하는 과정을 생략할 수 있다. 메서드자체를 다 자동으로 트랜잭션으로 처리한다.<br>
<br>
- Session Object<br>
1. 애플리케이션과 데이터베이스사이에 커넥션을 얻을 때 사용이 되며 thread-safe하지 않기 때문에 오랜 시간 동안 열어 둬선 안된다<br>
2. 잠시 열어뒀따가 CRUD작업을 수행한다<br>
<br>

<img width="844" alt="스크린샷 2020-06-29 오후 12 57 53" src="https://user-images.githubusercontent.com/44339530/85971466-274bb880-ba08-11ea-8e06-f20ed8e05a60.png"><br>

- 세션 객체와 DB사이에는 캐쉬가 존재한다<br>
- 데이터베이스에 쓰는것은 퍼포먼스에 영향을 줄 수가 있다 따라서 캐쉬에 모았다가 flush를 통해 한꺼번에 디비에 저장하는것이 바람직하다<br>
<br>

### Object States  
<img width="844" alt="스크린샷 2020-06-29 오후 1 01 07" src="https://user-images.githubusercontent.com/44339530/85971611-9aedc580-ba08-11ea-948e-a1f81b183957.png"><br>

- 객체를 처음에 메모리상태에 존재하는 Transient상태가 된다. save또는 saveOrUpdate를 수행하면 persistent상태로 바뀌어 DB에 저장된다 DB에서 제거가 되면 메모리상에만 남기 때문에 Transient상태가 된다 session을 close시키면 Detached상태가 되고 save또는 saveOrUpdate를 수행하면 다시 persistent상태로 바뀌게 된다<br>

- Example)<br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 04 54" src="https://user-images.githubusercontent.com/44339530/85971808-21a2a280-ba09-11ea-9d7e-4feeabad00d6.png"><br>

- 처음에 객체를 만들면 Transient<br>
- DB에 저장하면 Persistent Object<br>
- 세션을 close시키면 Detached상태가 된다<br>

- Persistent상태에서 save를 하지않고 변경을 해도 DB에 그대로 반영이 된다!!!<br>

### Session의 메소드  
1. get( )<br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 08 56" src="https://user-images.githubusercontent.com/44339530/85971993-b1e0e780-ba09-11ea-83e5-4bd759eecc5d.png"><br>

2. save & saveOrUpdate( )<br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 14 55" src="https://user-images.githubusercontent.com/44339530/85972286-87dbf500-ba0a-11ea-9384-d07650286637.png"><br>
※saveOrUpdate() -> DB에 저장된 게 잇으면 update가 호출되고 없으면 save가 호출된다.<br>

3. delete( )<br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 18 52" src="https://user-images.githubusercontent.com/44339530/85972470-1486b300-ba0b-11ea-8e99-aae4043c76e0.png"><br>

4. flush( ) -> 캐쉬 메모리를 비우는 것<br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 21 46" src="https://user-images.githubusercontent.com/44339530/85972628-7d6e2b00-ba0b-11ea-9943-da624f51eb4f.png"><br>
- DB와 세션Object에 있는 객체를 동기화 시켜주는 작업<br>
- 트랜잭션을 커밋하거나 세션을 close하기 전에 하나의 작업이 끝낫을때 호출을 해줘야함<br>
- Example)<br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 24 42" src="https://user-images.githubusercontent.com/44339530/85972801-e5247600-ba0b-11ea-9bea-9c424bc3cea0.png"><br>
※flush가 되지 않은 상태에서 A,B,C,D는 디비가 아닌 캐쉬에 저장되어있는 상태있기기에.스텝5,7 수행시 디비에 아무것도 없기에 결과가 제대로 나오지 않는다 따라서 적절한 타이밍에 flush시켜줘야함<br>

5. close( ) -> Session을 닫는 것<br>
※ 스프링 사용하는 환경에서는 특별하게 close시켜주지 않아도 스프링이 다 알아서 해준다<br>

※ 스프링JDBC가 아닌 hibernate를 이용하여 구현한 예시 (SQL문을 사용하지 않고 굉장히 심플하게 구현 가능)  
<img width="844" alt="스크린샷 2020-06-29 오후 1 27 33" src="https://user-images.githubusercontent.com/44339530/85972946-4ba99400-ba0c-11ea-8d60-450b425d9b49.png"><br>

6. createQuery( ) -> 좀더 디테일한 쿼리를 만들고 싶을때<br> 
<img width="844" alt="스크린샷 2020-06-29 오후 1 30 59" src="https://user-images.githubusercontent.com/44339530/85973136-c672af00-ba0c-11ea-9582-f93756b30bc8.png"><br>

### Transaction Object  
- DB의 작업 단위<br>
- 하나라도 실패하면 rollback<br>
- 모두 성공시 commit ()<br>

### Query Object  

- Session.createQuery()를 통해 Query객체 생성<br>
- SQL또는 HQL을 사용하여 DB로 부터 객체를 생성하거나 조회<br>
- Example)<br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 35 56" src="https://user-images.githubusercontent.com/44339530/85973353-77794980-ba0d-11ea-9bd6-a60c8fd716a8.png"><br>

### HQL  
<img width="844" alt="스크린샷 2020-06-29 오후 1 37 04" src="https://user-images.githubusercontent.com/44339530/85973420-a0014380-ba0d-11ea-8568-8c9bdcc2fd15.png"><br>
※table이나 column이름이 아닌 클래스나 필드를 사용<br>

<img width="844" alt="스크린샷 2020-06-29 오후 1 38 29" src="https://user-images.githubusercontent.com/44339530/85973482-d2ab3c00-ba0d-11ea-91cf-b964be7f70b7.png"><br>

- Example)<br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 39 50" src="https://user-images.githubusercontent.com/44339530/85973542-025a4400-ba0e-11ea-99f7-da1f9b5bb181.png"><br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 39 56" src="https://user-images.githubusercontent.com/44339530/85973545-05553480-ba0e-11ea-8922-60e47951135d.png"><br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 40 03" src="https://user-images.githubusercontent.com/44339530/85973550-0a19e880-ba0e-11ea-8f06-efbbe30d9f99.png"><br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 40 12" src="https://user-images.githubusercontent.com/44339530/85973553-0f773300-ba0e-11ea-8751-653cd9e1d5ce.png"><br>
<img width="844" alt="스크린샷 2020-06-29 오후 1 40 18" src="https://user-images.githubusercontent.com/44339530/85973558-13a35080-ba0e-11ea-8121-1196cf10b10d.png"><br>

















