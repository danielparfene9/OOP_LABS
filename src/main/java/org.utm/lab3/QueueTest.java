package org.utm.lab3;

public class QueueTest {
    public static void main(String[] args) {

        Queue<Integer> arrayQueue = new ArrayQueue<>();
        testQueue(arrayQueue);


        Queue<Integer> linkedListQueue = new LinkedListQueue<>();
        testQueue(linkedListQueue);


        Queue<Integer> dynamicArrayQueue = new DynamicArrayQueue<>();
        testQueue(dynamicArrayQueue);
    }

    private static void testQueue(Queue<Integer> queue) {
        System.out.println("Testing " + queue.getClass().getSimpleName());
        
        for (int i = 1; i <= 5; i++) {
            System.out.println("Enqueuing: " + i);
            queue.enqueue(i);
        }

        System.out.println("Peek: " + queue.peek());

        while (!queue.isEmpty()) {
            System.out.println("Dequeuing: " + queue.dequeue());
        }

        System.out.println();
    }
}
