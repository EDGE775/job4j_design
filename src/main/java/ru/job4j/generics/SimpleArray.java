package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] storage;
    private int size = 0;

    public SimpleArray(int capacity) {
        this.storage = new Object[capacity];
    }

    public boolean add(T model) {
        if (size < storage.length - 1) {
            storage[size++] = model;
            return true;
        }
        return false;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        storage[index] = model;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) storage[index];
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) storage[index++];
            }
        };
    }

    public static void main(String[] args) {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(1);
        array.add(2);
        array.add(3);
        array.set(3, 10);
        array.remove(4);
        for (Integer integer : array) {
            System.out.println(integer);
        }
    }
}
