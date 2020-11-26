# Spring 트랜잭션 처리

- 서비스단 비즈니스 로직에서의 트랜잭션 처리는 굉장히 중요하다.<br>
- 이를 처리하기 위한 트랜잭션은 두 가지 설정 방법이 존재한다.<br>

1. Annotation을 이용한 설정<br>

- context에 다음과 같이 설정
~~~
<tx:annotation-driven transaction-manager="transactionManager" />
~~~

- 트랜잭션을 적용할 클래스, 인터페이스, 메소드에 @Transactional 어노테이션을 선언<br>
~~~
public interface MemberService {
	public String selectUser(String userId);
	@Transactional
	public int inserUser(User user);
}

@Service
@Transactional
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberDao dao;
	
	public selectUser(String userId) {
		return dao.getUser(userId);
	}
	
	@Transactional
	public int insertUser(User user) {
		user.setUserno(dao.insertUser(user));
		return dao.insertUserIntereset(user);
	}
}
~~~ 

2. &lt;tx:advice&gt; 태그를 이용한 설정

- 참고 https://ssmlim.tistory.com/45