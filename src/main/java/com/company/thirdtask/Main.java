package com.company.thirdtask;

/**
 *         Реализовать метод, который на вход принимает три параметра:
 *         money - количество денег, за них можно покупать конфеты,
 *         price - цена 1 конфеты, k - коэффициент, показывающий сколько фантиков можно обменять на 1 бонусную конфету.
 *         Метод должен возвращать максимальное количество съеденных конфет.
 *         Пример: money = 10, price = 3, k = 3, метод вернет 4 (3 купили и обменяли фантики от них на 1 дополнительную конфету).
 *         Определить алг. сложность предложенного алгоритма.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(foo(10, 3, 3));
        // Алгоритмическая сложность O(1)
    }

    private static int foo(int money, int price, int k) {
        int candies = money / price;
        return candies + candies / k;
    }
}
