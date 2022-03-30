package com.tasneem.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="leaves")
public class Leave {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
  private int id;
  private String emp;
  private String from_date;
  private String to_date;
  private String reason;
  private int status;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getEmp() {
    return emp;
  }

  public void setEmp(String emp) {
    this.emp = emp;
  }

  public String getFrom_date() {
    return from_date;
  }

  public void setFrom_date(String from_date) {
    this.from_date = from_date;
  }

  public String getTo_date() {
    return to_date;
  }

  public void setTo_date(String to_date) {
    this.to_date = to_date;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
