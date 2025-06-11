package com.example.notapp.repository;

import com.example.notapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Gerekirse özel sorgular buraya yazılabilir
}
