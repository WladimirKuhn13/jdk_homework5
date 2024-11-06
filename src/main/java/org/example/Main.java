package org.example;

import java.util.concurrent.Semaphore;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int philosophersCount = 5;
        int numberOfMeals = 3;
        Semaphore[] forks = new Semaphore[philosophersCount];
        Philosopher[] philosophers = new Philosopher[philosophersCount];

        for (int i = 0; i < philosophersCount; i++) {
            forks[i] = new Semaphore(1);
        }

        for (int i = 0; i < philosophersCount; i++) {
            philosophers[i] = new Philosopher(i, forks, numberOfMeals);
        }

        for (Philosopher philosopher : philosophers) {
            philosopher.start();
        }

        for (Philosopher philosopher : philosophers) {
            try {
                philosopher.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Программа закончена");
    }
}