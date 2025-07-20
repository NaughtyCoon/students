package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class StudentPerformanceAnalyzerTest {

    private static final List<Student> students = StudentsClass.list;
    private static final double[] precalculatedAveragesForTesting = new double[]{
            4.625, 3.875, 4.300, 4.975, 4.200, 4.050, 4.625, 4.450, 3.800, 4.250,
            4.625, 4.625, 3.825, 4.175, 4.725, 4.700, 3.750, 4.000, 4.625, 4.350
    };
    private static final List<String> difficultCoursesList = List.of(
            "Microbiology", "Nuclear Physics", "Zoology", "Optics", "Thermodynamics", "Logic", "Ecology", "Virology"
    );
    private static final List<String> weakStudentsList = List.of("Ivy", "Mia");
    private final StudentPerformanceAnalyzer analyzer = new StudentPerformanceAnalyzer();

    static Stream<Arguments> studentGradeProvider() {
        return students.stream()
                .map(student -> Arguments.of(
                        student,
                        precalculatedAveragesForTesting[students.indexOf(student)]
                ));
    }

    @Test
    void getMapOfAverageGrades_WhenStudentListIsEmpty_ThenReturnEmptyMap() {

        Map<Student, Double> result = analyzer.getMapOfAverageGrades(List.of());
        assertTrue(result.isEmpty());

    }

    @Test
    void getMapOfAverageGrade_WhenStudentHasNoGrades_ThenReturnZero() {

        Student student = new Student("Иван Иванов", "Physics", Map.of());
        Map<Student, Double> result = analyzer.getMapOfAverageGrades(List.of(student));
        assertEquals(0.0, result.get(student), 0.001);

    }

    @ParameterizedTest
    @MethodSource("studentGradeProvider")
    void getAverageGrade_WhenSendFullListOfStudents_ThenReturnMapOfCorrectAverageGradesForAllStudents
            (Student student, double expectedAverage) {
        assertEquals(expectedAverage, student.getStudentAverageGrade(), 0.001);
    }

    @Test
    void getTopThreeStudents_WhenStudentListIsEmpty_ThenReturnEmptyList() {

        List<Student> result = analyzer.getTopThreeStudents(List.of());
        assertTrue(result.isEmpty());

    }

    @Test
    void getTopThreeStudents_WhenStudentListIsSufficient_ThenReturnCorrectTopThree() {

        List<Student> result = analyzer.getTopThreeStudents(students);

        assertEquals("David", result.get(0).getName());
        assertEquals("Olivia", result.get(1).getName());
        assertEquals("Paul", result.get(2).getName());

        assertEquals(3, result.size());

    }

    @Test
    void getDifficultCourses_WhenStudentListIsEmpty_ThenReturnEmptyDiffCoursesList() {

        List<String> result = analyzer.getDifficultCourses(List.of(), 4.0);
        assertTrue(result.isEmpty());

    }

    @Test
    void getDifficultCourses_WhenAllCoursesAreEasy_ThenReturnEmptyDiffCoursesList() {

        List<String> result = analyzer.getDifficultCourses(students, 1.0);
        assertTrue(result.isEmpty());

    }

    @Test
    void getDifficultCourses_WhenStudentListIsSufficient_ThenReturnCorrectDiffCoursesList() {

        List<String> result = analyzer.getDifficultCourses(students, 4.0);
        assertEquals(difficultCoursesList, result);

    }

    @Test
    void groupByFaculty_WhenStudentListIsEmpty_ThenReturnEmptyGroupedStudentsMap() {

        Map<String, List<Student>> result = analyzer.groupByFaculty(List.of());
        assertTrue(result.isEmpty());

    }

    @Test
    void groupByFaculty_WhenStudentListIsSufficient_ThenReturnMapWithFacultiesAsKeys() {

        Map<String, List<Student>> result = analyzer.groupByFaculty(students);
        assertFalse(result.keySet().isEmpty());

    }

    @Test
    void isThereAnyAMarkPerson_WhenStudentListIsEmpty_ThenReturnFalse() {

        boolean result = analyzer.isThereAnyAMarkPerson(List.of());
        assertFalse(result);

    }

    @Test
    void isThereAnyAMarkPerson_WhenStudentListContainsAMarkStudent_ThenReturnTrue() {

        boolean result = analyzer.isThereAnyAMarkPerson(students);
        assertTrue(result);

    }

    @Test
    void isThereAnyAMarkPerson_WhenStudentListContainsNoAMarkStudent_ThenReturnFalse() {

        // Чтобы проверить, вернётся ли false при тестировании, сначала создадим
        // список студентов, заведома обладающих хоть одной оценкой ниже 4.5
        List<Student> noAMarks = students.stream()
                .filter(student -> student.getGrades().values().stream()
                        .anyMatch(value -> value < 4.5))
                .toList();

        // Теперь посылаем этих "слабаков" в метод по проверке на наличие отличников
        boolean result = analyzer.isThereAnyAMarkPerson(noAMarks);
        assertFalse(result);

    }

    @Test
    void getListOfBadMarkPersons_WhenStudentListContainsBadStudents_ThenReturnThatList() {

        List<String> result = analyzer.getListOfBadMarkPersons(students).stream()
                .map(Student::getName)
                .toList();
        assertEquals(weakStudentsList, result);

    }

    @Test
    void getListOfBadMarkPersons_WhenStudentListContainsNoBadStudents_ThenReturnEmptyList() {

        // Чтобы проверить, вернётся ли empty List при тестировании, сначала создадим
        // список студентов, заведома не обладающих ни одной оценкой ниже 3
        List<Student> satisfactoryAndOverStudents = students.stream()
                .filter(student -> student.getGrades().values().stream()
                        .allMatch(value -> value >= 3))
                .toList();

        // Теперь направим этот список в метод по проверке на наличие двоечников
        List<Student> result = analyzer.getListOfBadMarkPersons(satisfactoryAndOverStudents);
        assertTrue(result.isEmpty());

    }

}
