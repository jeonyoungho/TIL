import java.util.StringTokenizer;

public class TestMain {

	public static void main(String[] args) {
	
		
		/* <StringTokenizer Constructor> 
		   1. public StringTokenizer(String str);	
		   - 전달된 매개변수 str을 기본(default) delim으로 분리합니다. 기본 delimiter는 공백 문자들인 " \t\n\r\t"입니다. 

		   2. public StringTokenizer(String str,String delim);	
		   - 특정 delim으로 문자열을 분리합니다.
			
		   3. public StringTokenizer(String str,String delim,boolean returnDelims);
	       - str을 특정 delim으로 분리시키는데 그 delim까지 token으로 포함할지를 결정합니다. 그 매개변수가 returnDelims로 true일시 포함, false일땐 포함하지 않습니다.
		  
		   <StringTokenizer method>
		   1. int countTokens() 
		   - 남아있는 token의 개수를 반환합니다. 전체 token의 갯수가 아닌 현재 남아있는 token 개수입니다.
			
		   2. boolean hasMoreElements(), boolean hasMoreTokens()
		   - 다음의 token을 반환합니다. StringTokenizer는 내부적으로 어떤 위치의 토큰을 사용하였는지 기억하고 있고 그 위치를 다음으로 옮깁니다. 두가지 메소드는 모두 같은 값을 반환합니다.
			
		   3. Object nextElement(), String nextToken()
		   - 이 두가지 메소드는 다음의 토큰을 반환합니다. 두가지 메소드는 같은 객체를 반환하는데 반환형은 다르네요. nextElement는 Object를, nextToken은 String을 반환하고 있습니다.
			
		   출처 - https://reakwon.tistory.com/90
		 */
		
		
		String str = "Hello World !!!";
		StringTokenizer st = new StringTokenizer(str);
		
		while(st.hasMoreTokens()) { // 
			String s = st.nextToken(); // Returns String type value from next token
			int remain = st.countTokens(); // Returns the number of remaining tokens  
			System.out.println(s + "(remail: " + remain + ")");
		}
		
	}

}
