package org.example;

import java.util.Map;

public class Student {
    private final String name;
    private final String faculty;
    private final int enrollmentYear;
    private final Map<String, Double> grades;

    public Student(String name, String faculty, int enrollmentYear, Map<String, Double> grades) {
        this.name = name;
        this.faculty = faculty;
        this.enrollmentYear = enrollmentYear;
        this.grades = grades;
    }

    public String getFaculty() {
        return faculty;
    }

    public Map<String, Double> getGrades() {
        return this.grades;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
