package ru.belov.hw2;

import java.util.ArrayList;

public class MyArrayList<T> extends ArrayList<T> {
    private Object[] array;
    private int size;//индекс текущего элемента
    private static final int DEFAULT_CAPACITY = 5;

    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T element) {
        try {
            if (size == 10) {
                throw new MyOutOfBoundsException("превышен лимит элементов в списке");
            }
            if (size == array.length) {
                increaseCapacity();
            }
            array[size] = element;
            size++;
        } catch (MyOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array[index] = element;
        return element;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return null;
    }

    @Override
    public void clear() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void increaseCapacity() {
        int newCapacity = array.length + 1;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}

