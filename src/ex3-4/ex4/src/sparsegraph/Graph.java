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

    @Override
    public boolean addEdge(V a, V b, L o) {
        if (containsEdge(a, b))
            return false;
        if (directed) {
            if (labelled)
                getNeighboursMap(a).put(b, o);
            else
                getNeighboursMap(a).put(b, null);
        } else {
            if (labelled) {
                getNeighboursMap(a).put(b, o);
                getNeighboursMap(b).put(a, o);
            } else {
                getNeighboursMap(a).put(b, null);
                getNeighboursMap(b).put(a, null);
            }
        }
        return true;
    }

    @Override
    public boolean containsNode(V a) {
        return adjacencyMap.containsKey(a);
    }

    @Override
    public boolean containsEdge(V a, V b) {
        if(containsNode(a) && containsNode(b)) {
            if(directed)
                return getNeighboursMap(a).containsKey(b);
            else
                return getNeighboursMap(a).containsKey(b) || getNeighboursMap(b).containsKey(a);
        }
        return false;
    }

    @Override
    public boolean removeNode(V a) {
        if(!containsNode(a))
            return false;
        for(V node : getNodes()) {
            removeEdge(node, a);
        }
        adjacencyMap.remove(a);
        return true;
    }

    private HashMap<V,L> getNeighboursMap(V a) {
        return adjacencyMap.get(a);
    }

}
