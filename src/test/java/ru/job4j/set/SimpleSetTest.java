package ru.job4j.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenContainsMultiNotNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(3));
        assertTrue(set.add(2));
        assertTrue(set.add(27));
        assertTrue(set.contains(27));
        assertFalse(set.contains(5));
        assertFalse(set.add(2));
    }

    @Test
    public void whenContainsMultiNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(3));
        assertTrue(set.add(2));
        assertTrue(set.add(27));
        assertTrue(set.add(null));
        assertTrue(set.contains(27));
        assertFalse(set.contains(5));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
        assertFalse(set.add(2));
    }
}