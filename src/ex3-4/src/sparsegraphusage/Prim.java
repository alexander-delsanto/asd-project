package sparsegraphusage;

import priorityqueue.PriorityQueue;
import sparsegraph.*;

import java.io.FileReader;
import java.util.*;
import java.io.BufferedReader;
import java.text.DecimalFormat;

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

    public static void printForest(LinkedList<Edge<String, Double>> forest) {
        forest.sort(Comparator.comparing(Edge::getLabel));
        Double totEdgeWeight = 0.0;
        for(Edge<String,Double> edge : forest) {
            System.out.println(edge.getStart() + "," +  edge.getEnd() + "," + edge.getLabel());
            totEdgeWeight += edge.getLabel();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
        System.out.println("Number of edges: " + forest.size());
        System.out.println("Total edge weight: " + decimalFormat.format(totEdgeWeight / 1000) + " km");
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
            }
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        @SuppressWarnings("unchecked")
        LinkedList<Edge<String,Double>> minimumSpanningForest = (LinkedList<Edge<String, Double>>)minimumSpanningForest(graph);
        printForest(minimumSpanningForest);
        System.out.println("Number of nodes: " + graph.numNodes());
    }
}

