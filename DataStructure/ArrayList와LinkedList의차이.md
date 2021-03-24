# ArrayList와 LinkedList의 차이
- 모두 Java에서 제공하는 List 인터페이스를 구현한 Collection 구현체
- 인터페이스만 같을 뿐 내부적으로 동작하는 방식은 다르다

## ArrayList
- 내부적으로 데이터를 배열에서 관리하며 데이터의 추가, 삭제를 위해 아래와 같이 임시 배열을 생성해 데이터를 복사 하는 방법을 이용한다.
<img width="267" alt="스크린샷 2021-03-24 오후 1 04 01" src="https://user-images.githubusercontent.com/44339530/112253020-6827e380-8ca1-11eb-926c-26e0801951a2.png"><br>

#### ArrayList의 장점
- 각 데이터는 인덱스를 가지고 있기에 인덱스를 통해 한 번에 참조가 가능하게 됨

#### ArrayList의 단점
- 데이터의 삽입/삭제가 빈번할 경우 그만큼 데이터 복사가 많게 되므로 성능이 저하 될 수 있음

## LinkedList
- 데이터를 저장하는 각 노드가 이전 노드와 다음 노드의 상태만 알고 있다.
<img width="456" alt="스크린샷 2021-03-24 오후 1 12 51" src="https://user-images.githubusercontent.com/44339530/112253752-a40f7880-8ca2-11eb-81ce-495a562d717a.png">

#### LinkedList의 장점
- ArrayList와 같이 데이터의 삽입/삭제시 불필요한 복사가 일어나지 않기에 데이터의 삽입/삭제가 빈번할 경우에 유리

#### LinkedList의 단점
- 데이터의 검색시에는 처음부터 노드를 순회해야 하기 때문에 성능상 불리

## 데이터의 검색, 삽입, 삭제시 성능 비교

#### 검색
- ArrayList가 LinkedList에 비해 굉장히 빠름
- ArrayList: 인덱스 기반의 자료 구조이며 get(int index)를 통해 O(1)의 시간복잡도를 가짐
- LinkedList: 모든 요소를 탐색해야 하기 때문에 최악의 경우에는 O(N)의 시간 복잡도를 가짐

#### 삽입/삭제
- LinkedList가 ArrayList에 비해 굉장히 빠르다.
- ArrayList: 삽입/ 삭제 이후 다른 데이터를 복사해야 하므로 최악의 경우 O(N)의 시간복잡도를 가지게 됨
- LinkedList: 이전 노드와 다음 노드를 참조하는 상태만 변경하면 됨, O(1)의 시간 복잡도를 가짐

## 정리
- 데이터의 삽입/삭제가 빈번할 경우 LinkedList를 사용!
- 데이터의 조회가 빈번할 경우 ArrayList를 사용!
