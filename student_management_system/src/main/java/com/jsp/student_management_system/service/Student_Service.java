package com.jsp.student_management_system.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.student_management_system.dao.Student_Dao;
import com.jsp.student_management_system.dto.Student;
import com.jsp.student_management_system.exception.IdNotFoundException;
import com.jsp.student_management_system.util.EmailUtil;
import com.jsp.student_management_system.util.ResponseStructure;

@Service
public class Student_Service {
	@Autowired
	private Student_Dao dao;
	@Autowired
	private EmailUtil emailUtil;

	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student) {
		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		structure.setData(dao.saveStudent(student));
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMsg("student data saved sucessfully");
		emailUtil.sendMail(student.getEmail());
		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Student>> saveImage(int id, MultipartFile file) throws IOException {
// first fetching data with id ...if data exist then we are uploading image or else throwing exception
		Student db = dao.findStudentByID(id);
		if (db != null) {
//			 we can send as student refernce for update , everytime we cant create n methods for update operation 
			db.setId(id);
			db.setImg(file.getBytes());
			ResponseStructure<Student> structure = new ResponseStructure<Student>();
			structure.setData(dao.updateStudent(db));
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMsg("student profile pic  saved sucessfully");
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
		} else // this is for custom exception
			throw new IdNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Student>> login(String email, long phone) {
		Student db = dao.findStudentByEmail(email);
		if (db != null) {
			if (db.getMobileNumber()==phone) {
				ResponseStructure<Student> structure = new ResponseStructure<Student>();
				structure.setData(db);
				structure.setStatusCode(HttpStatus.FOUND.value());
				structure.setMsg("student login .. sucessfully");
				return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
		
			}else
				throw new IdNotFoundException("phone number  not found");

		} else 
			throw new IdNotFoundException("email not found");

	}

	public ResponseEntity<ResponseStructure<Student>> fetchStudent(int id) {
		Student db = dao.findStudentByID(id);
		if (db != null) {

			ResponseStructure<Student> structure = new ResponseStructure<Student>();
			structure.setData(db);
			structure.setStatusCode(HttpStatus.FOUND.value());
			structure.setMsg("student data found  sucessfully");
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
		} else // this is for custom exception
			throw new IdNotFoundException();
	}

	public ResponseEntity<byte[]> fetchImage(int id) {

		byte[] db = dao.findStudentByID(id).getImg();
		if (db != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);

			return new ResponseEntity<byte[]>(db, headers, HttpStatus.FOUND);
		} else
			throw new IdNotFoundException();
	}

}
