package com.company.fifthtask;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CacheInvocationHandler implements InvocationHandler {

    private final Map<Key, Object> records = Collections.synchronizedMap(new HashMap<>());

    private final Object service;

    public CacheInvocationHandler(Object service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Key key = new Key(method, args);
        Method serviceMethod = service.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
        if (serviceMethod.isAnnotationPresent(Cached.class) && !records.containsKey(key)) {
            records.put(key, invokeMethod(key));
        }
        return records.get(key);
    }

    private Object invokeMethod(Key key) {
        try {
            return key.getMethod().invoke(service, key.getArgs());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class Key {

        private Method method;

        private Object[] args;

        public Key(Method method, Object[] args) {
            this.method = method;
            this.args = args;
        }

        public Method getMethod() {
            return method;
        }

        public Object[] getArgs() {
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

        @Override
        public int hashCode() {
            int result = Objects.hash(method);
            result = 31 * result + Arrays.hashCode(args);
            return result;
        }
    }
}
