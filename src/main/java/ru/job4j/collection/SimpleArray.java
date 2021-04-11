package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] storage;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray(int capacity) {
        this.storage = new Object[capacity];
    }

    public void add(T model) {
        if (size < storage.length - 1) {
            storage[size++] = model;
        } else {
            growUp();
            storage[size - 1] = model;
        }
        modCount++;
    }

    private void growUp() {
        int newSize = size + 1;
        Object[] newStorage = new Object[newSize];
        System.arraycopy(storage, 0, newStorage, 0, size);
        storage = newStorage;
        size = newSize;
        modCount++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        storage[index] = model;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) storage[index];
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
        modCount++;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private int expectedModCount;

            {
                expectedModCount = modCount;
            }

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (isModCountChanged()) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) storage[index++];
            }

            private boolean isModCountChanged() {
                return expectedModCount != modCount;
            }
        };
    }
}
