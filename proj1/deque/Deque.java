package deque;

public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    int size();
    default public boolean isEmpty() {
        if (size() == 0) {
            return true;
        } else {
            return false;
        }
    }
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
