package prac1101a;

import java.util.ArrayList;
import java.util.List;

public class MyReportGenerator extends ReportGenerator {

	@Override
	protected String generateHeader(List<Customer> selected) {
		return String.format("���� ����Դϴ�\n");
	}

	@Override
	protected boolean customerReportCondition(Customer customer) {
		return customer.getPoint() >= 50;
	}

	@Override
	protected String getReportForCustomer(Customer customer) {
		return String.format("*** %s : %d\n", customer.getName(), customer.getPoint());
	}

	@Override
	protected String generateReportFooter(List<Customer> selected) {
		Customer maxCustomer = null;
		Customer minCustomer = null;

		int min = 1001;
		int max = 0;

		for (Customer customer : selected) {
			if (customer.getPoint() < min) {
				min = customer.getPoint();
				minCustomer = customer;
			}
			if (customer.getPoint() > max) {
				max = customer.getPoint();
				maxCustomer = customer;
			}
		}

		return String.format("*** �ְ��� : %s , ������ : %s", maxCustomer.getName(), minCustomer.getName());
	}

}
