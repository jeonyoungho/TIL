import java.util.ArrayList;

public class PrinterManager {
	private static PrinterManager mgr = null;
	private ArrayList<Printer> managedPrinters = new ArrayList<Printer>();
	private PrinterManager() {
		managedPrinters.add(new Printer());
		managedPrinters.add(new Printer());
		managedPrinters.add(new Printer());
	}
	
	public static synchronized PrinterManager getPrinterManager() {
		if(mgr == null) {
			mgr = new PrinterManager();
		}
		return mgr;
	}
	
	public synchronized Printer getPrinter() {
		while(true) {
			for(Printer printer:managedPrinters) {
				if(printer.isAvailable()) {
					printer.setAvailable(false);
					return printer;
				}
			}
		}		
	}
}




/*
public class PrinterManager {
	private Printer[] managedPrinters = new Printer[3];
	private int i=0;
	private PrinterManager() {
		managedPrinters[0] = new Printer();
		managedPrinters[1] = new Printer();
		managedPrinters[2] = new Printer();
	}
	
	public static class LazyHolder{
		private static PrinterManager pmg = new PrinterManager();
	}
	
	public static PrinterManager getPrinterManager() {
		return LazyHolder.pmg;
	}
	
	public synchronized Printer getPrinter() {
		while(true) {
			if(managedPrinters[i].isAvailable()) {
				managedPrinters[i].setAvailable(false);
				System.out.println(i + "번째 객체 사용");
				return managedPrinters[i];
			}
			i++;
			if(i%3==0) {
				i=0;
			}
		}		
	}
	
}*/