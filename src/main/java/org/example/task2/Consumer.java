package org.example.task2;

public class Consumer implements Runnable {
    private Transfer transfer;

    public Consumer(Transfer transfer) {
        this.transfer = transfer;
    }

    public void run() {
        for (Object message = transfer.take();
             ! message.equals("DONE");
             message = transfer.take()) {
            System.out.println(message);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {}
        }
    }
}