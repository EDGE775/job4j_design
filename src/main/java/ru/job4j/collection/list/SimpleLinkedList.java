package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E value) {
        if (size == 0) {
            first = new Node<>(null, value, last);
        } else if (size == 1) {
            last = new Node<>(first, value, null);
            first.next = last;
        } else {
            Node<E> previousLast = last;
            last = new Node<>(previousLast, value, null);
            previousLast.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = first;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            private final int expectedModCount;
            private Node<E> buffer;

            {
                expectedModCount = modCount;
                buffer = first;
            }

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                if (isModCountChanged()) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = buffer.item;
                buffer = buffer.next;
                index++;
                return element;
            }

            private boolean isModCountChanged() {
                return expectedModCount != modCount;
            }
        };
    }

    private class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}