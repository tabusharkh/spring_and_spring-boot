package com.tasneem.controller;

import com.tasneem.model.Leave;
import com.tasneem.service.LeaveService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

  @Autowired
  private LeaveService leaveService;

  @GetMapping("/")
  public String getHome() {
    System.out.println("Home");
    return "login";
  }

  @GetMapping("/appLogin")
  public String login() {

    return "login";
  }

  @GetMapping("/secure/emp")
  public String postLogin(Model model) {
    Leave leave = new Leave();
    model.addAttribute("newleave", leave);

    String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
    List<Leave> allLeaves = leaveService.findByEmp(currentUserName);

    model.addAttribute("leaves", allLeaves);
    System.out.println("Post");
    return "leaves";
  }

  @GetMapping("/secure/man")
  public String postLoginMan(Model model) {
    List<Leave> available = leaveService.findAvailableLeaves();

    model.addAttribute("leaves", available);
    return "manager";
  }

  @PostMapping("/newleave")
  public String leaveRequest(@ModelAttribute("leave") Leave leave, Model model) {
    String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

    leave.setStatus(0);
    leave.setEmp(currentUserName);
    leaveService.saveOrUpdate(leave);

    Leave leave1 = new Leave();
    model.addAttribute("newleave", leave);

    List<Leave> allLeaves = leaveService.findByEmp(currentUserName);

    model.addAttribute("leaves", allLeaves);
    return postLoginMan(model);
  }

  @RequestMapping("/cancelLeave/{id}")
  public String cancelLeave(@PathVariable("id") int id, Model model) {
    String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

    Leave temp = leaveService.findById(id);
    temp.setStatus(1);
    leaveService.saveOrUpdate(temp);

    Leave leave = new Leave();
    model.addAttribute("newleave", leave);

    List<Leave> allLeaves = leaveService.findByEmp(currentUserName);

    model.addAttribute("leaves", allLeaves);
    return "leaves";
  }

  @RequestMapping("/approveLeave/{id}")
  public String approveLeave(@PathVariable("id") int id, Model model) {
    Leave temp = leaveService.findById(id);
    temp.setStatus(2);
    leaveService.saveOrUpdate(temp);

    List<Leave> available = leaveService.findAvailableLeaves();

    model.addAttribute("leaves", available);
    return "manager";
  }

  @RequestMapping("/rejectLeave/{id}")
  public String rejectLeave(@PathVariable("id") int id, Model model) {
    Leave temp = leaveService.findById(id);
    temp.setStatus(3);
    leaveService.saveOrUpdate(temp);

    List<Leave> available = leaveService.findAvailableLeaves();

    model.addAttribute("leaves", available);
    return "manager";
  }
}
