package com.example.notapp.repository;
import java.util.List;
import com.example.notapp.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    // Belirli bir öğrenciye ait notları çekmek için ek metod yazabiliriz:
    List<Grade> findByStudentId(Long studentId);
}
