package deque;

public interface deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public int size();
    default public boolean isEmpty() {
        if (size() == 0){
            return true;
        }else{
            return false;
        }
    }
    public void printDeque();
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}
