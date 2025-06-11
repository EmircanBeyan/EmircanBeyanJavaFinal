package com.example.notapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;  // ResponseEntity için
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.notapp.model.Grade;
import com.example.notapp.model.Student;
import com.example.notapp.repository.GradeRepository;
import com.example.notapp.repository.StudentRepository;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/student/{studentId}")
    public Grade addGrade(@PathVariable Long studentId, @RequestBody Grade grade) {
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı"));
        grade.setStudent(student);
        return gradeRepository.save(grade);
    }

    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudent(@PathVariable Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
    
    @DeleteMapping("/{gradeId}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long gradeId) {
        if (!gradeRepository.existsById(gradeId)) {
            return ResponseEntity.notFound().build();
        }
        gradeRepository.deleteById(gradeId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{gradeId}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long gradeId, @RequestBody Grade updatedGrade) {
        return gradeRepository.findById(gradeId)
            .map(grade -> {
                grade.setCourse(updatedGrade.getCourse());
                grade.setScore(updatedGrade.getScore());
                Grade saved = gradeRepository.save(grade);
                return ResponseEntity.ok(saved);
            }).orElse(ResponseEntity.notFound().build());
    }
}
