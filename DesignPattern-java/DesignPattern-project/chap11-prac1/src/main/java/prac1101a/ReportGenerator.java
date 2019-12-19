package prac1101a;

import java.util.ArrayList;
import java.util.List;

public abstract class ReportGenerator {

	private List<Customer> customers = new ArrayList<Customer>();
	private List<Customer> selected = new ArrayList<Customer>();

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public String generate() {
		String report;
		report = generateReportHeadder(customers);
		report += generateReportBody(selected);
		report += generateReportFooter(selected);

		return report;
	}

	protected String generateReportHeadder(List<Customer> customers) { //공통되는 부분을 묶어서 
		for (Customer customer : customers) {
			if (customerReportCondition(customer)) {
				selected.add(customer);
			}
		}
		return generateHeader(selected);
	}
	protected abstract boolean customerReportCondition(Customer selected);
	protected abstract String generateHeader(List<Customer> selected);

	
	
	protected String generateReportBody(List<Customer> selected) {
		String report = "";
		for (Customer customer : selected) {
			report += getReportForCustomer(customer);
		}
		return report;
	}
	protected abstract String getReportForCustomer(Customer customer);

	
	
	protected abstract String generateReportFooter(List<Customer> selected);

}
