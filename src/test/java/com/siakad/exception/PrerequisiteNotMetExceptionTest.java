package com.siakad.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test untuk PrerequisiteNotMetException
 * Target: 100% line & method coverage di JaCoCo
 */
class PrerequisiteNotMetExceptionTest {

    @Test
    void testConstructorWithMessage() {
        PrerequisiteNotMetException ex = new PrerequisiteNotMetException("Prerequisite not met");
        assertEquals("Prerequisite not met", ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        Throwable cause = new IllegalArgumentException("Missing prerequisite");
        PrerequisiteNotMetException ex = new PrerequisiteNotMetException("Failed to enroll", cause);

        assertEquals("Failed to enroll", ex.getMessage());
        assertEquals(cause, ex.getCause());
    }
}
