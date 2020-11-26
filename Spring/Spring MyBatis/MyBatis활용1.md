# MyBatis의 활용

-  <b>#{}과 ${}의 차이<b><br>
· #{}: preparedStatement<br>
· ${}: statement<br>
· 참고 https://madplay.github.io/post/difference-between-dollar-sign-and-sharp-sign-in-mybatis<br>


- <b>&lt;bind&gt; 문법</b><br>
· 외부에서 전달된 파라미터를 이용하여 변수 생성하는 엘리먼트, 동적 쿼리 변수를 생성할 때 사용한다.<br>
· 형식<br>
~~~
<select | insert | update | delete><br>
<bind name="지정할 변수이름" value="파라미터 값+부가 옵션"/><br>
</select | insert | update | delete><br>
~~~
· 예제
~~~
<select id="getTest" resultType="board">
SELECT * FROM board
<bind name="ids" value="'%'+id+'%'"/>
<bind name="subjects" value="'%'+subject+'%'"/>
<where>
<if test="id != null"> AND id like #{ids}</if>
<if test="subject != null"> AND subject like #{subjects} </if>
</where>
</select>
~~~
· 참고 https://java119.tistory.com/99<br>
  
- <b>&lt;where&gt; 문법</b>
· 추가 쿼리 문의 내용을 <where> 문 안에 작성하면 첫 조건이 AND로 시작할지라도 WHERE로 치환되어 쿼리가 수행 된다.<br>
· 형식<br>
~~~
<select id="id">
SELECT * FROM table
<where>
추가 SQL
</where>
</select>
~~~
  
· 예제<br>
~~~
<select id="getTest" resultType="board">
SELECT * FROM baord
<where>
<if test="id != null">AND id = #{id} </if>
<if test="subject != null">AND subject = #{subject} </if>
</where>
</select>
~~~

· 참고 https://java119.tistory.com/90<br>

- <b>HashMap을 이용한 동적 쿼리 생성</b><br>
· 참고 https://javaengine.tistory.com/entry/parameterType-HashMap-%EA%B4%80%EB%A0%A8-%EC%98%88%EC%8B%9C%EB%8B%A4%EC%A4%91%ED%8C%8C%EB%9D%BC%EB%AF%B8%ED%84%B0

