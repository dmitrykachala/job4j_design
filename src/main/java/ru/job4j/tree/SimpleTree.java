package ru.job4j.tree;

import java.util.*;
import java.util.function.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {

        this.root = new Node<>(root);
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    public boolean isBinary() {
        return findByPredicate(n -> n.getChildren().size() > 2).isEmpty();
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> findParent = findBy(parent);
        if (findParent.isPresent() && findBy(child).isEmpty()) {
            findParent.ifPresent(n -> n.getChildren().add(new Node<>(child)));
                return true;
            }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(n -> n.getValue().equals(value));
    }

    public static void main(String[] args) {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(4, 5);
        tree.add(5, 6);
         System.out.println(tree.isBinary());
    }
}