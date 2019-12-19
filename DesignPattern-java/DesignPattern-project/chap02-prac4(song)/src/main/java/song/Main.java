package song;

public class Main {

	public static void main(String[] args) {
		Song s1 = new Song("BTS song1");
		s1.setDiscountedMode(new OnSale());
		s1.setPrice(1000);
		
		Song s2 = new Song("BTS song2");
		s2.setDiscountedMode(new TodayEvent());
		s2.setPrice(1000);
		
		Song s3 = new Song("BTS song3");
		s3.setDiscountedMode(new NotDiscountedMode());
		s3.setPrice(1000);
		
		Song s4 = new Song("BTS song4");
		s4.setDiscountedMode(new XMas());
		s4.setPrice(1000);
		
		CartForSongs cart = new CartForSongs();
		
		cart.addSong(s1);
		cart.addSong(s2);
		cart.addSong(s3);
		cart.addSong(s4);
		
		System.out.println("음원 총 가격은 " + cart.totalPrice() + "원입니다.");
		
	}

}
