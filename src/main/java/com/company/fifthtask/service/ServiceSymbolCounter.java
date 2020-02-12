package com.company.fifthtask.service;

import com.company.fifthtask.SymbolsAmount;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceSymbolCounter implements Service {

    @Override
    public SymbolsAmount doHardWork(final String phrase) {
        if (phrase == null) {
            throw new IllegalArgumentException();
        }
        Map<Character, Integer> symbolsCount = new LinkedHashMap<>();
        for (char symbol : phrase.toCharArray()) {
            if (symbolsCount.containsKey(symbol)) {
                int count = symbolsCount.get(symbol);
                symbolsCount.put(symbol, count + 1);
            } else {
                symbolsCount.put(symbol, 1);
            }
        }
        return new SymbolsAmount(symbolsCount);
    }
}
