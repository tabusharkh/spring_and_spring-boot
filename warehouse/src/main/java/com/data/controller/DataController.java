package com.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.data.entity.Deal;
import com.data.entity.User;
import com.data.service.DealService;
import com.data.service.UserService;
import com.data.validate.DealValidator;
import com.data.validate.UserValidator;

@Controller
public class DataController {
	
	@Autowired
	private DealValidator dealValidator;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private DealService dealService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String getDeal(Model model) {
		
		model.addAttribute("loginForm", new User());
		return "login";
	}
	
	@PostMapping("/addDeal")
	public String addDeal(Model model, @ModelAttribute("dealForm") Deal deal, BindingResult bindingResult) {

		dealValidator.validate(deal, bindingResult);
		
		if(bindingResult.hasErrors()) {
			System.out.println("test");
			return "deal";
		}
		
		dealService.save(deal);	
		
		model.addAttribute("dealForm", new Deal());
		return"deal";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(Model model, @ModelAttribute("loginForm") User user, BindingResult bindingResult) {
		
		userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()) {
			return "login";
		}
		
		if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword()) == null) {
			model.addAttribute("message", "Invalid username or passowrd.");
			model.addAttribute("loginForm", new User());
			return "login";
		}
		
		model.addAttribute("dealForm", new Deal());
		return "deal";
	}

}
