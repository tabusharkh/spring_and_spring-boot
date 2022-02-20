package com.data.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.data.entity.Deal;
import com.data.service.DealService;

@Component
public class DealValidator implements Validator{
	
	@Autowired
	private DealService dealService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Deal.class.equals(clazz) ;
	}

	@Override
	public void validate(Object target, Errors errors) {
		Deal deal = (Deal) target;

		//Validating the first input (From currency code).
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromCurrencyCode", "NotEmpty");
		if(StringUtils.isNumeric(deal.getFromCurrencyCode())) {
			errors.reject("fromCurrencyCode", "currency.numeric.value");
		}
		
		if(deal.getFromCurrencyCode().length() > 3) {
			errors.rejectValue("fromCurrencyCode", "currency.length.long");
		}
		
		//Validating the second input (To currency code).
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toCurrencyCode", "NotEmpty");
		if(StringUtils.isNumeric(deal.getToCurrencyCode())) {
			errors.reject("toCurrencyCode", "currency.numeric.value");
		}
		
		if(deal.getToCurrencyCode().length() > 3) {
			errors.rejectValue("toCurrencyCode", "currency.length.long");
		}
		
		//Validating the third input (deal stamp)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stamp", "NotEmpty");
		if(deal.getStamp() != null && !deal.getStamp().isEmpty()) {
			System.out.println(deal.getStamp());
			if(dealService.findByStamp(deal.getStamp()) != null) {
				errors.rejectValue("stamp", "deal.stamp.exist");
			}
		}
		
		
		//Validating the last input (deal amount)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "NotEmpty");
		
	}

}
