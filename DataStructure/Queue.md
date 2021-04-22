# Queue(큐)

## Queue(큐)란?
![3](https://user-images.githubusercontent.com/44339530/115515403-0e2a3600-a2c0-11eb-85db-3b9d0068c0ad.png)<br>

- 먼저 들어온 데이터를 먼저 내보내는 선입선출(FIFO: First-In-First-Out) 구조의 자료구조
- 예를 들어, 영화관 매표소에서 예매를 하기 위해 손님들은 순서대로 줄을 스게 되며 먼저 온 손님부터 영화를 예매하게 됩니다.
- Java에서는 Queue를 사용하기 위해 java.util.Queue를 구현한 클래스를 사용한다.

### 큐의 연산
- add() : queue에 데이터 삽입.
- peek() : 제일 먼저 들어와 상단에 위치한 데이터를 리턴합니다.
- poll() : 상단에 위치한 데이터를 추출하고 queue에서 제거 합니다. 추출한 데이터를 리턴합니다.
- isEmpty() : stack이 비었는지 확인합니다. 비어있을 때 true, 비어있지 않을 때 false를 리턴합니다.
~~~
 Queue<String> queData = new ArrayDeque<String>();
// Queue<String> queData = new LinkedList<String>(); 

queData.add("first");
queData.add("second");
queData.add("third");
queData.add("forth");

for( String item : queData){
    System.out.println("순회 : " + item);
}

while( ! queData.isEmpty() ) {
    System.out.println("제거 : " + queData.poll() ); 
}

System.out.println("queData가 비었습니까 : " + queData.isEmpty());
~~~

### 큐의 구현
- 큐는 연결리스트로 구현할 수 있습니다. 연결리스트의 반대방향에서 항목을 추가하거나 제거하도록 구현합니다.
~~~
public class MyQueue {
  private static class QueueNode {
    private T data;
    private QueueNode next;

    public QueueNode(T data) {
      this.data = data;
    }
  }

  private QueueNode first;
  private QueueNode last;

  public void add(T item) {
    QueueNode t = new QueueNode(item);

    if (last != null) last.next = t;
    last = t;
    if (first == null) first = last;
  }

  public T remove() {
    if (first == null) throw new NoSuchElementException();
    T data = first.data;
    first = first.next;

    if (first == null) last = null;
    return data;
  }

  public T peek() {
    if (first == null) throw new NoSuchElementException();
    return first.data;
  }

  public boolean isEmpty() {
    return first == null;
  }
}
~~~

### 큐의 사용 사례
- BFS구현
    - 처리해야 할 노드의 리스트를 저장하는 용도로 사용
    - 노드를 하나 처리할 때마다 해당 노드와 인접한 노드들을 큐에 다시 저장한다.
    - 노드를 접근한 순서대로 처리할 수 있다.

- 캐쉬(Cache)구현
- 우선순위가 같은 작업 예약(인쇄 대기열)
- 선입선출이 필요한 대기열(영화관 매표소)
- 콜센터 고객 대기시간
- 프로세스 관리

### Java의 큐 관련 메서드
- 두 가지 형태의 메서드
    - 1)수행이 실패했을 때 exception을 발생
        - boolean add(E item): 해당 item을 Queue에 삽입 / 삽입에 성공하면 true를 반환, 삽입할 공간이 없는 경우는 예외(IllegalStateException)를 발생
        - E element(): Queue의 Head에 있는 item을 삭제하지않고 해당 item을 반환, 만약 Queue가 비어있으면 예외를 발생
        - E remove(): Queue의 Head에 있는 item을 삭제하고 해당 item을 반환, 만약 Queue가 비어있으면 예외를 발생
    - 2)수행이 실패했을 때 null 또는 false를 반환
        - boolean offer(E item): add(e)와 동일한 기능, 삽입에 성공하면 true를 반환, 실패하면 false를 반환
        - E peek(): element()와 동일한 기능, 만약 Queue가 비어있으면 null을 반환, poll()수행이 실패했을 때 exception을 발생하는 메서드
        - E poll(): remove()와 동일한 기능, 만약 Queue가 비어있으면 null을 반환
- java.util.Collection인터페이스를 구현된 메소드
    - addAll, clear, contains, containsAll, equals, hashCode, isEmpty, iterator, remove, removeAll, retainAll, size, toArray, toArray

### Java의 큐를 구현한 클래스
- 1)LinkedList
  - 끝에 요소를 추가하기에 용이하다.
  - List interface 구현
  - 요소에 null을 허용한다.
- 2)priorityQueue(우선순위 큐)
  - PIPO(Priority-In Priority-Out)
  - 정렬된 순서에 의해 반복
  - 요소에 null을 허용하지 않는다.
- 3)priorityBlockingQueue
  - priorityQueue의 동기화된 버전

#### 출처
- https://gmlwjd9405.github.io/2018/08/02/data-structure-queue.html
- https://honbabzone.com/java/java-dataStructure-1/#%EC%8A%A4%ED%83%9Dstack
