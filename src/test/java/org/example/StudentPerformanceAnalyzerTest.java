package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class StudentPerformanceAnalyzerTest {

    private final List<Student> students = StudentsClass.list;

    private final double[] precalculatedAveragesForTesting = new double[] {
            4.625, 3.875, 4.300, 4.975, 4.200, 4.050, 4.625, 4.450, 3.800, 4.250,
            4.625, 4.625, 3.825, 4.175, 4.725, 4.700, 3.750, 4.000, 4.625, 4.350
    };

    private final StudentPerformanceAnalyzer analyzer = new StudentPerformanceAnalyzer();

    @Test
    void getMapOfAverageGrades_WhenStudentsIsEmpty_ThenReturnEmptyMap() {
        Map<Student, Double> result = analyzer.getMapOfAverageGrades(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void getMapOfAverageGrade_WhenStudentHasNoGrades_ThenReturnZero() {
        Student student = new Student("Иван Иванов", "Physics", Map.of());
        Map<Student, Double> result = analyzer.getMapOfAverageGrades(List.of(student));
        assertEquals(0.0, result.get(student), 0.001);
    }

    @Test
    void getAverageGrade_WhenSendFullListOfStudents_ThenReturnMapOfCorrectMapOfAverageGradesForAllStudents() {
        Map<Student, Double> result = analyzer.getMapOfAverageGrades(students);

    }

    @ParameterizedTest
    @MethodSource("studentGradeProvider") // имя метода-поставщика
    void testGetMapOfAverageGrades(Student student, double expectedAverage) {
        assertEquals(expectedAverage, student.getStudentAverageGrade());
    }

    private Stream<Arguments> studentGradeProvider() {
        return students.stream()
                .map(student -> Arguments.of(
                        student,
                        precalculatedAveragesForTesting[students.indexOf(student)]
                ));
    }

}
