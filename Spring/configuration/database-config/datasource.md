# BasicDataSource 
~~~
<bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
	<property name="driverClassName" value="${jdbc.driver}" />
	<property name="url" value="${jdbc.url}" />
	<property name="username" value="${jdbc.username}" />
	<property name="password" value="${jdbc.password}" />
	<property name="initialSize" value="5"/> 
	<property name="maxTotal" value="5" />
	<property name="maxIdle" value="5" />
	<property name="maxWaitMillis" value="50000" />
	<property name="timeBetweenEvictionRunsMillis" value="500000"/>
	<property name="minEvictableIdleTimeMillis" value="50000" />
	<property name="defaultAutoCommit" value="false" />
	<property name="testWhileIdle" value="true" />
	<property name="validationQuery" value="SELECT 1" /> <!-- For MsSql, PostgreSQL -->
</bean>
~~~
- Property<br>
· driverClassName: 해당 DBMS에 해당하는 driverClassName지정<br>
· url: jdbc URL설정<br>
· username: username 설정<br>
· password: password 설정<br>
· initialSize: BasicDataSource 클래스 생성 후 최초로 getConnection() 메서드를 호출할 때 커넥션 풀에 채워 넣을 커넥션 개수<br>
· maxTotal: 동시에 사용할 수 있는 최대 커넥션 개수(기본값: 8)<br>
· maxIdle: 커넥션 풀에 반납할 때 최대로 유지될 수 있는 커넥션 개수(기본값: 8)<br>
· maxWaitMillis: 커넥션 풀 안의 커넥션이 고갈됐을 때 커넥션 반납을 대기하는 시간(밀리초)<br>
· timeBetweenEvictionRunsMillis: Evictor 스레드가 동작하는 간격. 기본값은 -1이며 Evictor 스레드의 실행이 비활성화돼 있음<br>
· minEvictableIdleTimeMillis: Evictor 스레드 동작 시 커넥션의 유휴 시간을 확인해 설정 값 이상일 경우 커넥션을 제거한다(기본값: 30분)<br>
· defaultAutoCommit: defaultAutoCommit 속성을 false로 설정하면 애플리케이션에서 트랜잭션 처리가 되어 있지 않은 경우에는 INSERT 쿼리나 UPDATE 쿼리가 제대로 반영되지 않음<br>
· testWhileIdle: Evictor 스레드가 실행될 때 (timeBetweenEvictionRunMillis > 0) 커넥션 풀 안에 있는 유휴 상태의 커넥션을 대상으로 테스트 실행(기본값: false), 만약 오랫동안 대기 상태였던 커넥션이 끊어지는 현상을 방지하려면 true로 설정해야 함<br>
· validationQuery: JDBC 커넥션의 유효성 확인<br>

# SqlSessionFactoryBean
~~~
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	<property name="dataSource" ref="dataSource"></property>
	<property name="configLocation" value="classpath:/mybatis-config.xml" />
	<property name="mapperLocations" value="classpath:mapper/${jdbc.type}/**/*.xml" />
</bean>
~~~
- Property<br>
· dataSource: dataSource지정<br>
· configLocation: mybatis의 config파일 설정<br>
· mapperLocations: 매퍼의 위치를 설정 (예시에서는 jdbc의 타입을 통해 매퍼의 위치를 달리 설정하고 있음)<br>

- 참고<br>
· https://d2.naver.com/helloworld/5102792<br>
· https://kookyungmin.github.io/server/2018/08/13/spring_06/<br>
· https://lemontia.tistory.com/406<br>