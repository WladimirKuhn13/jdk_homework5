package org.example;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread{
    private int number;
    private Semaphore[] forks;
    private int numberOfMeals;

    public Philosopher(int number, Semaphore[] forks, int numberOfMeals) {
        this.number = number;
        this.forks = forks;
        this.numberOfMeals = numberOfMeals;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfMeals; i++) {
            think();
            eat();
        }
    }

    private void eat() {
        int leftFork = number;
        int rightFork = (number + 1) % forks.length;

        try {
            forks[leftFork].acquire();
            forks[rightFork].acquire();
            System.out.println("Философ " + number + " ест");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            forks[rightFork].release();
            forks[leftFork].release();
        }
    }

    private void think() {
        System.out.println("Философ " + number + " думает");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
