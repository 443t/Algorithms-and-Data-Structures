package DequesAndRandomizedQueues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int lastIndex;

    
    public RandomizedQueue() {

        items = (Item[]) new Object[2];
        this.lastIndex = 0;
        
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }

    public int size() {
        return lastIndex;
    }


    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < lastIndex; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    private void moveRight() {

        Item[] temp = (Item[]) new Object[items.length];
        for (int i = 0, j = 1; i < lastIndex; i++, j++) {
            temp[j] = items[i];
        }
        items = temp;
        lastIndex++;

    }

    private void moveLeft(int position) {
//        if (items.length / 2 > lastIndex) {
//            resize(items.length / 2);
//        }

        for (int i = position; i < lastIndex-1; i++) {
           items[i] = items[i + 1];

        }
        // lastIndex--;

    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }

        if (isEmpty()) {

            items[0] = item;
            lastIndex++;
            return;
        }

        if (lastIndex == items.length) {
            resize(items.length * 2);
        }

        moveRight();
        items[0] = item;
        
    }
    public Item dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        

        else {
            int i = StdRandom.uniform(lastIndex);

            Item temp = items[i];
            moveLeft(i);
            lastIndex--;
            if (lastIndex > 0 && lastIndex == items.length / 4) {
                resize(items.length / 2);
            }
            return temp;
        }
    }
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty!");
        }
        else {

            return items[StdRandom.uniform(lastIndex)];
        }
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }




    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] random;
        private int current;

        public RandomizedQueueIterator() {
            this.random = new int[lastIndex];
            for (int i = 0; i < lastIndex; i++) {
                random[i] = i;
            }
            StdRandom.shuffle(random);
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current != random.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("There is no more items!");
            }
            return items[random[current++]];
        }

        @Override
        public void remove() {

            throw new UnsupportedOperationException("Remove operator is unsupported!");
        }
    }


    public static void main(String[] args) {
        RandomizedQueue rq = new RandomizedQueue<>();

        rq.enqueue(15);
        rq.dequeue();
        System.out.println(rq.isEmpty());
       
        
    }
}                                                                    