package com.jsp.student_management_system.dao;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.jsp.student_management_system.dto.Student;
import com.jsp.student_management_system.repository.Student_Repository;
import com.jsp.student_management_system.util.ResponseStructure;

@Repository
public class Student_Dao {
	@Autowired
	private Student_Repository repository;

	public Student saveStudent(Student student) {

		return repository.save(student);
	}

	public Student updateStudent(Student student) {

		Optional<Student> studentdb = repository.findById(student.getId());
		if (studentdb.isPresent()) {
			Student db = studentdb.get();
			if (db.getImg() == null) {
				db.setImg(student.getImg());
			}
//            db.setDateTime(LocalDateTime.now());
//            db.setDate(Date.valueOf("2024-09-03"));
			return repository.save(db);
		} else
			return null;
	}

	public Student findStudentByID(int student_id) {
		Optional<Student> studentdb = repository.findById(student_id);
		if (studentdb.isPresent()) {
			Student db = studentdb.get();

			return db;
		} else
			return null;
	}

	public Student findStudentByEmail(String email) {
		Student db = repository.findByEmail(email);
		if (db != null) {

			return db;
		} else
			return null;
	}

}
