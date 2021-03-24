## Collection

### Collection의 개념
- Java에서 컬렉션(Collection)이란 데이터의 집합, 그룹을 의미
- JCF(Java Collections Framework)는 이러한 데이터, 자료구조인 컬렉션과 이를 구현하는 클래스를 정의하는 인터페이스를 제공

### Java 컬렌션 프레임워크의 상속구조
<img width="619" alt="스크린샷 2021-03-24 오전 11 54 28" src="https://user-images.githubusercontent.com/44339530/112247463-b0da9f00-8c97-11eb-814b-b8d8e462674f.png"><br>

- Collection 인터페이스는 List, Set, Queue로 크게 3가지 상위 인터페이스로 분류할 수 있다.
- Map의 경우 Collection 인터페이스를 상속받고 있지 않지만 Collection으로 분류한다.

### Collection 인터페이스의 특징
- 1. Set: 순서를 유지하지 않는 데이터의 집합으로 데이터의 중복을 허용하지 않는다.
    - <b>HashSet</b>
        - 가장빠른 임의 접근 속도
        - 순서를 예측할 수 없음
    - <b>TreeSet</b>
        - 정렬 방법을 지정할 수 있음

- 2. List: 순서가 있는 데이터의 집합으로 데이터의 중복을 허용
    - LinkedList
        - 양방향 포인터 구조로 데이터의 삽입, 삭제가 빈번할 경우 데이터의 위치정보만 수정하면 되기에 유용
        - 스택, 큐, 양방향 큐등을 만들기 위한 용도로 쓰임
    - Vector
        - 과거에 대용량 데이터 처리를 위해 사용했으며, 내부에서 자동으로 동기화 처리가 일어나 비교적 성능이 좋지 않아 잘 쓰이지 않음
    - ArrayList
        - 단방향 포인터 구조로 각 데이터에 대한 인덱스를 가지고 있어 조회 기능에 성능이 뛰어남

- 3. Queue: List와 유사
    - LinkedList
    - Priority Queue

- 4. Map: 키, 값으로 이루어진 데이터 집합, 순서는 유지되지 않으며 키(Key)의 중복을 허용하지 않으나 값(Value)의 중복은 허용
    - HashTable
        - HashMap보다 느리지만 동기화 보장
        - null불가
    - HashMap
        - 중복과 순서가 허용되지 않고 null 허용
    - TreeMap
        - 정렬된 순서대로 키(Key)와 값(Value)을 저장하여 검색이 빠름

#### 출처
- https://gangnam-americano.tistory.com/41