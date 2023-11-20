package org.utm.lab3;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Stack<T> {

    private T[] array;
    private int top;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayStack() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        top = -1;
    }

    @Override
    public void push(T item) {
        if (top == array.length - 1) {
            resize(2 * array.length);
        }
        array[++top] = item;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = array[top];
        array[top--] = null;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(array, 0, temp, 0, array.length);
        array = temp;
    }
}

