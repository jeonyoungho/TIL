# Array와List(그리고 Java List)

## Array의 개념
- 같은 타입의 여러 데이터를 하나의 이름으로 그룹핑해서 관리하기 위한 자료구조
- index와 값의 쌍으로 구성
- index는 값에 대한 유일무이한 식별자(List에서 인덱스는 몇 번째 데이터인가 정도의 의미를 가짐)
- 연속된 메모리의 공간으로 이루어져 있다.
- 논리적 저장 순서와 물리적 저장 순서가 일치 => index로 해당 원소에 접근할 수 있다. (시간복잡도: O(1))
- 배열은 선언과 동시에 길이를 지정하며 길이를 변경할 수 없다.

### Array의 장점
- 인덱스를 통한 검색이 용이함
- 연속적이므로 메모리 관리가 편함

### Array의 단점
- 배열의 크기를 컴파일하기전에 정해주어야 한다.
- 크기가 고정되어 있어서 컴파일 이후 배열의 크기를 변경할 수 없다.
- 크기가 고정되어 있기 때문에 어떤 엘리먼트가 삭제되면, 삭제된 상태를 빈 공간으로 남겨두어야 하기에 <b>메모리 낭비</b>가 발생한다.

## List의 개념
- <b>순서가 있는 데이터들의 집합으로 배열과는 다르게 빈 엘리먼트는 절대 허용하지 않는다.</b>
- 배열이 가진 인덱스라는 장점을 버리고 대신 빈틈없는 데이터의 적재라는 장점이 있다.
- List에서의 인덱스는 몇 번째 데이터인가 정도(순서)의 의미를 가진다(배열에서의 인덱스는 값에 대한 유일무이한 식별자)
- 빈 엘리먼트는 허용하지 않는다. => java에서는 허용하는 경우도 있음
- 순차성을 보장하지 못하기 때문에 spacial locality 보장이 되지 않아서 cash hit가 어렵다.(데이터 갯수가 확실하게 정해져 있고, 자주 사용된다면 array가 더 효율적이다.)
- 불연속적으로 메모리 공간을 차지
- 포인터를 통한 접근

### List의 장점
- 포인터를 통하여 다음 데이터의 위치를 가르키고 있기에 삽입 삭제가 용이하다.
- 메모리 공간의 크기를 동적으로 변경할 수 있다.(컴파일 후 데이터 추가 가능)
- 메모리의 재사용이 편리하다.
- 불연속적이므로 메모리 관리가 편리하다.

### List의 단점
- 검색 성능이 좋지 않다.
- 포인터를 통해 다음 데이터를 가리키므로 추가적인 메모리 공간 발생.

## Array와 List의 삽입, 삭제, 조회 비교
||추가/삭제|조회|
|-----|-----|-----|
|Array|느림|빠름|
|List|빠름|느림|
- 배열: 데이터의 크기가 정해져 있고, 추가적인 삽입 삭제가 일어나지 않으며 검색을 필요로 할 때 유리하다.
- 리스트: 데이터의 크기가 가변적이고, 삽입 삭제가 많이 일어나며, 검색이 적은 경우에 유리하다.

## Java List Collection
- List는 Collection 인터페이스를 확장한 자료형으로 동일한 데이터의 중복 입력이 가능하다.
- 주로 순차적이고 다량의 데이터를 입력할 때 사용한다.
- 종류는 Vector, Arraylist, LinkedList가 있다.

### ArrayList
- <b>일반 배열과 ArrayList는 인덱스로 객체를 관리한다는 점은 동일하지만, ArrayList는 크기를 동적으로 늘릴 수 있다는 점에서 차이점이 존재한다.</b>
- ArrayList는 내부에서 처음 설정한 저장 용량(capacity)가 있다.(DEFAULT_CAPACITY=10)
- 데이터 추가 시 공간이 부족한 경우 배열 크기를 <b>1.5배</b>로 증가시킨다.
~~~
// DEFAULT_CAPACITY=10
// 기본 저장용량 10으로 리스트 생성
List<String> list = new ArrayList<>(); 

// 저장 용량을 100으로 설정해 ArrayList 생성 
List<String> list = new ArrayList<>(100);
~~~

- 메모리 공간을 빈 공간 없이 자동으로 관리한다.
    - ArrayList에서 특정 인덱스의 객체를 제거하게 되면, 제거한 객체의 인덱스부터 마지막 인덱스까지 모두 앞으로 1칸씩 앞으로 이동한다. 객체를 추가하게 되면 1칸씩 뒤로 이동하게 된다. 인덱스 값을 유지하기 위해서 전체 객체가 위치가 이동한다.
