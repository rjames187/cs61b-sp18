public class LinkedListDeque<T> {

    private class Node<T> {
        public T value;
        public Node<T> prev = null;
        public Node<T> next = null;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node sentinel = new Node<T>(null);
    private int length = 0;

    public LinkedListDeque() {
        sentinel.next = sentinel; // get first element
        sentinel.prev = sentinel; // get last element
    }

    public void addFirst(T item) {
        Node newFirst = new Node(item);
        Node first = sentinel.next;

        newFirst.next = first;
        newFirst.prev = sentinel;

        first.prev = newFirst;
        sentinel.next = newFirst;

        length++;
    }

    public void addLast(T item) {
        Node newLast = new Node(item);
        Node last = sentinel.prev;

        newLast.next = sentinel;
        newLast.prev = last;

        last.next = newLast;
        sentinel.prev = newLast;

        length++;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public void printDeque() {
        Node pointer = sentinel.next;
        String res = "";

        while (pointer != sentinel) {
            res += pointer.value + " ";
            pointer = pointer.next;
        }

        System.out.println(res);
    }

    public T removeFirst() {
        if (length == 0) {
            return null;
        }

        Node<T> first = sentinel.next;
        Node newFirst = first.next;

        sentinel.next = newFirst;
        newFirst.prev = sentinel;

        length--;
        return first.value;
    }

    public T removeLast() {
        if (length == 0) {
            return null;
        }

        Node<T> last = sentinel.prev;
        Node newLast = last.prev;

        sentinel.prev = newLast;
        newLast.next = sentinel;

        length--;
        return last.value;
    }

    public T get(int index) {
        Node<T> pointer = sentinel.next;
        int i = 0;

        while (pointer != sentinel && i < index) {
            pointer = pointer.next;
            i++;
        }

        if (pointer == sentinel) {
            return null;
        } else {
            return pointer.value;
        }
    }

    public T getRecursive(int index) {
        return (T) recursiveHelper(0, index, sentinel.next);
    }

    private T recursiveHelper(int curIndex, int targetIndex, Node<T> node) {
        if (node == sentinel) {
            return null;
        }
        if (curIndex == targetIndex) {
            return node.value;
        }
        return recursiveHelper(curIndex + 1, targetIndex, node.next);
    }
}
