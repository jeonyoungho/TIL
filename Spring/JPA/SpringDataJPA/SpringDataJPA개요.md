# Spring Data JPA개요

## Spring Data JPA란?
- JPA를 사용해서 관계형 DB와 쉽게 연동하기 위한 Spring에서 제공하는 Spring ORM Module중 하나
- Spring에서 CRUD 구현체를 제공
    - 이를 통해 boiler-pate DAO코드를 최소화
    - entity type과 primary key의 정보만 넣어주면 자동으로 dao클래스를 생성해줌<br>
    - ![image](https://user-images.githubusercontent.com/44339530/113396805-1422aa80-93d7-11eb-90bf-2f4b5c5d9afb.png)<br>
    - <b>CrudRepository 클래스를 상속받아 추가적인 메소드만 구현해주면 된다.</b>


#### Spring Data JPA에서 제공하는 3가지 인터페이스
![image](https://user-images.githubusercontent.com/44339530/113397665-5f898880-93d8-11eb-8bf1-ba4f76f6d28c.png)<br>
1. CrudRepository
- 주로 기본적인 CRUD연산에 대한 기능 제공

2. PagingAndSortingRepository
- CrudRepository를 상속 받아 추가적인 페이징 처리 기능과 정렬 기능을 제공

3. JpaRepository
- pagingAndSortingRepository를 상속받아 추가적인 persistence context를 flush기능과 배치 속 레코드를 삭제하는 추가적인 기능을 제공

## Hibernate와 Spring Data JPA의 비교

#### Hibernate를 사용하여 구현한 DAO클래스
![image](https://user-images.githubusercontent.com/44339530/113397802-96f83500-93d8-11eb-871d-97e98a127d85.png)

#### Spring Data JPA를 사용하여 구현한 DAO클래스
![image](https://user-images.githubusercontent.com/44339530/113397817-9e1f4300-93d8-11eb-8286-dad43f949324.png)

## Spring Data JPA의 추가적인 기능 구현
![image](https://user-images.githubusercontent.com/44339530/113398084-ff471680-93d8-11eb-8b12-dfc095850119.png)<br>
- 위의 그림과 같이 인터페이스에 메소드만 정의해주면 추가적인 기능을 구현할 수 있다.
- 'findBy + 필드이름'이름으로 메소드만 정의해주면 자동으로 구현 가능하다.
- 예를 들어, findByProductname(String productName)메소드만 구현해주면 Spring Data JPA는 자동으로 "where productName = ?" 다음과 같은 where절을 쿼리에 추가해준다.