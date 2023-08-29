package priorityqueue;

public interface AbstractQueue<E> {
    public boolean empty();
    public boolean push(E e);
    public boolean contains(E e);
    public E top();
    public void pop();
    public boolean remove(E e);
}
