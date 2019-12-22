
public class UserThread extends Thread {
	private Ticket myTicket;

	public UserThread(String name) {
		super(name);
	}
	
	public void run() {
		TicketManager mgr = TicketManager.getTicketManager();
		myTicket = mgr.getTicket();//티켓 구입 요령
	}
	
	public Ticket getMyTicket() {
		return myTicket;
	}
}
