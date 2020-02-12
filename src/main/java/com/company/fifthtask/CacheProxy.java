package com.company.fifthtask;

import com.company.fifthtask.service.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheProxy {

    private Map<String, SymbolsAmount> records = new LinkedHashMap<>();

    private SymbolsAmount getFromCache(final String phrase) {
        return records.getOrDefault(phrase, new SymbolsAmount(new LinkedHashMap<>()));
    }

    public Service cache(Service service) {
        InvocationHandler handler = (proxy, method, args) -> {
            if(method.getName().equals("doHardWork")) {
                String phrase = (String) args[0];
                SymbolsAmount symbolsAmount = getFromCache(phrase);
                if (symbolsAmount.getSymbolsAmount().isEmpty()) {
                    symbolsAmount = (SymbolsAmount) method.invoke(service, args);
                    records.put(phrase, symbolsAmount);
                    return symbolsAmount;
                } else {
                    System.out.println("Got from cache");
                    return symbolsAmount;
                }
            }
            return method.invoke(service, args);
        };
        return (Service) Proxy.newProxyInstance(service.getClass().getClassLoader(), new Class[]{Service.class}, handler);
    }


}
