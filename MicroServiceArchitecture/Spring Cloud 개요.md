# Spring Cloud 개요

## Spring Cloud란?
- 마이크로서비스의 개발, 배포, 운영에 필요한 아키텍처를 쉽게 구성할 수 있도록 지원하는 Spring Boot기반의 프레임워크
- <b>즉, MSA구성을 지원하는 Springboot기반의 프레임워크이다.</b>

## Spring Cloud의 핵심 Component
![3](https://user-images.githubusercontent.com/44339530/115350118-a86e7900-a1ef-11eb-86bc-f6383465687e.png)<br>

#### Spring cloud 컴포넌트 메뉴얼
- Spring cloud는 Spring community인 spring.io에서 오픈소스 프로젝트로 관리되고 있다.
- http://spring.io 를 들어가셔서 Projects > Spring Cloud에서 자세하게 확인 가능하다.<br>
- https://docs.spring.io/spring-cloud/docs/current/reference/html/

- <b>중요) Spring Cloud Netflix의 zuul, hystrix, ribbon EOS(End Of Service)</b>
- 2018년 12월부터 아래 component들은 Maintenance모드(기능 업그레이드 없고 유지만 함)로 들어갔습니다.
- 또한 Springboot 2.4.X부터는 더 이상 지원하지 않습니다.
- 따라서 대체되는 다른 Component들을 사용하는것이 좋습니다.
- https://spring.io/blog/2018/12/12/spring-cloud-greenwich-rc1-available-now<br>

#### 출처
- https://happycloud-lee.tistory.com/207?category=902419