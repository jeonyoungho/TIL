<h2> HikariCP를 사용한 DataSource 설정 </h2>

1. pom.xml에 HikariCP를 추가<br>
· pom.xml<img width="844" alt="hikariDatasource-pom xml추가" src="https://user-images.githubusercontent.com/44339530/75091857-4eefc780-55b5-11ea-8d17-44c4209d4715.png">

2. root-context.xml or RootConfig클래스 설정<br>
· root-context.xml<img width="844" alt="root-context xml 작성" src="https://user-images.githubusercontent.com/44339530/75091858-51522180-55b5-11ea-8bbb-dee3c18e1b24.png"><br>· RootConfig.java<img width="844" alt="RootConfig 클래스작성" src="https://user-images.githubusercontent.com/44339530/75091862-531be500-55b5-11ea-98ac-9762474af3b7.png">

3. Junit Test<br>
· DataSourceTests.java<img width="844" alt="JunitTest" src="https://user-images.githubusercontent.com/44339530/75091863-544d1200-55b5-11ea-97c1-6e3035b9ae09.png">