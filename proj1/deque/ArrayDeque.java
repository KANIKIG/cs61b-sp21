package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int ind = 0;
        for (int i = 0; i < size; i += 1) {
            ind = arrayInd(i);
            a[capacity / 4 + i] = items[ind];
        }
        items = a;
        nextFirst = capacity / 4 - 1;
        nextLast = nextFirst + size + 1;
    }

    private int arrayInd(int ind) {
        if (nextFirst + 1 + ind >= items.length) {
            return nextFirst + 1 + ind - items.length;
        } else {
            return nextFirst + 1 + ind;
        }
    }

    public void addFirst(T item) {
        if (size == items.length - 2) {
            resize((int) (items.length * 2));
        }

        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size = size + 1;
    }

    public void addLast(T item) {
        if (size == items.length - 2) {
            resize((int) (items.length * 2));
        }

        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size = size + 1;
    }

    private T getFirst() {
        int ind = arrayInd(0);
        return items[ind];
    }

    private T getLast() {
        int ind = arrayInd(size - 1);
        return items[ind];
    }

    public T get(int i) {
        int ind =  arrayInd(i);
        return items[ind];
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 8)) {
            resize(items.length / 2);
        }
        T item = getFirst();
        int ind = arrayInd(0);
        items[ind] = null;
        size = size - 1;
        nextFirst = ind;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 8)) {
            resize(items.length / 2);       
        }
        T item = getLast();
        int ind = arrayInd(size - 1);
        items[ind] = null;
        size = size - 1;
        nextLast = ind;
        return item;
    }

    public void printDeque() {
        for (T i : this) {
            System.out.print(i + " ");
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        private ArrayDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T item = get(wizPos);
            wizPos += 1;
            return item;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayDeque<T> oa = (ArrayDeque<T>) o;
        if (oa.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (oa.get(i) != this.get(i)) {
                return false;
            }
        }
        return true;
    }

    private void printArray() {
        for (int i = 0; i < items.length; i += 1) {
            System.out.print(items[i] + " ");
        }
    }

    private static void main(String[] args) {
        int n = 99;

        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            ad1.addLast(i);
        }

        ArrayDeque<Integer> ad2 = new ArrayDeque<>();
        for (int i = n; i >= 0; i--) {
            ad2.addFirst(i);
        }

        ad1.printDeque();

        System.out.println(ad1.equals(ad2));
    }
}
