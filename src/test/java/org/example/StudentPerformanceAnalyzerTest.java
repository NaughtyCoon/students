package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

class StudentPerformanceAnalyzerTest {

    private final List<Student> students = StudentsClass.list;

    private final StudentPerformanceAnalyzer analyzer = new StudentPerformanceAnalyzer();

    @Test
    void getAverageGrade_WhenStudentsIsEmpty_ThenReturnEmptyMap() {
        Map<Student, Double> result = analyzer.getAverageGrade(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void getAverageGrade_WhenStudentHasNoGrade_ThenReturnZero() {
        Student student = new Student("Иван Иванов", "Physics", Map.of());
        Map<Student, Double> result = analyzer.getAverageGrade(List.of(student));
        assertEquals(0.0, result.get(student), 0.001);
    }

    @Test
    void getAverageGrade_WhenSendFullListOfStudents_ThenReturnMapOfCorrectAverageGradesForAllStudents () {
        Map<Student, Double> result = analyzer.getAverageGrade(students);

    }

}
