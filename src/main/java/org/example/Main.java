package org.example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Alice", "CS", 2021, Map.of("Math", 4.8, "Physics", 4.5, "Algorithms", 5.0, "Databases", 4.2)),
                new Student("Bob", "Physics", 2020, Map.of("Math", 3.5, "Physics", 4.0, "Chemistry", 4.2, "Quantum Mechanics", 3.8)),
                new Student("Charlie", "CS", 2021, Map.of("Math", 4.0, "Algorithms", 4.5, "Databases", 4.8, "OS", 3.9)),
                new Student("David", "Math", 2022, Map.of("Math", 5.0, "Physics", 5.0, "Chemistry", 5.0, "Number Theory", 4.9)),
                new Student("Eva", "Biology", 2020, Map.of("Biology", 4.7, "Chemistry", 4.1, "Genetics", 4.5, "Ecology", 3.5)),
                new Student("Frank", "Physics", 2021, Map.of("Math", 4.2, "Physics", 4.8, "Astronomy", 4.0, "Thermodynamics", 3.2)),
                new Student("Grace", "CS", 2022, Map.of("Algorithms", 4.9, "Databases", 4.7, "Networks", 4.3, "AI", 4.6)),
                new Student("Henry", "Math", 2020, Map.of("Math", 4.8, "Physics", 4.4, "Topology", 4.7, "Statistics", 3.9)),
                new Student("Ivy", "Biology", 2021, Map.of("Biology", 4.5, "Chemistry", 3.8, "Biochemistry", 4.0, "Microbiology", 2.9)),
                new Student("Jack", "Physics", 2022, Map.of("Physics", 4.9, "Quantum Mechanics", 4.3, "Electromagnetism", 4.1, "Optics", 3.7)),
                new Student("Kate", "CS", 2020, Map.of("Algorithms", 5.0, "OS", 4.8, "Compilers", 4.5, "Security", 4.2)),
                new Student("Leo", "Math", 2021, Map.of("Math", 4.9, "Number Theory", 4.7, "Algebra", 4.6, "Geometry", 4.3)),
                new Student("Mia", "Biology", 2022, Map.of("Biology", 4.6, "Genetics", 4.4, "Ecology", 3.8, "Zoology", 2.5)),
                new Student("Noah", "Physics", 2020, Map.of("Physics", 4.7, "Thermodynamics", 3.9, "Astrophysics", 4.1, "Relativity", 4.0)),
                new Student("Olivia", "CS", 2021, Map.of("Databases", 4.8, "Networks", 4.5, "AI", 4.7, "ML", 4.9)),
                new Student("Paul", "Math", 2022, Map.of("Math", 5.0, "Statistics", 4.6, "Calculus", 4.8, "Probability", 4.4)),
                new Student("Quinn", "Biology", 2020, Map.of("Biology", 4.3, "Microbiology", 3.7, "Genetics", 4.0, "Virology", 3.0)),
                new Student("Ryan", "Physics", 2021, Map.of("Physics", 4.5, "Electromagnetism", 4.2, "Optics", 3.8, "Nuclear Physics", 3.5)),
                new Student("Sophia", "CS", 2022, Map.of("Algorithms", 4.9, "OS", 4.7, "Security", 4.3, "Cryptography", 4.6)),
                new Student("Tom", "Math", 2020, Map.of("Math", 4.7, "Topology", 4.5, "Algebra", 4.4, "Logic", 3.8))
        );

        StudentPerformanceAnalyzer analyzer = new StudentPerformanceAnalyzer();

        task("2.\tНайти средний балл каждого студента (Map<Student, Double>).");
        System.out.println(analyzer.getAverageGrade(students).toString());


        task("3.\tНайти топ-3 студентов с самым высоким средним баллом.");
        System.out.println(analyzer.getTopThreeStudents(students));


        task("4.\tОпределить, какие курсы являются самыми сложными (средний балл по курсу ниже заданного порога).");

        double hardGradeLevel = 4.0;
        System.out.println("Сложные курсы (средний балл < " + hardGradeLevel + "):");
        analyzer.getDifficultCourses(students, hardGradeLevel).forEach(System.out::println);


        task("5.\tСгруппировать студентов по факультетам (если факультет не указан, считать \"Общий\").");
        System.out.println(analyzer.groupByFaculty(students));


        task("6.\tПроверить, есть ли \"универсальные отличники\" — студенты, у которых все оценки ≥ 4.5.");

    }

    private static void task(String message) {
        System.out.println(GREEN + "\n" + message + RESET);
    }

}