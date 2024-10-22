package org.example;
import java.util.Scanner;

/**
 * Основний клас.
 */
public class Main {

    /**
     * Основний метод програми.
     */
    public static void main(String[] args) {
        boolean check = true;
        Scanner info = new Scanner(System.in);
        System.out.println("Введіть число N:");
        while (check) {

            // Перевіряємо, чи є введені дані коректні
            if (info.hasNextInt()) {
                int n = info.nextInt();
                if (n > 0) {
                    check = false;
                    Fibonacci fibonacci = new Fibonacci(n);
                    fibonacci.print();
                } else {
                    System.out.println("Число має бути додатнім. Введіть ще раз:");
                }
            } else {
                System.out.println("Введені дані не є числом. Введіть ще раз:");
                info.next();
            }
        }
        info.close();
    }
}
