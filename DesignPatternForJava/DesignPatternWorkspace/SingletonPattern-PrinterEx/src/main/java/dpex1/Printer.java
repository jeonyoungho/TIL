package dpex1;

public class Printer {
	//private static Printer instance = new Printer();
	/*
	 * �� Ŭ������ ������ �ɶ� �޸�(JVM)�� �ε��Ǿ� ������ , instance������ ���� ���ε� �� �ʿ䰡 ��� Ŭ������ �����ϴ� ����
	 * ���ʿ��ϰ� ��ü�� ������
	 */

	private Printer() {
	}

	public static class LazyHolder {
		private static Printer instance = new Printer();
	} // getPrinter�� ȣ��Ǵ� ���� �� Ŭ������ �޸𸮿� ����Ǿ� instance ������ ������

	public static Printer getPrinter() { // synchronized �Ӱ豸������

		return LazyHolder.instance;
	}

	/*
	 * public static Printer getPrinter() { //synchronized �Ӱ豸������
	 *  if(instance ==null) {
	 *   return new Printer(); 
	 *   } return instance; //return
	 * LazyHolder.instance; }
	 */

	public void print(String str) {
		System.out.println(str);
	}

}
