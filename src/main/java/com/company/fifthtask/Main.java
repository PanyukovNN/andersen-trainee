package com.company.fifthtask;

import com.company.fifthtask.formatter.Formatter;
import com.company.fifthtask.service.Service;
import com.company.fifthtask.service.ServiceSymbolCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Некоторые методы могут выполняться очень долго, хочется иметь возможность кешировать результаты возврата.
 * Например, у нас есть интерфейс Service c методом doHardWork().
 * Повторный вызов этого метода с теми же параметрами должен возвращать рассчитанный результат из кэша.
 * <p>
 * Необходимо реализовать класс CacheProxy с методом cache(), который принимает ссылку на сервис и возвращает кеширующую
 * версию этого сервиса.
 * Результаты вызова метода кеш должен хранить в памяти.
 * Должна быть возможность настройки кеша с помощью аннотаций: кешировать определенные методы или все методы класса.
 * Логика по кешированию должна навешиваться с помощью DynamicProxy.
 * <p>
 * Дизайн аннотаций, классов реализаций на вкус.
 * Код должен быть читаем, классы не перегружены логикой, классы должны лежать в нужных пакетах.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        CacheProxy cacheProxy = new CacheProxy();
        Service service = new ServiceSymbolCounter();
        Service cacheService = cacheProxy.cache(service);
        Formatter formatter = new Formatter();
        System.out.println("Write phrases:");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String phrase;
            while (!(phrase = reader.readLine()).equals("exit")) {
                SymbolsAmount symbolsAmount = cacheService.doHardWork(phrase);
                String output = formatter.format(symbolsAmount);
                System.out.println(output);
            }
        }

    }
}
