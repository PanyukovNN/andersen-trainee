package com.company.fifthtask;

import java.lang.reflect.Proxy;

public class CacheProxy {

    public static <T> T cache(final T service) {
        return (T) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new MyInvocationHandler(service));
    }
}
