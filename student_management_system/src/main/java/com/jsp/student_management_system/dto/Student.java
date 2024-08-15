package com.jsp.student_management_system.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fname;
	private String lname;
	@Column(unique = true)
	private String email;
	private String address;
	private long mobileNumber;
//	private LocalDateTime dateTime;
//	Date date;
	@Lob  //to consider as large object
	@Column(columnDefinition = "longblob",length = 999999999) //size 
	private byte img[];
	
	
	
	
	
	
	
	
	
	

}
