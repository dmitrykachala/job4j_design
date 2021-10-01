package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class SimpleLinkedList<E> implements List<E> {

    private int size = 0;
    private Node<E> first = null;
    private Node<E> last = null;
    private int modCount = 0;

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        public E getItem() {
            return item;
        }
    }

    @Override
    public void add(E value) {
        Node<E> clast = last;
        last = new Node<>(value, null);
        if (clast != null) {
            clast.next = last;
        }
        if (first == null) {
            first = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> citem = first;
        for (int pos = 0; pos < index; pos++) {
            citem = citem.next;
        }
        return citem.getItem();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;
            private SimpleLinkedList.Node<E> citem = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return citem != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = citem.getItem();
                citem = citem.next;
                return rsl;
            }
        };
    }
}
