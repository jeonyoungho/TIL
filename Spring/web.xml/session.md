<h1>session설정</h1>

- 세션: 웹 서버쪽의 웹 컨테이너에 상태를 유지하기 위한 정보<br>
- web.xml에 세션 유지 시간 지정 (단위: 분)<br>
- 디폴트는 30분<br>
![image](https://user-images.githubusercontent.com/44339530/98612902-83b8d000-2338-11eb-83ee-e448a587245f.png)<br>

- 참고 https://codedragon.tistory.com/5124

<h1>Spring Security 동시 세션 제어</h1>
- session timeout만료 후에도 session이 제거되지 않는 현상을 해결<br>
- SessionDestroyedEvent 이벤트를 발생시켜주는 Event Publisher인 스프링에서 제공하는 HttpSessionEventPublisher를 listener로 다음과 같이 web.xml에 등록<br>
![image](https://user-images.githubusercontent.com/44339530/100308201-1e790600-2feb-11eb-9e7d-5872c1cae235.png)<br>
- HttpSessionEventPublisher가 session이 만료되는 것을 리스닝하면서 만료될때마다 sessionDestroyed를 호출하여 sesseion을 종료심<br>
