package com.company.firsttask.patterns.adapter;

/**
 * Примеры Адаптеров в стандартных библиотеках Java:
 * java.util.Arrays#asList()
 * java.util.Collections#list()
 * java.io.InputStreamReader(InputStream) (возвращает объект Reader)
 * java.io.OutputStreamWriter(OutputStream)(возвращает объект Writer)
 */
public class Main {

    public static void main(String[] args) {
        USB cardReader = new CardReader(new MemoryCard());
        cardReader.connectViaCable();
    }
}
