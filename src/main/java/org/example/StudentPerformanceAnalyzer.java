package org.example;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentPerformanceAnalyzer {

    public Map<Student, Double> getMapOfAverageGrades(List<Student> students) {

        return
                students.stream()
                        .collect(Collectors.toMap(
                                student -> student,
                                Student::getStudentAverageGrade
                        ));

    }

    public List<Student> getTopThreeStudents(List<Student> students) {

        return
                getMapOfAverageGrades(students).entrySet().stream()
                        .sorted(Map.Entry.<Student, Double>comparingByValue().reversed())
                        .limit(3)
                        .map(Map.Entry::getKey)
                        .toList();

    }

    public List<String> getDifficultCourses(List<Student> students, double hardGradeLevel) {

        return
                flatMapOfGrades(students).entrySet().stream()
                        .filter(entry -> {
                            double average = entry.getValue().stream()
                                    .mapToDouble(Double::doubleValue)
                                    .average()
                                    .orElse(5.0); // если оценок вообще нет, считаем курс не сложным
                            return average < hardGradeLevel;
                        })
                        .map(Map.Entry::getKey)
                        .toList();

    }

    public Map<String, List<Student>> groupByFaculty(List<Student> students) {

        return
                students.stream()
                        .collect(Collectors.groupingBy(
                                        student -> student.getFaculty() != null ? student.getFaculty() : "General"
                                )
                        );

    }

    public boolean isThereAnyAMarkPerson(List<Student> students) {

        return
                students.stream()
                        .anyMatch(student -> student.getGrades().entrySet().stream()
                                .allMatch(entry -> entry.getValue() >= 4.5));

    }

    public List<Student> getListOfBadMarkPersons(List<Student> students) {

        return
                students.stream()
                        .filter(student -> student.getGrades().entrySet().stream()
                                .anyMatch(entry -> entry.getValue() < 3.0))
                        .toList();

    }

    public boolean isThereAnyIdealCourse(List<Student> students) {

        return
                flatMapOfGrades(students).entrySet().stream()
                        .anyMatch(entry -> entry.getValue().stream()
                                .allMatch(grade -> grade >= 4.0));

    }

    public double diversityIndex(String faculty, List<Student> students) {
        // сначала соберём в список всех студентов с запрошенного факультета...
        List<Student> facultyStudents = students.stream()
                .filter(student -> faculty.equals(student.getFaculty()))
                .toList();

        // Если для запрошенного факультета никого не нашлось...
        if (facultyStudents.isEmpty()) {
            return -1.0; // ...сообщить вот так об ошибке
        }

        // Создадим Set уникальных курсов
        Set<String> uniqueCourses = facultyStudents.stream()
                .flatMap(student -> student.getGrades().keySet().stream())
                .collect(Collectors.toSet());

        return (double) uniqueCourses.size() / facultyStudents.size();

    }


    private Map<String, List<Double>> flatMapOfGrades(List<Student> students) {

        return
                students.stream()
                        .flatMap(student -> student.getGrades().entrySet().stream())
                        .collect(Collectors.groupingBy(
                                        Map.Entry::getKey,
                                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                                )
                        );

    }

}
