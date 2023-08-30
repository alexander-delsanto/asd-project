package sparsegraphusage;

import sparsegraph.*;
import java.util.*;

public class Prim {
    public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(Graph<V, L> graph) {
        // calcola la minima foresta ricoprente con l'algoritmo di Prim
        // restituisce la collezione degli archi che formano la foresta
    }
    public static void main(String[] args) {
        // leggi i dati CSV del grafo dal percorso in args[1]
        // calcola la minima foresta ricoprente con minimumSpanningForest
        // scrivi su standard output solo la descrizione della foresta calcolata come CSV con formato analogo a quello in input
        // su standard error si possono scrivere ulteriori informazioni, come il numero di nodi e archi nella foresta calcolata,
        // o il peso totale della foresta
    }
}

