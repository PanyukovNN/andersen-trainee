package com.company.firsttask;

import java.util.Set;

public class SingleThread extends Thread {

    private int i;

    private Set<Double> res;

    public SingleThread(int i, Set<Double> res) {
        this.i = i;
        this.res = res;
    }

    public void run() {
        try {
            res.addAll(TestCalc.calculate(i));
        } catch (TestException e) {
            throw new CalculationException(e.getMessage(), e);
        }
    }
}
