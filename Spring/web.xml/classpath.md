<h1>Spring의 classpath란?</h1>

- spring의 web.xml설정 중 classpath를 통해 파일 위치를 설정할 수 있다.<br>

- 이클립스의 프로젝트 우클릭 => build path => configure build path => 가장 왼쪽의 soucre메뉴를 클릭하면 classpath를 확인 할 수 있다.<br>

![image](https://user-images.githubusercontent.com/44339530/98516485-e619be00-22af-11eb-9634-2a5ad2ed26dd.png)<br>


- root-context.xml과 servlet-context.xml을 해당 경로에 위치 시켜두고<br>
![image](https://user-images.githubusercontent.com/44339530/98517388-31809c00-22b1-11eb-91dd-1ab3f3e4e85e.png)<br>

- web.xml 설정을 다음과 같이 바꾸면 정상적으로 해당 파일들을 불러들일 수 있다.<br>
![image](https://user-images.githubusercontent.com/44339530/98517509-5d9c1d00-22b1-11eb-89ce-63111457c9c6.png)<br>

- 참고 https://developer-joe.tistory.com/225




