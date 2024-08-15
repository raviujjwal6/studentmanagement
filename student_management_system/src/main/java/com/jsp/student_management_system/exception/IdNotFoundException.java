package com.jsp.student_management_system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IdNotFoundException  extends RuntimeException{

	private  String msg="hello";
	
	@Override
	public String getMessage() {
		return msg;
	}
}
