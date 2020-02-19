1. pom.xml 에 spring-test, lombok, log4j 라이브러리 추가(Log4j는 1.2.15로 설정되어 있으므로 1.2.17버전을 추가하고 기존라이브러리는 삭제)

2. 기존 junit라이브러리 버전 4.12로 변경

3. Chef 클래스 및 Restaurant클래스 작성

4. xml 방식 ->root-context.xml , java 방식 -> RootConfig클래스 를 통하여 Bean설정

5. Junit 테스트 설정
※//pom.xml의 junit과 spring-test의 <scope>test</scope>를 지워야 import됨

