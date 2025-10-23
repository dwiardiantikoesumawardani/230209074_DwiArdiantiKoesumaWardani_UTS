package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test untuk CourseFullException
 * Target: 100% line & method coverage di JaCoCo
 */
class CourseFullExceptionTest {

    @Test
    void testConstructorWithMessage() {
        CourseFullException ex = new CourseFullException("Course is full");
        assertEquals("Course is full", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new IllegalArgumentException("Invalid capacity");
        CourseFullException ex = new CourseFullException("Course full", cause);

        assertEquals("Course full", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
