package priorityqueue;

import java.util.*;

public class PriorityQueue<E> implements AbstractQueue<E>{

    private final ArrayList<E> heap;
    private final Comparator<E> comparator;
    private final HashSet<E> elementsSet;

    public PriorityQueue(Comparator<E> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
        this.elementsSet = new HashSet<>();
    }

    @Override
    public boolean empty() {
        return heap.isEmpty();
    }

    @Override
    public boolean push(E e) {
        if(elementsSet.contains(e))
            return false;
        heap.add(e);
        elementsSet.add(e);
        int currentIndex = getSize() - 1;
        if(compareElements(currentIndex, getParentIndex(currentIndex)) < 0) {
            while (currentIndex > 0) {
                int parentIndex = getParentIndex(currentIndex);
                if (compareElements(parentIndex, currentIndex) <= 0) {
                    break;
                }
                Collections.swap(heap, currentIndex, parentIndex);
                currentIndex = parentIndex;
            }
        }
        return true;
    }

    @Override
    public boolean contains(E e) {
        return elementsSet.contains(e);
    }

    @Override
    public E top() {
        if(empty())
            throw new NoSuchElementException("top: priority queue is empty.");
        return heap.get(0);
    }

    public int getSize() {
        return heap.size();
    }

    private int compareElements(int indexElement1, int indexElement2){
        return comparator.compare(heap.get(indexElement1), heap.get(indexElement2));
    }

    private int getParentIndex(int currentIndex){
        return (currentIndex - 1) / 2;
    }
