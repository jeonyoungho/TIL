package com.mjart.block.controller;	



import java.text.ParseException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjart.block.model.Ecodog;
import com.mjart.block.model.ReservationInformation;
import com.mjart.block.service.DdnayoService;
import com.mjart.block.service.HoteltimeService;
import com.mjart.block.service.NaverService;
import com.mjart.block.service.OndaService;
import com.mjart.block.service.YanoljaService;

@Controller
public class EcodogController {
	@Autowired
	private Ecodog ecodog;
	
	@Autowired
	private DdnayoService ddnayoService;
	@Autowired
	private OndaService ondaService;
	@Autowired
	private NaverService naverService;
	@Autowired
	private HoteltimeService hoteltimeService;
	@Autowired
	private YanoljaService yanoljaService;
	
	@RequestMapping("/ecodogblockroom")
	public String ecodogBlockRoom(Model model,ReservationInformation ri) { //스프링에의해 자동적으로 바인딩되서 착착 들어간다
		System.out.println("asdf");
		model.addAttribute("pension",ecodog);
		model.addAttribute("ri",ri);
		
		
		if(!ri.getFlowinDomain().equals("떠나요")) {
			model.addAttribute("ddnayoResult",ddnayoService.ddnayoBlock(ecodog, ri));
		}
		
		Calendar cal = Calendar.getInstance();
    	cal.set(ri.getYear(),ri.getMonth()-1,ri.getDay());
    	
		for(int i=0;i<ri.getLengthOfStay();i++) { //연박만큼방막기
	    	cal.add(Calendar.DATE,i);
	    	ri.setYear(cal.get(Calendar.YEAR));
	    	ri.setMonth(cal.get(Calendar.MONTH)+1);
	    	ri.setDay(cal.get(Calendar.DAY_OF_MONTH));
	    	System.out.println(ri.getYear()+"년 " + ri.getMonth()+ "월 " +ri.getDay()+"일 막기" );
			for(String saleschannel : ecodog.getSalesChannel()) {

				if(!ri.getFlowinDomain().equals(saleschannel) && saleschannel.equals("온다")) {
					model.addAttribute("ondaResult",ondaService.ondaBlock(ecodog, ri));
				}
				if(!ri.getFlowinDomain().equals(saleschannel) && saleschannel.equals("네이버")) {
					try {
						model.addAttribute("naverResult",naverService.naverBlock(ecodog, ri));
						}catch (ParseException e) {return "result";}
				}
				if(!ri.getFlowinDomain().equals(saleschannel) && saleschannel.equals("호텔타임")) {
					model.addAttribute("hoteltimeResult",hoteltimeService.hoteltimeBlock(ecodog, ri));
				}
				if(!ri.getFlowinDomain().equals(saleschannel) && saleschannel.equals("야놀자")) {
					model.addAttribute("yanoljaResult",yanoljaService.yanoljaBlock(ecodog, ri));
				}
			}
		}
		
		cal.add(Calendar.DATE,-ri.getLengthOfStay()+1);
    	ri.setYear(cal.get(Calendar.YEAR));
    	ri.setMonth(cal.get(Calendar.MONTH)+1);
    	ri.setDay(cal.get(Calendar.DAY_OF_MONTH));
		
		return "result";
	}
	
	@RequestMapping("/ecodogopenroom")
	public String ecodogOpenRoom(Model model,ReservationInformation ri) { //스프링에의해 자동적으로 바인딩되서 착착 들어간다
		
		model.addAttribute("pension",ecodog);
		model.addAttribute("ri",ri);
		
		
		if(!ri.getFlowinDomain().equals("떠나요")) {
			model.addAttribute("ddnayoResult",ddnayoService.ddnayoOpen(ecodog, ri));
		}
		
		Calendar cal = Calendar.getInstance();
    	cal.set(ri.getYear(),ri.getMonth()-1,ri.getDay());
    	
		for(int i=0;i<ri.getLengthOfStay();i++) { //연박만큼방막기
	    	cal.add(Calendar.DATE,i);
	    	ri.setYear(cal.get(Calendar.YEAR));
	    	ri.setMonth(cal.get(Calendar.MONTH)+1);
	    	ri.setDay(cal.get(Calendar.DAY_OF_MONTH));
	    	System.out.println(ri.getYear()+"년 " + ri.getMonth()+ "월 " +ri.getDay()+"일 막기" );
			for(String saleschannel : ecodog.getSalesChannel()) {

				if(!ri.getFlowinDomain().equals(saleschannel) && saleschannel.equals("온다")) {
					model.addAttribute("ondaResult",ondaService.ondaOpen(ecodog, ri));
				}
				if(!ri.getFlowinDomain().equals(saleschannel) && saleschannel.equals("네이버")) {
					try {
						model.addAttribute("naverResult",naverService.naverOpen(ecodog, ri));
						}catch (ParseException e) {return "result";}
				}
				if(!ri.getFlowinDomain().equals(saleschannel) && saleschannel.equals("호텔타임")) {
					model.addAttribute("hoteltimeResult",hoteltimeService.hoteltimeOpen(ecodog, ri));
				}
				if(!ri.getFlowinDomain().equals(saleschannel) && saleschannel.equals("야놀자")) {
					model.addAttribute("yanoljaResult",yanoljaService.yanoljaOpen(ecodog, ri));
				}
			}
		}
		
		cal.add(Calendar.DATE,-ri.getLengthOfStay()+1);
    	ri.setYear(cal.get(Calendar.YEAR));
    	ri.setMonth(cal.get(Calendar.MONTH)+1);
    	ri.setDay(cal.get(Calendar.DAY_OF_MONTH));
		
		return "result";
	}

	@RequestMapping("/ecodog")
	public String selectEcodog(Model model) {
		return "ecodog";
	}
	
	@RequestMapping("/ecodogblock")
	public String selectBlockRoom(Model model) {
		return "ecodogblock";
	}
	
	@RequestMapping("/ecodogopen")
	public String selectOpenRoom(Model model) {
		return "ecodogopen";
	}

}