- 하지만 특정 인덱스를 추가 및 삭제하며 임시 배열을 생성 후 처리하기 때문에 잦은 삽입/삭제가 발생한다면 LinkedList를 사용하여야한다.
- [ArrayList와 LinkedList의 차이]( https://github.com/jeonyoungho/TIL/blob/master/DataStructure/ArrayList%EC%99%80LinkedList%EC%9D%98%EC%B0%A8%EC%9D%B4.md)

#### 배열과 ArrayList의 차이
- 배열은 크기가 고정되어있지만 ArrayList는 사이즈가 동적인 배열이다.
- 배열은 primitive type(int, byte, char 등)과 object 모두를 담을 수 있지만, ArrayList는 object element만 담을 수 있다.
- 배열은 제네릭을 사용할 수 없지만, ArrayList는 타입 안정성을 보장해주는 제네릭을 사용할 수 있다.
- 길이에 대해 배열은 length 변수를 쓰고, arrayList는 size() 메서드를 써야한다.
- 배열은 element들을 할당하기 위해 assignment(할당) 연산자를 써야하고, ArrayList는 add() 메서드를 통해 element를 삽입한다.

~~~
// ArrayList.class

private Object[] grow(int minCapacity) {
        // newCapacity 길이의 새 배열에 기존 배열 복사
        return this.elementData = Arrays.copyOf(this.elementData, this.newCapacity(minCapacity));
    }
    
 private int newCapacity(int minCapacity) {
        int oldCapacity = this.elementData.length;
        // 기존 리스트 길이 + (기존리스트길이/2)
        // 길이가 부족할 경우 1.5배 길이를 늘린다
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity <= 0) {
            if (this.elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
                return Math.max(10, minCapacity);
            } else if (minCapacity < 0) {
                throw new OutOfMemoryError();
            } else {
                return minCapacity;
            }
        } else {
            return newCapacity - 2147483639 <= 0 ? newCapacity : hugeCapacity(minCapacity);
        }
    }
~~~

#### ArrayList의 삽입/삭제 과정
<img width="720" alt="스크린샷 2021-04-21 오후 2 42 44" src="https://user-images.githubusercontent.com/44339530/115502399-d74c2400-a2af-11eb-9a2e-2bce4b88b5c7.png">

### LinkedList
- 노드 간에 연결(link)을 통해서 리스트로 구현된 객체이다.
- <b>다음 노드의 위치 정보만 가지고 있으며 인덱스를 가지고 있지 않기 때문에 탐색시 순차접근만 가능하다(randomAccess 불가능 및 탐색 시 시간이 많이 소요될 수 있음)</b>
- <b>노드 삽입/삭제는 위치 정보의 수정만으로 가능하기 때문에 성능이 좋다.</b>
- LinkedList는 ArrayList와 달리 List인터페이스를 구현한 AbstractList를 상속하지 않고 AbstractSquentialList를 상속한다.<br>
![1](https://user-images.githubusercontent.com/44339530/115502664-3e69d880-a2b0-11eb-935e-1f1bfe9e1821.png)<br>


#### LinkedList의 삽입/삭제 과정
<img width="719" alt="스크린샷 2021-04-21 오후 2 46 05" src="https://user-images.githubusercontent.com/44339530/115502708-4de92180-a2b0-11eb-8322-4b014fb9264e.png"><br>

### Vector
- Vector는 ArrayList와 동일한 내부 구조를 가지고 있다. Vector 객체를 생성하기 위해서는 저장할 타입을 지정해야 한다.
- <b>ArrayList와 차이점으로는 Vector 클래스는 동기화된(synchronized) 메서드로 구성되어 있다.</b>
    - 따라서 멀티 스레드 환경에서 안전하게 객체를 삽입/삭제할 수 있다.(Thread-Safe)
- 하지만 Thread-safe하기 때문에 lock을 걸고 푸는 과정을 반드시 거쳐야하므로 삽입/삭제시 다소 느리다.
- 데이터 추가 시 공간이 부족한 경우 배열 크기를 <b>2배</b>로 증가시킨다.

## ArrayList, Vector 와 LinkedList의 삽입 과정 비교
<img width="717" alt="스크린샷 2021-04-21 오후 2 51 47" src="https://user-images.githubusercontent.com/44339530/115503268-1af35d80-a2b1-11eb-855b-ccfca5947ef4.png"><br>

- ArrayList : 객체 검색, 맨 마지막 인덱스에 객체 추가에 좋은 성능을 발휘함, 싱글스레드 환경에서 사용, 공간이 부족할시 1.5배로 늘림
- Vector: ArrayList와 동일하지만 멀티스레드 환경에서 사용, 공간이 부족할시 2배로 늘림
- LinkedList : 객체 삽입 및 삭제에 좋은 성능을 발휘함

#### 출처
- https://velog.io/@adam2/Array%EC%99%80-List%EA%B7%B8%EB%A6%AC%EA%B3%A0-Java-List