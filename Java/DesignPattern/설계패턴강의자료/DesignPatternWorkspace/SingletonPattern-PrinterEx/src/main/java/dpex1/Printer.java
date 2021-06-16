package dpex1;

public class Printer {
	//private static Printer instance = new Printer();
	/*
	 * 이 클래스가 참조가 될때 메모리(JVM)에 로딩되어 생성됨 , instance변수가 굳이 바인딩 될 필요가 없어도 클래스를 참조하는 순간
	 * 불필요하게 객체가 생성됨
	 */

	private Printer() {
	}

	public static class LazyHolder {
		private static Printer instance = new Printer();
	} // getPrinter가 호출되는 순간 이 클래스가 메모리에 적재되어 instance 변수가 생성됨

	public static Printer getPrinter() { // synchronized 임계구역설정

		return LazyHolder.instance;
	}

	/*
	 * public static Printer getPrinter() { //synchronized 임계구역설정
	 *  if(instance ==null) {
	 *   return new Printer(); 
	 *   } return instance; //return
	 * LazyHolder.instance; }
	 */

	public void print(String str) {
		System.out.println(str);
	}

}
