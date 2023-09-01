package priorityqueue;

/**
 * Interface for the implementation of an abstract queue.
 *
 * @param <E> the type of the elements held in the queue.
 */
public interface AbstractQueue<E> {
    public boolean empty();
    public boolean push(E e);
    public boolean contains(E e);
    public E top();
    public void pop();
    public boolean remove(E e);
}
