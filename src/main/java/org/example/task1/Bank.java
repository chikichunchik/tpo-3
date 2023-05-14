package org.example.task1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    public static final int NTEST = 100;
//    private final int[] accounts;
//    private long ntransacts = 0;

    private final AtomicLong ntransacts = new AtomicLong(0);
    private final AtomicIntegerArray accounts;

    private final ReentrantLock lock = new ReentrantLock();

    public Bank(int n, int initialBalance) {
        int i;

//        accounts = new int[n];
//        for (i = 0; i < accounts.length; i++) {
//            accounts[i] = initialBalance;
//        }

        accounts = new AtomicIntegerArray(new int[n]);
        for (i = 0; i < accounts.length(); i++) {
            accounts.set(i, initialBalance);
        }
        ntransacts.set(0);

    }

    public void transfer(int from, int to, int amount) throws InterruptedException {
        accounts.addAndGet(from, -amount);
        accounts.addAndGet(to, amount);
        ntransacts.incrementAndGet();
        if (ntransacts.get() % NTEST == 0) test();
    }


//    public void transfer(int from, int to, int amount) throws InterruptedException {
//        lock.lock();
//        accounts[from] -= amount;
//        accounts[to] += amount;
//        ntransacts++;
//
//        if (ntransacts % NTEST == 0) test();
//        lock.unlock();
//    }

//    public void transfer(int from, int to, int amount) throws InterruptedException {
//        synchronized (this) {
//            accounts[from] -= amount;
//            accounts[to] += amount;
//            ntransacts++;
//            if (ntransacts % NTEST == 0) test();
//        }
//    }

    public void test() {
        AtomicInteger sum = new AtomicInteger(0);
        for (int i = 0; i < accounts.length(); i++) sum.addAndGet(accounts.get(i));
        System.out.println("Transactions:" + ntransacts.get() + " Sum: " + sum.get());

//
//        int sum = 0;
//        for (int account : accounts) sum += account;
//        System.out.println("Transactions:" + ntransacts + " Sum: " + sum);
    }

    public int size() {
//        return accounts.length;
        return accounts.length();
    }
}
