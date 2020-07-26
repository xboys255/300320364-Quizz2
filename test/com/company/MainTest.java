package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {

        Employee e = new Employee("Toan", 89, "F") {
            @Override
            public String getName() {
                return super.getName();
            }
        };
        assertNotNull(e.getName());
    }
}