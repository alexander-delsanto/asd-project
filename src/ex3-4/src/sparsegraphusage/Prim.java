package sparsegraphusage;

import priorityqueue.PriorityQueue;
import sparsegraph.*;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;

public class Prim {

    public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(Graph<V, L> graph) {
        LinkedList<Edge<V,L>> minimumSpanningForest = new LinkedList<>();
        HashSet<V> visited = new HashSet<>();
        HashSet<V> allVertices = new HashSet<>(graph.getNodes());
        PriorityQueue<Edge<V,L>> priorityQueue = new PriorityQueue<>(Comparator.comparing(edge -> edge.getLabel().doubleValue()));

        while(visited.size() != allVertices.size()) { // use a for loop instead
            LinkedList<Edge<V,L>> minimumSpanningTree = new LinkedList<>();
            V start = allVertices.stream().filter(v -> !visited.contains(v)).findFirst().orElse(null);
            if (start == null) break;

            visited.add(start);
            addAdjacentToPriority(graph, priorityQueue, start);
            while (!priorityQueue.empty()) {

                Edge<V,L> currentEdge = priorityQueue.top();
                priorityQueue.pop();

                V node = currentEdge.getEnd();
                if (visited.contains(node))
                    continue;
                minimumSpanningTree.add(currentEdge);

                visited.add(node);
                allVertices.remove(node);
                addAdjacentToPriority(graph, priorityQueue, node);
            }
            minimumSpanningForest.addAll(minimumSpanningTree);
        }

        return minimumSpanningForest;
    }

    private static <V,L extends Number> void addAdjacentToPriority(Graph<V, L> graph, PriorityQueue<Edge<V,L>> priorityQueue, V vertex){
        for(V next : graph.getNeighbours(vertex)){
            L label = graph.getLabel (vertex, next);
            priorityQueue.push(new Edge<>(vertex, next, label));
        }
    }

    public static void printForestToFile(LinkedList<Edge<String, Double>> forest, String fileName) {
        forest.sort(Comparator.comparing(Edge::getLabel));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Double totEdgeWeight = 0.0;

            for (Edge<String, Double> edge : forest) {
                writer.write(edge.getStart() + "," + edge.getEnd() + "," + edge.getLabel());
                writer.newLine();
                totEdgeWeight += edge.getLabel();
            }

            DecimalFormat decimalFormat = new DecimalFormat("#.###");
            decimalFormat.setGroupingUsed(true);
            decimalFormat.setGroupingSize(3);

            writer.write("Number of edges: " + forest.size());
            writer.newLine();
            writer.write("Total edge weight: " + decimalFormat.format(totEdgeWeight / 1000) + " km");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
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
            System.err.println("Error reading file: " + e.getMessage());
        }
        @SuppressWarnings("unchecked")
        LinkedList<Edge<String,Double>> minimumSpanningForest = (LinkedList<Edge<String, Double>>)minimumSpanningForest(graph);
        printForest(minimumSpanningForest);
        System.out.println("Number of nodes: " + graph.numNodes());
    }
}

