package com.example.first.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BudgetService {

    private double balance = 1000.0;

    private final ReentrantLock lock = new ReentrantLock();

    public void spend(double amount) {

        lock.lock();
        try {
            if (balance >= amount) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                balance -= amount;
            }
        } finally {

            lock.unlock();
        }
    }

    public double getBalance() {
        return balance;
    }

    public void reset() {
        this.balance = 1000.0;
    }
}