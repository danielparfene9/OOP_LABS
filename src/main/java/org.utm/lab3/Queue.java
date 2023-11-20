package org.utm.lab3;

public interface Queue<T> {

    void enqueue(T item);
    T dequeue();
    T peek();
    boolean isEmpty();
    int size();

}

