package receiptPrinter;

public class Main {

	public static void main(String[] args) {
		Item item1 = new Item();
		item1.setName("jelly1");
		item1.setPrice(1000);
		
		Item item2 = new Item();
		item2.setName("jelly2");
		item2.setPrice(2000);
		
		Sale sale = new Sale();
		sale.addItem(item1);
		sale.addItem(item2);
		
		FakePrinter f = new FakePrinter();
		sale.setPrinter(f);
		sale.receiptPrint();
		
		String s = f.getContents();
		System.out.println(s);
		if(s.equals("jelly11000jelly22000"))
			System.out.println("OK");
		else {
			System.out.println("fail");
		}
		
	}

}
