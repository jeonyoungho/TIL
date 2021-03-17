
public class Main {

	public static void main(String[] args) {
		//Printer printer1 = Printer.getPrinter();
		//printer1.print(printer1.hashCode() + " Design Pattern 1");
		
		//Printer printer2 = Printer.getPrinter();
		//printer2.print(printer2.hashCode() + " Design Pattern 2");
		/*각 객체의 동일한 ID를 구하는 메소드 , 헤쉬코드가 같으면 동일한 객체
		    두 printer1 , 2 메소드가 동일한 객체를 참조할 뿐이다.*/
		
		UserThread[] user = new UserThread[5];
		for(int i=0;i<5;i++) {
			user[i] = new UserThread((i+1)+"-thread");
			user[i].start();
		}//다중스레드 환경에서 싱글턴패턴이 적용되지 않는다. -> 임계구역이 정해져있기 때문
	
	}

}
