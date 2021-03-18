## Restful API의 개요

### Rest란?
- Representational State Transfer의 약자(상태를 전달한다)
- 웹 표준에 기반한 아키텍처 스타일
- 데이터 커뮤니케이션을 위해서 HTTP프로토콜을 사용
- HTTP 표준 프로톨콜을 사용한 인터페이스를 통해 접근한 리소스를 중심으로 동작<br>
<img width="435" alt="스크린샷 2021-03-18 오후 6 12 05" src="https://user-images.githubusercontent.com/44339530/111601169-74c9b900-8815-11eb-82fd-46c3633aed92.png"><br>

- Rest아키텍처에서 Rest서버는 단순하게 자원에 대한 접근성을 제공하고 Rest클라이언트는 자원에 접근하여 해당 자원을 사용하여 표현한다.
    - HTTP표준 메소드와 URI를 통해서만으로도 어떤 요청인지 알 수 있음
    - REST는 Text, JSON, XML과 같은 다양한 포맷으로 자원을 표현함
    - JSON이 가장 흔하게 사용 되는 포맷<br>

<img width="493" alt="스크린샷 2021-03-18 오후 6 15 24" src="https://user-images.githubusercontent.com/44339530/111601642-ea358980-8815-11eb-83ef-a887861a6826.png"><br>

~~~
GET /api/users - 사용자의 리스트를 리턴
GET /api/users/1 - ID가 1인 특정 사용자를 리턴
POST /api/users - 새로운 유저를 생성
PUT /api/users/3 - ID가3인 유저를 업데이트
DELETE /api/users/4 - ID가 4인 유저를 삭제
DELETE /api/users - 전체 사용자들을 전부 삭제
~~~
- 웹 서비스를 설계 할 때 이와 같이 필요한 자원을 생각해보고 CRUD 인터페이스를 제공하기 위해 이러한 테이블을 만들어주면 좋음
- 클라이언트는 메소드랑 URI를 통해서만으로도 어떤 요청인지 충분히 유추가 가능

