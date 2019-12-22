package kr.ac.hansung.csemall;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/ac/hansung/conf/beans.xml"); //컨테이너 생성
		
		OfferDao offerDao = (OfferDao)context.getBean("offerDao"); //Dao객체생성 - 실제 DB에 접근하는 객체
		
		System.out.println("The record count is " + offerDao.getRowCount() );
		
		List<Offer> offerList = offerDao.getOffers(); //Read
		
		for(Offer offer:offerList) {
			System.out.println(offer);
		}
		
		Offer offer = new Offer();
		offer.setName("trudy");
		offer.setEmail("trudy.hansung.ac.kr");
		offer.setText("instructor of spring framework");
		
		if(offerDao.insert(offer)) { //creat
			System.out.println("object is inserted successfully");
		}else {
			System.out.println("object insert failed");
		}

		offer = offerDao.getOffer("trudy"); //read
		offer.setName("YoungHo");
		
		if(offerDao.update(offer)) { //update
			System.out.println("object is updated successfully");
		}else {
			System.out.println("object update failed");
		}
		
		offer = offerDao.getOffer("YoungHo"); //read
		System.out.println(offer);
		
		if(offerDao.delete(offer.getId())) {//delete
			System.out.println("object is deleted successfully");
		}else {
			System.out.println("object delete failed");
		}
		
		context.close();
	}

}
