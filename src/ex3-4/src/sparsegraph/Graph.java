package sparsegraph;

import java.util.*;

/**
 * A class that represents a Graph with nodes of type V and edges with labels of type L.
 * This graph can be directed or undirected and can have labelled or unlabelled edges.
 *
 * @param <V> the type of the nodes in the graph.
 * @param <L> the type of the labels on the edges in the graph.
 */
public class Graph<V,L> implements AbstractGraph<V,L> {

    private final boolean directed;
    private final boolean labelled;
    HashMap<V, LinkedList<Edge<V,L>>> adjacencyList;


    /**
     * Constructs a Graph with the specified characteristics.
     *
     * @param directed indicates if the graph is directed.
     * @param labelled indicates if the edges of the graph have labels.
     */
    public Graph(boolean directed, boolean labelled) {
        this.directed = directed;
        this.labelled = labelled;
        adjacencyList = new HashMap<>();
    }

    /**
     * Checks if the graph is directed.
     *
     * @return true if the graph is directed, false otherwise.
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * Checks if the graph is labelled.
     *
     * @return true if the graph is labelled, false otherwise.
     */
    @Override
    public boolean isLabelled() {
        return labelled;
    }

    /**
     * Adds a node to the graph if it doesn't exist already.
     *
     * @param a the node to be added.
     * @return true if the node was added, false if the node already exists.
     */
    @Override
    public boolean addNode(V a) {
        if(containsNode(a))
            return false;
        adjacencyList.put(a, new LinkedList<>());
        return true;
    }

    /**
     * Adds an edge between two nodes a and b in the graph if they exist. The edge only added from a to b if the graph is
     * directed, otherwise it is added both ways.
     *
     * @param a the starting node of the edge.
     * @param b the ending node of the edge.
     * @param o the label of the edge, if the graph is unlabelled all the labels will be se to null.
     * @return true if the edge was added, false if the edge already exists.
     */
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

    /**
     * Checks if a node exists in the graph.
     *
     * @param a the node to check.
     * @return true if the node exists, false otherwise.
     */
    @Override
    public boolean containsNode(V a) {
        return adjacencyList.containsKey(a);
    }

    /**
     * Checks if an edge exists in the graph between two nodes.
     *
     * @param a the starting node of the edge.
     * @param b the ending node of the edge.
     * @return true if the edge exists, false otherwise.
     */
    @Override
    public boolean containsEdge(V a, V b) {
        return findEdge(a, b) != null;
    }

    /**
     * Removes a node from the graph if it exists.
     *
     * @param a the node to be removed.
     * @return true if the node was removed, false if the node doesn't exist.
     */
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

    /**
     * Removes an edge between two nodes in the graph.
     *
     * @param a the starting node of the edge.
     * @param b the ending node of the edge.
     * @return true if the edge was removed, false if the edge doesn't exist.
     */
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

    /**
     * Returns the number of nodes in the graph.
     *
     * @return the number of nodes.
     */
    @Override
    public int numNodes() {
        return adjacencyList.size();
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return the number of edges.
     */
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

    /**
     * Returns a collection of all nodes in the graph. In particular, it returns a set of the nodes.
     *
     * @return a collection of the nodes in the graph.
     */
    @Override
    public Collection<V> getNodes() {
        return new HashSet<>(adjacencyList.keySet());
    }

    /**
     * Returns a set of all edges in the graph.
     *
     * @return a set of the edges in the graph.
     */
    @Override
    public HashSet<Edge<V,L>> getEdges() {
        HashSet<Edge<V, L>> edges = new HashSet<>();
        for (List<Edge<V, L>> edgeList : adjacencyList.values()) {
            edges.addAll(edgeList);
        }
        return edges;
    }

    /**
     * Prints all the edges in the graph.
     */
    public void printEdges() {
        HashSet<Edge<V,L>> edges =  getEdges();
        for(Edge<V,L> i : edges) {
            System.out.println(i.getStart().toString() + " to " +  i.getEnd().toString() + " at distance " + i.getLabel().toString());
        }
    }


    /**
     * Returns a collection of neighbours of a given node. In particular, it returns a list of nodes.
     *
     * @param a the node for which neighbours are to be fetched.
     * @return a collection of neighbouring nodes.
     */
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

    /**
     * Returns the label of the edge between two given nodes.
     *
     * @param a the starting node of the edge.
     * @param b the ending node of the edge.
     * @return the label of the edge, or null if the edge doesn't exist.
     */
    @Override
    public L getLabel(V a, V b) {
        Edge<V, L> edge = findEdge(a, b);
        if (edge == null)
            return null;
        return edge.getLabel();
    }

    /**
     * Returns the adjacency list for a given node.
     *
     * @param a the node for which the adjacency list is to be fetched.
     * @return the adjacency list for the node.
     */
    private LinkedList<Edge<V,L>> getNeighboursMap(V a) {
        return adjacencyList.get(a);
    }

    /**
     * Finds and returns the edge between two given nodes if it exists.
     *
     * @param a the starting node of the edge.
     * @param b the ending node of the edge.
     * @return the edge between the nodes, or null if the edge doesn't exist.
     */
    private Edge<V,L> findEdge(V a, V b) {
        if(containsNode(a) && containsNode(b))
            for (Edge<V, L> edge : adjacencyList.get(a))
                if (edge.getEnd().equals(b))
                    return edge;
        return null;
    }
}
