# DesignPattern

### [Chapter01-객체지향 모델링](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter1-%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%EB%AA%A8%EB%8D%B8%EB%A7%81)

### [Chapter02-객체지향 원리](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter02-%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%20%EC%9B%90%EB%A6%AC)

### [Chapter03-SOLID원칙](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter03-SOLID%20%EC%9B%90%EC%B9%99)

### [Chapter04-디자인패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter04-%EB%94%94%EC%9E%90%EC%9D%B8%ED%8C%A8%ED%84%B4)

### [Chapter05-스트래티지패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter05-%EC%8A%A4%ED%8A%B8%EB%9E%98%ED%8B%B0%EC%A7%80%ED%8C%A8%ED%84%B4)
- 전략을 쉽게 바꿀 수 있도록 해주는 디자인 패턴
- 여기에서 전략이란 어떤 목적을 달성하기 위해 일을 수행하는 방식, 비즈니스 규칙, 문제를 해결하는 알고리즘
- 특히 게임 프로그래밍에서 게임 캐릭터가 자신이 처한 상황에 따라 공격이나 행동하는 방식을 바꾸고 싶을 때 스트래티지 패턴은 매우 유용함

### [Chapter06-싱글턴패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter06-%EC%8B%B1%EA%B8%80%ED%84%B4%ED%8C%A8%ED%84%B4)
- 인스턴스를 불필요하게 생성하지 않고 오직 JVM내에서 한 개의 인스턴스만 생성하여 재사용을 위해 사용되는 패턴
- ex) 프린터 관리자를 구현하는 프로그램

### [Chapter08-커맨드패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter08-%EC%BB%A4%EB%A7%A8%EB%93%9C%ED%8C%A8%ED%84%B4)
- 이벤트가 발생했을 때 실행될 기능이 다양하면서 변경이 필요한 경우 이벤트를 발생시키는 클래스의 변경없이 재사용하고자 할 때 사용하는 패턴
- ex) 알람 램프 버튼 프로그램

### [Chapter09-옵서버패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter09-%EC%98%B5%EC%84%9C%EB%B2%84%ED%8C%A8%ED%84%B4)
- 데이터의 변경이 발생하였을 때 상대 클래스 및 객체에 의존하지 않으면서 데이터 변경을 통보하고자 할 때 사용하는 패턴
- ex) 학생 성적 출력하는 프로그램

### [Chapter10-데커레이터패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter10-%EB%8D%B0%EC%BB%A4%EB%A0%88%EC%9D%B4%ED%84%B0%ED%8C%A8%ED%84%B4)
- 데코레이터 패턴은 기본 기능에 추가될 수 있는 많은 수의 부가 기능에 대해서 다양한 조합을 동적으로 구현할 수 있는 패턴
- ex) 도로 표시하는 프로그램

### [Chapter11-템플릿메서드패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter11-%ED%85%9C%ED%94%8C%EB%A6%BF%EB%A9%94%EC%84%9C%EB%93%9C%ED%8C%A8%ED%84%B4)
- 전체적으로 동일하면서 부분적으로 상이한 문장을 가지는 메소드의 코드 중복을 최소화할 때 유용한 패턴
- 전체적인 틀은 상위클래스에게 상속받고 변경되는 부분만 하위클래스에서 오버라이딩 하는 패턴(변화되는 부분만 추상 메소드로 만들어주면 됨)
- ex) 모터 프로그램

### [Chapter12-팩토리메서드패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter12-%ED%8C%A9%ED%86%A0%EB%A6%AC%EB%A9%94%EC%84%9C%EB%93%9C%ED%8C%A8%ED%84%B4)
- 객체의 생성 코드를 별도의 클래스/메소드로 분리함으로써 객체 생성의 변화를 대비하는 데 유용한 패턴
- ex) 엘리베이터 스케쥴링 프로그램

### [Chapter13-추상팩토리패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter13-%EC%B6%94%EC%83%81%ED%8C%A9%ED%86%A0%EB%A6%AC%ED%8C%A8%ED%84%B4)
- 관련성이 있는 여러 종류의 객체를 일관된 방식으로 생성하는 경우에 유용한 패턴
- ex) 엘리베이터 모터 설정 프로그램

### [Chapter14-컴퍼지트패턴](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Chapter14-%EC%BB%B4%ED%8D%BC%EC%A7%80%ED%8A%B8%ED%8C%A8%ED%84%B4)
- 부분(part)-전체(whole)의 관계를 가지는 객체들을 정의할 때 유용한 패턴(클라이언트는 전체와 부분을 구분하지 않고 동일한 인터페이스를 사용할 수가 있음)
- ex) 컴퓨터 부품 추가 프로그램

### [설계 패턴 실습 코드](https://github.com/jeonyoungho/TIL/tree/master/Java/DesignPattern/Lecture)

