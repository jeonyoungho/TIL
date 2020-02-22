※ 스프링의 주요 특징
- POJO 기반의 구성
- 의존성 주입을 통한 객체 간의 관계 구성
- AOP(Aspect-Oriented-Programming) 지원
- 편리한 MVC 구조
- WAS의 종속적이지 안흥ㄴ 개발 환경

※ Java Configuration을 하는 경우
1. web.xml 및 spring디렉토리(servlet-context.xml , root-context.xml)삭제
2. pom.xml에 mavenC-war-plugin 추가 및 스프링 버전, 컴파일 관련 버전 수정 후 Update Project<img width="773" alt="maven-war-plugin 추가" src="https://user-images.githubusercontent.com/44339530/74820272-18137a80-5345-11ea-83dc-df22819976c3.png">

3. config package 생성 후 RootConfig , WebConfig 클래스 추가
· Java 설정을 이용하는 경우 xml대신 설정 파일을 직접 작성해야만한다.
· @Configuration이라는 어노테이션을 이용해 해당 클래스의 인스턴스를 이용해서 설정 파일을 명시
· Webconfig 클래스는 3개의 추상 메서드를 오버라이드 (getRootConfigClasses(), getServletConfigClasses(), getServletMappings()) 하도록 작성된다.
· getRootConfigClasses() 는 root-context.xml을 대신하는 클래스를 지정<br><img width="257" alt="config 패키지생성" src="https://user-images.githubusercontent.com/44339530/74820285-1e095b80-5345-11ea-9696-ec302d1b4db5.png"><img width="738" alt="RootConfig" src="https://user-images.githubusercontent.com/44339530/74820288-1ea1f200-5345-11ea-9bf3-fec10b0c44a5.png"><img width="600" alt="Webconfig" src="https://user-images.githubusercontent.com/44339530/74820292-1f3a8880-5345-11ea-8269-297d0d610365.png">



