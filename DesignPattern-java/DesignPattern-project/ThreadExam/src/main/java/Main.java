
public class Main {

	public static void main(String[] args) {
        JoinExam thread = new JoinExam();
        // Thread ���� 
        thread.start(); 
        System.out.println("Thread�� ����ɶ����� ��ٸ��ϴ�.");
//        try {
//            // �ش� �����尡 ���⶧���� ����
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Thread�� ����Ǿ����ϴ�."); 

	}

}
