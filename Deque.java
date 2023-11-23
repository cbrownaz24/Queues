import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first; // keeping track of front of deque
    private Node last; // keeping track of front and back of deque
    private int size; // keeping track of size of deque

    private class Node {
        private Node previous; // reference to Node in front of current Node
        private Node next; // reference to Node after current Node
        private Item item; // what the Node contains
    }

    // constructor for Deque
    // implements a doubly linked list
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items in deque
    public int size() {
        return size;
    }

    // add an item to the front of deque
    public void addFirst(Item item) {
        // check if input is null
        if (item == null) throw new IllegalArgumentException("cannot add null item");

        // edge case where user is adding element to empty list
        if (first == null) {
            // initiate a new Node
            first = new Node();

            // update the doubly linked list to contain first item
            // map first and last onto the same Node
            first.item = item;
            last = first;
        }
        else {
            // if not the first item, make the current first Node the second Node
            // add the new item to the new first Node
            Node current = first;
            first = new Node();
            current.previous = first;
            first.next = current;
            first.previous = null;
            first.item = item;
        }

        size++; // increase size count of list
    }

    // add an item to the end of deque
    public void addLast(Item item) {
        // check if input is null
        if (item == null) throw new IllegalArgumentException("cannot add null item");

        // edge case where user is adding element to empty list
        if (last == null) {
            // initiate a new Node
            last = new Node();

            // update the doubly linked list to contain last item
            // map last and first onto the same Node
            last.item = item;
            first = last;
        }
        else {
            // if not the first item, make the current last Node the penultimate Node
            // add the new item to the new last Node
            Node current = last;
            last = new Node();
            current.next = last;
            last.previous = current;
            last.next = null;
            last.item = item;
        }

        size++; // increase the size count of list
    }

    // remove and return item at the front of deque
    public Item removeFirst() {
        // throw NoSuchElementException if the deque is empty
        if (isEmpty()) {
            throw new NoSuchElementException("empty deque");
        }

        // if removing the last item, set first and last nodes to null
        // decrement size count
        else if (size() == 1) {
            Item item = first.item;
            first = null;
            last = null;
            size--;
            return item;
        }

        // if not removing the last item, pull the original first Node's item
        // make the new first node be the original second node
        // erase the orignal first node as a reference
        // return the original first node's item value
        else {
            Node returnedItem = first;
            first = first.next;
            first.previous = null;
            size--;
            return returnedItem.item;
        }
    }

    // remove and return item at the end of deque
    public Item removeLast() {
        // throw NoSuchElementException if the deque is empty
        if (isEmpty()) {
            throw new NoSuchElementException("empty deque");
        }

        // if removing the last item, set first and last nodes to null
        // decrement size count
        else if (size() == 1) {
            Item item = last.item;
            first = null;
            last = null;
            size--;
            return item;
        }

        // if not removing the last item, pull the original last Node's item
        // make the new last node be the original penultimate node
        // erase the orignal last node as a reference
        // return the original last node's item value
        else {
            Node returnedItem = last;
            last = last.previous;
            last.next = null;
            size--;
            return returnedItem.item;
        }
    }

    // return an independent iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // outlines methods for determining how to iterate through deque
    private class DequeIterator implements Iterator<Item> {
        private Node current = first; // current Node for each loop in iteration

        public boolean hasNext() {
            // StdOut.println("hasNext()"); // TESTING
            return (current != null);
        }

        // iterate one-by-one linearly until the end of deque
        public Item next() {
            // check if at end of queue
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;

            // if the queue only had one item, set the next Node to null
            // this is necessary with how a singular item is represented
            if (size() == 1) {
                current = null;
            }
            else {
                current = current.next;
            }

            return item;
        }
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        deque.addFirst(2);
        StdOut.println("Size: " + deque.size());
        StdOut.println("isEmpty()? " + deque.isEmpty());
        StdOut.println(deque.removeLast());
        deque.addLast(1);
        deque.addFirst(2);
        StdOut.println(deque.removeFirst());

        for (Integer i : deque) {
            StdOut.println("Item: " + i);
        }
    }
}
