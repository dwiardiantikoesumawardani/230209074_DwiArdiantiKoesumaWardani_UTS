package com.siakad.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test untuk class CourseGrade
 * Target: 100% line dan method coverage di JaCoCo
 */
class CourseGradeTest {

    @Test
    void testDefaultConstructor() {
        CourseGrade grade = new CourseGrade();
        assertNotNull(grade);
        assertNull(grade.getCourseCode());
        assertEquals(0, grade.getCredits());
        assertEquals(0.0, grade.getGradePoint());
    }

    @Test
    void testParameterizedConstructorSetsAllFields() {
        CourseGrade grade = new CourseGrade("CS101", 3, 4.0);

        assertEquals("CS101", grade.getCourseCode());
        assertEquals(3, grade.getCredits());
        assertEquals(4.0, grade.getGradePoint());
    }

    @Test
    void testSettersAndGettersWorkCorrectly() {
        CourseGrade grade = new CourseGrade();

        grade.setCourseCode("CS202");
        grade.setCredits(2);
        grade.setGradePoint(3.5);

        assertEquals("CS202", grade.getCourseCode());
        assertEquals(2, grade.getCredits());
        assertEquals(3.5, grade.getGradePoint());
    }

    @Test
    void testSetterOverwriteValues() {
        CourseGrade grade = new CourseGrade("CS303", 4, 2.5);

        grade.setCourseCode("CS404");
        grade.setCredits(6);
        grade.setGradePoint(3.9);

        assertEquals("CS404", grade.getCourseCode());
        assertEquals(6, grade.getCredits());
        assertEquals(3.9, grade.getGradePoint());
    }
}
