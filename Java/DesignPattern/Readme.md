# DesignPattern

### [1. 객체지향 모델링](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter1-%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%EB%AA%A8%EB%8D%B8%EB%A7%81)
- 연관 관계(association): 클래스들이 개념상 서로 연결되었음을 나타냄(실선이나 화살표로 표시하며 보통은 한 클래스가 다른 클래스에서 제공하는 기능을 사용하는 상황일때 표시)
- 일반화 관계(generalization): 객체지향 개념에서는 상속 관계라고 함(한 클래스가 다른 클래스를 포함하는 상위 개념일때의 관계이며 속이 빈 화살표를 사용해 표시)
- 집합 관계(Composition, aggregation): 클래스들 사이의 전체 또는 부분 같은 관계를 나타냄(집약 관계와 합성 관계가 존재함)
- 의존 관계(dependency): 연관 관계와 같이 한 클래스가 다른 클래스에서 제공하는 기능을 사용할 때를 나타냄(차이점은 두 클래스의 관계가 한 메서드를 실행하는 동안과 같은, 매우 짧은 시간만 유지된다는 점, 점선 화살표를 사용해 표시)
- 실체화 관계(realization): 책임들의 집합인 인터페이스와 이 책임들을 실제로 실현한 클래스들 사이의 관계를 나타냄(상속과 유사하게 빈 삼각형을 사용하며 머리에 있는 실선 대신 점선을 사용해 표시)

### [2. 객체지향 원리](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter02-%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%EC%9B%90%EB%A6%AC)
- 캡슐화: 캡슐화를 통해 <b>높은 응집도와 낮은 결합도</b>를 갖는 설계를 구현해야 함
- 일반화 관계: 클래스 자체를 캡슐화하여 변경에 대비할 수 있는 설계를 가능하게 함. 즉, <b>새로운 클래스가 추가되더라도 클라이언트는 영향 받지 않음</b>
- 피터코드의 상속 규칙: 
    - 자식 클래스와 부모 클래스 사이는 ‘역할 수행is role played by’ 관계가 아니어야 한다.
    - 한 클래스의 인스턴스는 다른 서브 클래스의 객체로 변환할 필요가 절대 없어야 한다.
    - 자식 클래스가 부모 클래스의 책임을 무시하거나 재정의하지 않고 확장만 수행해야 한다.
    - 자식 클래스가 단지 일부 기능을 재사용할 목적으로 유틸리티 역할을 수행하는 클래스를 상속하지 않아야 한다.
    - 자식 클래스가 ‘역할role’, ‘트랜잭션transaction’, ‘디바이스device’ 등을 특수화specialization해야 한다.

### [3. SOLID원칙](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter03-SOLID%20%EC%9B%90%EC%B9%99)
- 로버트 마틴이 주장한 다섯 가지 설계 원칙
- SRP(단일 책임 원칙, Siingle Responsibility Principle): 즉, 클래스는 단 하나의 책임만을 가지도록 설계해야 하는 원칙(단일 책임 원칙)
- OCP(개방 폐쇄 원칙, Open Closed Principle): 기존의 코드를 변경하지 않으면서 새로운 기능을 추가할 수 있도록 설계하는 원칙
- LSP(리스코프의 대입 원칙, Liskov Substitution Principle): 일반화 관계는 슈퍼 클래스가 제공하는 오퍼레이션과 파생클래스에서 제공하는 오퍼레이션 간에는 <b>행위적으로 일관성이 있도록</b> 설계가 되어야 한다는 원칙
- ISP(인터페이스 분리 원칙, Interface Segregation Principle): 인터페이스를 클라이언트에 특화되도록 분리시키라는 설계 원칙
- DIP(의존성 역전 원칙, Dependency Inversion Principle): 의존 관계를 맺을 때 변화하기 쉬운 것 또는 변화가 자주 되는 것보다는 변화하기가 어려운 것, 변화가 거의 되지 않는 것에 의존하라는 원칙

### [4. 디자인패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter04-%EB%94%94%EC%9E%90%EC%9D%B8%ED%8C%A8%ED%84%B4)
- 소프트웨어를 설계할 때 특정 맥락에서 자주 발생하는 고질적인 문제들이 또 발생했을 때 재사용할 수 있는 훌륭한 해결책
- 아키텍처 패턴에서 컴포넌트의 내부 구조를 대상으로 한 클래스/ 객체의 구조와 협업 방법을 패턴화한 것이다.

