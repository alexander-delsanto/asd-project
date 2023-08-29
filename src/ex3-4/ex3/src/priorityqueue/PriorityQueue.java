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
        if(contains(e))
            return false;
        heap.add(e);
        int currentIndex = getSize() - 1;
        elementsMap.put(e, currentIndex);
        if(compareElements(currentIndex, getParentIndex(currentIndex)) < 0)
            fixHeapUp(currentIndex);
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

    @Override
    public void pop() {
        if(empty())
            throw new NoSuchElementException("pop: priority queue is empty.");

        elementsSet.remove(heap.get(0));
        Collections.swap(heap, 0, getSize() - 1);
        heap.remove(getSize() - 1);
        fixHeapDown(0);
    }

    @Override
    public boolean remove(E e) {
        if(!elementsSet.contains(e))
            return false;
        int indexToRemove = heap.indexOf(e);
        int lastIndex = getSize() - 1;
        if(indexToRemove != lastIndex)
            Collections.swap(heap, indexToRemove, lastIndex);
        heap.remove(lastIndex);
        elementsSet.remove(e);

        if(indexToRemove < getSize()){
            fixHeapUp(indexToRemove);
            fixHeapDown(indexToRemove);
        }
        return true;
    }

    public boolean changePriority(E oldElement, E newElement) {
            if(!remove(oldElement))
                return false;
            return push(newElement);
    }

    private void fixHeapDown(int currentIndex){
        while(true){
            int smallestElIndex = currentIndex;
            int leftChildIndex = getLeftChildIndex(currentIndex);
            int rightChildIndex = getRightChildIndex(currentIndex);
            if(rightChildIndex < getSize()){
                smallestElIndex = getSmallestElementIndex(leftChildIndex, rightChildIndex);
            }else if(leftChildIndex < getSize()){
                smallestElIndex = getSmallestElementIndex(currentIndex, leftChildIndex);
            }else break;
            if(compareElements(smallestElIndex, currentIndex) < 0){
                Collections.swap(heap, smallestElIndex, currentIndex);
                currentIndex = smallestElIndex;
            } else break;
        }
    }

    private void fixHeapUp(int currentIndex){
        while(currentIndex > 0){
            int parentIndex = getParentIndex(currentIndex);
            if(compareElements(currentIndex, parentIndex) < 0){
                    Collections.swap(heap, currentIndex, parentIndex);
                    currentIndex = parentIndex;
            } else break;
        }
    }

    @Override
    public String toString() {
        StringBuilder heapString = new StringBuilder();
        for (E e : heap) {
            heapString.append(e).append(" ");
        }
        return heapString.toString();
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

    private int getLeftChildIndex(int parentIndex){
        return parentIndex * 2 + 1;
    }

    private int getRightChildIndex(int parentIndex){
        return parentIndex * 2 + 2;
    }

    private int getSmallestElementIndex(int indexElement1, int indexElement2){
        return (compareElements(indexElement1, indexElement2) < 0) ? indexElement1 : indexElement2;
    }
}