### RESTful Web Services
![image](https://user-images.githubusercontent.com/44339530/111601893-2963da80-8816-11eb-89e8-e7c054896325.png)<br>
- Restful web services의 장점으로는 클라이언트와 서버를 완전히 분리해놓음으로써 결합도를 낮출 수 있고(Decoupling) 플랫폼에 의존하지 않게 된다. (안드로이드, 아이폰, 데스크탑이든 어떤 플랫폼이든 HTTP호출만하면 작동 가능하게 됨)
- 서버 단위에서는 단순하게 Rest API만 제공하면 클라이언트쪽에서 API만 호출해서 CRUD연산을 수행하면 됨<br>

<img width="494" alt="스크린샷 2021-03-18 오후 6 20 18" src="https://user-images.githubusercontent.com/44339530/111602352-99726080-8816-11eb-83ca-0e2715503ad9.png"><br>

- 자원에 대한 커맨드가 존재하고 커맨드를 실행하면 응답이 발생하게 되있음
- REST의 주요 3가지 구성요소
    - Method(POST)
    - Resource(Things)
    - Message(201 CREATED)
- 서버가 처리한 다음엔 응답상태를 같이 보내주게 되있음

<img width="438" alt="스크린샷 2021-03-18 오후 6 21 45" src="https://user-images.githubusercontent.com/44339530/111602557-cd4d8600-8816-11eb-9f7c-fc686b1885da.png"><br>

- 객체를 JSON으로 표현하는 (Serialization: Object -> JSON, DeSerialization: JSON -> Object)<br>
<img width="485" alt="스크린샷 2021-03-18 오후 6 23 40" src="https://user-images.githubusercontent.com/44339530/111602811-1271b800-8817-11eb-9832-8a021b2d5c73.png"><br>

- 메시지 response message의 body부분에 데이터가 담겨서 날라가게 되있음

#### HTTP Request메시지
<img width="470" alt="스크린샷 2021-03-18 오후 6 24 38" src="https://user-images.githubusercontent.com/44339530/111602939-33d2a400-8817-11eb-88b8-da2911b86527.png"><br>

- HTTP Request의 5가지 주요한 구성 요소
    - 1. Method(메소드): HTTP 메소드는 GET, POST, DELETE, PUT 등등과 같은 메소드가 있음
    - 2. URI(Uniform Resource Identifier): 서버에 대한 자원을 구분한다
    - 3. HTTP version: HTTP의 버전을 나타냄. 예를 들면, HTTP v1.1
    - 4. Requset Header: key-value형태로서 HTTP Request메시지의 메타데이터를 담고 있음. 예를 들면, 클라이언트(브라우저) 타입, 메시지 body의 포맷, 클라이언트에 의해 지원 되는 포맷, 캐시 설정 등등이 있음
    - 5. Request Body: 메시지 내용이나 자원이 담김

- Header와 Body를 구분하기 위해서 반드시 Blank line이 있어야 함

#### HTTP Response메시지
<img width="481" alt="스크린샷 2021-03-18 오후 6 30 29" src="https://user-images.githubusercontent.com/44339530/111603772-0508fd80-8818-11eb-8039-c3ca2da155a5.png"><br>

- HTTP Response의 4가지 주요한 구성 요소
    - 1. Status/Reponse Code: 요청된 자원에 대한 서버의 상태를 나타냄. 예를 들어, 404는 자원을 찾을 수 없음을 나타내고 200은 응답이 정상임을 나타냄
    - 2. HTTP Version: HTTP의 버전을 나타냄. 예를 들면, HTTP v1.1
    - 3. Response Header: key-value형태로 HTTP Response메시지의 메타 정보를 담고 있음. 예를 들어, content length, content type 등등
    - 4. Response Body: Response message의 내용 또는 자원을 담고 있음


#### Content Negotitaion
- Request(Client): 클라이언트가 받아서 처리할 수 잇는 리스트를 보내주면 서버가 이를 참고하여 보내주게 됨
    - ex. Accept: text/html, application/xhtml+xml, application/xml
- Response(Server): 서버가 보내주는 자원에 대한 종류를 보내주게 됨
    - ex. Content-Type: text/html; charset=UTF-8

#### HTTP Status/Response Codes
~~~
2xx Success (201 CREATED, 200 OK)
3xx Redirection (303 See other)
4xx Client error(404 NOT FOUND)
5xx Server error (500 Internal Server Error)
~~~

#### Rest아키텍처에서의 URI
- Rest 아키텍처에서 각 자원은 URI에 의해 식별 됨<br>
<img width="413" alt="스크린샷 2021-03-18 오후 6 43 03" src="https://user-images.githubusercontent.com/44339530/111605604-c70cd900-8819-11eb-96c1-c94533ba0e6a.png"></br>

- URI생성 규칙
    - 1. 자원을 표현할때 복수형을 사용해야함
    - 2. 스페이스는 피해야함 (하이픈('-')이나 언더바('_')를 써야함)
    - 3. 소문자를 사용해야함
    - 4. 호환성을 유지해아함(예전 URI로 리퀘스트가 와도 새로운 URI로 리다이렉트를 시켜줘야함)
    - 5. HTTP Verb메서드를 사용해서 자원에 대한 Operation을 사용해야함(URI의 Operation name을 주는 것을 피해야함)

    - 예시<br>
    - <img width="476" alt="스크린샷 2021-03-18 오후 6 44 59" src="https://user-images.githubusercontent.com/44339530/111605907-0c310b00-881a-11eb-96ef-e82a246a6bac.png"><br>

#### Rest아키텍처에서의 Methods
<img width="394" alt="스크린샷 2021-03-18 오후 6 45 50" src="https://user-images.githubusercontent.com/44339530/111606027-2a970680-881a-11eb-9fd0-15ecb2c0f271.png"><br>

- OPTIONS메서드: 서버 단위에서 지원하는 메서드가 뭔지 조회하는 메서드
- HEAD메서드: 서버가 Body 정보를 제외한 Header 정보만 보내준다 
(네이버에 접속 할 때마다 네이버에서 웹 페이지를 가져오지 않는다.
보통 캐슁을 하게 돼있는데 서버에 업데이트 되었는지 확인 할 필요가 있다.
업데이트 되있으면 서버에서 가져오면 되는거고 업데이트 안되었으면 로컬 캐쉬에 보관하고 있는것만 렌더링해주면 되기 때문이다. 헤더정보만 요청해서 수정된 시간이나 여부만 파악할 때 HEAD메서드를 사용할 수 있다.)

#### Rest아키텍처에서의 Statelessness (상태를 유지하지 않는다)
- Client -> LB -> Server1 ,Server2
- Restful Web Service는 클라이언트의 상태를 서버에서 유지하지 않음
    - 백만명 요청에 대해서 상태를 유지 한다는건 어마어마한 부담이기에 상태를 유지하지 않으며 이전에 어떤 정보를 요청했는지 등등 을 알 수 없다
    - 클라이언트 요청을 1번에도 보낼 수 있고 2번에도 보낼 수 있지만 상태를 유지한다고 하면 다음 번 요청도 반드시 Server1에 보내야함
- 단, 서버에서 클라이언트에 대한 정보를 유지해야 될 경우도 있음
    - 예를 들어, 서버에서 클라이언트가 로그인된 상태인지 아닌지 파악하여야 하기 때문에 그럴 경우에는 내부적인 Context이런 서버에서 유지하고 다만 클라이언트쪽에 세션id를 너어주면 브라우저가 쿠키에 세션id를 유지하게 됨, 세션에 대한 정보는 서버에서 유지하지만 id에 대한 정보는 클라이언트에서 제공하게 됨

- Statelessness의 이점
    - 1. 각각의 Request를 독립적으로 처리 할 수 있음
    - 2. 클라이언트의 이전 request에 대한 정보를 유지 하지 않음
    - 3. HTTP 자체가 statelessness 프로토콜이기에 Restfule 웹 서비스도 HTTP프로토콜과 부드럽게 통합이 될 수 있음

#### Rest아키텍처에서의 Caching
<img width="491" alt="스크린샷 2021-03-18 오후 6 51 17" src="https://user-images.githubusercontent.com/44339530/111606735-ece6ad80-881a-11eb-8857-abfd3d0c4a22.png"><br>

- 캐슁이란 서버 응답을 클라이언트 자체가 저장하고 있는 것
- 브라우저는 내부적으로 캐슁을 하기에 동일한 자원에 대해서 반복적으로 서버에 요청 할 필요가 없음
    - 처음에 네이버에 접속하게 되면 브라우저에 캐슁 하기 때문에 두번째, 세번째 요청 할 때는 캐슁 되어있는 걸 보여주면 되고 굳이 다시 받아 올 필요가 없음
- 서버가 응답을 할 때 어떻게 캐슁을 해야하는지 정책을 내려줌(Response 헤더에 cache-control이라는 캐슁 정책을 설정해서 보내주게 됨)

- 캐슁 정책<br>
- <img width="491" alt="스크린샷 2021-03-18 오후 6 52 56" src="https://user-images.githubusercontent.com/44339530/111606981-27e8e100-881b-11eb-926a-86d8e0b1f7f1.png"><br>
    - 1. Public: 서버에서 오는 Response자원이 어떤 컴포넌트든 캐슁 할 수 있음
    - 2. Private: 클라이언트나 서버만이 캐슁 할 수 있음, 중간에 있는 무언가는 캐슁 할 수가 없음(CDN을 통해서 서버에 request를 요청 하는 경우가 있는데 CDN과 같은 장치는 캐슁 할 수 없음)
    - 3. No-store: 절대 캐쉬 할 수 없음
    - 4. Max-age: 캐슁이 언제까지 유효한지 명시(초 단위)
    - 5. Must-revalidate: max-age가 지났을 경우 자원이 유효하지 안한지에 한해서 서버에 꼭 물어봐야 함
    - <img width="483" alt="스크린샷 2021-03-18 오후 6 55 49" src="https://user-images.githubusercontent.com/44339530/111607408-8f069580-881b-11eb-9c6e-9516f232055e.png"><br>

#### CDN(content delivery network)
<img width="431" alt="스크린샷 2021-03-18 오후 6 54 25" src="https://user-images.githubusercontent.com/44339530/111607193-5d8dca00-881b-11eb-80cf-0de7662f3589.png"><br>

- Request를 보내게 되면 보통 CDN을 거치고 CDN도 캐슁을 하기 때문에 CDN이 캐쉬에 있으면 바로 응답을 하게 됨
- 은행 계좌 조회, 학교 학점 조회 -> 개인적인 정보이기 때문에 CDN에서 캐슁을 하기엔 곤란하기 때문에 Private이란 속성을 주게 되면 CDN이 캐슁할 수 없음

<img width="494" alt="스크린샷 2021-03-18 오후 6 56 23" src="https://user-images.githubusercontent.com/44339530/111607488-a34a9280-881b-11eb-9784-61f324e12ca1.png"><br>

- max-age가 지나면 update됐는지 체크해봐야 하는데 체크하는 방법으로 ETag를 사용함
- ETag는 리소스에 대한 버전과 같은 개념
- 만약 리소스가 변경되면 ETag가 달라지게 됨
- 예를 들어, 120초가 지나면 반드시 수정됐는지 아닌지 체크해봐야하는데 서버에 ETag값을 보내 리소스가 변경 되었다면 서버로 부터 새로 받아오고 그대로 라면 기존 캐슁되어있는 데이터를 사용하면 됨
- 서버 단에서 캐쉬에 대한 정책을 설정해줘야 함

#### Rest아키텍처에서의 Security
- API를 공개하기 때문에 누구나 들어 올 수 있음. 그러기에 반드시 보안 사항도 고려해야함.
    - 1. Validation: 사용자가 보내는 모든 input에 대해서 검증해야 함. SQL인젝션 공격에 대해 보호해야 함.
    - 2. Session Based Authentication: 인증 할 경우에 세션에 기반해서 인증해야 함
    - 3. URL속 민감한 데이터를 넣어선 안됨(POST메서드를 사용해서 Body부문에 담아 보내줘야 함)
    - 4. 메소드 실행에 대해 제한을 가해야 함(GET메서드로 데이터를 삭제해선 안됨)
    - 5. XML/JSON데이터에 대해 검증해야 함
    - 6. 에러 처리를 잘해야 한다(클라이언트가 잘 못했을때 에러가 발생할수도 있고 서버쪽에서도 Exception이 발생할 수 있는데 ExceptionHandler를 만들어서 이를 잘 처리해줘야 함)

