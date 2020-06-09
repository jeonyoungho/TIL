package song;

import java.util.ArrayList;

public class CartForSongs {
	ArrayList<Song> songs = new ArrayList<Song>();
	
//	public double calculateTotalPrice() {
//		double total=0.0;
//		Iterator<Song> itr = songs.iterator();
//		
//		while(itr.hasNext()) {
//			Song s =itr.next();
//			if(s.getDiscoungMode().equals("OnSale"))
//				total = total + (s.getPrice() - 0.1 * s.getPrice());
//			else if(s.getDiscountMode().equals("TodayEvent"))
//				total = total + (s.getPrice() - 0.3 * s.getPrice());
//			else
//				total = total + s.getPrice();
//		}
//		return total;
//	}
	
	public double totalPrice() {
		double tot = 0.0;
		for(Song song: songs) {
			tot += song.getDiscountedMode().getDiscountedPrice(song.getPrice()); 
		}
		return tot;
	}
	
	public void addSong(Song s) {
		songs.add(s);
	}
}
