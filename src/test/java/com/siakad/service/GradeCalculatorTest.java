package com.siakad.service;

import com.siakad.model.CourseGrade;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test untuk class GradeCalculator
 * Sesuai dengan soal UTS poin A:
 * "Class ini akan diuji dengan UNIT TESTING BIASA (tanpa mock/stub)"
 */
class GradeCalculatorTest {

    private final GradeCalculator calculator = new GradeCalculator();

    // ----------------- TEST CALCULATE GPA -----------------
    @Test
    void testCalculateGpaWithValidGrades() {
        List<CourseGrade> grades = List.of(
                new CourseGrade("CS101", 3, 4.0),
                new CourseGrade("CS102", 2, 3.0)
        );
        double gpa = calculator.calculateGPA(grades);
        // (3*4 + 2*3) / 5 = (12+6)/5 = 3.6
        assertEquals(3.6, gpa, 0.01);
    }

    @Test
    void testCalculateGpaWithEmptyListReturnsZero() {
        assertEquals(0.0, calculator.calculateGPA(List.of()));
    }

    @Test
    void testCalculateGpaWithInvalidGradeThrowsException() {
        List<CourseGrade> invalid = List.of(new CourseGrade("CS101", 3, 4.5));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateGPA(invalid));
    }

    @Test
    void testCalculateGpaWithZeroCreditsReturnsZero() {
        List<CourseGrade> grades = List.of(new CourseGrade("CS999", 0, 4.0));
        assertEquals(0.0, calculator.calculateGPA(grades));
    }

    @Test
    void testCalculateGpaWithNullListReturnsZero() {
        assertEquals(0.0, calculator.calculateGPA(null));
    }

    @Test
    void testCalculateGpaWithGradesButZeroTotalCredits() {
        List<CourseGrade> grades = List.of(
                new CourseGrade("CS111", 0, 3.5),
                new CourseGrade("CS222", 0, 2.5)
        );
        assertEquals(0.0, calculator.calculateGPA(grades));
    }

    @Test
    void testCalculateGpaWhenListIsNull() {
        // pastikan ketika grades == null, return 0.0
        assertEquals(0.0, calculator.calculateGPA(null));
    }

    @Test
    void testCalculateGpaWhenListIsEmpty() {
        // pastikan ketika list kosong, return 0.0
        assertEquals(0.0, calculator.calculateGPA(List.of()));
    }

    @Test
    void testCalculateGpaWithNegativeGradeThrowsException() {
        List<CourseGrade> invalidGrades = List.of(new CourseGrade("CS101", 3, -1.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateGPA(invalidGrades));
    }


    // ----------------- TEST DETERMINE ACADEMIC STATUS -----------------
    @Test
    void testDetermineStatusSemester1And2() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.5, 1));
        assertEquals("PROBATION", calculator.determineAcademicStatus(1.5, 2));
    }

    @Test
    void testDetermineStatusSemester3And4() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.3, 3)); // >=2.25
        assertEquals("PROBATION", calculator.determineAcademicStatus(2.1, 3)); // 2.0–2.24
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.9, 3)); // <2.0
    }

    @Test
    void testDetermineStatusSemester5Plus() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.7, 5));
        assertEquals("PROBATION", calculator.determineAcademicStatus(2.2, 6));
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.5, 8));
    }

    @Test
    void testDetermineStatusInvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(-1.0, 3));
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(3.0, 0));
    }

    @Test
    void testDetermineStatusAtBoundaryForSemester3And4() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.25, 4));
    }

    @Test
    void testDetermineStatusAtBoundaryForSemester5Plus() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.5, 7));
    }

    @Test
    void testDetermineStatusSemester3BoundarySuspendedCase() {
        // semester 3, gpa tepat di bawah 2.0 -> harus SUSPENDED
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.99, 3));
    }

    // ----------------- TEST CALCULATE MAX CREDITS -----------------
    @Test
    void testCalculateMaxCreditsRanges() {
        assertEquals(24, calculator.calculateMaxCredits(3.1)); // >=3.0
        assertEquals(21, calculator.calculateMaxCredits(2.7)); // 2.5–2.99
        assertEquals(18, calculator.calculateMaxCredits(2.1)); // 2.0–2.49
        assertEquals(15, calculator.calculateMaxCredits(1.5)); // <2.0
    }

    @Test
    void testCalculateMaxCreditsInvalidGpa() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateMaxCredits(-0.5));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateMaxCredits(4.5));
    }

    // ----------------- Tambahan untuk Full Branch Coverage -----------------
    @Test
    void testCalculateGpaWithNullList() {
        // Cek kalau grades == null -> harus return 0.0
        assertEquals(0.0, calculator.calculateGPA(null));
    }

    @Test
    void testDetermineAcademicStatusBoundarySemester5() {
        // Tes batas minimal IPK di semester 5 supaya branch terakhir ikut ter-cover
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.5, 5)); // batas ACTIVE
        assertEquals("PROBATION", calculator.determineAcademicStatus(2.2, 5)); // batas PROBATION
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.9, 5)); // bawah PROBATION
    }

    @Test
    void testDetermineAcademicStatusGpaExactlyZero() {
        assertEquals("PROBATION", calculator.determineAcademicStatus(0.0, 1));
    }

    @Test
    void testDetermineAcademicStatusGpaExactlyFour() {
        assertEquals("ACTIVE", calculator.determineAcademicStatus(4.0, 6));
    }

    @Test
    void testDetermineAcademicStatusLowerBoundGpaAndHighSemester() {
        // cek gpa di bawah 0 — harus lempar IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(-0.1, 5));
    }

    @Test
    void testDetermineAcademicStatusUpperBoundGpa() {
        // cek gpa di atas 4.0 — harus lempar IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> calculator.determineAcademicStatus(4.1, 3));
    }

    @Test
    void testDetermineAcademicStatusSemesterExactlyOneBoundary() {
        // semester 1 dengan gpa di bawah 2.0 → PROBATION
        assertEquals("PROBATION", calculator.determineAcademicStatus(1.9, 1));
    }

    @Test
    void testDetermineAcademicStatusSemesterExactlyTwoBoundary() {
        // semester 2 dengan gpa di atas batas → ACTIVE
        assertEquals("ACTIVE", calculator.determineAcademicStatus(2.0, 2));
    }

    @Test
    void testDetermineAcademicStatusSemesterExactlyFourBoundary() {
        // semester 4 dengan gpa pas di bawah 2.0 → SUSPENDED
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.99, 4));
    }

    @Test
    void testDetermineAcademicStatusSemesterExactlyFiveBoundary() {
        // semester 5 dengan gpa di bawah 2.0 → SUSPENDED
        assertEquals("SUSPENDED", calculator.determineAcademicStatus(1.99, 5));
    }

}
