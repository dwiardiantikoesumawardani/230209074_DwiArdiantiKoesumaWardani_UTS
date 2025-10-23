package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test untuk EnrollmentException
 * Target: 100% line dan method coverage di JaCoCo
 */
class EnrollmentExceptionTest {

    @Test
    void testConstructorWithMessage() {
        EnrollmentException ex = new EnrollmentException("Enrollment failed");
        assertEquals("Enrollment failed", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new RuntimeException("Database unavailable");
        EnrollmentException ex = new EnrollmentException("Unable to process enrollment", cause);

        assertEquals("Unable to process enrollment", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
