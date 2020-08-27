1. pom.xml 에 spring-test, lombok, log4j 라이브러리 추가(Log4j는 1.2.15로 설정되어 있으므로 1.2.17버전을 추가하고 기존라이브러리는 삭제)
<img width="628" alt="라이브러리 세개 추가" src="https://user-images.githubusercontent.com/44339530/74830097-bf98a900-5355-11ea-8e48-75ff16140b28.png">
2. 기존 junit라이브러리 버전 4.12로 변경
<img width="430" alt="junit버전변경" src="https://user-images.githubusercontent.com/44339530/74830104-c2939980-5355-11ea-8bdd-fedb03b6996f.png">
3. Chef 클래스 및 Restaurant클래스 작성
4. xml 방식 ->root-context.xml , java 방식 -> RootConfig클래스 를 통하여 Bean설정
<img width="684" alt="root-context수정" src="https://user-images.githubusercontent.com/44339530/74830107-c3c4c680-5355-11ea-839f-00e626fb1aa8.png">
<img width="656" alt="RootConfig클래스수정" src="https://user-images.githubusercontent.com/44339530/74830106-c32c3000-5355-11ea-9d0c-f625693c0d79.png">
5. Junit 테스트 설정
※//pom.xml의 junit과 spring-test의 <scope>test</scope>를 지워야 import됨
<img width="482" alt="sprnig-test" src="https://user-images.githubusercontent.com/44339530/74830030-99730900-5355-11ea-87ac-6788a01c8651.png">
<img width="330" alt="junit" src="https://user-images.githubusercontent.com/44339530/74830034-9b3ccc80-5355-11ea-9776-5d96baf9cbee.png">
<img width="1099" alt="xml-junittest" src="https://user-images.githubusercontent.com/44339530/74830038-9bd56300-5355-11ea-9024-b9ee8ee7c92d.png">
<img width="1051" alt="java-junittest" src="https://user-images.githubusercontent.com/44339530/74830043-9d9f2680-5355-11ea-8143-8af7314921c2.png">






