package com.example.demo;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpa extends JpaRepository<Student, Integer> {
}
