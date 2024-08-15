package com.jsp.student_management_system.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.student_management_system.exception.IdNotFoundException;
import com.jsp.student_management_system.util.ResponseStructure;
//@ControllerAdvice // we use then needs to extends to ResponseEntityExceptionHandler
@RestControllerAdvice// no need to extends ResponseEntityExceptionHandler
public class StudentExceptionHandler  {

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> idNotFound(IdNotFoundException idNotFoundException) {
		ResponseStructure<String>responseStructure=new ResponseStructure<String>();
//		responseStructure.setData(idNotFoundException.getMessage());
		responseStructure.setData(idNotFoundException.getMsg());
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMsg("you cant perform this operation");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
		

	}
}