### 생성 패턴
- 객체 생성에 관련된 패턴. 객체의 생성과 조합을 캡슐화해 특정 객체가 생성되거나 변경되어도 프로그램 구조에 영향을 크게 받지 않도록 유연성을 제공하는 패턴
    - [1. 싱글턴패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter06-%EC%8B%B1%EA%B8%80%ED%84%B4%ED%8C%A8%ED%84%B4)
        - 인스턴스를 불필요하게 생성하지 않고 오직 JVM내에서 한 개의 인스턴스만 생성하여 재사용을 위해 사용되는 패턴
        - ex) 프린터 관리자를 구현하는 프로그램

    - [2. 추상팩토리패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter13-%EC%B6%94%EC%83%81%ED%8C%A9%ED%86%A0%EB%A6%AC%ED%8C%A8%ED%84%B4)
        - 관련성이 있는 여러 종류의 객체를 일관된 방식으로 생성하는 경우에 유용한 패턴
        - ex) 엘리베이터 모터 설정 프로그램

    - [3. Chapter15-Builder패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter15-Builder%ED%8C%A8%ED%84%B4)
        - 객체의 생성과 표현을 분리하기 위한 패턴

    - [4. 팩토리메서드패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter12-%ED%8C%A9%ED%86%A0%EB%A6%AC%EB%A9%94%EC%84%9C%EB%93%9C%ED%8C%A8%ED%84%B4)
        - 객체의 생성 코드를 별도의 클래스/메소드로 분리함으로써 객체 생성의 변화를 대비하는 데 유용한 패턴
        - ex) 엘리베이터 스케쥴링 프로그램

### 구조 패턴
- 클래스나 객체를 조합해 더 큰 구조를 만드는 패턴. 예를 들어 서로 다른 인터페이스를 지닌 2개의 객체를 묶어 단일 인터페이스를 제공하거나 서로 다른 객체들을 묶어 새로운 기능을 제공하는 패턴
    - [1. 데커레이터패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter10-%EB%8D%B0%EC%BB%A4%EB%A0%88%EC%9D%B4%ED%84%B0%ED%8C%A8%ED%84%B4)
        - 데코레이터 패턴은 기본 기능에 추가될 수 있는 많은 수의 부가 기능에 대해서 다양한 조합을 동적으로 구현할 수 있는 패턴
        - ex) 도로 표시하는 프로그램
    - [2. 컴퍼지트패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter14-%EC%BB%B4%ED%8D%BC%EC%A7%80%ED%8A%B8%ED%8C%A8%ED%84%B4)
        - 부분(part)-전체(whole)의 관계를 가지는 객체들을 정의할 때 유용한 패턴(클라이언트는 전체와 부분을 구분하지 않고 동일한 인터페이스를 사용할 수가 있음)
        - ex) 컴퓨터 부품 추가 프로그램

### 행위 패턴
- 객체나 클래스 사이의 알고리즘이나 책임 분배에 관련된 패턴. 한 객체가 혼자 수행할 수 없는 작업을 여러개의 객체로 어떻게 분배하는지, 또 그렇게 하면서도 객체 사이의 결합도를 최소화하는것에 중점을 두는 패턴
    - [1. 옵서버패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter09-%EC%98%B5%EC%84%9C%EB%B2%84%ED%8C%A8%ED%84%B4)
        - 데이터의 변경이 발생하였을 때 상대 클래스 및 객체에 의존하지 않으면서 데이터 변경을 통보하고자 할 때 사용하는 패턴
        - ex) 학생 성적 출력하는 프로그램

    - [2. 스트래티지패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter05-%EC%8A%A4%ED%8A%B8%EB%9E%98%ED%8B%B0%EC%A7%80%ED%8C%A8%ED%84%B4)
        - 전략을 쉽게 바꿀 수 있도록 해주는 디자인 패턴
        - 여기에서 전략이란 어떤 목적을 달성하기 위해 일을 수행하는 방식, 비즈니스 규칙, 문제를 해결하는 알고리즘
        - 특히 게임 프로그래밍에서 게임 캐릭터가 자신이 처한 상황에 따라 공격이나 행동하는 방식을 바꾸고 싶을 때 스트래티지 패턴은 매우 유용함

    - [3. 템플릿메서드패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter11-%ED%85%9C%ED%94%8C%EB%A6%BF%EB%A9%94%EC%84%9C%EB%93%9C%ED%8C%A8%ED%84%B4)
        - 전체적으로 동일하면서 부분적으로 상이한 문장을 가지는 메소드의 코드 중복을 최소화할 때 유용한 패턴
        - 전체적인 틀은 상위클래스에게 상속받고 변경되는 부분만 하위클래스에서 오버라이딩 하는 패턴(변화되는 부분만 추상 메소드로 만들어주면 됨)
        - ex) 모터 프로그램

    - [4. 커맨드패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter08-%EC%BB%A4%EB%A7%A8%EB%93%9C%ED%8C%A8%ED%84%B4)
        - 이벤트가 발생했을 때 실행될 기능이 다양하면서 변경이 필요한 경우 이벤트를 발생시키는 클래스의 변경없이 재사용하고자 할 때 사용하는 패턴
        - ex) 알람 램프 버튼 프로그램

