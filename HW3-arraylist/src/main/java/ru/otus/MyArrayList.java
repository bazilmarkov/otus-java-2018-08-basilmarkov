package ru.otus;

import java.util.*;

public class MyArrayList<T> implements List<T> {

    private int size;

    @SuppressWarnings("WeakerAccess")
    transient Object[] elementData;
    private static final Object[] EMPTY_ELEMENTDATA = {};

    @SuppressWarnings("WeakerAccess")
    public MyArrayList(int initialCapacity) {
        this.size = 0;
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    public MyArrayList() {
        this.size = 0;
        this.elementData = EMPTY_ELEMENTDATA;
    }


    public Object[] toArray() {
        throw new RuntimeException();
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            this.elementData[i] = 0;
        }
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= this.size) throw new IndexOutOfBoundsException("Index " + index + "out of bound");
        return (T) this.elementData[index];
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        throw new RuntimeException();
    }

    public ListIterator<T> listIterator() {
        return new CustomIterator(0);
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new RuntimeException();
    }

    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException();
    }

    public Iterator<T> iterator() {
        return this.listIterator();
    }

    public boolean add(T element) {
        if (this.elementData.length == this.size) {
            final Object[] oldElementData = this.elementData;
            this.elementData = new Object[this.size() + 1];
            System.arraycopy(oldElementData, 0, this.elementData, 0, oldElementData.length);
        }
        this.elementData[this.size++] = element;
        return true;
    }

    public T set(int index, T element) {
        if (index >= this.size) throw new IndexOutOfBoundsException("Index " + index + " out of bound");
        this.elementData[index] = element;
        return element;
    }

    public boolean contains(Object o) {
        throw new RuntimeException();
    }

    public int size() {
        return this.size;
    }

    public ListIterator<T> listIterator(int index) {
        throw new RuntimeException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new RuntimeException();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsAll(Collection<?> c) {
        return false;
    }

    public int indexOf(Object o) {
        throw new RuntimeException();
    }

    public boolean addAll(Collection<? extends T> c) {
        throw new RuntimeException();
    }

    public boolean remove(Object o) {
        throw new RuntimeException();
    }

    public int lastIndexOf(Object o) {
        throw new RuntimeException();
    }

    public void add(int index, T element) {
        throw new RuntimeException();
    }

    public T remove(int index) {
        throw new RuntimeException();
    }

    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) elementData, 0, size, c);
    }

    private class CustomIterator implements ListIterator<T> {

        private int index;

        private int lastIndex = -1;


        CustomIterator(int index) {
            if (index < 0 || index > MyArrayList.this.size) {
                throw new IndexOutOfBoundsException("Index " + index + " out of bound");
            }
            this.index = index;

        }

        public boolean hasNext() {
            return MyArrayList.this.size() > this.index;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            this.lastIndex = this.index++;
            return (T) MyArrayList.this.elementData[this.lastIndex];

        }


        public boolean hasPrevious() {
            throw new RuntimeException();
        }


        public T previous() {
            throw new RuntimeException();
        }


        public int nextIndex() {
            throw new RuntimeException();
        }


        public int previousIndex() {
            throw new RuntimeException();
        }


        public void remove() {
            throw new RuntimeException();
        }


        public void set(T element) {
            MyArrayList.this.set(lastIndex, element);
        }


        public void add(T t) {
            throw new RuntimeException();
        }
    }


    public static void main(String[] arguments) {

        MyArrayList<String> myArrayList = new MyArrayList<String>(6);
        myArrayList.add("String1");
        myArrayList.add("String2");
        myArrayList.add("String3");
        myArrayList.add("String4");
        MyArrayList<Integer> myArrayList2 = new MyArrayList<Integer>(5);
        myArrayList2.add(1);
        myArrayList2.add(142);
        myArrayList2.add(11);
        myArrayList2.add(31);
        MyArrayList<String> myArrayList3 = new MyArrayList<String>(10);
        myArrayList3.add("1");
        myArrayList3.add("2");
        myArrayList3.add("3");
        myArrayList3.add("4");
        System.out.println("Check add operation");
        System.out.println("-------------------");
        for (String element : myArrayList) {
            System.out.println(element);
        }
        System.out.println("-------------------");
        Collections.copy(myArrayList3, myArrayList);
        Collections.sort(myArrayList2);
        System.out.println("Check sort operation");
        System.out.println("-------------------");

        for (Integer element : myArrayList2) {
            System.out.println(element);
        }
        System.out.println("-------------------");
        Collections.addAll(myArrayList, "String5", "String6");
        System.out.println("Check copy operation");
        System.out.println("-------------------");
        for (String element : myArrayList3) {
            System.out.println(element);
        }
        System.out.println("-------------------");
        System.out.println("Check addAll operation");
        System.out.println("-------------------");
        for (String element : myArrayList) {
            System.out.println(element);
        }
        System.out.println("-------------------");
    }
}
