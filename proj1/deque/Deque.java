package deque;

public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
    void printDeque();
    T removeFirst();
    T removeLast();
    T get(int index);
}
