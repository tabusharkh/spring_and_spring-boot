package com.data.service;

import com.data.entity.Deal;

public interface DealService {
	
	Deal findByStamp(String dealStamp);
	void save(Deal deal);

}
