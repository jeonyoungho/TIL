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

## 우선순위 큐(Priority Queue)란?
- 우선순위에 따라 우선순위가 높은 객체가 먼저 나오는 자료구조
- 우선순위 큐는 3가지 방법으로 구현할 수 있다.
    - 1)배열 사용
        - 데이터 삽입, 삭제과정에서 데이터를 밀고 당기는 연산을 해야 하는 단점이 존재
    - 2)연결리스트 사용
        - 연결리스트로 우선순위 que를 구현하였을 경우 삽입의 위치를 찾기 위해 첫 번째 노드부터 마지막에 저장된 노드까지 순회해야 하는 단점
    - 3)Heap을 사용
        - <b>가장 일반적인 사용 방법!</b>
## Heap
![4](https://user-images.githubusercontent.com/44339530/115523011-95c77300-a2c7-11eb-8904-788f4e4617df.png)<br>

- 우선순위에 따라 우선순위가 높은 객체가 먼저 나오는 트리 구조의 큐
- 부모의 노드는 항상 자식의 노드보다 클 경우를 Max heap, 작을 경우를 Min Heap
- Heap의 삽입/삭제 과정: https://ahribori.com/article/5952f94f22eced098cbd8e3c

### Java에서의 우선순위 큐 구현
- java.util.PriorityQueue를 사용
- java.util.PriorityQueue의 경우 기본 Heap구성은 minHeap(우선 순위가 낮은 것부터)으로 구성된다.(디폴트)
- 만약 우선순위가 maxHeap(우선 순위가 높은 것부터)으로 구성하기 위해서 Comparator.reverseOrder()를 인자로 추가
~~~
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
~~~

#### Java에서 우선순위 큐 구현시 객체의 우선순위를 나타내는 방법
- 1)기본 타입
~~~
//int형 priorityQueue 선언 (우선순위가 낮은 숫자 순)
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

//int형 priorityQueue 선언 (우선순위가 높은 숫자 순)
PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

//String형 priorityQueue 선언 (우선순위가 낮은 숫자 순)
PriorityQueue<String> priorityQueue = new PriorityQueue<>(); 

//String형 priorityQueue 선언 (우선순위가 높은 숫자 순)
PriorityQueue<String> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

// 출처: https://coding-factory.tistory.com/603
~~~

- 2)큐에 저장될 객체(Student)에 Comparable인터페이스의 compareTo 메서드를 구현하는 방법
~~~
// Comparable 구현 
public class Student implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student input) {
        int result = Integer.compare(score, input.score);
        if( result == 0 ) {
            result = input.name.compareTo(name);
        }
        return result;
    } // 시험 점수가 높은 사람을 top으로 위치시키고 동점자의 경우 이름의 사전순으로 정렬
    
    @Override
    public String toString() {
        return "이름 : " + name + " 점수 : " + score;
    }
}
~~~

~~~
// main
PriorityQueue<Student> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
priorityQueue.add(new Student("김석진", 100));
priorityQueue.add(new Student("박석진", 90));
priorityQueue.add(new Student("진석진", 95));
priorityQueue.add(new Student("구석진", 88));
priorityQueue.add(new Student("다석진", 100));
priorityQueue.add(new Student("사석진", 100));
priorityQueue.add(new Student("나석진", 100));
~~~

~~~
// 출력 
이름 : 김석진 점수 : 100
이름 : 나석진 점수 : 100
이름 : 다석진 점수 : 100
이름 : 사석진 점수 : 100
이름 : 진석진 점수 : 95
이름 : 박석진 점수 : 90
이름 : 구석진 점수 : 88
~~~

- 3)PriorityQueue생성 시 생성자로 Comparator인터페이스를 구현한 객체(익명 함수 compare메서드 구현)를 넘기는 방법
~~~
// comparator 이용 
public class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "이름 : " + name + " 점수 : " + score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
}
~~~

~~~
// main
PriorityQueue<Student> priorityQueue = new PriorityQueue<>(new Comparator<Student>() {
    @Override
    public int compare(Student first, Student second) {
        int result = Integer.compare(second.getScore(), first.getScore());
        if( result == 0 ) {
            result = first.getName().compareTo(second.getName());
        }
        return result;
    }
}) ;

priorityQueue.add(new Student("김석진", 100));
priorityQueue.add(new Student("박석진", 90));
priorityQueue.add(new Student("진석진", 95));
priorityQueue.add(new Student("구석진", 88));
priorityQueue.add(new Student("다석진", 100));
priorityQueue.add(new Student("사석진", 100));
priorityQueue.add(new Student("나석진", 100));

while( !priorityQueue.isEmpty() ) {
    Student student = priorityQueue.poll();
    System.out.println(student.toString());
    
}
~~~

~~~
// 출력 
이름 : 김석진 점수 : 100
이름 : 나석진 점수 : 100
이름 : 다석진 점수 : 100
이름 : 사석진 점수 : 100
이름 : 진석진 점수 : 95
이름 : 박석진 점수 : 90
이름 : 구석진 점수 : 88
~~~



#### 출처
- https://gmlwjd9405.github.io/2018/08/02/data-structure-queue.html
- https://honbabzone.com/java/java-dataStructure-1/#%EC%8A%A4%ED%83%9Dstack