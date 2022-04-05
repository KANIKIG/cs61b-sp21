package deque;

public class LinkedListDeque<T> implements deque<T> {
    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(T i, Node n) {
			item = i;
			next = n;
		}
    }
    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null, null);
		size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(T item) {
		sentinel = new Node(null, null);
		sentinel.next = new Node(item, null);
		size = 1;
        sentinel.next.next = sentinel;
        sentinel.prev = sentinel.next;
	}

    public void addFirst(T item){
        Node firstNode = sentinel.next;
        firstNode.prev = new Node(item, firstNode);
        sentinel.next = firstNode.prev;
        firstNode.prev.prev = sentinel;
		size = size + 1;
    }

    public void addLast(T item){
        Node lastNode = sentinel.prev;
        lastNode.next = new Node(item, sentinel);
        sentinel.prev = lastNode.next;
        lastNode.next.prev = lastNode;
        size = size + 1;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }else{
            return false;
        }
    }

    public void printDeque(){
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            System.out.print(p.item + " ");
        }
    }

    public T removeFirst(){
        if (isEmpty()) {
            return null;
        }
        Node rmNode = sentinel.next;
        T rmItem = rmNode.item;
        sentinel.next = rmNode.next;
        rmNode.next.prev = sentinel;
        rmNode.item = null;
        rmNode.next = null;
        rmNode.prev = null;
        size = size - 1;
        return rmItem;
    }

    public T removeLast(){
        if (isEmpty()) {
            return null;
        }
        Node rmNode = sentinel.prev;
        T rmItem = rmNode.item;
        sentinel.prev = rmNode.prev;
        rmNode.prev.next = sentinel;
        rmNode.item = null;
        rmNode.prev = null;
        rmNode.next = null;
        size = size - 1;
        return rmItem;
    }

    public T get(int index){
        if (index <= 0) {
            return null;
        }
        int nodeInd = 1;
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            if (nodeInd != index){
                nodeInd += 1;
            } else {
                return p.item;
            }
        }
        return null;
    }

    public int size() {
		return size;
	}

    // public Iterator<T> iterator(){

    // }

    // public boolean equals(Object o){

    // }

    private T getRecurHelper(int index, int nodeInd, Node p) {
        if (p.item == null) {
            return null;
        }
        if (nodeInd == index){
            return p.item;
        }
        return getRecurHelper(index, nodeInd + 1, p.next);
    }

    public T getRecursive(int index)  {
        Node p = sentinel.next;
        if (index <= 0) {
            return null;
        }
        int nodeInd = 1;

        return getRecurHelper(index, nodeInd, p);
    }

}
