package com.mjart.block.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mjart.block.dao.OfferDao;
import com.mjart.block.model.Offer;

@Service
public class OfferService {
	
	@Autowired
	private OfferDao offerDao;
	
	public List<Offer> getCurrent(){
		return offerDao.getOffers();
	}

	public void insert(Offer offer) {
		offerDao.insert(offer);
		
	}
}
