package com.jsp.student_management_system.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.student_management_system.dao.Student_Dao;
import com.jsp.student_management_system.dto.Student;
import com.jsp.student_management_system.service.Student_Service;
import com.jsp.student_management_system.util.ResponseStructure;

@RestController
public class Student_Controller {
	@Autowired
	private Student_Service service;

	@Autowired
	private Student_Dao dao;

	@PostMapping("/savestudent")
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student) {
		return service.saveStudent(student);
	}

	@PutMapping("/studentProfile")
	public ResponseEntity<ResponseStructure<Student>> saveProfileImage(@RequestParam int id,
			@RequestBody MultipartFile file) throws IOException {
		return service.saveImage(id, file);
	}

	@GetMapping("/fetchProfile/{id}")//
	public ResponseEntity<byte[]> fetchProfileImage(@PathVariable int id) {
		return service.fetchImage(id);
	}

	@GetMapping("/fetchStudent/{id}")
	public ResponseEntity<ResponseStructure<Student>>fetchStudent(@PathVariable int id) {

		return service.fetchStudent(id);
	}

}
