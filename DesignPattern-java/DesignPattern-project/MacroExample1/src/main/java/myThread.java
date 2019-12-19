
public class myThread extends Thread {
	private int n;

	public void run() {
		while (true) {
	
				++n;
				System.out.println(n);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("interrupted exception!!");
					break;
				}
			}
		}
	}
	
//	public void changeFalse() {
//		flag = false;
//	}
//	public void changeTrue() {
//		flag =true;
//	}
	
	
	
