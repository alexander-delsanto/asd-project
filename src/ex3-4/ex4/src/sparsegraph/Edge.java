package sparsegraph;

public class Edge<V,L> implements AbstractEdge<V,L>{

    private final V start;
    private final V end;
    private final L label;

    public Edge(V start, V end, L label) {
        this.start = start;
        this.end = end;
        this.label = label;
    }

    @Override
    public V getStart() {
        return start;
    }

    @Override
    public V getEnd() {
        return end;
    }

    @Override
    public L getLabel(){
        return label;
    }


}
