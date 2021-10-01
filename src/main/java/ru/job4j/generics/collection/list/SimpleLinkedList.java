package ru.job4j.generics.collection.list;

import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public E getItem() {
            return item;
        }
    }

    private int size = 0;
    private Node<E> first = null;
    private Node<E> last = null;

    private int modCount = 0;

    @Override
    public void add(E value) {
        Node<E> clast = last;
        last = new Node<>(last, value, null);
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
        int ind = Objects.checkIndex(index, size);
        int pos = 0;
        Node<E> citem = first;
        while (pos < ind) {
            citem = citem.next;
            pos++;
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
