package com.company.fifthtask;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CacheInvocationHandler implements InvocationHandler {

    private final Map<Key, CachedResult> records = new ConcurrentHashMap<>();

    private final Object service;

    public CacheInvocationHandler(Object service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Key key = new Key(method, args);
        if (checkAnnotation(proxy, method)) {
            records.computeIfAbsent(key, this::invokeMethod);
        }
        if (records.containsKey(key)) {
            return records.get(key).getValue();
        } else {
            return invokeMethod(key).getValue();
        }
    }

    private boolean checkAnnotation(Object proxy, Method method) {
        List<Method> methods = new ArrayList<>(Arrays.asList(service.getClass().getDeclaredMethods()));
        for (Class classItem : proxy.getClass().getInterfaces()) {
            methods.addAll(Arrays.asList(classItem.getDeclaredMethods()));
        }
        for (Method methodItem : methods) {
            if (methodItem.getName().equals(method.getName()) && Arrays.equals(methodItem.getParameterTypes(), method.getParameterTypes())) {
                return true;
            }
        }
        return false;
    }

    private CachedResult invokeMethod(Key key) {
        try {
            return new CachedResult(key.getMethod().invoke(service, key.getArgs()));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static class Key {

        private Method method;

        private Object[] args;

        private Key(Method method, Object[] args) {
            this.method = method;
            this.args = args;
        }

        private Method getMethod() {
            return method;
        }

        private Object[] getArgs() {
            return args;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return Objects.equals(method, key.method) &&
                    Arrays.equals(args, key.args);
        }

//        @Override
//        public int hashCode() {
//            return Objects.hash(method, args);
//        }

        @Override
        public int hashCode() {
            int result = Objects.hash(method);
            result = 31 * result + Arrays.hashCode(args);
            return result;
        }
    }
}
