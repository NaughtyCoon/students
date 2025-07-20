package org.example;

import java.util.Map;

public class Student {
    private final String name;
    private final String faculty;
    private final Map<String, Double> grades;

    public Student(String name, String faculty, Map<String, Double> grades) {
        this.name = name;
        this.faculty = faculty;
        this.grades = grades;
    }

    public String getFaculty() {
        return faculty;
    }

    public Map<String, Double> getGrades() {
        return this.grades;
    }

    public double getStudentAverageGrade() {
        return grades.values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
