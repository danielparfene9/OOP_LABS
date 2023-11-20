package org.utm.lab3;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {

    private T[] array;
    private int front, rear, size, capacity;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayQueue() {

        array = (T[]) new Object[DEFAULT_CAPACITY];
        front = size = 0;
        rear = capacity - 1;
    }

    @Override
    public void enqueue(T item) {
        if (size == capacity) {
            resize(2 * capacity);
        }
        rear = (rear + 1) % capacity;
        array[rear] = item;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = array[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(front + i) % capacity];
        }
        array = newArray;
        front = 0;
        rear = size - 1;
        capacity = newCapacity;
    }
}

