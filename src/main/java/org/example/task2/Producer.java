package org.example.task2;

import java.util.Random;

public class Producer implements Runnable {
    private Transfer transfer;
    private final Random random = new Random();
    private int[] arr;

    public Producer(Transfer transfer, int arrSize) {
        this.transfer = transfer;
        initArray(arrSize);
    }

    public void run() {
        for (int j : arr) {
            transfer.put(j);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.err.println("Producer interrupted - " + e);
            }
        }
        transfer.put("DONE");
    }

    private void initArray(int size) {
        arr = new int[size];
        for(int i=0; i<size; i++) {
            arr[i] = i;
        }
    }
}
