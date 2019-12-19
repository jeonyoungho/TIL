package prac1101a;

import java.util.ArrayList;
import java.util.List;

public class SimpleReportGenerator extends ReportGenerator {

	@Override
	protected String generateHeader(List<Customer> selected) {
		return String.format("���� %d\n", selected.size()); // �̾�Ŀ� ���缭 ���ڿ��� ����� String �޼ҵ�
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
