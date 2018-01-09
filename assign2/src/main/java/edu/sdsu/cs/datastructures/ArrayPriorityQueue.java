package edu.sdsu.cs.datastructures;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * An array-based priority queue implementation.
 * <p>
 * A first-in-first-out (FIFO) data container, the basic queue represents
 * a line arranged in the order the items arrive. This queue differs from
 * the standard implementation in that it requires all enclosed objects
 * support comparison with like-type objects (Comparable), and it
 * automatically arranges the items in the queue based upon their ranking.
 * Lower items appear closer to the queue's front than larger items.
 * </p>
 * Due to their nature, queues must support rapid enqueue (offer) and
 * dequeue (poll) operations. That is, getting in and out of line must
 * occur rapidly. In this priority queue, the poll operation shall occur
 * in constant time, but because the queue requires arrangement, adding
 * to the circular-array based priority queue may produce slower timings,
 * for the data structure must use binary-search to locate where the new
 * item belongs in relation to everything else, and it must then shift
 * the existing contents over to make room for the addition.
 * </p>
 * <p>
 * In the likely event two objects share the same priority, new items
 * added to the queue shall appear after existing entries. When a standard
 * priority customer enters the line, it should naturally go behind the
 * last standard customer currently in line.
 * </p>
 * <p>
 * Although technically a Collection object, the queue does not support
 * most of the standard Collection operations. That is, one may not add
 * or remove from the Queue using the collection methods, for it breaks
 * the abstraction. One may, however, use a Queue object as an input
 * parameter to any other Collection object constructor.
 * </p>
 *
 * @param <E> Object to store in container. It must support comparisons with
 *            other objects of the same type.
 * @author STUDENT NAME, csscXXXX@edoras.sdsu.edu
 */
public final class ArrayPriorityQueue<E extends Comparable<? extends E>>
        extends AbstractCollection<E> implements Queue<E> {

    private List<E> queue = new CirArrayList<>();

    /**
     * Builds a new, empty priority queue.
     */
    public ArrayPriorityQueue() {

    }

    /**
     * Builds a new priority queue containing every item in the provided
     * collection.
     *
     * @param col the Collection containing the objects to add to this queue.
     */
    public ArrayPriorityQueue(Collection<? extends E> col) {

    }

    /**
     * Returns an iterator over the elements in this collection. The iterator
     * produces results in the order in which they would appear were one to
     * successively poll the queue.
     *
     * @return an Iterator over the elements in this queue.
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Reports the number of items in this queue.
     *
     * @return the number of items in this queue.
     * @implNote it is INCORRECT to track this as a field in this class.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions. When using a
     * capacity-restricted queue, this method is generally preferable to add
     * (E), which can fail to insert an element only by throwing an exception.
     *
     * @param e the element to add
     * @return true if element added to queue, false otherwise
     */
    @Override
    public boolean offer(E e) {
        return false;
    }

    /**
     * Retrieves and removes the head of this queue. This method differs from
     * poll only in that it throws an exception if this queue is empty.
     *
     * @return the queue's head
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    @Override
    public E remove() {
        return null;
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this
     * queue is empty.
     *
     * @return this queue's head or null if this queue is empty.
     */
    @Override
    public E poll() {
        return null;
    }

    /**
     * Retrieves, but does not remove, the head of this queue. This method
     * differs from peek only in that it throws an exception if this queue is
     * empty.
     *
     * @return the head of this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    @Override
    public E element() {
        return null;
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns
     * null if this queue is empty.
     *
     * @return the head of this queue or null if this queue is empty
     */
    @Override
    public E peek() {
        return null;
    }
}
