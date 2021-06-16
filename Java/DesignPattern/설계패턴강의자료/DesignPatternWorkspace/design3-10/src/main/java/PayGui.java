import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class PayGui extends Frame implements ActionListener, DocumentListener {
	private PayCalculator payCalculator;
	private JLabel workingHoursLabel = new JLabel("Working Hours = ", Label.RIGHT);
	private JLabel overTimeHoursLabel = new JLabel("Overtime Hours = ", Label.RIGHT);
	private JLabel payAmountLabel = new JLabel("Pay Amount = ", Label.RIGHT);
	
	private JTextField tfWorkingHours = new JTextField();
	private JTextField tfOvertimeHours = new JTextField();
	private JTextField tfResult = new JTextField();

	private JButton calcButton = new JButton("Calculate");
	private JButton resetButton = new JButton("Reset");
	private JButton end = new JButton("Stop");
	
	public PayGui() {
		super("Payment Calculation");
		this.init();
		this.start();
		this.setPayLogic(new PayLogic1());
		this.setSize(500, 250);
		this.setVisible(true);
	}
	
	public void init() {
		this.setLayout(new GridLayout(5,1));
		
		Panel p = new Panel(new BorderLayout());
		p.add("West", workingHoursLabel);
		p.add("Center", tfWorkingHours);
		this.add(p);
		
		Panel p1 = new Panel(new BorderLayout());
		p1.add("West", overTimeHoursLabel);
		p1.add("Center", tfOvertimeHours);
		this.add(p1);
		
		Panel p2 = new Panel(new FlowLayout(FlowLayout.CENTER));
		p2.add(calcButton);
		this.add(p2);
		
		Panel p3 = new Panel(new BorderLayout());
		p3.add("West", payAmountLabel);
		p3.add("Center", tfResult);
		this.add(p3);
		
		Panel p4 = new Panel(new FlowLayout(FlowLayout.RIGHT));
		p4.add(resetButton);
		p4.add(end);
		this.add(p4);

	
	}
	
	public void setPayLogic(PayCalculator payLogic) {
		this.payCalculator = payLogic;
	}
	
	public void start() {
		calcButton.addActionListener(this);
		resetButton.addActionListener(this);
		
		tfWorkingHours.getDocument().addDocumentListener(this);
		tfOvertimeHours.getDocument().addDocumentListener(this);
		
		end.addActionListener(this);
		
		calcButton.setEnabled(false);
		resetButton.setEnabled(false);
	}
	
	public boolean isDataEntered() {
		if(tfWorkingHours.getText().trim().length() == 0 || tfOvertimeHours.getText().length() == 0)
			return false;
		return true;
	}
	
	//DocumentListener : 입력값이 바뀌면 액션을 취하는 리스너
	//아래의 insert, remove, changed Update 메소드들이 불린다
	public void insertUpdate(DocumentEvent e) {
		checkData();
	}
	
	public void removeUpdate(DocumentEvent e) {
		checkData();
	}
	
	public void changedUpdate(DocumentEvent e) {
		checkData();
	}
	
	private void checkData() {
		calcButton.setEnabled(isDataEntered());
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == end) {
			System.exit(0);
		}
		
		if(e.getSource() == resetButton) {
			tfWorkingHours.setText("");
			tfOvertimeHours.setText("");
			tfWorkingHours.requestFocus();
			tfResult.setText("");
			resetButton.setEnabled(false);
			return;
		}
		
		if(e.getSource() == calcButton) {
			int x = 0;
			try {
				x = Integer.parseInt(tfOvertimeHours.getText().trim());
			} catch(NumberFormatException ee) {
				tfWorkingHours.setText("");
				tfWorkingHours.requestFocus();
				return;
			}
			
			int y = 0;
			try {
				y = Integer.parseInt(tfOvertimeHours.getText().trim());
			} catch(NumberFormatException ee) {
				tfOvertimeHours.setText("");
				tfOvertimeHours.requestFocus();
				return;
			}
			
			int payAmount = 0;
			payAmount = payCalculator.calculate(x, y);
			
			tfResult.setText(String.valueOf(payAmount));
			resetButton.setEnabled(true);
		}
	}


	
	
	
	
	
	
	
	
}
