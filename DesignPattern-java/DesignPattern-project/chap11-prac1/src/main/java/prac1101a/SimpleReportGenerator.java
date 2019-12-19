package prac1101a;

import java.util.ArrayList;
import java.util.List;

public class SimpleReportGenerator extends ReportGenerator {

	@Override
	protected String generateHeader(List<Customer> selected) {
		return String.format("고객수 %d\n", selected.size()); // 이양식에 맞춰서 문자열을 만드는 String 메소드
	}

	@Override
	protected boolean customerReportCondition(Customer customer) {
		return true;
	}

	@Override
	protected String getReportForCustomer(Customer customer) {
		return String.format("%s : %d\n", customer.getName(), customer.getPoint());
	}

	@Override
	protected String generateReportFooter(List<Customer> customers) {
		return "";
	}

}
