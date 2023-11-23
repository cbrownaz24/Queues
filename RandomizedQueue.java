import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] storage; // stores the Items in queue
    private int size; // keeps track of the size of queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        storage = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        // edge case if trying to enqueue null item
        if (item == null) throw new IllegalArgumentException("cannot add null item");

        // if storage array reaches max capacity, double its size
        if (size == storage.length) resize(size * 2);

        // add the item to next available space
        storage[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        // check that queue has items
        if (isEmpty()) throw new NoSuchElementException("empty queue");

        // randomly pick a queue element
        int index = StdRandom.uniformInt(size);
        Item item = storage[index];

        // replace the randomly selected queue element with the last item
        // remove the now copy of last item
        // simulatenously decrements size of queue
        storage[index] = storage[--size];
        storage[size] = null;

        // if the size of queue reaches 25% storage capacity, half the storage capacity
        if (size > 0 && size == storage.length / 4) resize(size * 2);

        // return the orginal randomly selected item
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        // check that queue has items
        if (isEmpty()) throw new NoSuchElementException("empty queue");
        
        int index = StdRandom.uniformInt(size);
        return storage[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // holds methods for organizing how to iterate over RandomizedQueue
    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] copy; // stores a copy of storage array for shuffling
        private int j = 0; // keeps track of location in copy

        // creates a copy of the storage array with EXACT size
        // this copy is then shuffled randomly
        public RandomizedQueueIterator() {
            // instantiate copy array
            copy = (Item[]) new Object[size];

            // copy elements from storage
            for (int i = 0; i < size; i++) {
                copy[i] = storage[i];
            }

            // shuffle the copy
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return j != size;
        }

        // iterate over ONLY existing items in storage array
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[j++];
        }
    }

    // resizes the array to a given capacity
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < size(); i++) {
            copy[i] = storage[i];
        }
        storage = copy;
    }

    // unit testing
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        StdOut.println(queue.size());
        queue.enqueue(175);
        queue.enqueue(362);
        StdOut.println(queue.sample());
        for (Integer i : queue) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        queue.enqueue(64);
        queue.enqueue(94);
        StdOut.println(queue.dequeue());
        queue.enqueue(277);
        queue.enqueue(382);
        for (Integer i : queue) {
            StdOut.print(i + " ");
        }
        StdOut.println(queue.isEmpty());
    }
}
