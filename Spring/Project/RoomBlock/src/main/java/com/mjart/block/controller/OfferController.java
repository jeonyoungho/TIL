package com.mjart.block.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mjart.block.model.Offer;
import com.mjart.block.service.OfferService;

@Controller
public class OfferController {
	
	@Autowired
	private OfferService offerService;
	
	@RequestMapping("/offers")
	public String showOffers(Model model) {
		List<Offer> offers = offerService.getCurrent();
		model.addAttribute("offers", offers);
		
		return "offers";
	}
	
	@RequestMapping("/docreate")
	public String docreate(Model model,@Valid Offer offer, BindingResult result) { //스프링에의해 자동적으로 바인딩되서 착착 들어간다
		//offer 자체가 model에도 들어가서 controller에 넘어감 , Valid annotation을 달아주면 스프링이 검증해서 결과를 BindingResult 객체에 넣어줌
		if(result.hasErrors()) {
			System.out.println("=== Web Form data does not validated ===");
			List<ObjectError> errors = result.getAllErrors();
	//		System.out.println(result.toString());
			for(ObjectError error:errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "createoffer";
		}
		
		offerService.insert(offer); //controller -> service -> dao
		
		return "offercreated";
	}
	
}
