<h1>Apache Tiles탄생배경</h1>
-중복된 코드를 제거하기 위한 Apache에서 제공하는 오픈소스<br>

<h1>필요성</h1>
-웹사이트가 500개 이상의 동적,정적 뷰가 존재 할시 Tiles프레임워크를 사용 할 수 있다.<br>
-기본적인 틀(template)을 디자인하고 틀에 컨텐트들을 넣는 구조<br>
-만약 layout변경사항시 layout에 관계된 페이지의 내용만 변경 하면 된다.<br>
<img width="844" alt="스크린샷 2020-03-31 오후 4 24 34" src="https://user-images.githubusercontent.com/44339530/77998558-3ae57580-736c-11ea-9f4b-4d3c03e809d3.png">
<br>
-이런 템플릿을 만들어주면 실제로 rendering할때 동적인 부분이 주입이 되어서 하나의 페이지가 그려지게 되어있음<br>

<h1>Apache Tiles란?</h1>
-자바 어플리케이션을 위한 무료 오픈 소스 templating framework<br>
-MVC구조와 함께 할 수 있는 쉽고 가장 우아한 복잡한 웹사이트를 위한 방법<br>
-runtime시에 page조각들을 구성해서 하나의 완성된 페이지를 내게됨<br>
-전체 application이 일관성 있는 look and feel을 가질 수 있음<br>
<img width="844" alt="스크린샷 2020-03-31 오후 4 30 18" src="https://user-images.githubusercontent.com/44339530/77998987-e989b600-736c-11ea-841f-00a5fc38d20e.png">
<br>

<h1>Tiles의 개념 및 용어</h1>
1.Page Template<br>
<img width="844" alt="스크린샷 2020-03-31 오후 4 31 21" src="https://user-images.githubusercontent.com/44339530/77999068-0f16bf80-736d-11ea-97d5-c553e7ddd13a.png">
<br>

2.Tiles Definition<br>
<img width="844" alt="스크린샷 2020-03-31 오후 4 34 34" src="https://user-images.githubusercontent.com/44339530/77999359-82203600-736d-11ea-962d-c0d9ffc2251a.png">
<br>
- 하나의 웹페이지를 정의하는데 템플릿과 각각의 attribute를 정의 해줌<br>
- definition에 정의된 내용들을 바탕으로 하나의 웹페이지를 만들어줄 수 있음<br>
- attribute를 확장하여 상속이라는 개념을 활용할 수 있다<br>

<h1>Tiles의 사용</h1>
1.pom.xml에 tiles 라이브러리 추가<br>
<img width="844" alt="스크린샷 2020-03-31 오후 4 39 34" src="https://user-images.githubusercontent.com/44339530/77999787-35892a80-736e-11ea-9b66-fb1cdd8e7ff0.png"><br>

2.Spring 설정<br>
<img width="844" alt="스크린샷 2020-03-31 오후 4 41 30" src="https://user-images.githubusercontent.com/44339530/77999965-7a14c600-736e-11ea-84c0-ad95cc78bc2a.png"><br>
-기존의 InternalResourceViewResolver를 지우고 TilesViewResolver를 추가<br>
-TilesConofigurer bean추가<br>
-TilesViewResolver : logical view name을 tiles definition과 매핑시켜주는 역할<br>
-TilesConofigurer:definition을 정의해주는 부분<br>

3.Template에 관련된 layout정의<br>
<img width="844" alt="스크린샷 2020-03-31 오후 4 45 59" src="https://user-images.githubusercontent.com/44339530/78000351-1a6aea80-736f-11ea-9708-6859f4794bca.png">
<br>

4.각각의 웹페이지마다 definition 정의<br> 
<img width="844" alt="스크린샷 2020-03-31 오후 4 47 18" src="https://user-images.githubusercontent.com/44339530/78000470-49815c00-736f-11ea-8de3-2c34bb3a7be1.png"><br>
-home은 base라는 definition을 그대로 상속을 받아서 title , content를 재정의<br>

  

