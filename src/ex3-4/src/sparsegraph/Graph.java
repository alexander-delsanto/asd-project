package sparsegraph;

import java.util.*;

public class Graph<V,L> implements AbstractGraph<V,L> {

    private final boolean directed;
    private final boolean labelled;
    HashMap<V, LinkedList<Edge<V,L>>> adjacencyList;


    public Graph(boolean directed, boolean labelled) {
        this.directed = directed;
        this.labelled = labelled;
        adjacencyList = new HashMap<>();
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
        adjacencyList.put(a, new LinkedList<>());
        return true;
    }

    @Override
    public boolean addEdge(V a, V b, L o) {
        if (containsEdge(a, b))
            return false;
        if (directed) {
            if (labelled)
                getNeighboursMap(a).add(new Edge<>(a, b, o));
            else
                getNeighboursMap(a).add(new Edge<>(a, b, null));
        } else {
            if (labelled) {
                getNeighboursMap(a).add(new Edge<>(a, b, o));
                getNeighboursMap(b).add(new Edge<>(b, a, o));
            } else {
                getNeighboursMap(a).add(new Edge<>(a, b, null));
                getNeighboursMap(b).add(new Edge<>(b, a, null));
            }
        }
        return true;
    }

    @Override
    public boolean containsNode(V a) {
        return adjacencyList.containsKey(a);
    }

    @Override
    public boolean containsEdge(V a, V b) {
        return findEdge(a, b) != null;
    }

    @Override
    public boolean removeNode(V a) {
        if(!containsNode(a))
            return false;
        for(V node : getNodes()) {
            removeEdge(node, a);
        }
        adjacencyList.remove(a);
        return true;
    }

    @Override
    public boolean removeEdge(V a, V b) {
        if(containsEdge(a, b)) {
            Edge<V,L> edge = findEdge(a, b);
            if(directed){
                getNeighboursMap(a).remove(edge);
            }else {
                Edge<V,L> reverseEdge = findEdge(b, a);
                getNeighboursMap(a).remove(edge);
                getNeighboursMap(b).remove(reverseEdge);

            }
            return true;
        }
        return false;
    }

    @Override
    public int numNodes() {
        return adjacencyList.size();
    }

    @Override
    public int numEdges() {
        int numEdges = 0;
        for(V key : adjacencyList.keySet()) {
            numEdges += getNeighbours(key).size();
        }
        if(!directed)
            numEdges /= 2;
        return numEdges;
    }

    @Override
    public Collection<V> getNodes() {
        return new HashSet<>(adjacencyList.keySet());
    }

    @Override
    public HashSet<Edge<V,L>> getEdges() {
        HashSet<Edge<V, L>> edges = new HashSet<>();
        for (List<Edge<V, L>> edgeList : adjacencyList.values()) {
            edges.addAll(edgeList);
        }
        return edges;
    }

    public void printEdges() {
        HashSet<Edge<V,L>> edges =  getEdges();
        for(Edge<V,L> i : edges) {
            System.out.println(i.getStart().toString() + " to " +  i.getEnd().toString() + " at distance " + i.getLabel().toString());
        }
    }


    @Override
    public Collection<V> getNeighbours(V a) {
        if (!containsNode(a))
            return new LinkedList<>();
        LinkedList<V> neighbours = new LinkedList<>();
        for (Edge<V, L> edge : adjacencyList.get(a)) {
            neighbours.add(edge.getEnd());
        }
        return neighbours;
    }

    @Override
    public L getLabel(V a, V b) {
        Edge<V, L> edge = findEdge(a, b);
        if (edge == null)
            return null;
        return edge.getLabel();
    }

    private LinkedList<Edge<V,L>> getNeighboursMap(V a) {
        return adjacencyList.get(a);
    }

    private Edge<V,L> findEdge(V a, V b) {
        if(containsNode(a) && containsNode(b))
            for (Edge<V, L> edge : adjacencyList.get(a))
                if (edge.getEnd().equals(b))
                    return edge;
        return null;
    }
}
