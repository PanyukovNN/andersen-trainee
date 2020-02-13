package com.company.fifthtask;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheProxy {

    public static <T> T cache(final T service) {
        List<Class> allInterfaces = new ArrayList<>();
        for (Class current = service.getClass(); current != null ; current = current.getSuperclass()) {
            allInterfaces.addAll(Arrays.asList(current.getInterfaces()));
        }
        Class[] allInterfacesArray = new Class[allInterfaces.size()];
        return (T) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                allInterfaces.toArray(allInterfacesArray),
                new CacheInvocationHandler(service));
    }
}
