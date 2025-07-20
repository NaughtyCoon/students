package org.example;

import java.util.List;

public class Main {

    static final String RESET = "\u001B[0m";
    static final String GREEN = "\u001B[32m";

    public static void main(String[] args) {

        List<Student> students = StudentsClass.list;

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
        System.out.println(analyzer.isThereAnyAMarkPerson(students) ? "Есть" : "Нет");


        task("7.\tНайти студентов, у которых есть хотя бы одна \"неудовлетворительная\" оценка (< 3.0).");
        System.out.println(analyzer.getListOfBadMarkPersons(students));


        task("8.\tОпределить, есть ли курсы, на которых все студенты получили ≥ 4.0 (\"идеальные курсы\").");
        System.out.println(analyzer.isThereAnyIdealCourse(students) ? "Есть" : "Нет");


        task("9.\tРассчитать \"индекс разнообразия\" факультета (количество уникальных курсов / количество студентов).");
        System.out.println("Physics: " + analyzer.diversityIndex("Physics", students));
        System.out.println("CS: " + analyzer.diversityIndex("CS", students));

    }

    private static void task(String message) {
        System.out.println(GREEN + "\n" + message + RESET);
    }

}