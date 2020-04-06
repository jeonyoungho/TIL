1.Spring form tag를 사용하여 Data Buffering구현<br>
-사용자가 열심히 form을 작성했는데 error발생시 처음부터 다시 다 작성하기에는 너무 수고 많이 들기에 데이터를 다시 input테그에 넣어줌<br>
<img width="844" alt="스크린샷 2020-04-06 오후 5 39 54" src="https://user-images.githubusercontent.com/44339530/78539496-a1b3d480-782d-11ea-877d-7231e890ecb7.png"><br>

2.spring의 form tag로 변경<br>
-modelAttribute에 있는 값으로 나중에 Buffering이 구현됨<br>
-name을 path로 변경<br>
<img width="581" alt="스크린샷 2020-04-06 오후 5 44 52" src="https://user-images.githubusercontent.com/44339530/78539939-53530580-782e-11ea-9a1a-4f5927632e47.png">

3.예시)<br>
<img width="844" alt="스크린샷 2020-04-06 오후 5 47 57" src="https://user-images.githubusercontent.com/44339530/78540201-c0ff3180-782e-11ea-9473-a7f3b1b82e6c.png"><br>

4.TIP)
-View에서 Model에 있는 객체를 가져와서 Buffering 시켜주는 구조<br>
<img width="844" alt="스크린샷 2020-04-06 오후 5 57 26" src="https://user-images.githubusercontent.com/44339530/78541118-16880e00-7830-11ea-875b-b8dfb7779941.png"><br>

-따라서 form을 보여주는 컨트롤러에서 빈 객체를 모델에 남아서 넘겨줘야함<br>

<img width="576" alt="스크린샷 2020-04-06 오후 5 55 42" src="https://user-images.githubusercontent.com/44339530/78540944-d6c12680-782f-11ea-9390-1407ec6852be.png"><br>
-이때는 DataBinding이 이뤄지므로 자동적으로 model에 객체가 담김<br>


