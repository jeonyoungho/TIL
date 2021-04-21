# Stack(스택)

## 스택이란?
![2](https://user-images.githubusercontent.com/44339530/115507464-221d6a00-a2b7-11eb-9fce-6594270d9945.png)<br>

- 마지막에 들어온 데이터를 먼저 내보내는 후입선출(LIFO: Last-In-First-Out)을 표현하기 위한 자료구조
- 예를 들어 접시 쌓기를 예로 들 수 있습니다. 접시를 쌓을 때 위에서부터 차곡차곡 쌓지만 빼낼때는 가장 맨위의(마지막에 들어온) 접시부터 빼내게 됩니다.

### 스택의 장점
- 스택에서 데이터를 추가하거나 삭제하는 연산은 상수 시간(O(1))에 가능
- 배열처럼 원소들을 하나씩 옆으로 밀어 줄 필요가 없다.

### 스택의 단점
- 배열과 달리 스택은 상수 시간에(O(1)) 특정 원소를 접근할 수 없다.

### 스택의 연산
- push() : 마지막 위치에 데이터 삽입
- pop() : 마지막 데이터 제거 후 제거된 데이터 return
- peek() : 마지막에 들어온 데이터를 return 합니다.
- isEmpty() : stack이 비었는지 확인합니다.
~~~
Stack<String> stack = new Stack<>();
stack.push("first");
System.out.println("삽입 : " + stack.peek() );
stack.push("second");
System.out.println("삽입 : " +  stack.peek() );
stack.push("third");
System.out.println( "삽입 : " + stack.peek() );
stack.push("forth");
System.out.println( "삽입 : " + stack.peek() );

for( String item : stack  ){
    System.out.println("순회 : " + item);
}

while( !stack.isEmpty() ) {
    System.out.println("제거 : " + stack.pop() ); 
}

System.out.println("Stack이 비었습니까 : " + stack.isEmpty());
~~~

### 스택의 구현
- 스택은 연결리스트로 구현이 가능하다.  연결리스트의 같은 방향에서 아이템을 추가하고 삭제하도록 구현한다.
~~~
public class MyStack {
  private static class StackNode {
    private T data;
    private StackNode next;

    public StackNode(T data) {
      this.data = data;
    }
  }

  private StackNode top;

  public T pop() {
    if (top == null) throw new NoSuchElementException();
    T item = top.data;
    top = top.next;

    return item;
  }

  public void push(T item) {
    StackNode t = new StackNode(item);
    t.next = top;
    top = t;
  }

  public T peek() {
    if (top == null) throw new NoSuchElementException();
    return top.data;
  }

  public boolean isEmpty() {
    return top == null;
  }
}
출처: https://gmlwjd9405.github.io/2018/08/03/data-structure-stack.html
~~~

### 스택의 사용 사례
- 재귀 알고리즘
    - <img width="801" alt="스크린샷 2021-04-21 오후 4 37 02" src="https://user-images.githubusercontent.com/44339530/115515149-cdcab800-a2bf-11eb-8efb-08ec25360635.png">
    - 출처: https://today-sy-learned.tistory.com/21 
- 웹 브라우저 방문기록(뒤로가기)
- 실행 취소(undo)
- 역순 문자열 만들기
- 수식의 괄호 검사(Ex) 올바른 괄호 문자열(VPS, Valid Parenthesis String) 판단하기)
- 후위 표기법 계산

### Java의 스택(Stack) 관련 메서드
- 1)push(E item)
    - 해당 item을 Stack의 top에 삽입
    - Vector의 addElement(item)과 동일
- 2)pop()
    - Stack의 top에 있는 item을 삭제하고 해당 item을 반환
- 3)peek()
    - Stack의 top에 있는 item을 삭제하지않고 해당 item을 반환
- 4)empty()
    - Stack이 비어있으면 true를 반환 그렇지않으면 false를 반환
- 5)search(Object o)
    - 해당 Object의 위치를 반환
    - Stack의 top 위치는 1, 해당 Object가 없으면 -1을 반환
- 6)java.lang.Object클래스로부터 상속된 메소드
    - finalize, getClass, notify, notifyAll, wait, wait, wait
- 7)java.util.Vector클래스로부터 상속된 메소드
    - add, add, addAll, addAll, addElement, capacity, clear, clone, contains, containsAll, copyInto, elementAt, elements, ensureCapacity, equals, firstElement, get, hashCode, indexOf, indexOf, insertElementAt, isEmpty, iterator, lastElement, lastIndexOf, lastIndexOf, listIterator, listIterator, remove, remove, removeAll, removeAllElements, removeElement, removeElementAt, removeRange, retainAll, set, setElementAt, setSize, size, subList, toArray, toArray, toString, trimToSize

#### 출처
- https://honbabzone.com/java/java-dataStructure-1/#%EC%8A%A4%ED%83%9Dstack
- https://gmlwjd9405.github.io/2018/08/03/data-structure-stack.html
