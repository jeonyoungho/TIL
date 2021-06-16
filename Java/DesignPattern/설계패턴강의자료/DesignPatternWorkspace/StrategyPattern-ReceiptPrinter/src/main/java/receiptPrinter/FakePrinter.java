package receiptPrinter;


public class FakePrinter extends Printer {
	
	private String contents;
	
	public void print(String str) {
		System.out.println(str);
		contents = str;
	}
	
	public String getContents() {
		return contents;
	}
}
