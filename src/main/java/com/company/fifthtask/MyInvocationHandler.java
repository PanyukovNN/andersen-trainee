package com.company.fifthtask;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyInvocationHandler implements InvocationHandler {

    private static Map<Method, Object> records = new HashMap<>();

    private Object service;

    public MyInvocationHandler(Object service) {
        this.service = service;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = records.get(method);
        if (result == null && !records.containsKey(method)) {
            result = method.invoke(service, args);
            Method serviceMethod = service.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
            if (serviceMethod.isAnnotationPresent(MyCacheAnnotation.class)) {
                records.put(method, result);
            }
        }
        return result;
    }
}
