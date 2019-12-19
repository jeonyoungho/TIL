import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatePanel extends JPanel {
	public static JLabel stateLabel = new JLabel();
	public StatePanel() {
		stateLabel.setText("Ready");
		add(stateLabel);
	}
	
	public static void setLabel(String text) {
		stateLabel.setText(text);
	}
}
