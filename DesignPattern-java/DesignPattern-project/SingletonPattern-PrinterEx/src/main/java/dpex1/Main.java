package dpex1;

public class Main {

	public static void main(String[] args) {
		//Printer printer1 = Printer.getPrinter();
		//printer1.print(printer1.hashCode() + " Design Pattern 1");
		
		//Printer printer2 = Printer.getPrinter();
		//printer2.print(printer2.hashCode() + " Design Pattern 2");
		/*�� ��ü�� ������ ID�� ���ϴ� �޼ҵ� , �콬�ڵ尡 ������ ������ ��ü
		    �� printer1 , 2 �޼ҵ尡 ������ ��ü�� ������ ���̴�.*/
		
		UserThread[] user = new UserThread[5];
		for(int i=0;i<5;i++) {
			user[i] = new UserThread((i+1)+"-thread");
			user[i].start();
		}//���߽����� ȯ�濡�� �̱��������� ������� �ʴ´�. -> �Ӱ豸���� �������ֱ� ����
	
	}

}
