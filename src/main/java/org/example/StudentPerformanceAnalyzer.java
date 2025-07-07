package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentPerformanceAnalyzer {

    public Map<Student, Double> getAverageGrade (List<Student> students) {

        return
                students.stream()
                        .collect(Collectors.toMap(
                                student -> student,
                                student -> student.getGrades().values().stream()
                                        .mapToDouble(Double::doubleValue)
                                        .average()
                                        .orElse(0.0))
                        );

    }

    public List<Student> getTopThreeStudents (List<Student> students) {

        return
                getAverageGrade(students).entrySet().stream()
                        .sorted(Map.Entry.<Student, Double>comparingByValue().reversed())
                        .limit(3)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());

    }

    public List<String> getDifficultCourses(List<Student> students, double hardGradeLevel) {

        return students.stream()
                .flatMap(student -> student.getGrades().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ))
                .entrySet().stream()
                .filter(entry -> {
                    double average = entry.getValue().stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(5.0); // если оценок вообще нет, считаем курс не сложным
                    return average < hardGradeLevel;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Map<String, List<Student>> groupByFaculty (List<Student> students) {

        return
            students.stream()
                .collect(Collectors.groupingBy(
                    student -> student.getFaculty() != null ? student.getFaculty() : "General"
                    )
                );

    }

    public boolean isThereAnyAMarkPerson (List<Student> students) {
        return
                getAverageGrade(students).entrySet().stream()
                        .filter(entry -> entry.getValue() >= 4.5)
                        .toList();
    }

}
