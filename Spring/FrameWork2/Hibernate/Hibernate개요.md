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
  
### - Hibernate 동작 원리  
  
<img width="844" alt="스크린샷 2020-06-29 오전 11 59 15" src="https://user-images.githubusercontent.com/44339530/85968385-f5365880-b9ff-11ea-95c7-8a98aeea4ddc.png">  
  
1. Hibernate에 대한 설정정보 및 매핑 정보를 읽어들여 Configuration클래스가 SessionFactory(하나만 존재)를 생성한다  
  
2. SessionFactory가 Session을 만들어서 결국 이 Session을 이용해서 JDBC를 사용하여 데이터베이스를 접근하게 된다.   

  





