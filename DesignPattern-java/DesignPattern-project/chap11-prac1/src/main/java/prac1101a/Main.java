package prac1101a;

public class Main {

	public static void main(String[] args) {
		/* ------- SimpleReportGenerator -------*/
		SimpleReportGenerator simpleReportGenerator = new SimpleReportGenerator();
		Customer customer1 = new Customer("ȫ�浿1",150);
		Customer customer2 = new Customer("ȫ�浿2",350);
		Customer customer3 = new Customer("ȫ�浿3",50);
		Customer customer4 = new Customer("ȫ�浿4",450);
		Customer customer5 = new Customer("ȫ�浿5",550);
		
		simpleReportGenerator.addCustomer(customer1);
		simpleReportGenerator.addCustomer(customer2);
		simpleReportGenerator.addCustomer(customer3);
		simpleReportGenerator.addCustomer(customer4);
		simpleReportGenerator.addCustomer(customer5);
		
		System.out.println("---------- SimpleReportGenerator ----------");
		System.out.println(simpleReportGenerator.generate());

		/* ------- ComplexReportGenerator -------*/
		ComplexReportGenerator complexReportGenerator = new ComplexReportGenerator();
		
		complexReportGenerator.addCustomer(customer1);
		complexReportGenerator.addCustomer(customer2);
		complexReportGenerator.addCustomer(customer3);
		complexReportGenerator.addCustomer(customer4);
		complexReportGenerator.addCustomer(customer5);
		
		System.out.println("---------- ComplexReportGenerator ----------");
		System.out.println(complexReportGenerator.generate());
		
		/* ------- MyReportGenerator -------*/
		
		MyReportGenerator myReportGenerator = new MyReportGenerator();
		
		myReportGenerator.addCustomer(customer1);
		myReportGenerator.addCustomer(customer2);
		myReportGenerator.addCustomer(customer3);
		myReportGenerator.addCustomer(customer4);
		myReportGenerator.addCustomer(customer5);
		
		System.out.println("---------- MyReportGenerator ----------");
		System.out.println(myReportGenerator.generate());
		
	}

}
