<h1>Tomcat 배포 오류</h1>
Tomcat War파일 배포시 위 처럼 업로드가 되지 않을때<br>
<img width="892" alt="톰캣에러브라우저" src="https://user-images.githubusercontent.com/44339530/76384429-2b44c380-63a2-11ea-96c2-de0294742d2e.png"><br>

Tomcat의 Log파일을 확인해보니<br>
<img width="739" alt="cat manager" src="https://user-images.githubusercontent.com/44339530/76384433-2d0e8700-63a2-11ea-9201-06d3e5e2d62d.png"><br>

위와 같이 SizeLimitExceededException이 발생하였다.<br>
톰캣의 최대 파일 업로드 사이즈가 52428800인데 65705825크기의 파일을 업로드하려하니 Exception이 발생하였다.<br>
<img width="1292" alt="tomcat-log" src="https://user-images.githubusercontent.com/44339530/76384434-2ed84a80-63a2-11ea-9ee3-ab3002352a94.png"><br>

webapps/manager/WEB-INF/web.xml을 확인해보니<br>
<img width="542" alt="vi" src="https://user-images.githubusercontent.com/44339530/76384449-339cfe80-63a2-11ea-855d-594ce3436f07.png"><br>

최대 파일 업로드사이즈가 52428800로 설정되있었음<br>
<img width="413" alt="수정전" src="https://user-images.githubusercontent.com/44339530/76384447-33046800-63a2-11ea-9a24-f17a2f258eba.png"><br>

이를 65705825로 수정 하여 해결<br>
<img width="817" alt="수정후" src="https://user-images.githubusercontent.com/44339530/76384450-339cfe80-63a2-11ea-9b1a-12d1e01145a7.png"><br>

참고자료 https://midas123.tistory.com/231