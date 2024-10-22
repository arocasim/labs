package org.example;

/**
 * Клас Фінобоначі, який обраховує число.
 */

public class Fibonacci {
    private final int inputnum;
    private final int fibnum;

    /**
     * Конструктор класу Fibonacci.
     *
     * @param n Ціле число для обчислення числа Фібоначчі.
     */
    public Fibonacci(int n) {
        this.inputnum = n;
        this.fibnum = Calculate(n);
    }

    /**
     * Обчислює n-е число Фібоначчі.
     *
     * @param n Ціле число, для якого потрібно обчислити число Фібоначчі.
     * @return n-е число Фібоначчі.
     */
    private int Calculate(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int fib1 = 0, fib2 = 1;
        int fibNum = 0;
        for (int i = 2; i <= n; i++) {
            fibNum = fib1 + fib2;
            fib1 = fib2;
            fib2 = fibNum;
        }
        return fibNum;
    }

    /**
     * Перевіряє умову задачі.
     *
     * @return true, якщо умова виконується, false - якщо ні.
     */
    public boolean Check() {
        int fibPlus2 = Calculate(this.inputnum + 2);
        int TwoN = (int) Math.pow(2, this.inputnum);
        return fibPlus2 < TwoN;
    }

    /**
     * Повертає вхідне число n.
     *
     * @return Вхідне число n.
     */
    public int getNumber() {
        return this.inputnum;
    }

    /**
     * Повертає розраховане число Фібоначчі.
     *
     * @return Розраховане число Фібоначчі.
     */
    public int getNumber2() {
        return this.fibnum;
    }

    /**
     * Виводить результати обчислення числа Фібоначчі та перевірку умови.
     */
    public void print() {
        System.out.print("\nЗначення числа Фібоначі: " + this.fibnum);
        System.out.print("\nПеревірка умови задачі: " + Check());
    }
}

