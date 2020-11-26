# MyBatis Settings

~~~
<settings>
	<setting name="cacheEnabled" value="true" />				
	<setting name="lazyLoadingEnabled" value="false" />
	<setting name="multipleResultSetsEnabled" value="true" />	
	<setting name="useColumnLabel" value="true" />				
	<setting name="useGeneratedKeys" value="false" />
	<setting name="autoMappingBehavior" value="PARTIAL" />
	<setting name="defaultExecutorType" value="SIMPLE" />
	<setting name="jdbcTypeForNull" value="VARCHAR"/>
	<setting name="callSettersOnNulls" value="true"/>
</settings>
~~~

- cacheEnabled: 각 mapper 에 설정된 캐시를 전역적으로 사용할지 말지에 대한 여부 (true | false, default: true)<br>
- lazyLoadingEnabled: 늦은 로딩을 사용할지에 대한 여부, 사용하지 않는다면 모두 즉시 로딩, 이 값은 fetchType 속성을 사용해서 대체할 수 있음 (true | false, default: false)<br>
- multipleResultSetsEnabled: 한개의 구문에서 여러개의 ResultSet 을 허용할지의 여부(드라이버가 해당 기능을 지원해야 함) (true | false, default: true)<br>
- useColumnLabel: 칼럼명 대신에 칼럼라벨을 사용, 드라이버마다 조금 다르게 작동, 문서와 간단한 테스트를 통해 실제 기대하는 것처럼 작동하는지 확인해야 함 (true | false, default: true)<br>
- useGeneratedKeys: 생성키에 대한 JDBC 지원을 허용, 지원하는 드라이버가 필요, true 로 설정하면 생성키를 강제로 생성, 일부 드라이버(예를들면, Derby)에서는 이 설정을 무시함 (true | false, default: false)<br>
- autoMappingBehavior: MyBatis 가 칼럼을 필드/프로퍼티에 자동으로 매핑할지와 방법에 대해 명시, PARTIAL 은 간단한 자동매핑만 할뿐 내포된 결과에 대해서는 처리하지 않음, FULL 은 처리가능한 모든 자동매핑을 처리. (NONE, PARTIAL, FULL PARTIAL, default: PARTIAL)<br>
- defaultExecutorType:  디폴트 실행자(executor) 설정, SIMPLE 실행자는 특별히 하는 것이 없음, REUSE 실행자는 PreparedStatement 를 재사용, BATCH 실행자는 구문을 재사용하고 수정을 배치처리 (SIMPLE REUSE BATCH, default: SIMPLE)<br>
- jdbcTypeForNull: JDBC타입을 파라미터에 제공하지 않을때 null값을 처리한 JDBC타입을 명시, 일부 드라이버는 칼럼의 JDBC타입을 정의하도록 요구하지만 대부분은 NULL, VARCHAR 나 OTHER 처럼 일반적인 값을 사용해서 동작, 대부분은 NULL, VARCHAR 나 OTHER 를 공통적으로 사용 (default: OTHER)<br>
- callSettersOnNulls: 가져온 값이 null일때 setter나 맵의 put 메소드를 호출할지를 명시, Map.keySet() 이나 null값을 초기화할때 유용, int, boolean 등과 같은 원시타입은 null을 셋팅할 수 없음 (true | false default: false)<br>

- 참고<br>
· https://m.blog.naver.com/PostView.nhn?blogId=niee&logNo=220171990222&proxyReferer=https:%2F%2Fwww.google.com%2F