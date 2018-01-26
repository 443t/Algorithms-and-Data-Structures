package DequesAndRandomizedQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;

        public Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;

        }

    }


    public Deque() {

        n = 0;
        first = null;
     //   first = last;

    }


    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }

        if (!isEmpty()) {
            Node oldfirst = first;
            first = new Node(item, oldfirst, null);
            oldfirst.prev = first;

        } else {
            first = new Node(item, null, null);
            last = first;

        }
        n++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }

        if (!isEmpty()) {
            Node newLast = new Node(item, null, last);
            last.next = newLast;
            last = newLast;
        } else {
            last = new Node(item, null, null);
            first = last;
        }

        n++;
    }

    public Item removeFirst() {


        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        } else if (size() == 1) {
            Item item = first.item;
            first = null;

            n--;
            return item;
        }
        else {
            Item item = first.item;
            first = first.next;
            first.prev = null;
            n--;
            return item;
        }
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }

        Item item = last.item;
        if (size() == 1) {
            last = null;
            first = null;
        } else {
            last = last.prev;
        }
        n--;
        return item;
    }


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current.next != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    public static void main(String[] args) {
        Deque<Integer> m = new Deque<>();


        m.removeFirst();
        m.removeLast();


        System.out.println(m.isEmpty());


    }
}