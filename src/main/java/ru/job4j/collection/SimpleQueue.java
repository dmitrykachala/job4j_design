package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;
    private int sizeOut;

    public T poll() throws NoSuchElementException {
        if (size == 0 && sizeOut == 0) {
            throw new NoSuchElementException();
        }
        if (sizeOut == 0) {
            for (int i = 0; i < size; i++) {
                out.push(in.pop());
                sizeOut++;
            }
        }
        sizeOut--;
        size--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
