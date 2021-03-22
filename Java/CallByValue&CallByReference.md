## Call by value와 Call by reference
- Call by value와 Call by reference는 메소드 호출 시 인자 값을 어떤식으로 받아오는지에 대한 차이이다.
    - Call by value: 값에 의한 호출
    - Call by reference: 참조에 의한 호출
- Java는 Call by value형식이다.

### Call by value
- 메소드 호출 시에 사용되는 인자의 메모리에 저장되어 있는 값(value)을 복사하여 보냄

~~~
public class Main {
	
	private static void swap(int x, int y) {
		int tmp = x;
		x = y;
		y =tmp;
	}
	
	public static void main(String[] args) {
		int a = 10;
		int b = 20;
		System.out.println("[swap() 호출 전] a: " + a + ", b: " + b);
		swap(a,b);
		System.out.println("[swap() 호출 후] a: " + a + ", b: " + b);				
	}
	
}
~~~

- 실행 결과<br>
![스크린샷 2021-03-23 오전 7 21 32](https://user-images.githubusercontent.com/44339530/112065674-66c6c000-8ba8-11eb-808b-7cfac14a7195.png)<br>

#### 설명
<img width="574" alt="스크린샷 2021-03-23 오전 7 55 48" src="https://user-images.githubusercontent.com/44339530/112068418-303f7400-8bad-11eb-9dee-85bcf3d7bcb2.png"><br>

- 인자로 넘겨 준 a와 b의 값은 메모리 상에 복사되어 x와 y가 참조하게 된다.
- swap함수에서는 복사된 x와 y의 값을 서로 바꾸기에 원본 a와 b는 동일한 값을 유지하게 된다. 

### Call by reference
- 메소드 호출 시에 사용되는 인자의 메모리에 저장되어 있는 주소(Address)를 복사하여 보냄

~~~
public class Main {
	
	public static class Foo {
		int a;
		
		public Foo(int a) {
			this.a = a;
		}
	}

	private static void swap(Foo x, Foo y) {
		int tmp = x.a;
		x.a = y.a;
		y.a =tmp;
	}
	
	public static void main(String[] args) {
		Foo foo1 = new Foo(10);
		Foo foo2 = new Foo(20);
		System.out.println("[swap() 호출 전] foo1.a: " + foo1.a + ", foo2.a: " + foo2.a);
		swap(foo1, foo2);
		System.out.println("[swap() 호출 후] foo1.a: " + foo1.a + ", foo2.a: " + foo2.a);				
	}
}
~~~

- 실행 결과<br>
![스크린샷 2021-03-23 오전 7 47 54](https://user-images.githubusercontent.com/44339530/112067834-15b8cb00-8bac-11eb-8124-13700b3ff893.png)<br>

#### 설명
<img width="542" alt="스크린샷 2021-03-23 오전 7 58 11" src="https://user-images.githubusercontent.com/44339530/112068566-844a5880-8bad-11eb-8a01-9a24d786a71b.png"><br>

- 변수 foo1과 foo2는 실제 인스턴스가 위치하는 주소 값을 가리키고 있다.
- 함수를 호출하면 foo1과 foo2 인스턴스의 주소 값이 새로 복사되어 x와 y의 변수로 참조하게 된다.
- swap함수에서 멤버 변수 a의 값을 바꾸면 실제 인스턴스의 a값이 바뀌게 된다.

#### 정리
- 자바는 기본형 타입은 단순하게 복사해서 주지만 참조형 타입은 값의 래퍼러스(주소)가 저장되기에 그 값의 래퍼런스가 복사 된다.
- 객체를 메소드로 넘길 때는 Call By Reference임을 기억해야 한다.

#### 출처
- https://sleepyeyes.tistory.com/11
- https://re-build.tistory.com/3