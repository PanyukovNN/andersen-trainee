package com.company.fifthtask;

public class CachedResult {

    private final Object value;

    public CachedResult(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
