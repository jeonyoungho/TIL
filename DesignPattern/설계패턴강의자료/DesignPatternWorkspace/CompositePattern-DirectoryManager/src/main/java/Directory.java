import java.util.ArrayList;
import java.util.List;

public class Directory extends AbstractFile{
	private List<AbstractFile> entries = new ArrayList<AbstractFile>();
	
	public Directory(String name) {
		super(name);
	}
	
	public void addEntry(AbstractFile entry) {
		entries.add(entry);
		entry.setDepth(getDepth() + 1);
	}
	
	public void removeEntry(Object entry) {
		entries.remove(entry);
	}
	
	public int getSize() {
		int size = 0;
		for(AbstractFile entry:entries) {
			size += entry.getSize();
		}
		return size;
	}
	
	public void print() {
		for(int i=0;i<getDepth();i++)
			System.out.println("\t");
		
		System.out.println("[Directory] " + getName() + ", Size: " +getSize());
		
		for(AbstractFile entry:entries) {
			entry.print();
		}
		
	}
	
	
	
}
