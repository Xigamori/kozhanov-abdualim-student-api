package com.kozhanov.studentapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void studentStoresName() {
        Student student = new Student();
        student.setName("Kozhanov Abdualim");
        assertEquals("Kozhanov Abdualim", student.getName());
    }
}
