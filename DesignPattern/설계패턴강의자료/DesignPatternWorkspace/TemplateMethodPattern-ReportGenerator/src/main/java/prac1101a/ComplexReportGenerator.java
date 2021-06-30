package prac1101a;

import java.util.List;

public class ComplexReportGenerator extends ReportGenerator {

	@Override
	protected String generateHeader(List<Customer> selected) {
		return String.format("** 고객수 %d입니다 **\n", selected.size()); // 이양식에 맞춰서 문자열을 만들어서 report변수에 대입하라
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

		return String.format("** 점수 합계 : %d", totalPoint);
	}
}
