package kr.ac.hansung.exception;

public class UserNotFoundException extends RuntimeException {

	// 객체를 네트워크로 보낸다 또는 디비에 저장할 때 serialization 거쳐서 byte stream으로 변환해야만 한다
	// 이거를 객체로 로딩할때 deserialization
	// serialization과 deserialization의 버전이 일치해야 하기 때문에 버전을 지정해줘야함
	private static final long serialVersionUID = 8795546082074185666L;

	private long userId;

	public UserNotFoundException(long userId) {
		this.userId = userId;
	}

	public long getUserId() {
		return userId;
	}

}
