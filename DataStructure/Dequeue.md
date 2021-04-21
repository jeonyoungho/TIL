# Deque(덱)

## Deque(덱)
![6](https://user-images.githubusercontent.com/44339530/115526273-d2489e00-a2ca-11eb-90e8-61f5a0b77ee6.jpeg)<br>
- Deque란 Stack과 queue 양쪽으로 엘리먼트의 삽입과 삭제를 수행할 수 있는 자료구조 
- Deque(덱 혹은 데크)은 Double-Ended Queue의 줄임말로 큐의 양쪽으로 엘리먼트의 삽입과 삭제를 수행할 수 있는 자료구조이다.
- 카프카의 소스코드 내부에서 Deque 클래스를 사용한다.
- 덱(Deque)은 어떤 쪽으로 입력하고 어떤 쪽으로 출력하느냐에 따라서 스택(Stack)으로 사용할 수도 있고, 큐(Queue)로도 사용할 수 있다.
- 특히 한쪽으로만 입력 가능하도록 설정한 덱을 스크롤(scroll)이라고 하며, 한쪽으로만 출력 가능하도록 설정한 덱을 셸프(shelf)라고 한다.

### Deque 연산
- add() : 데이터를 que형식으로 삽입합니다..
- addFirst() : 데이터를 위에서 삽입합니다.(Stack 형식)
- addLast() : 데이터를 아래에서 삽입합니다.(Que 형식 == add() )

- poll() : que의 poll과 같은 기능을 합니다.
- pollFirst() : que의 poll과 같은 기능을 합니다.
- pollLast() : deque의 하단에 tail 쪽의 데이터를 제거하고 return합니다.


### Java에서의 Deque
- 자바에서의 덱은 인터페이스로 구현되었다.
- 덱 자료구조의 여러 연산들을 정의한 Deque 인터페이스가 있고 이를 구현한 ArrayDeque, LinkedBlockingDeque, ConcurrentLinkedDeque, LinkedList 등의 클래스가 있다.
- Deque Reference: https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html#peekFirst()

#### 삽입 관련 메소드
<img width="450" alt="7" src="https://user-images.githubusercontent.com/44339530/115526797-5f8bf280-a2cb-11eb-9a73-4d4286e51c05.png"><br>

- 두 가지 형태의 메서드
    - 1)수행이 실패했을 때 exception을 발생
        - void addFirst(E e): 덱의 앞쪽에 엘리먼트를 삽입한다. 용량 제한이 있는 덱의 경우, 용량을 초과하면 예외(Exception)가 발생
        - boolean add(E e), void addLast(E e): 덱의 마지막 쪽에 엘리먼트를 삽입한다. 용량 제한이 있는 덱의 경우, 용량 초과시 예외가 발생한다

    - 2)수행이 실패했을 때 null 또는 false를 반환
        - boolean offerFirst(E e): 덱의 앞쪽에 엘리먼트를 삽입한다. 정상적으로 엘리먼트가 삽입된 경우 true가 리턴되며, 용량 제한에 걸리는 경우 false를 리턴한다. 
        - boolean offer(E e), boolean offerLast(E e): 덱의 마지막 쪽에 엘리먼트를 삽입한다. 정상적으로 엘리먼트가 삽입된 경우 true가 리턴되며, 용량 제한에 걸리는 경우 false를 리턴한다. 

#### 삭제 관련 메소드
<img width="450" alt="8" src="https://user-images.githubusercontent.com/44339530/115527936-84349a00-a2cc-11eb-9ede-5188d883883a.png"><br>

- 두 가지 형태의 메서드
    - 1)수행이 실패했을 때 exception을 발생
        - E remove(), E removeFirst(): 덱의 앞쪽에서 엘리먼트 하나를 뽑아서 제거한 다음 해당 엘리먼트를 리턴한다. 덱이 비어있으면 예외가 발생한다. 
        - E removeLast(): 덱의 마지막 쪽에서 엘리먼트 하나를 뽑아서 제거한 다음 해당 엘리먼트를 리턴한다. 덱이 비어있으면 예외가 발생한다. 

    - 2)수행이 실패했을 때 null 또는 false를 반환
        - E poll(), E pollFirst(): 덱의 앞쪽에서 엘리먼트 하나를 뽑아서 제거한 다음 해당 엘리먼트를 리턴한다. 덱이 비어있으면 null 이 리턴된다. 
        - E pollLast(): 덱의 마지막 쪽에서 엘리먼트 하나를 뽑아서 제거한 다음 해당 엘리먼트를 리턴한다. 덱이 비어있으면 null 이 리턴된다. 

#### 조회 관련 메소드
- 두 가지 형태의 메서드
    - 1)수행이 실패했을 때 exception을 발생
        - E getFirst(): 덱의 앞쪽 엘리먼트 하나를 제거하지 않은채 리턴한다. 덱이 비어있으면 예외 발생
        - E getLast(): 덱의 마지막쪽 엘리먼트 하나를 제거하지 않은채 리턴한다. 덱이 비어있으면 예외가 발생한다. 

    - 2)수행이 실패했을 때 null 또는 false를 반환
        - E peek(), E peekFirst(): 덱의 앞쪽 엘리먼트 하나를 제거하지 않은채 리턴한다. 덱이 비어있으면 null이 리턴
        - E peekLast(): 덱의 마지막 엘리먼트 하나를 제거하지 않은 채 리턴한다. 덱이 비어있으면 null이 리턴

#### 그 외 추가적인 메소드
- removeFirstOccurrence(Object o): 덱의 앞쪽에서부터 탐색하여 입력한 Object o와 동일한 첫 엘리먼트를 제거한다. Object o 와 같은 엘리먼트가 없으면 덱에 변경이 발생하지 않는다. 

- removeLastOccurrence(Object o): 덱의 뒤쪽에서부터 탐색하여 입력한 Object o와 동일한 첫 엘리먼트를 제거한다. Object o 와 같은 엘리먼트가 없으면 덱에 변경이 발생하지 않는다. 

- element(): removeFirst()와 동일

- addAll(Collection <? extends E c): 입력 받은 Collection의 모든 데이터를 덱의 뒤쪽에 삽입한다.

- push(): addFirst()와 동일. 덱을 스택으로 사용할 때 쓰임

- pop(): removeFirst()와 동일. 덱을 스택으로 사용할 때 쓰임

- remove(Object o): removeFirstOccurrence(Object o)와 동일

- contain(Object o): 덱에 Object o와 동일한 엘리먼트가 포함되어 있는지 확인

- size(): 덱의 크기 

- iterator(): 덱의 앞쪽부터 순차적으로 실행되는 이터레이터(iterator)를 얻어옴

- descendingIterator(): 덱의 뒤쪽부터 순차적으로 실행되는 이터레이터(iterator)를 얻어옴

## 테스트 코드
~~~
class TestClass {
    
    public static void main(String[] args) throws InterruptedException {
      
        System.out.println("Stack!!");
        Deque<String> stack = new ArrayDeque<>();
        stack.addFirst("Element1");
        stack.addFirst("Element2");
        stack.addFirst("Element3");
        System.out.println(stack.removeFirst());
        System.out.println(stack.removeFirst());
        System.out.println(stack.removeFirst());
      
        System.out.println("Queue!!");
        Deque<String> queue = new ArrayDeque<>();
        queue.addFirst("Element1");
        queue.addFirst("Element2");
        queue.addFirst("Element3");
        System.out.println(queue.removeLast());
        System.out.println(queue.removeLast());
        System.out.println(queue.removeLast());
    }
}
~~~

#### 출처
- https://soft.plusblog.co.kr/24