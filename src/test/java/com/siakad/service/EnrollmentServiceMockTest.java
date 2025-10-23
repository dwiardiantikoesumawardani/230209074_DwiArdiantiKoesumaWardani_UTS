package com.siakad.service;

import com.siakad.exception.*;
import com.siakad.model.Course;
import com.siakad.model.Enrollment;
import com.siakad.model.Student;
import com.siakad.repository.CourseRepository;
import com.siakad.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit Test menggunakan MOCK untuk EnrollmentService
 * Sesuai soal UTS poin C.
 */
class EnrollmentServiceMockTest {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private NotificationService notificationService;
    private GradeCalculator gradeCalculator;
    private EnrollmentService service;

    @BeforeEach
    void setup() {
        studentRepository = mock(StudentRepository.class);
        courseRepository = mock(CourseRepository.class);
        notificationService = mock(NotificationService.class);
        gradeCalculator = mock(GradeCalculator.class);
        service = new EnrollmentService(studentRepository, courseRepository, notificationService, gradeCalculator);
    }

    @Test
    void testEnrollCourseSuccess() {
        Student student = new Student();
        student.setStudentId("S001");
        student.setEmail("test@student.com");
        student.setAcademicStatus("ACTIVE");

        Course course = new Course();
        course.setCourseCode("CS101");
        course.setCourseName("Pemrograman Dasar");
        course.setCapacity(30);
        course.setEnrolledCount(10);

        when(studentRepository.findById("S001")).thenReturn(student);
        when(courseRepository.findByCourseCode("CS101")).thenReturn(course);
        when(courseRepository.isPrerequisiteMet("S001", "CS101")).thenReturn(true);

        Enrollment result = service.enrollCourse("S001", "CS101");

        assertNotNull(result);
        assertEquals("S001", result.getStudentId());
        assertEquals("CS101", result.getCourseCode());
        verify(notificationService, times(1))
                .sendEmail(eq("test@student.com"), anyString(), contains("Pemrograman Dasar"));
    }

    @Test
    void testEnrollCourseStudentNotFound() {
        when(studentRepository.findById("S404")).thenReturn(null);
        assertThrows(StudentNotFoundException.class,
                () -> service.enrollCourse("S404", "CS101"));
    }

    @Test
    void testEnrollCourseStudentSuspended() {
        Student s = new Student();
        s.setStudentId("S001");
        s.setAcademicStatus("SUSPENDED");
        when(studentRepository.findById("S001")).thenReturn(s);
        assertThrows(EnrollmentException.class,
                () -> service.enrollCourse("S001", "CS101"));
    }

    @Test
    void testEnrollCourseNotFound() {
        Student s = new Student();
        s.setStudentId("S001");
        s.setAcademicStatus("ACTIVE");
        when(studentRepository.findById("S001")).thenReturn(s);
        when(courseRepository.findByCourseCode("CS999")).thenReturn(null);
        assertThrows(CourseNotFoundException.class,
                () -> service.enrollCourse("S001", "CS999"));
    }

    @Test
    void testEnrollCourseFull() {
        Student s = new Student();
        s.setStudentId("S001");
        s.setAcademicStatus("ACTIVE");
        when(studentRepository.findById("S001")).thenReturn(s);

        Course c = new Course();
        c.setCourseCode("CS101");
        c.setCapacity(30);
        c.setEnrolledCount(30);
        when(courseRepository.findByCourseCode("CS101")).thenReturn(c);

        assertThrows(CourseFullException.class,
                () -> service.enrollCourse("S001", "CS101"));
    }

    @Test
    void testEnrollCoursePrerequisiteNotMet() {
        Student s = new Student();
        s.setStudentId("S001");
        s.setAcademicStatus("ACTIVE");

        Course c = new Course();
        c.setCourseCode("CS101");
        c.setCapacity(20);
        c.setEnrolledCount(10);

        when(studentRepository.findById("S001")).thenReturn(s);
        when(courseRepository.findByCourseCode("CS101")).thenReturn(c);
        when(courseRepository.isPrerequisiteMet("S001", "CS101")).thenReturn(false);

        assertThrows(PrerequisiteNotMetException.class,
                () -> service.enrollCourse("S001", "CS101"));
    }
}
