package com.company.thirdtask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ThirdTaskTest {

    private ThirdTask thirdTask;

    @BeforeEach
    void setUp() {
        thirdTask = new ThirdTask();
    }

    @Test
    void foo_Insert_10_3_3_Return_4() {
        int actual = thirdTask.foo(10, 3, 3);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    void foo_Insert_10_1_1_Return_4() {
        int actual = thirdTask.foo(10, 1, 1);
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    void foo_Insert_20_3_3_Return_8() {
        int actual = thirdTask.foo(20, 3, 3);
        int expected = 8;
        assertEquals(expected, actual);
    }

    @Test
    void foo_Insert_105_3_3_Return_52() {
        int actual = thirdTask.foo(105, 3, 3);
        int expected = 52;
        assertEquals(expected, actual);
    }
}