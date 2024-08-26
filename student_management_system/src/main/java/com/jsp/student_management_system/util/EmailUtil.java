package com.jsp.student_management_system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
	JavaMailSender mailSender; //interface
	
	public void sendMail(String to) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setFrom("raviujjwal6@gmail.com");
	        message.setSubject("confirmation mail");
	        message.setText("hi baby i got ur data");

	        mailSender.send(message);
	}
	

}
