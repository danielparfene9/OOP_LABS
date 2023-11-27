package org.utm.lab3;

public class DoubleStackQueueTest {
    public static void main(String[] args) {
        DoubleStackQueue<Integer> queue = new DoubleStackQueue<>();

        System.out.println("Is the queue empty? " + queue.isEmpty());

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        System.out.println("Is the queue empty? " + queue.isEmpty());

        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());

        queue.enqueue(4);
        queue.enqueue(5);

        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());

        System.out.println("Is the queue empty? " + queue.isEmpty());
    }
}
