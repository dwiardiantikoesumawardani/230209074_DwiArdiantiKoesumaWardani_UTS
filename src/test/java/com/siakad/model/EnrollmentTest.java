package com.siakad.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test untuk class Enrollment
 * Target: 100% line dan method coverage di JaCoCo
 */
class EnrollmentTest {

    @Test
    void testDefaultConstructor() {
        Enrollment enrollment = new Enrollment();
        assertNotNull(enrollment);
        assertNull(enrollment.getEnrollmentId());
        assertNull(enrollment.getStudentId());
        assertNull(enrollment.getCourseCode());
        assertNull(enrollment.getEnrollmentDate());
        assertNull(enrollment.getStatus());
    }

    @Test
    void testParameterizedConstructorSetsAllFields() {
        LocalDateTime now = LocalDateTime.now();
        Enrollment enrollment = new Enrollment(
                "ENR001", "S001", "CS101", now, "APPROVED"
        );

        assertEquals("ENR001", enrollment.getEnrollmentId());
        assertEquals("S001", enrollment.getStudentId());
        assertEquals("CS101", enrollment.getCourseCode());
        assertEquals(now, enrollment.getEnrollmentDate());
        assertEquals("APPROVED", enrollment.getStatus());
    }

    @Test
    void testSettersAndGettersWorkCorrectly() {
        Enrollment enrollment = new Enrollment();
        LocalDateTime date = LocalDateTime.of(2025, 10, 22, 10, 0);

        enrollment.setEnrollmentId("ENR002");
        enrollment.setStudentId("S002");
        enrollment.setCourseCode("CS202");
        enrollment.setEnrollmentDate(date);
        enrollment.setStatus("PENDING");

        assertEquals("ENR002", enrollment.getEnrollmentId());
        assertEquals("S002", enrollment.getStudentId());
        assertEquals("CS202", enrollment.getCourseCode());
        assertEquals(date, enrollment.getEnrollmentDate());
        assertEquals("PENDING", enrollment.getStatus());
    }

    @Test
    void testSetterOverwriteValues() {
        LocalDateTime date1 = LocalDateTime.of(2025, 1, 1, 12, 0);
        LocalDateTime date2 = LocalDateTime.of(2025, 5, 1, 12, 0);
        Enrollment enrollment = new Enrollment("E01", "STU01", "CS303", date1, "REJECTED");

        enrollment.setEnrollmentId("E02");
        enrollment.setStudentId("STU02");
        enrollment.setCourseCode("CS404");
        enrollment.setEnrollmentDate(date2);
        enrollment.setStatus("APPROVED");

        assertEquals("E02", enrollment.getEnrollmentId());
        assertEquals("STU02", enrollment.getStudentId());
        assertEquals("CS404", enrollment.getCourseCode());
        assertEquals(date2, enrollment.getEnrollmentDate());
        assertEquals("APPROVED", enrollment.getStatus());
    }
}
