package com.mjart.block.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.mjart.block.model.Ecodog;

@Component
public class PensionContext {
	
	@Bean Ecodog createEcodog() {
		Ecodog ecodog = new Ecodog();
		
		ecodog.setDdnayoId("dog8555");
		ecodog.setDdnayoPW("008500");
		ecodog.setDdnayoListIndex(5);

		ecodog.setOndaId("jastudy@naver.com");
		ecodog.setOndaPw("a123456789!");
		ecodog.setOndaListIndex(10);
		ecodog.setPensionName("에코독애견펜션");
		
		ecodog.setNaverId("pjw8211");
		ecodog.setNaverPw("abcd1234");
		ecodog.setNaverProductNumber(281271);
		
		ecodog.setHoteltimeId("가평에코독펜션");
		ecodog.setHoteltimePW("a123456789!");
		
		ecodog.setYanoljaId("가평에코독");
		ecodog.setYanoljaPw("a123456789!");
		
		List<String> channelList = new ArrayList<String>();
		channelList.add("온다");
		channelList.add("네이버");
		channelList.add("호텔타임");
		channelList.add("야놀자");
		ecodog.setSalesChannel(channelList);
		
		return ecodog;
	}
	
}
