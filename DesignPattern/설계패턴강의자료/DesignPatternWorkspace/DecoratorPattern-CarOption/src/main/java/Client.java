
public class Client {

	public static void main(String[] args) {
		CarComponent car = new BasicCar(1000);

		for (String decoratorName : args) {
			if (decoratorName.equalsIgnoreCase("AirBag"))
				car = new AirBagDecorator(car, 100);
			if (decoratorName.equalsIgnoreCase("ESC"))
				car = new ESCDecorator(car, 50);
			if (decoratorName.equalsIgnoreCase("Navi"))
				car = new NaviDecorator(car, 30);
			if (decoratorName.equalsIgnoreCase("SCC"))
				car = new SCCDecorator(car, 70);
		}


		System.out.println("Car Price : " + car.getPrice());
		System.out.println("Car Info : " + car.getCarinfo());

		/*
		 * System.out.println(); System.out.println("Car Price : " +
		 * basicCar.getPrice()); System.out.println("Car Info : " +
		 * basicCar.getCarinfo());
		 * 
		 * CarComponent basicCarWithAirBag = new AirBagDecorator(basicCar, 10000);
		 * 
		 * System.out.println(); System.out.println("Car Price : " +
		 * basicCarWithAirBag.getPrice()); System.out.println("Car Info : " +
		 * basicCarWithAirBag.getCarinfo());
		 * 
		 * CarComponent basicCarWithAirBagSCC = new SCCDecorator(basicCarWithAirBag,
		 * 10000);
		 * 
		 * System.out.println(); System.out.println("Car Price : " +
		 * basicCarWithAirBagSCC.getPrice()); System.out.println("Car Info : " +
		 * basicCarWithAirBagSCC.getCarinfo());
		 * 
		 * CarComponent basicCarWithSCCNaviESC = new SCCDecorator(new NaviDecorator(new
		 * ESCDecorator(basicCar, 10000), 10000), 10000);
		 * 
		 * System.out.println(); System.out.println("Car Price : " +
		 * basicCarWithSCCNaviESC.getPrice()); System.out.println("Car Info : " +
		 * basicCarWithSCCNaviESC.getCarinfo());
		 */
	}

}
