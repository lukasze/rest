package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// add some superpowers (ORM): java object <-> RDBMS table
// @Entity & @Id is the minimum
@Entity
public class Greeting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cientName;
	private String greetingMsg;
	private LocalDateTime localDateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCientName() {
		return cientName;
	}
	public void setCientName(String cientName) {
		this.cientName = cientName;
	}
	public String getGreetingMsg() {
		return greetingMsg;
	}
	public void setGreetingMsg(String greetingMsg) {
		this.greetingMsg = greetingMsg;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

}
