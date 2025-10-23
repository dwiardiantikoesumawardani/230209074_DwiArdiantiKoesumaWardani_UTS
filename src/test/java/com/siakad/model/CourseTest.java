package com.siakad.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk class Course
 * Tujuannya agar 100% line & branch coverage sesuai laporan JaCoCo.
 */
class CourseTest {

    @Test
    void testDefaultConstructorInitializesList() {
        Course course = new Course();
        assertNotNull(course.getPrerequisites());
        assertTrue(course.getPrerequisites().isEmpty());
    }

    @Test
    void testParameterizedConstructorInitializesFields() {
        Course course = new Course("CS101", "Algorithms", 3, 40, 10, "Dr. Alice");

        assertEquals("CS101", course.getCourseCode());
        assertEquals("Algorithms", course.getCourseName());
        assertEquals(3, course.getCredits());
        assertEquals(40, course.getCapacity());
        assertEquals(10, course.getEnrolledCount());
        assertEquals("Dr. Alice", course.getLecturer());
        assertNotNull(course.getPrerequisites());
        assertTrue(course.getPrerequisites().isEmpty());
    }

    @Test
    void testSettersAndGettersWorkProperly() {
        Course course = new Course();
        course.setCourseCode("CS102");
        course.setCourseName("Data Structures");
        course.setCredits(4);
        course.setCapacity(50);
        course.setEnrolledCount(12);
        course.setLecturer("Prof. Bob");

        assertEquals("CS102", course.getCourseCode());
        assertEquals("Data Structures", course.getCourseName());
        assertEquals(4, course.getCredits());
        assertEquals(50, course.getCapacity());
        assertEquals(12, course.getEnrolledCount());
        assertEquals("Prof. Bob", course.getLecturer());
    }

    @Test
    void testAddPrerequisiteAddsCourseToList() {
        Course course = new Course();
        course.addPrerequisite("CS100");
        assertEquals(1, course.getPrerequisites().size());
        assertTrue(course.getPrerequisites().contains("CS100"));
    }

    @Test
    void testAddPrerequisiteWhenListIsNullCreatesNewList() {
        Course course = new Course();
        course.setPrerequisites(null); // bikin null agar branch "if (this.prerequisites == null)" dieksekusi
        course.addPrerequisite("CS200");

        assertNotNull(course.getPrerequisites());
        assertEquals(1, course.getPrerequisites().size());
        assertTrue(course.getPrerequisites().contains("CS200"));
    }

    @Test
    void testSetPrerequisitesAndGetPrerequisites() {
        Course course = new Course();
        List<String> preReqs = new ArrayList<>();
        preReqs.add("CS001");
        preReqs.add("CS002");

        course.setPrerequisites(preReqs);
        assertEquals(2, course.getPrerequisites().size());
        assertTrue(course.getPrerequisites().contains("CS001"));
    }
}
