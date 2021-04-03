package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private int[] numbers;
    private int index = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        while (index < numbers.length) {
            if (numbers[index] % 2 == 0) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return numbers[index++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
