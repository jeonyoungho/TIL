# SOLID 원칙

## SOLID 원칙 - 로버트 마틴이 주장한 다섯 가지 설계 원칙

### 1) SRP(단일 책임 원칙, Siingle Responsibility Principle)
- 단일 책임 원칙이다. 즉, 클래스는 단 하나의 책임만을 가지도록 설계해야 한다는 의미
- 책임은 '해야 하는 것'과 '변경 이유' 두 가지로 해석 될 수 있음
- 다음 그림은 SRP를 만족하지 않음<br>
- ![image](https://user-images.githubusercontent.com/44339530/109752623-14872480-7c24-11eb-96dd-86106a3b01c9.png)<br>
    - 하는 일의 관점: 하나는 임금을 계산하는 것이고 다른 하나는 영역의 합을 콘솔에 출력하는 일이다.
    - 변경 이유의 관점: 임금을 구하는 방식이 변경되면 클래스는 변경되어야 한다. 또한 출력 매체가 파일이나 HTML, XML 등으로 변경될 때에도 클래스는 변경되어져야 한다.

### 2) OCP(개방 폐쇄 원칙, Open Closed Principle)
- <b>기존의 코드를 변경하지 않으면서 새로운 기능을 추가할 수 있도록 설계하는 원칙이다.</b>
- 무엇이 변화되는지를 생각해야함
- 다음 그림은 OCP를 만족하지 않음<br>
- ![image](https://user-images.githubusercontent.com/44339530/109752916-9e36f200-7c24-11eb-83f9-439729ec0e45.png)<br>
    - 임금을 구하는 방식이 변경되면 종업원 클래스의 calculatePay() 메소드를 변경해야 하기에 이는 OCP를 만족하지 않는다.

- 다음 그림과 같이 임금을 계산하는 Logic을 인터페이스로 두어 수정해야 한다.<br>
- <img width="775" alt="스크린샷 2021-03-03 오후 1 34 57" src="https://user-images.githubusercontent.com/44339530/109753236-4056da00-7c25-11eb-9b5c-1d8ec415bad7.png"><br>

### 3) LSP(리스코프의 대입 원칙, Liskov Substitution Principle)
- 일반화 관계를 적절하게 사용했는지를 점검하는 원칙
- LSP는 일반화 관계는 슈퍼 클래스가 제공하는 오퍼레이션과 파생클래스에서 제공하는 오퍼레이션 간에는 <b>행위적으로 일관성이 있도록</b> 설계가 되어야 한다는 원칙
- 프로그램에서 슈퍼 클래스의 인스턴스 대신에 파생 클래스의 인스턴스로 대체하여도 프로그램의 의미는 변화되지 않도록 설계 <b>(부모 대신 자신을 대입해도 문제가 없어야 한다)</b>

#### 행위 일관성
- pre⇒pre'(만약 선조건 pre가 만족된다면 pre'가 만족되어야 한다.)
    - pre: 태풍이 분다(일반 로봇)
    - pre': 태풍이 불거나 홍수가 난다(인명 구조 로봇)
- post'⇒post(만약 후조건 post'가 만족된다면 post가 만족되어야 한다.)
    - post': 사람의 생명 또는 가족의 생명을 구한다(인명 구조 로봇)
    - post: 사람의 생명을 구한다
- 선조건(precondition): 기능A가 실행 되기 전에 만족되야 하는 조건
- 후조건(postcondition): 기능시 수행된 후 만족된 조건

- 예시
~~~
Ex1)
public class MinMax {
	public ArrayList<Integer>mimax(ArrayList<Integer> a) {
		int minValue;
		int maxValue;
		ArrayList<Integer> b;
		b=a;
		minValue = Collections.min(a);
		maxValue = Collections.max(a);
		b.set(0, minValue);
		b.set(a.size()-1, maxValue);
		return b;
	}
}

public class App {
	
	public static void testLSP(MinMax m) {
	
	ArrayList<Integer> a= new ArrayList<Integer>();
	ArrayList<Integer> b;
	a.add(100);
	a.add(500);
	a.add(50);
	a.add(505);
	a.add(30);
	
	b = m.mimax(a);
	System.out.println("smallest Value:: "+ b.get(0));
	System.out.println("largest Value:: "+ b.get(b.size()-1));
	
	}
	public static void main(String[] args) {
		testLSP(new MinMax());	
	}
}
~~~

~~~
Ex2)
public class MinMax1 extends MinMax {
	
