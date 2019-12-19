import java.util.ArrayList;
import java.util.List;

public class MultiButtonController {
	private List<Command> theCommands = new ArrayList<Command>();

	public void addCommand(Command theCommand) {
		theCommands.add(theCommand);
	}
	
	public void setCommand(Command theCommand,int pos) {
		theCommands.set(pos,theCommand);
	}
	
	public void pressed(int pos) {
		theCommands.get(pos).execute();
}
	
	
}
