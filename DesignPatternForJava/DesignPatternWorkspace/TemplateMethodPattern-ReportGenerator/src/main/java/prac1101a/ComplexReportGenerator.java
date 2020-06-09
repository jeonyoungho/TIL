package prac1101a;

import java.util.List;

public class ComplexReportGenerator extends ReportGenerator {

	@Override
	protected String generateHeader(List<Customer> selected) {
		return String.format("** ���� %d�Դϴ� **\n", selected.size()); // �̾�Ŀ� ���缭 ���ڿ��� ���� report������ �����϶�
	}

	@Override
	protected boolean customerReportCondition(Customer customer) {
		return customer.getPoint() > 100;
	}

	@Override
	protected String getReportForCustomer(Customer customer) {
		return String.format("%d : %s\n", customer.getPoint(), customer.getName());
	}

	@Override
	protected String generateReportFooter(List<Customer> selected) {
		int totalPoint = 0;

		for (Customer customer : selected) {
			totalPoint += customer.getPoint();
		}

		return String.format("** ���� �հ� : %d", totalPoint);
	}
}
