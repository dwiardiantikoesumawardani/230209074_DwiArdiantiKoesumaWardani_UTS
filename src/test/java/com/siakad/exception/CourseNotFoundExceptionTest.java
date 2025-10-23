package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test untuk CourseNotFoundException
 * Target: 100% line dan method coverage di JaCoCo
 */
class CourseNotFoundExceptionTest {

    @Test
    void testConstructorWithMessage() {
        CourseNotFoundException ex = new CourseNotFoundException("Course not found");
        assertEquals("Course not found", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new NullPointerException("Database missing entry");
        CourseNotFoundException ex = new CourseNotFoundException("Course lookup failed", cause);

        assertEquals("Course lookup failed", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
