package com.company.thirdtask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ThirdTaskTest {

    private int money;

    private int price;

    private int k;

    private int expected;

    public ThirdTaskTest(int money, int price, int k, int expected) {
        this.money = money;
        this.price = price;
        this.k = k;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}:candiesFor({0},{1},{2})={3}")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {10, 3, 3, 4},
                {20, 3, 3, 8},
                {100, 3, 3, 49},
                {105, 3, 3, 52}
        });
    }

    @Test
    public void paramTest() {
        assertEquals(expected, new ThirdTask().buyCandies(money, price, k));
    }
}