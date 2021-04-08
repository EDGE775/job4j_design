package ru.job4j.generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {
    private SimpleArray<Integer> numbers;

    @Before
    public void setUp() throws Exception {
        numbers = new SimpleArray<>(10);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
    }

    @Test
    public void whenAddCorrect() {
        numbers.add(7);
        assertThat(numbers.get(6), is(7));
    }

    @Test
    public void whenSetCorrect() {
        numbers.set(5, 10);
        assertThat(numbers.get(5), is(10));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetUnCorrect() {
        numbers.set(10, 10);
    }

    @Test
    public void whenGetCorrect() {
        int result = numbers.get(0);
        assertThat(result, is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetUnCorrect() {
        int result = numbers.get(10);
    }

    @Test
    public void whenRemoveCorrect() {
        numbers.remove(2);
        assertThat(numbers.get(2), is(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveUnCorrect() {
        numbers.remove(6);
    }
}