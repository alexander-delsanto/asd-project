package sparsegraph;

/**
 * Represents an edge in a graph. The edge has a starting node, an ending node,
 * and an optional label. The types of the nodes and labels are defined by the
 * generic parameters V and L, respectively.
 *
 * @param <V> the type of the nodes in the edge.
 * @param <L> the type of the label on the edge.
 */
public class Edge<V,L> implements AbstractEdge<V,L>{

    private final V start;
    private final V end;
    private final L label;

    /**
     * Constructs an Edge with the specified starting node, ending node, and label.
     *
     * @param start the starting node of the edge.
     * @param end the ending node of the edge.
     * @param label the label of the edge.
     */
    public Edge(V start, V end, L label) {
        this.start = start;
        this.end = end;
        this.label = label;
    }

    /**
     * Returns the starting node of the edge.
     *
     * @return the starting node.
     */
    @Override
    public V getStart() {
        return start;
    }

    /**
     * Returns the ending node of the edge.
     *
     * @return the ending node.
     */
    @Override
    public V getEnd() {
        return end;
    }

    /**
     * Returns the label of the edge.
     *
     * @return the label.
     */
    @Override
    public L getLabel(){
        return label;
    }


}
