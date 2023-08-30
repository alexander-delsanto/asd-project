package sparsegraph;

import java.util.*;

public class Graph<V,L> implements AbstractGraph<V,L> {

    private final boolean directed;
    private final boolean labelled;
    HashMap<V, HashMap<V,L>> adjacencyMap;


    public Graph(boolean directed, boolean labelled) {
        this.directed = directed;
        this.labelled = labelled;
        adjacencyMap = new HashMap<>();
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean isLabelled() {
        return labelled;
    }

    @Override
    public boolean addNode(V a) {
        if(containsNode(a))
            return false;
        adjacencyMap.put(a, new HashMap<V,L>());
        return true;
    }
    private HashMap<V,L> getNeighboursMap(V a) {
        return adjacencyMap.get(a);
    }

}
