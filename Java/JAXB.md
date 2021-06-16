# JAXB

### JAXB란?
- JAXB(Java Architecture for XML Binding)는 Java Object를 XML로 직렬화(마샬링)하고, XML을 Java Object로 역직렬화(언마샬링)해주는 자바 API
- JDK6 ~ 9 버전은 JAXB가 내장되어 있어 라이브러리를 추가 할 필요가 없음

### Annotation
- @XmlRootElement - XML의 Root Element 명을 정의합니다.
- @XmlElement - XML의 Element 명을 정의합니다.
- @XmlType - XML 스키마 이름과 namespace를 정의합니다. propOrder 속성을 이용해서 XML 표현 시 요소들의 표현 순서를 정의합니다.
- @XmlElementWrapper - 다른 XML 요소들을 감싸는 역할을 합니다. List 같은 컬렉션 객체들을 XML 변환할 때 사용할 수 있습니다.

### 출처
- https://zgundam.tistory.com/17
- https://tychejin.tistory.com/135
- https://m.blog.naver.com/PostView.nhn?blogId=hummer98&logNo=10113833356&proxyReferer=https:%2F%2Fwww.google.com%2F
- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api/2.3.1
- https://ukzzang.tistory.com/12
- https://xens.tistory.com/entry/JAXB%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%9C-XML-Marshalling-UnMarshallingFile-%EC%98%88%EC%A0%9C-1