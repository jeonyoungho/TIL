- MyBatis와 전통적인 JDBC프로그램의 비교
![MyBatis와 전통적인 JDBC프로그램의 비교](https://user-images.githubusercontent.com/44339530/75093707-7b144400-55c7-11ea-8cf1-e6526c98c691.png)

- mybatis-spring이라는 라이브러리를 통해서 쉽게 연동 가능

- Architecture
![architecture](https://user-images.githubusercontent.com/44339530/75094048-b6fcd880-55ca-11ea-873e-ef786a3cfc42.png)

- MyBatis설정

1. 라이브러리 추가
·spring-jdbc/spring-tx : 스프링에서 DB처리와 트랜잭션 처리(해당 라이브러리들은 MyBatis와 무관하게 보이지만 추가하지 않은 경우에 에러발생)
·mybatis/mybatis-spring : MyBatis와 스프링 연동용 라이브러리

<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis</artifactId>
    <version>3.4.6</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
<dependency>
    <groupId>org.mybatis</groupId>
    <artifactId>mybatis-spring</artifactId>
    <version>1.3.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-tx</artifactId>
    <version>${org.springframework-version}</version>
</dependency>

<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-jdbc</artifactId>
	<version>${org.springframework-version}</version>
</dependency>

2. root-context.xml 에 SQLSessionFactory Bean등록
MyBatis에서 가장 핵심적인 객체로 SQLSession과 SQLSessionFactory가 있다.
SQLSessionFactory에서 생성된 SQLSession을 통해 Connection을 생성하거나 원하는 SQL을 전달하고, 결과를 리턴 받는 구조로 흘러감

· xml을 이용한 설정
<img width="626" alt="sqlsessionFactory-bean등록" src="https://user-images.githubusercontent.com/44339530/75093659-3ee0e380-55c7-11ea-9be1-a8984dd1a81b.png">
· Java를 이용한 설정
<img width="895" alt="RootConfig" src="https://user-images.githubusercontent.com/44339530/75093660-3ee0e380-55c7-11ea-8f63-0ad839648d0c.png">
3. JUnit Test
<img width="1367" alt="JunitTest" src="https://user-images.githubusercontent.com/44339530/75093661-40121080-55c7-11ea-96b2-e005f874f56d.png">

- 스프링과의 연동 처리
· MyBatis의 Mapper객체를 통해 SQL을 어떻게 처리할 것인지를 별도의 설정을 분리해 주고, 자동으로 처리되는 방식을 이용한다. 즉 SQL과 그에 대한 처리를 지정하는 역할

1. TimeMapper 인터페이스 작성
<img width="1013" alt="Mapper인터페이스작성" src="https://user-images.githubusercontent.com/44339530/75093662-40aaa700-55c7-11ea-9b6e-2835115d6b33.png">
2. MyBatis가 동작할때 Mapper를 인식할 수 있도록 추가적인 설정

· xml을 이용한 설정
root-context.xml에 다음 태그 추가
<mybatis:scan base-package="org.zerock.mapper"/>
· Java를 이용한 설정
RootConfig.java에 다음 추가
@MapperScan(basePackages = {"org.zero.mapper"})

3. Mapper테스트
· MyBatis-Spring은  Mapper 인터페이스를 이용해서 실제 SQL 처리가 되는 클래스를 자동으로 생성한다.
· 따라서 개발자들은 인터페이스와 SQLaㅏㄴ을 작성하는 방식으로 모든 JDBC처리를 끝낼 수 있다.
<img width="1057" alt="JunitTest-TimeMapper" src="https://user-images.githubusercontent.com/44339530/75093663-40aaa700-55c7-11ea-92d7-1688e863928a.png">
·인터페이스만 만들어 주었는데 내부적으로 적당한 클래스가 만들어진다.

4. XML매퍼와 같이 쓰기
· MyBatis를 이용해서 SQL을 처리할 때 어노테이션을 이용하는 방식이 압도적으로 편리하짐나 SQL이 길어지거나 복잡한 경우 XML을 이용하는 방식이 더 선호된다. 다행히도 MyBatis-Spring의 경우 Mapper인터페이스와 XML을 동시에 이용가능하다.

·TimeMapper인터페이스에 다음 메소드 추가
<img width="1066" alt="TimeMapper" src="https://user-images.githubusercontent.com/44339530/75094431-58d1f480-55ce-11ea-879d-0a0819e8d1f8.png">

·sources/main/resources 밑에 TimeMapper.xml작성
<img width="847" alt="timemapper xml" src="https://user-images.githubusercontent.com/44339530/75094430-57a0c780-55ce-11ea-817e-ac1b59c3d5b0.png">

※XML매핑 사용시 <mapper>태그의 namespace속성 값을 신경써야함.MyBatis는 Mapper인터페이스와 XML을 인터페이스의 이름과namespace속성 
값을 가지고 판단함.위같이 orgzeorck.mapper.TimeMapper 인터페이스가 존재하고,XML의<mapper namespace="orgzeorck.mapper.TimeMapper">와
동일한 이름이 존재하면 ,이를 병합해서 처리함. 따라서 위의 경우 메서드 선언은 인터페이스에 존재하고 SQL에 대한 처리는 XML을 이용하는 방식이라고 볼수있음.

※<select> 태그의 id 속성의 값은 메서드의 이름과 동일하게 맞춰야함.<select>태그의 경우resultType속성을 가지는데 이 값은 인터페이스에 선언된 메서드의 리턴타입과 동일하게 작성

5. 최종적인 확인을 위한 테스트  TimeMapperTests 클래스이용
<img width="826" alt="JUnitTestResult" src="https://user-images.githubusercontent.com/44339530/75093934-b1eb5980-55c9-11ea-96f4-1bcd4cf62cb7.png">

·결과는 동일

- log4jdbc-log4j2 설정
· MyBatis는 내부적으로 JDBC의 PreparedStatement를 이용해서 SQL을 처리함 따라서 SQL에 전달되는 파라미터는 '?'로 치환되어서 처리됨. 복잡한 SQL의 경우 '?'로 나오는 값이 제대로 되었는지 확인하기가 쉽지 않고 실행된 SQL의 내용을 정확히 확인하기는 어려움. 이런 문제를 해결하기 위해 SQL을 변환해서 PreparedStatementdㅔ 사용된 '?'가 어떤 값으로 처리되었는지 확인하는 기능을 추가해야함. 

1. SQL로그를 제대로 보기 위해선 log4jdbc-log4j2 라이브러리를 추가해야함
<!-- https://mvnrepository.com/artifact/org.bgee.log4jdbc-log4j2/log4jdbc-log4j2-jdbc4 -->
<dependency>
    <groupId>org.bgee.log4jdbc-log4j2</groupId>
    <artifactId>log4jdbc-log4j2-jdbc4</artifactId>
    <version>1.16</version>
</dependency>

2. 로그 설정 파일을 추가해줘야함 (src/main/resources 밑에 log4jdbc-log4j2.properties파일을 추가)
<img width="844" alt="properties" src="https://user-images.githubusercontent.com/44339530/75094192-69816b00-55cc-11ea-86ae-b520386011af.png">

3. JDBC 연결 정보 (JDBC드라이버와 URL정보)를 수정
<img width="1307" alt="jdbc정보수정" src="https://user-images.githubusercontent.com/44339530/75094190-65554d80-55cc-11ea-8581-939fdd1822b6.png">

4. 테스트
<img width="648" alt="JUnit-log4jdbc-result" src="https://user-images.githubusercontent.com/44339530/75094429-54a5d700-55ce-11ea-8e51-f1e85d4d8a01.png">

· JDBC와 관련된 로그들이 출력됨

- 로그의 레벨 설정
· 테스트를 실행하면 상당히 많은 양의 로그가 출력되기 때문에 불편하다.이런 상황에서는 로그 레벨을 이용해서 조금 수정해줄 필요가 있음.

· 테스트 코드가 실행될 때의 로그와 관련 설정은 src/test/resources밑에 log4j.xml을 이용함.(src/main/resources 아닌src/test/resources 란 걸 주의)
<img width="302" alt="1" src="https://user-images.githubusercontent.com/44339530/75095061-7c983900-55d4-11ea-9882-62f24ecc120b.png">

· 테스트코드가 실행될때 'INFO...',메세지는 log4j.xml의 마지막 부분에 있는 설정에 영향을 받기 때문
<img width="362" alt="2" src="https://user-images.githubusercontent.com/44339530/75095063-7d30cf80-55d4-11ea-99e3-8d10b62596a5.png">

· 만일 log4jdbc에서 출력되는 로그를 조절하고 싶다면 추가적인 <logger>를 지정해서 처리함.
	<logger name="jdbc.audit">
		<level value="warn" />
	</logger>
	<logger name="jdbc.resulteset">
		<level value="warn" />
	</logger>
	<logger name="jdbc.connection">
		<level value="warn" />
	</logger>
<img width="890" alt="3" src="https://user-images.githubusercontent.com/44339530/75095057-77d38500-55d4-11ea-8b74-b523d909b7b5.png">

· 기본 설정의 로그는 info 레벨이기 때문에 warn과 같이 좀더 높은 레벨의 로그만 기록하게 수정하면 테스트 코드를 실행할 때 이전에 비해 로그양이 줄어드는것을 볼 수 있음.(더 자세한내용 참고https://logging.apache.org/log4j/2.x/manual/customloglevels.html)
<img width="580" alt="스크린샷 2020-02-23 오전 12 39 09" src="https://user-images.githubusercontent.com/44339530/75095098-ee708280-55d4-11ea-8e94-04e943167d27.png">