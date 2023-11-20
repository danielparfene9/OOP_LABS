package org.utm.lab3;

public interface Stack<T> {

    void push(T item);
    T pop(); // Removes and returns the top item from the stack
    T peek(); // Returns the top item without removing it
    boolean isEmpty();
    int size();

}
