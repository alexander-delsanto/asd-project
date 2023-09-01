package sparsegraphusage;

import priorityqueue.PriorityQueue;
import sparsegraph.*;

import java.io.FileReader;
import java.util.*;
import java.io.BufferedReader;

public class Prim {

    @SuppressWarnings("unchecked")
    public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(Graph<V, L> graph) {
        LinkedList<Edge<V,L>> minimumSpanningForest = new LinkedList<>();
        HashSet<V> visited = new HashSet<>();
        HashSet<V> allVertices = new HashSet<>(graph.getNodes());
        PriorityQueue<Edge<V,L>> priorityQueue = new PriorityQueue<>(Comparator.comparing(edge -> edge.getLabel().doubleValue()));
        HashSet<Edge<V,L>> graphEdges = new HashSet<>((Collection<Edge<V, L>>) graph.getEdges());

        while(visited.size() != allVertices.size()) {
            LinkedList<Edge<V,L>> minimumSpanningTree = new LinkedList<>();
            V start = allVertices.stream().filter(v -> !visited.contains(v)).findFirst().orElse(null);
            if (start == null) break;
            visited.add(start);

            addAdjacentEdgesToPriorityQueue(priorityQueue, graphEdges, start);

            while (!priorityQueue.empty()) {
                Edge<V,L> currentEdge = priorityQueue.top();
                priorityQueue.pop();
                V source = currentEdge.getStart();
                V dest = currentEdge.getEnd();
                if (visited.contains(source) && !visited.contains(dest)) {
                    minimumSpanningTree.add(currentEdge);
                    visited.add(dest);
                    addAdjacentEdgesToPriorityQueue(priorityQueue, graphEdges, dest);
                } else if (!visited.contains(source) && visited.contains(dest)) {
                    minimumSpanningTree.add(currentEdge);
                    visited.add(source);
                    addAdjacentEdgesToPriorityQueue(priorityQueue, graphEdges, source);
                }
            }
            minimumSpanningForest.addAll(minimumSpanningTree);
        }
        return minimumSpanningForest;
    }

    private static <V,L extends Number> void addAdjacentEdgesToPriorityQueue(PriorityQueue<Edge<V,L>> priorityQueue, HashSet<Edge<V,L>> graphEdges, V vertex) {
        for (Edge<V,L> edge : graphEdges) {
            if (edge.getStart().equals(vertex) || edge.getEnd().equals(vertex)) {
                priorityQueue.push(edge);
            }
        }
    }

    public static void main(String[] args) {
        if(args.length < 1) {
            System.err.println("Usage: java Prim <inputFile>");
            System.exit(1);
        }

        Graph<String, Double> graph = new Graph<>(false, true);
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                graph.addNode(row[0]);
                graph.addNode(row[1]);
                graph.addEdge(row[0], row[1], Double.valueOf(row[2]));
                System.out.println(row[0] + " to " + row[1] + " dist: " + Double.valueOf(row[2]));
            }
            graph.printEdges();
            System.out.println(graph.numNodes());
            System.out.println(graph.numEdges());
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

