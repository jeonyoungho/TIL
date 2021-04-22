# Array&ArrayList&LinkedList의 비교
- ArrayList와 LinkedList는 모두 Java에서 제공하는 List 인터페이스를 구현한 Collection 구현체이며, 인터페이스만 같을 뿐 내부적으로 동작하는 방식은 다르다

## 1) Array
- 같은 타입의 여러 데이터를 하나의 이름으로 그룹핑해서 관리하기 위한 자료구조
- index와 값의 쌍으로 구성
- 논리적 저장 순서와 물리적 저장 순서가 일치함

### Array의 장점
- 만약 인덱스 값을 알고 있다면 Big-O(1)으로 접근 가능
- random access(비순차적 접근)가 가능

### Array의 단점
- 삭제 시, 연속성이 깨지게 됨
- 삭제한 원소보다 큰 인덱스 갖는 원소들을 shift 해줘야 하는 비용 발생 = 시간복잡도 O(n)
- 삽입 시에도 첫번째에 원소를 추가하고 싶다면 1씩 shift해줘야 한다. => O(n)

## 2) ArrayList
- 내부적으로 데이터를 배열에서 관리하며 데이터의 추가, 삭제를 위해 아래와 같이 임시 배열을 생성해 데이터를 복사 하는 방법을 이용한다.
<img width="267" alt="스크린샷 2021-03-24 오후 1 04 01" src="https://user-images.githubusercontent.com/44339530/112253020-6827e380-8ca1-11eb-926c-26e0801951a2.png"><br>

### ArrayList의 장점
- 각 데이터는 인덱스를 가지고 있기에 인덱스를 통해 한 번에 참조가 가능하게 됨

### ArrayList의 단점
- 데이터의 삽입/삭제가 빈번할 경우 그만큼 데이터 복사가 많게 되므로 성능이 저하 될 수 있음

## 3) LinkedList
- 데이터를 저장하는 각 노드가 이전 노드와 다음 노드의 상태만 알고 있다.
- 즉, 각 노드들이 다음에 올 노드의 주소값만 기억하고 있으면 된다.
- tree 구조의 근간
<img width="456" alt="스크린샷 2021-03-24 오후 1 12 51" src="https://user-images.githubusercontent.com/44339530/112253752-a40f7880-8ca2-11eb-81ce-495a562d717a.png">

### LinkedList의 장점
- ArrayList와 같이 데이터의 삽입/삭제시 불필요한 복사가 일어나지 않기에 데이터의 삽입/삭제가 빈번할 경우에 유리

### LinkedList의 단점
- 논리적 저장 순서와 물리적 저장 순서 일치하지 않기 때문에 탐색 시 첫 번째 원소부터 순차적으로 다 확인해야만 함
- 즉, 데이터의 검색시에는 처음부터 노드를 순회해야 하기 때문에 성능상 불리
- 이는 어떤 원소를 추가 및 삭제할 때에도 탐색으로 인한 O(n)의 시간 발생
- 결국 search에도 O(n)의 시간, 삽입/삭제에서도 O(n)의 시간 복잡도를 갖음

## 데이터의 검색, 삽입, 삭제시 성능 비교

### 검색
- Array&ArrayList가 LinkedList에 비해 굉장히 빠름
- Array&ArrayList: 인덱스 기반의 자료 구조이며 get(int index)를 통해 O(1)의 시간복잡도를 가짐
- LinkedList: 모든 요소를 탐색해야 하기 때문에 최악의 경우에는 O(N)의 시간 복잡도를 가짐

### 삽입/삭제
- LinkedList가 Array&ArrayList에 비해 굉장히 빠르다.
- Array&ArrayList: 삽입/ 삭제 이후 다른 데이터를 복사해야 하므로 최악의 경우 O(N)의 시간복잡도를 가지게 됨
    - <b>Array는 크기가 고정되어있지만 ArrayList는 사이즈가 동적이기에 적절히 사용해주면 된다.(ArrayList의 경우는 메모리에 할당 된 공간 외에 추가적으로 삽입이 요청되면 기존 용량의 1.5배만큼 증가시키기 때문이다.)</b>
    - Array는 primitive type(int, byte, char 등)과 object 모두를 담을 수 있지만, ArrayList는 object만 담을 수 있다.
- LinkedList: <b>삽입/삭제할 특정 노드를 탐색하기까지에는 O(N)의 시간 복잡도를 가지지만 단순하게 삽입/삭제하는데에는 이전 노드와 다음 노드를 참조하는 상태만 변경하면 되기에 O(1)의 시간 복잡도를 가짐</b>

## 정리
- 데이터의 삽입/삭제가 빈번할 경우 LinkedList를 사용!
- 데이터의 조회가 빈번할 경우 Array&ArrayList를 사용!
    - 데이터의 크기가 고정되어 있다면 Array사용 / 데이터의 크기가 가변적이라면 ArrayList사용!

#### 출처
- https://heekim0719.tistory.com/274 [별토끼 DEVLOG]