# @Bean vs @Component

### @Bean 어노테이션
- 개발자가 컨트롤이 불가능한 외부 라이브러리들을 Bean으로 등록하고 싶은 경우에 사용
- 예를 들어, RestTemplate 등

### @Component 어노테이션
- 직접 컨트롤이 가능한 Class들을 Bean으로 등록하고 싶은 경우에 사용

### 개발자가 생성한 Class에 @Bean은 설정이 가능한가?
- 불가능하다.
- <b>@Bean과 Component는 각자 선언할 수 있는 타입이 정해져 있어</b> 해당 용도외에는 컴파일 에러를 발생시킨다.<br>

<img width="691" alt="스크린샷 2021-05-03 오후 4 08 04" src="https://user-images.githubusercontent.com/44339530/116849471-c5f90500-ac29-11eb-81b1-3dc430f143cd.png"><br>

#### 출처
- https://jojoldu.tistory.com/27