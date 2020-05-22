package kr.ac.hansung.exception;


public class UserDuplicatedException extends RuntimeException {

	private static final long serialVersionUID = 2081249869346761091L;
	
	private String userName;
	
	public UserDuplicatedException(String userName) {
		super();
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
}
