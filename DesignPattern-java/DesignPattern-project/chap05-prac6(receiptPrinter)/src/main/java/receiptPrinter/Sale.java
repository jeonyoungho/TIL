package receiptPrinter;

import java.util.*;

public class Sale {
	private List<Item> items = new ArrayList<Item>();
	private Printer printer;
	
	public void receiptPrint() {
		StringBuffer buf = new StringBuffer();
		for(Item item:items) {
			buf.append(item.getName());
			buf.append(item.getPrice());
		}
		printer.print(buf.toString());
	}
	
	public void addItem(Item item) {
		items.add(item);
	}

	public Printer getPrinter() {
		return printer;
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
}


