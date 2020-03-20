<h1>BootStrap개요</h1>
https://getbootstrap.com/<br>
 
<h1>BootStrap의 두 가지 특징</h1> 
1.반응형(responsive) 웹페이지<br>
-페이지로 다양한 디바이스(데스크탑, 테블릿pc, 모바일)나 브라우저의 크기에 맞춰서 적절한 사이즈로 보여주는 방식<br>

-사이즈가 줄어들게 되면 Stacked to Horizontal 방식으로 세로 방향으로 줄어들게 됨<br>

2.Mobile-first<br>
-웹페이지를 디자인할때 모바일 환경을 먼저 고려해야한다. 모바일 환경에서 꼭 필요한 컨텐트들을 고려하고 브라우저 사이즈가 커질때는 추가적으로 해나가는 방식<br> 

-작은 가로부터 큰 가로폭 순서로 만들고, min-width를 이용함<br>

<img width="844" alt="스크린샷 2020-03-17 오후 5 50 09" src="https://user-images.githubusercontent.com/44339530/76838823-cf81ab00-6877-11ea-9161-794b69d38739.png"><br>
->먼저 스타일링을 설정해주고 기본일 경우엔 A를 적용하고 최소 768이상이면 B라는걸 적용시키고 더 큰경우에는 C를 적용하는 스타일<br>

<h1>Example</h1>
<img width="844" alt="스크린샷 2020-03-17 오후 5 56 17" src="https://user-images.githubusercontent.com/44339530/76839318-a7467c00-6878-11ea-86df-d212c04ce44a.png"><br>
<img width="844" alt="스크린샷 2020-03-17 오후 5 55 59" src="https://user-images.githubusercontent.com/44339530/76839329-a9a8d600-6878-11ea-9f15-bb89c991a8bf.png"><br>
->기본정의를 해놓고 (A)만약에 미디어가 480이하(작은 디바이스)면 jb-container의 width속성을 auto로 하라고 속성을 재정의<br>

※(desktop-first -> 데스트탑 환경에 맞게 디자인을 하고 사이즈가 줄을땐 필요없는걸 빼나가는 방식, 큰 가로폭부터 작은 가로폭 순서로 만들고,max-width를 이용)<br>

<h1>Container</h1>

<img width="844" alt="스크린샷 2020-03-17 오후 6 09 58" src="https://user-images.githubusercontent.com/44339530/76840439-8848e980-687a-11ea-99df-eee3d88f2027.png"><br>

-container-fluid -> row가 꽉 차 있고 사이즈가 변경되도 적절하게 사이즈가 맞춰짐(colum이 가변적으로 움직임)<br>
-container -> 사이즈가 변경되도 colum이 고정되어 있음<br>

<h1>Grid System</h1>

-col-sm-4 -> colum의 배치를 기본적으로 12개로 나누는데 colum을 4:4:4로 3등분하여 배치는 구조<br>
<img width="844" alt="스크린샷 2020-03-17 오후 6 17 15" src="https://user-images.githubusercontent.com/44339530/76841045-8fbcc280-687b-11ea-91ad-c1f6ed03a7b8.png"><br>

-sm -> small devices (576px보다 같거나 큰 디바이스에 대해선 Grid시스템에서 움직이고 작을 경우엔 자동적으로 stack에 쌓이는 구조로 보여짐)<br>
<img width="844" alt="스크린샷 2020-03-17 오후 6 19 08" src="https://user-images.githubusercontent.com/44339530/76841224-d14d6d80-687b-11ea-8dde-c8d3b408a810.png"><br>

-Example
1.576px보다 클 때 그리드 방식적용<br> 
<img width="1216" alt="스크린샷 2020-03-17 오후 6 23 51" src="https://user-images.githubusercontent.com/44339530/76841780-9b5cb900-687c-11ea-9bea-c5444b037658.png"><br>
2.576px보다 작을 때 Stacked to Horizontal 방식 적용<br>
<img width="492" alt="스크린샷 2020-03-17 오후 6 23 00" src="https://user-images.githubusercontent.com/44339530/76841782-9bf54f80-687c-11ea-8a8f-92d31959fdc4.png"><br>

※ 참고자료 
https://www.codingfactory.net/10534<br>
https://www.w3schools.com/bootstrap4/default.asp<br>