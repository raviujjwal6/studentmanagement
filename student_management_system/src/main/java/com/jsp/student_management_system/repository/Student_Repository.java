package com.jsp.student_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.student_management_system.dto.Student;

public interface Student_Repository extends JpaRepository<Student, Integer>{

  Student findByEmail(String email);
	
}
