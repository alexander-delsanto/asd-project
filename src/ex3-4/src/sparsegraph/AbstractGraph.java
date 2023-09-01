package sparsegraph;

import java.util.Collection;

public interface AbstractGraph<V,L> {
    public boolean isDirected();
    public boolean isLabelled();
    public boolean addNode(V a);
    public boolean addEdge(V a, V b, L l);
    public boolean containsNode(V a);
    public boolean containsEdge(V a, V b);
    public boolean removeNode(V a);
    public boolean removeEdge(V a, V b);
    public int numNodes();
    public int numEdges();
    public Collection<V> getNodes();
    public Collection<? extends AbstractEdge<V,L>> getEdges();
    public Collection<V> getNeighbours(V a);
    public L getLabel(V a, V b);
}
