# TypeAlias 설정<br>

- TypeAlias는 자바타입에 대한 간단하고 짧은 이름이며 XML설정에만 관계된다. 이를 통해 클래스 풀네임 대신 지정한 alias로 대신 명시해줄수 있다.<br>

- 클래스 별로 설정
~~~
<typeAliases>
	<typeAlias type="com.example.vo.MemberVO" alias="MemberVO" />
	<typeAlias type="com.example.vo.SampleVO" alias="SampleVO"/>
</typeAliases>
~~~

- 패키지로 설정
~~~
<typeAliases>
	<package name="com.example.vo"/> 
</typeAliases>
~~~

- annotation으로 설정
~~~
@Alias("alias name")
~~~

- mapper.xml에서 parameterType이나 resultType으로 해당 alias를 사용하면 된다.<br>
~~~
<select id="getLikeHate" parameterType="SampleVO" resultType="java.util.HashMap">
	select * from tbl_member where userid = #{userid}
</select>
~~~

- 참고<br>
· https://sas-study.tistory.com/193<br>
· https://wondongho.tistory.com/72<br>