package com.siakad.repository;

import com.siakad.model.Course;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test untuk interface CourseRepository.
 * Karena interface tidak bisa di-instantiate langsung,
 * maka kita uji dengan membuat anonymous implementation di dalam test.
 */
class CourseRepositoryTest {

    // Anonymous implementation untuk pengujian
    private final CourseRepository repo = new CourseRepository() {
        @Override
        public Course findByCourseCode(String courseCode) {
            // simulasi: kembalikan null
            return null;
        }

        @Override
        public void update(Course course) {
            // tidak melakukan apa-apa
        }

        @Override
        public boolean isPrerequisiteMet(String studentId, String courseCode) {
            // simulasi hasil false
            return false;
        }
    };

    @Test
    void testFindByCourseCodeReturnsNull() {
        assertNull(repo.findByCourseCode("CS101"));
    }

    @Test
    void testIsPrerequisiteMetReturnsFalse() {
        assertFalse(repo.isPrerequisiteMet("S001", "CS101"));
    }

    @Test
    void testUpdateDoesNotThrowException() {
        assertDoesNotThrow(() -> repo.update(new Course()));
    }
}
