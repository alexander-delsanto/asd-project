package sparsegraph;

public interface AbstractEdge<V,L> {
    public V getStart();
    public V getEnd();
    public L getLabel();

}
