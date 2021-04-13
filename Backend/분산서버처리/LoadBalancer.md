# LoadBalancer(로드 밸런서)

## 로드 밸러서란?
- 부하 분산을 위해 가상 IP를 통해 각 서버에 Request를 분배하는 기능
- 즉, 대용량 트래픽을 장애없이 처라히기 위해 여러 대의 서버에 적절히 트래픽을 분배해주는 기능<br>

### 로드 밸런서의 예시
![1](https://user-images.githubusercontent.com/44339530/114534656-3398bd80-9c8a-11eb-8366-67d2d3413fde.png)<br>
- 다음 그림과 같이 Request A~F는 로드 밸런서 알고리즘에 의해 적절하게 분배된다.
- 만약 라운드 로빈 방식으로 분배된다하면 다음과 같이 분배된다.<br>
![2](https://user-images.githubusercontent.com/44339530/114535042-99854500-9c8a-11eb-8c20-263fcc741830.png)

## 로드 밸런서 사용 시의 이슈 중 세션 관리 문제
- 로드 밸런서에 의해 이전 요청과 현 요청에 분배되는 서버가 다를 경우 세션 관리 일관성에 문제가 발생한다.<br>

### 로드 밸런서 세션 관리 문제의 예시
![2](https://user-images.githubusercontent.com/44339530/114535272-d6e9d280-9c8a-11eb-8cf8-05e5b9c66332.gif)<br>
- 1) 먼저 로그인 요청을 A서버로 보낸다. A서버의 세션에는 martin이라는 값이 저장되고 A서버에서는 로그인 성공이라는 응답을 보낸다.
- 2) 동일한 클라이언트는 다시 로드 밸런서에 유저 정보를 조회하는 요청을 보낸다.
- 3) 하지만 이 요청은 로드 밸런서를 통해 C서버로 전달되며 C 서버의 세션에는 이 사용자가 로그인 한 사용자라는 정보가 없기에 조회 실패 후 로그인 페이지로 리다이렉트 될 것이다. 결국 로그인 했는데 또 로그인 하라는 사태가 발생할 것이다.

## 로드 밸런서 사용 시의 이슈 중 세션 관리 문제 해결법
### 1) sticky session
- sticky session이란 로드 밸런서가 세션id에 기반하여 특정 서버로 보내주는 방식이다.(특정 세션의 요청을 처음 처리한 서버로만 보내는 방식)<br>
- ![3](https://user-images.githubusercontent.com/44339530/114535913-82932280-9c8b-11eb-892f-24167fefbd8f.gif)<br>
    - 1) vsh123, oeeen은 모두 로드 밸런서를 통해 첫 요청을 보낸다.
    - 2) vsh123의 첫요청은 C서버로, oeeen의 첫요청은 B서버로 로드 밸런싱 되었다.
    - 3) 그 이후로 들어오는 vsh123의 모든 요청은 C서버로, oeeen의 모든 요청은 B서버로 가게 된다.

- 특정 서버로 요청 처리를 고정시키는 방법은 두 가지가 존재한다.
    - 1) Cookie를 사용하는 방법
    - 2) 클라이언트의 IP tracking 방법
    - [참고로 AWS ELB는 cookie를 사용하여 Http Response에 cookie를 이용해서 해당 클라이언트가 가야하는 EC2 instance의 정보를 저장해두고 그걸 활용하여 특정 EC2로 요청을 고정한다.](https://aws.amazon.com/ko/blogs/aws/new-elastic-load-balancing-feature-sticky-sessions/)

#### sticky session의 단점
- 로드밸런싱이 잘 동작하지 않을 수 있다.
- 특정 서버만 과부하가 올 수 있다.
- 특정 서버 Fail시 해당 서버에 붙어 있는 세션들이 소실될 수 있다.

### 2) Session Clustering기법
- sticky session의 단점을 보완할 수 있는 방식으로 여러 WAS의 세션을 동일한 세션으로 관리하는 방식이다.<br>
![4](https://user-images.githubusercontent.com/44339530/114536656-414f4280-9c8c-11eb-8ecf-d9fbaf570765.gif)<br>
- 각 WAS들은 세션을 각각 가지고 있지만, 이를 하나로 묶어 클러스터로 관리하는 것이다. 이 상태에서 하나의 WAS가 다운되어도 WAS가 들고 있던 세션은 다른 WAS로 이동되어 그 WAS가 해당 세션을 관리한다.

#### Session Clustering기법의 단점
- scale out 관점에서 새로운 서버가 하나 뜰 때마다 기존에 존재하던 WAS에 새로운 서버의 IP/Port를 입력해서 클러스터링해줘야 하는 번거로움이 있다.
- 위의 이유로 새로운 서버를 띄울 경우 기존 서버에 수정이 발생하고, 휴먼 에러가 발생할 가능성도 충분히 존재한다.

### 3) 세션 서버를 분리하는 방식
- Session Clustering기법의 단점을 극복하기 위해 Redis와 같은 세션 서버를 별도로 분리하는 방식이다.<br>
![5](https://user-images.githubusercontent.com/44339530/114536954-99864480-9c8c-11eb-9800-d9422ad625d9.png)<br>
- 새로운 서버를 띄우더라도 해당 서버에만 세션 서버의 정보를 적어주고 연결 해주면 되기 때문에 scale out시 기존 서버의 수정이 발생하지 않게 된다. 
- 그리하여 Redis Session 서버의 중요성이 점점 올라가며, 해당 세션 서버가 죽는 순간 모든 세션이 사라지기에 Redis 서버의 Replication설정도 필수로 수반된다.

#### 출처
- https://smjeon.dev/web/sticky-session/