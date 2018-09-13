package ru.otus;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.RandomAccess;

public class CustomArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    private static final int DEFAULT_CAPACITY = 10;

    private int size;

    @SuppressWarnings("WeakerAccess")
    transient Object[] elementData;


    public CustomArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    public CustomArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }


    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index >= size) throw new IndexOutOfBoundsException("Index " + index + "out of bound");
        return (E) this.elementData[index];
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public static void main(String[] arguments) {

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("");
        arrayList.size();
        arrayList.get(1);
    }

}
