package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size = 0;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (container.length == size) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size] = value;
        modCount++;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        int ind = Objects.checkIndex(index, container.length);
        T tmp = container[ind];
        container[ind] = newValue;
        return tmp;
    }

    @Override
    public T remove(int index) {
        int ind = Objects.checkIndex(index, container.length);
        T tmp = container[ind];
        System.arraycopy(container, ind + 1, container, ind, container.length - ind - 1);
        modCount++;
        size--;
        return tmp;
    }

    @Override
    public T get(int index) {
        int ind = Objects.checkIndex(index, container.length);
        return container[ind];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                throw new NoSuchElementException();
            }
                return container[index++];
            }

        };
    }
}
