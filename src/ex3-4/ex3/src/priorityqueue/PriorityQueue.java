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
