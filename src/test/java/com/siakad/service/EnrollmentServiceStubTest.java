package com.siakad.service;

import com.siakad.exception.CourseNotFoundException;
import com.siakad.exception.StudentNotFoundException;
import com.siakad.model.Course;
import com.siakad.model.Student;
import com.siakad.repository.CourseRepository;
import com.siakad.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test menggunakan STUB untuk EnrollmentService
 * Sesuai soal UTS poin B.
 */
class EnrollmentServiceStubTest {

    private EnrollmentService service;

    @BeforeEach
    void setup() {
        // ==== Stub StudentRepository ====
        StudentRepository studentRepo = new StudentRepository() {
            @Override
            public Student findById(String id) {
                if ("S001".equals(id)) {
                    Student s = new Student();
                    s.setStudentId("S001");
                    s.setEmail("test@student.com");
                    s.setAcademicStatus("ACTIVE");
                    s.setGpa(3.0);
                    return s;
                }
                return null;
            }
        };

        // ==== Stub CourseRepository ====
        CourseRepository courseRepo = new CourseRepository() {
            @Override
            public Course findByCourseCode(String courseCode) {
                if ("CS101".equals(courseCode)) {
                    Course c = new Course();
                    c.setCourseCode("CS101");
                    c.setCourseName("Pemrograman Dasar");
                    c.setEnrolledCount(5);
                    c.setCapacity(10);
                    return c;
                }
                return null;
            }

            @Override
            public boolean isPrerequisiteMet(String studentId, String courseCode) {
                return true; // dianggap selalu terpenuhi
            }

            @Override
            public void update(Course course) {
                // dummy update
            }
        };

        // ==== Dummy NotificationService ====
        NotificationService notificationService = new NotificationService() {
            @Override
            public void sendEmail(String to, String subject, String message) {
                // dummy
            }

            @Override
            public void sendSMS(String to, String message) {
                // dummy
            }
        };

        // ==== GradeCalculator ====
        GradeCalculator gradeCalculator = new GradeCalculator();

        service = new EnrollmentService(studentRepo, courseRepo, notificationService, gradeCalculator);
    }

    // ---------------- VALIDATE CREDIT LIMIT ----------------
    @Test
    void testValidateCreditLimitWithinLimit() {
        assertTrue(service.validateCreditLimit("S001", 18)); // max 24 SKS
    }

    @Test
    void testValidateCreditLimitExceedsLimit() {
        assertFalse(service.validateCreditLimit("S001", 25)); // melebihi batas
    }

    @Test
    void testValidateCreditLimitStudentNotFound() {
        assertThrows(StudentNotFoundException.class,
                () -> service.validateCreditLimit("UNKNOWN", 10));
    }

    // ---------------- DROP COURSE ----------------
    @Test
    void testDropCourseSuccess() {
        assertDoesNotThrow(() -> service.dropCourse("S001", "CS101"));
    }

    @Test
    void testDropCourseStudentNotFound() {
        assertThrows(StudentNotFoundException.class,
                () -> service.dropCourse("S999", "CS101"));
    }

    @Test
    void testDropCourseCourseNotFound() {
        assertThrows(CourseNotFoundException.class,
                () -> service.dropCourse("S001", "INVALID"));
    }
}
