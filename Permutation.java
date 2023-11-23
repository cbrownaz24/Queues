import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]); // number of dequeued items
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

        // enqueueing all String elements of the file
        while (!StdIn.isEmpty())
            randomizedQueue.enqueue(StdIn.readString());

        // print k amount of randomly dequeued items
        for (int i = 0; i < k; i++)
            StdOut.println(randomizedQueue.dequeue());
    }
}
