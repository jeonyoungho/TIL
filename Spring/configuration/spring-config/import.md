# import

- spring-context.xml에서 다른 context.xml을 import하여 등록하고 싶을 경우 다음과 같이 설정한다.
~~~
<import resource="spring/datasource.xml"/>
<import resource="spring/mybatis.xml"/>
~~~~

- 참고 https://cheershennah.tistory.com/77