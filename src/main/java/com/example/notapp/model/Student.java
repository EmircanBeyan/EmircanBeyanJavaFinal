    package com.example.notapp.model;

    import java.util.List;

    import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

    @Entity
    public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String surname;

        @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
        private List<Grade> grades;

        // Getters and Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getSurname() { return surname; }
        public void setSurname(String surname) { this.surname = surname; }

        public List<Grade> getGrades() { return grades; }
        public void setGrades(List<Grade> grades) { this.grades = grades; }
    }
