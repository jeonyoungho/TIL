# &lt;Component-Scan&gt;

- base-package에 지정된 패키지를 풀스캔하여 bean으로 등록

~~~
<context:component-scan base-package="com.esum.web" use-default-filters="false">
	<context:include-filter type="annotation" expression="org.springframework.sterotype.Controller" />
</context:component-scan>
~~~

- include-filter와 exclude-filter를 통해 자동 스캔 대상에 포함 시킬 클래스와 포함시키지 않을 클래스를 지정 할 수 있다.<br>
- use-default-filters='false'속성은 expression에 지정된 타입만 포함할 수 있다.<br>
- 참고 https://codedragon.tistory.com/9017