	public ArrayList<Integer> mimax(ArrayList<Integer> a) {
		int minValue;
		int maxValue;
		ArrayList<Integer> b;
	
		minValue = Collections.min(a);
		maxValue = Collections.min(a);
		a.set(0, minValue);
		a.set(a.size()-1, maxValue);
		b=a;
		return b;
	}
}

public class App {
	
	public static void testLSP(MinMax m) {
	
	ArrayList<Integer> a= new ArrayList<Integer>();
	ArrayList<Integer> b;
	a.add(100);
	a.add(500);
	a.add(50);
	a.add(505);
	a.add(30);
	
	b = m.mimax(a);
	System.out.println("smallest Value:: "+ b.get(0));
	System.out.println("largest Value:: "+ b.get(b.size()-1));
	
	}
	public static void main(String[] args) {
		testLSP(new MinMax1());	
	}
}
~~~
- MinMax클래스와 MinMax클래스를 상속받은 MinMax1클래스가 있다.
- MinMax클래스의 mimax()메소드를 MinMax1클래스에서 오버라이딩하고 있다.
- MinMax클래스의 mimax()메소드는 최소값과 최대값을 출력할 수 있지만 MinMax1클래스의 mimax()메소드는 최소값만을 출력하기에 행위적으로 일관성이 없음을 의미한다. pre⇒pre1가 만족되지만  ~(post1⇒post)
- 즉 MinMax클래스의 인스턴스 대신 MinMax1클래스의 인스턴스로 대치 할 수 없다는 것을 의미한다.
- 행위적으로 일관성을 유지하려면 다음과 같이 수정해야 한다.
~~~
public class MinMax2 extends MinMax{
	public ArrayList<Integer> mimax(ArrayList<Integer> a) {
		ArrayList<Integer> b;
		b=a;
		Collections.sort(b);
		return b;
	}
}
~~~
-  MinMax클래스의 인스턴스 대신 MinMax2의 인스터스로 대치 할 수 있다. 즉, 행위적 일관성이 유지 된다. pre⇒pre1가 만족되고  (post2⇒post)

### 4) ISP(인터페이스 분리 원칙, Interface Segregation Principle)
- 인터페이스를 클라이언트에 특화되도록 분리시키라는 설계 원칙
- 클라이언트의 관점에서 클라이언트 자신이 이용하지 않는 기능에는 영향을 받지 않아야 한다는 내용이 담겨 있다.<br>
![image](https://user-images.githubusercontent.com/44339530/109759736-dcd2a980-7c30-11eb-97b0-53cbb988211c.png)<br>

### 5) DIP(의존성 역전 원칙, Dependency Inversion Principle)
- 의존 관계를 맺을 때 변화하기 쉬운 것 또는 변화가 자주 되는 것보다는 변화하기가 어려운 것, 변화가 거의 되지 않는 것에 의존하라는 원칙이다. 
- 그렇다면 변하기 쉬운 것과 변하기 어려운 것은 무엇인가? 가령 정책, 전략과 같은 어떤 큰 흐름이나 개념 같은 추상적인 것은 변하기 어려운 것에 해당하고 구체적인 방식, 사물 등과 같은 것은 변하기 쉬운 것에 해당한다. 
- 객체지향 관점에서 변하기 어려운 추상적인 것들을 표현하는 수단으로 추상 클래스와 인터페이스가 있다. 
- <b>DIP를 만족하기 위해서는 어떤 클래스가 도움을 받을 때는 구체적인 클래스 보다는 인터페이스나 추상 클래스에 의존관계를 맺도록 설계해야 한다.</b>
- ![image](https://user-images.githubusercontent.com/44339530/109759893-1c999100-7c31-11eb-85cb-e1a5abe25f5c.png)<br>
- 예시
~~~
public class Person {
	private Pet pet;
	public void setPet(Pet pet) {this.pet=pet;}
	public void loves() {
		System.out.println("I love "+pet.toString());
	}
}
public abstract class Pet {}
public class Cat extends Pet {
	public String toString() {
		return "Cat"
	}	
}

public class Main {
	public static void main(String[] args) {
		Pet t = new Cat();
		Person p = new Person();
		p.setPet(t);
		p.loves();
	}
}
~~~
- DIP 따른 설계는 의존성 주입(dependency injection)의 기반
- 예제에서 사람이 마음이 바뀌어 좋아하는 애완동물이 바뀌어도 전혀 Person, Pet, Cat 등의 기존의 코드에 영향 없이 의존성 주입을 통해 애완동물을 바꿀 수 있다.

#### 출처
- Java객체지향 디자인패턴(한빛미디어)