package com.tasneem.service;

import com.tasneem.model.Leave;
import com.tasneem.repo.LeaveRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

  @Service
  public class LeaveService {

  @Autowired
  LeaveRepository leaveRepository;

  public void saveOrUpdate(Leave leave) {
    leaveRepository.save(leave);
  }

  public List<Leave> findByEmp(String name) {
    return leaveRepository.findByEmp(name);
  }

  public Leave findById(int id) {
    return leaveRepository.findById(id);
  }

  public List<Leave> findAvailableLeaves() {
    return leaveRepository.findAvailableLeaves();
  }
}
