package com.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.entity.Deal;

public interface DealRepository extends JpaRepository<Deal, Long>{
	
	Deal findByStamp(String dealStamp);

}
