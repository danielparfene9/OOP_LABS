package org.utm.lab3;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class DynamicArrayQueue<T> implements Queue<T> {
    private ArrayList<T> list;

    public DynamicArrayQueue() {
        list = new ArrayList<>();
    }

    @Override
    public void enqueue(T item) {
        list.add(item);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.remove(0);
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }
}

