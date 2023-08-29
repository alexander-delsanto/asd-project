package priorityqueue;

import java.util.*;

/**
 * A priority queue implementation.
 *
 * @param <E> the type of elements held in this priority queue.
 */
public class PriorityQueue<E> implements AbstractQueue<E>{

    private final ArrayList<E> heap;
    private final Comparator<E> comparator;
    private final HashMap<E, Integer> elementsMap;

    /**
     * Constructs a priority queue with the specified comparator.
     *
     * @param comparator the comparator to determine the priority of the elements.
     */
    public PriorityQueue(Comparator<E> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
        this.elementsMap = new HashMap<>();
    }

    /**
     * Checks if the priority queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    @Override
    public boolean empty() {
        return heap.isEmpty();
    }

    /**
     * Adds a new elements to the priority queue if it's not in it already.
     *
     * @param e the element to be added to the priority queue.
     * @return true if the element was successfully added, false otherwise.
     */
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

    /**
     * Checks if a specified element is contained in the priority queue.
     *
     * @param e the element to be checked for containment in the priority queue.
     * @return true if the priority queue contains the specified element, false otherwise.
     */
    @Override
    public boolean contains(E e) {
        return elementsMap.containsKey(e);
    }

    /**
     * Returns the element with the highest priority in the priority queue.
     *
     * @return the element with the highest priority in the priority queue
     */
    @Override
    public E top() {
        if(empty())
            throw new NoSuchElementException("top: priority queue is empty.");
        return heap.get(0);
    }

    /**
     * Removes the element with the highest priority from the priority queue.
     * It then fixes the heap structure so that the heap property is maintained.
     */
    @Override
    public void pop() {
        if(empty())
            throw new NoSuchElementException("pop: priority queue is empty.");

        int lastIndex = getSize() - 1;
        swapAndMapElements(0, lastIndex);
        elementsMap.remove(heap.get(lastIndex));
        heap.remove(lastIndex);
        if(getSize() > 1)
            fixHeapDown(0);
    }

    /**
     * Removes a specified element from the priority queue.
     * It then fixes the heap either downwards or upwards, as needs be, so that the heap property is maintained.
     *
     * @param e the element to be removed from the priority queue.
     * @return true if the element was successfully removed, false otherwise.
     */
    @Override
    public boolean remove(E e) {
        if(!contains(e))
            return false;
        int indexToRemove = elementsMap.get(e);
        int lastIndex = getSize() - 1;
        if(indexToRemove != lastIndex)
            swapAndMapElements(indexToRemove, lastIndex);
        heap.remove(lastIndex);
        elementsMap.remove(e);

        if(indexToRemove < getSize()){
            if(indexToRemove > 0 && compareElements(indexToRemove, getParentIndex(indexToRemove)) < 0) {
                fixHeapUp(indexToRemove);
            } else {
                fixHeapDown(indexToRemove);
            }
        }
        return true;
    }

    /**
     * Swaps an element with a different one, changing its priority.
     *
     * @param oldElement the old element that needs to be replaced.
     * @param newElement the new element that will replace the old one.
     * @return true if the elements were swapped successfully, false otherwise.
     */
    public boolean changePriority(E oldElement, E newElement) {
            if(!remove(oldElement))
                return false;
            return push(newElement);
    }

    /**
     * Fixes the heap property downwards from a given index.
     *
     * @param currentIndex the index from which to start fixing downwards.
     */
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
                swapAndMapElements(smallestElIndex, currentIndex);
                currentIndex = smallestElIndex;
            } else break;
        }
    }

    /**
     * Fixes the heap property upwards from a given index.
     *
     * @param currentIndex the index from which to start fixing upwards.
     */
    private void fixHeapUp(int currentIndex){
        while(currentIndex > 0){
            int parentIndex = getParentIndex(currentIndex);
            if(compareElements(currentIndex, parentIndex) < 0){
                    swapAndMapElements(currentIndex, parentIndex);
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

    /**
     * Returns the number of elements in this priority queue.
     *
     * @return the size of the priority queue.
     */
    public int getSize() {
        return heap.size();
    }

    /**
     * Swaps two elements in the heap and updates their indices in the elementsMap HashMap.
     *
     * @param indexElement1 the index of the first element to be swapped.
     * @param indexElement2 the index of the second element to be swapped.
     */
    private void swapAndMapElements(int indexElement1, int indexElement2) {
        Collections.swap(heap, indexElement1, indexElement2);
        elementsMap.put(heap.get(indexElement1), indexElement1);
        elementsMap.put(heap.get(indexElement2), indexElement2);
    }

    /**
     * Compares two elements in the heap by their indices.
     *
     * @param indexElement1 the index of the first element.
     * @param indexElement2 the index of the second element.
     * @return a negative integer, zero, or a positive integer as the first element is less than, equal to, or greater than the second.
     */
    private int compareElements(int indexElement1, int indexElement2) {
        return comparator.compare(heap.get(indexElement1), heap.get(indexElement2));
    }

    /**
     * Returns the index of the parent of a given element.
     *
     * @param currentIndex the index of the element.
     * @return the index of the parent.
     */
    private int getParentIndex(int currentIndex){
        return (currentIndex - 1) / 2;
    }

    /**
     * Returns the index of the left child of a given element.
     *
     * @param parentIndex the index of the parent element.
     * @return the index of the left child.
     */
    private int getLeftChildIndex(int parentIndex){
        return parentIndex * 2 + 1;
    }

    /**
     * Returns the index of the right child of a given element.
     *
     * @param parentIndex the index of the parent element.
     * @return the index of the right child.
     */
    private int getRightChildIndex(int parentIndex){
        return parentIndex * 2 + 2;
    }

    /**
     * Compares two elements and returns the index of the smallest.
     *
     * @param indexElement1 the index of the first element.
     * @param indexElement2 the index of the second element.
     * @return the index of the smallest element.
     */
    private int getSmallestElementIndex(int indexElement1, int indexElement2){
        return (compareElements(indexElement1, indexElement2) < 0) ? indexElement1 : indexElement2;
    }
}
