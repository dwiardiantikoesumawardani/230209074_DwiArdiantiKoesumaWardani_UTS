package com.siakad.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk class Student
 * Tujuan: 100% Line Coverage di JaCoCo
 */
class StudentTest {

    @Test
    void testDefaultConstructor() {
        Student student = new Student();
        assertNotNull(student);
        assertNull(student.getStudentId());
        assertNull(student.getName());
        assertNull(student.getEmail());
        assertNull(student.getMajor());
        assertEquals(0, student.getSemester());
        assertEquals(0.0, student.getGpa());
        assertNull(student.getAcademicStatus());
    }

    @Test
    void testParameterizedConstructorSetsAllFields() {
        Student student = new Student(
                "S001", "Dwi", "dwi@mail.com", "Cyber Security", 4, 3.45, "ACTIVE"
        );

        assertEquals("S001", student.getStudentId());
        assertEquals("Dwi", student.getName());
        assertEquals("dwi@mail.com", student.getEmail());
        assertEquals("Cyber Security", student.getMajor());
        assertEquals(4, student.getSemester());
        assertEquals(3.45, student.getGpa());
        assertEquals("ACTIVE", student.getAcademicStatus());
    }

    @Test
    void testSettersAndGettersWorkProperly() {
        Student student = new Student();

        student.setStudentId("S002");
        student.setName("Ardian");
        student.setEmail("ardian@mail.com");
        student.setMajor("Informatics");
        student.setSemester(3);
        student.setGpa(3.8);
        student.setAcademicStatus("PROBATION");

        assertEquals("S002", student.getStudentId());
        assertEquals("Ardian", student.getName());
        assertEquals("ardian@mail.com", student.getEmail());
        assertEquals("Informatics", student.getMajor());
        assertEquals(3, student.getSemester());
        assertEquals(3.8, student.getGpa());
        assertEquals("PROBATION", student.getAcademicStatus());
    }

    @Test
    void testSetterOverwriteValuesCorrectly() {
        Student student = new Student("S003", "Wardani", "wardani@mail.com", "IT", 2, 2.5, "ACTIVE");

        student.setName("Wardani Updated");
        student.setMajor("Software Engineering");
        student.setSemester(5);
        student.setAcademicStatus("SUSPENDED");

        assertEquals("Wardani Updated", student.getName());
        assertEquals("Software Engineering", student.getMajor());
        assertEquals(5, student.getSemester());
        assertEquals("SUSPENDED", student.getAcademicStatus());
    }
}
