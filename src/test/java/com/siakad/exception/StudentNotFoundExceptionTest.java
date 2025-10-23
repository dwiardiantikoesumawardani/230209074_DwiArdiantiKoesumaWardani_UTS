package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test untuk StudentNotFoundException
 * Target: 100% line dan method coverage di JaCoCo
 */
class StudentNotFoundExceptionTest {

    @Test
    void testConstructorWithMessage() {
        StudentNotFoundException ex = new StudentNotFoundException("Student not found");
        assertEquals("Student not found", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new IllegalStateException("Invalid student record");
        StudentNotFoundException ex = new StudentNotFoundException("Unable to find student", cause);

        assertEquals("Unable to find student", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
