# Builder패턴(Builder Pattern)
- 객체의 생성과정과 표현을 분리하는 패턴입니다.

### 왜 Builder패턴을 사용하느가?
- 다음의 UserInfo클래스가 있다고 가정해보자.
~~~
/**
*  다음과 같이 객체를 만듭니다.
*/
public class UserInfo {
   private String name;
   private int age;
   private String addr;

   public UserInfo(String name, int age, String addr){
       this.name = name;
       this.age = age;
       this.addr = addr;
   }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getUserInfo(){
       return String.format("name: %s, age: %d, addr: %s", name, age, addr);
    }
}
~~~

- 객체를 사용할 때 다음과 같이 생성 후 사용한다.
~~~
UserInfo userInfo = new UserInfo("테스터", 25, "서울시 강남구");
System.out.println(userInfo.getUserInfo());

// 결과 => name: 테스터, age: 25, addr: 서울시 강남구
~~~

- 만약 위의 예제에서 주소를 입력하지 않는다면 다음과 같이 생성할 수 있습니다.
~~~
UserInfo userInfo2 = new UserInfo("테스터", 25, null);
System.out.println(userInfo2.getUserInfo());

// 결과 => name: 테스터, age: 25, addr: null
~~~

- 다만 null을 입력하는 모양새가 좋지 않으니 추가로 생성자를 만들어 주는 방법이 있다.
- <b>그런데 만약 요건이 자주 변경된다면 매번 생성자를 만드는 것도 일이 될 것이다. 그래서 이런 상황을 피하기 위해 좀 더 직관적인 객체를 만들수 있게 돕는 것이 바로 빌더 패턴이다.</b>

### Builder패턴을 적용 시에 얻는 효과
- 1)불필요한 생성자 제거
- 2)데이터의 순서에 상관없이 객체 생성 가능
- 3)명시적 선언으로 이해하기 쉬움

#### Builder패턴 적용 예시
- 다음과 같이 UserInfoBuilder클래스를 추가한다.
~~~
/**
* 위에서 사용한 UserInfo 클래스에 빌더 패턴을 적용한 클래스
*/
public class UserInfoBuilder {
    private String name;
    private int age;
    private String addr;

    public UserInfoBuilder setName(String name){
        this.name = name;
        return this;
    }

    public UserInfoBuilder setAge(int age){
        this.age = age;
        return this;
    }

    public UserInfoBuilder setAddr(String addr){
        this.addr = addr;
        return this;
    }

    public UserInfo build(){
        return new UserInfo(name, age, addr);
    }
}
~~~

- 다음과 같이 객체를 사용한다.
~~~
UserInfoBuilder userInfoBuilder = new UserInfoBuilder();
UserInfo userInfo3 = userInfoBuilder
        .setName("테스터3")
        .setAddr("주소")
        .setAge(26)
        .build();

System.out.println(userInfo3.getUserInfo());

// 결과 => name: 테스터3, age: 26, addr: 주소
~~~

- 여기서 좋은점은 생성자를 이용해 만든게 아니기 때문에 순서를 마음대로 해도 된다는 점이다.
- <b>여기서 아쉬운 점이 있는데, 대상클래스와 그것을 연결할 빌더클래스 2개를 생성해야 한다는 점이다. 내부 클래스로도 가능하긴 하짐나 그것보다 코드를 압도적으로 줄여주는 플러그인이 있는데 바로 Lombok이다.</b>

### Lombok을 사용한 예시
- UserInfo클래스와 닮은 클래스를 다음과 같이 새로 추가한다.
~~~
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoLombok {
    private String name;
    private int age;
    private String addr;
}
~~~

- 다음과 같이 객체를 생성 후 사용한다.
~~~
UserInfoLombok userInfoLombok = UserInfoLombok.builder()
        .name("Lombok 적용")
        .addr("주소 테스트")
        .build();

System.out.println(userInfoLombok);

// 결과=> UserInfoLombok(name=Lombok 적용, age=0, addr=주소 테스트)
~~~

- <b>특정 변수에 기본값을 설정할 수도 있다.</b>
~~~
@Data
@Builder
public class UserInfoLombok {
    private String name;
    @Builder.Default private int age = 30;
    private String addr;
}
~~~

- 다음과 같이 실행하면 된다.
~~~
UserInfoLombok userInfoLombok = UserInfoLombok.builder()
        .name("Lombok 적용")
        .addr("주소 테스트")
        .build();

// 결과=> UserInfoLombok(name=Lombok 적용, age=30, addr=주소 테스트)
~~~

#### 출처
- https://lemontia.tistory.com/483