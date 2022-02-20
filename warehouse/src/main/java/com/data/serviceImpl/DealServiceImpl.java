package com.data.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.data.entity.Deal;
import com.data.repo.DealRepository;
import com.data.service.DealService;

@Service
public class DealServiceImpl implements DealService{
	
	@Autowired
	private DealRepository dealRepository;

	@Override
	public Deal findByStamp(String dealStamp) {
		return dealRepository.findByStamp(dealStamp);
	}

	@Override
	@Transactional
	public void save(Deal deal) {
		dealRepository.save(deal);
		
	}

}
