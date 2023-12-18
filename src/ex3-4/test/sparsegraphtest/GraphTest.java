package sparsegraphtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import sparsegraph.*;



class GraphTest {
    Graph<Integer, Integer> graph;
    Graph<Integer, Integer> graph2;
    Graph<Integer, Integer> graph3;
    Graph<Integer, Integer> graph4;

    @BeforeEach
    void setUp() {
        graph = new Graph<>(false, false);
        graph2 = new Graph<>(false, true);
        graph3 = new Graph<>(true, false);
        graph4 = new Graph<>(true, true);
    }

    @Test
    void testAddNode() {
        assertTrue(graph.addNode(1));
        assertTrue(graph.addNode(2));
        assertTrue(graph.addNode(3));
        assertTrue(graph.addNode(4));
        assertFalse(graph.addNode(1));
        assertFalse(graph.addNode(2));
        assertFalse(graph.addNode(3));
        assertFalse(graph.addNode(4));

        // graph2 tests
        assertTrue(graph2.addNode(1));
        assertTrue(graph2.addNode(2));
        assertTrue(graph2.addNode(3));
        assertTrue(graph2.addNode(4));
        assertFalse(graph2.addNode(1));
        assertFalse(graph2.addNode(2));
        assertFalse(graph2.addNode(3));
        assertFalse(graph2.addNode(4));

        // graph3 tests
        assertTrue(graph3.addNode(1));
        assertTrue(graph3.addNode(2));
        assertTrue(graph3.addNode(3));
        assertTrue(graph3.addNode(4));
        assertFalse(graph3.addNode(1));
        assertFalse(graph3.addNode(2));
        assertFalse(graph3.addNode(3));
        assertFalse(graph3.addNode(4));

        // graph4 tests
        assertTrue(graph4.addNode(1));
        assertTrue(graph4.addNode(2));
        assertTrue(graph4.addNode(3));
        assertTrue(graph4.addNode(4));
        assertFalse(graph4.addNode(1));
        assertFalse(graph4.addNode(2));
        assertFalse(graph4.addNode(3));
        assertFalse(graph4.addNode(4));
    }

    @Test
    void testRemoveNode() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        assertTrue(graph.removeNode(1));
        assertTrue(graph.removeNode(2));
        assertTrue(graph.removeNode(3));
        assertTrue(graph.removeNode(4));
        assertFalse(graph.removeNode(1));
        assertFalse(graph.removeNode(2));
        assertFalse(graph.removeNode(3));
        assertFalse(graph.removeNode(4));

        //graph2 tests
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);
        graph2.addNode(4);
        assertTrue(graph2.removeNode(1));
        assertTrue(graph2.removeNode(2));
        assertTrue(graph2.removeNode(3));
        assertTrue(graph2.removeNode(4));
        assertFalse(graph2.removeNode(1));
        assertFalse(graph2.removeNode(2));
        assertFalse(graph2.removeNode(3));
        assertFalse(graph2.removeNode(4));

        //graph3 tests
        graph3.addNode(1);
        graph3.addNode(2);
        graph3.addNode(3);
        graph3.addNode(4);
        assertTrue(graph3.removeNode(1));
        assertTrue(graph3.removeNode(2));
        assertTrue(graph3.removeNode(3));
        assertTrue(graph3.removeNode(4));
        assertFalse(graph3.removeNode(1));
        assertFalse(graph3.removeNode(2));
        assertFalse(graph3.removeNode(3));
        assertFalse(graph3.removeNode(4));

        //graph4 tests
        graph4.addNode(1);
        graph4.addNode(2);
        graph4.addNode(3);
        graph4.addNode(4);
        assertTrue(graph4.removeNode(1));
        assertTrue(graph4.removeNode(2));
        assertTrue(graph4.removeNode(3));
        assertTrue(graph4.removeNode(4));
        assertFalse(graph4.removeNode(1));
        assertFalse(graph4.removeNode(2));
        assertFalse(graph4.removeNode(3));
        assertFalse(graph4.removeNode(4));
    }

    @Test
    void testRemoveEdge() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);
        graph.addNode(7);
        graph.addNode(8);
        graph.addNode(9);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(1, 5, 1);
        graph.addEdge(1, 6, 1);
        graph.addEdge(1, 7, 1);
        graph.addEdge(1, 8, 1);
        graph.addEdge(1, 9, 1);
        assertTrue(graph.removeEdge(1, 2));
        assertTrue(graph.removeEdge(1, 3));
        assertTrue(graph.removeEdge(1, 4));
        assertTrue(graph.removeEdge(1, 5));
        assertTrue(graph.removeEdge(1, 6));
        assertTrue(graph.removeEdge(1, 7));
        assertTrue(graph.removeEdge(1, 8));
        assertTrue(graph.removeEdge(1, 9));
        assertFalse(graph.removeEdge(1, 2));
        assertFalse(graph.removeEdge(1, 3));
        assertFalse(graph.removeEdge(1, 4));
        assertFalse(graph.removeEdge(1, 5));
        assertFalse(graph.removeEdge(1, 6));
        assertFalse(graph.removeEdge(1, 7));
        assertFalse(graph.removeEdge(1, 8));
        assertFalse(graph.removeEdge(1, 9));

        // graph2 tests
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);
        graph2.addNode(4);
        graph2.addNode(5);
        graph2.addNode(6);
        graph2.addNode(7);
        graph2.addNode(8);
        graph2.addNode(9);
        graph2.addEdge(1, 2, 1);
        graph2.addEdge(1, 3, 1);
        graph2.addEdge(1, 4, 1);
        graph2.addEdge(1, 5, 1);
        graph2.addEdge(1, 6, 1);
        graph2.addEdge(1, 7, 1);
        graph2.addEdge(1, 8, 1);
        graph2.addEdge(1, 9, 1);
        assertTrue(graph2.removeEdge(1, 2));
        assertTrue(graph2.removeEdge(1, 3));
        assertTrue(graph2.removeEdge(1, 4));
        assertTrue(graph2.removeEdge(1, 5));
        assertTrue(graph2.removeEdge(1, 6));
        assertTrue(graph2.removeEdge(1, 7));
        assertTrue(graph2.removeEdge(1, 8));
        assertTrue(graph2.removeEdge(1, 9));
        assertFalse(graph2.removeEdge(1, 2));
        assertFalse(graph2.removeEdge(1, 3));
        assertFalse(graph2.removeEdge(1, 4));
        assertFalse(graph2.removeEdge(1, 5));
        assertFalse(graph2.removeEdge(1, 6));
        assertFalse(graph2.removeEdge(1, 7));
        assertFalse(graph2.removeEdge(1, 8));
        assertFalse(graph2.removeEdge(1, 9));

        // graph3 tests
        graph3.addNode(1);
        graph3.addNode(2);
        graph3.addNode(3);
        graph3.addNode(4);
        graph3.addNode(5);
        graph3.addNode(6);
        graph3.addNode(7);
        graph3.addNode(8);
        graph3.addNode(9);
        graph3.addEdge(1, 2, 1);
        graph3.addEdge(1, 3, 1);
        graph3.addEdge(1, 4, 1);
        graph3.addEdge(1, 5, 1);
        graph3.addEdge(1, 6, 1);
        graph3.addEdge(1, 7, 1);
        graph3.addEdge(1, 8, 1);
        graph3.addEdge(1, 9, 1);
        assertTrue(graph3.removeEdge(1, 2));
        assertTrue(graph3.removeEdge(1, 3));
        assertTrue(graph3.removeEdge(1, 4));
        assertTrue(graph3.removeEdge(1, 5));
        assertTrue(graph3.removeEdge(1, 6));
        assertTrue(graph3.removeEdge(1, 7));
        assertTrue(graph3.removeEdge(1, 8));
        assertTrue(graph3.removeEdge(1, 9));
        assertFalse(graph3.removeEdge(1, 2));
        assertFalse(graph3.removeEdge(1, 3));
        assertFalse(graph3.removeEdge(1, 4));
        assertFalse(graph3.removeEdge(1, 5));
        assertFalse(graph3.removeEdge(1, 6));
        assertFalse(graph3.removeEdge(1, 7));
        assertFalse(graph3.removeEdge(1, 8));
        assertFalse(graph3.removeEdge(1, 9));

        // graph4 tests
        graph4.addNode(1);
        graph4.addNode(2);
        graph4.addNode(3);
        graph4.addNode(4);
        graph4.addNode(5);
        graph4.addNode(6);
        graph4.addNode(7);
        graph4.addNode(8);
        graph4.addNode(9);
        graph4.addEdge(1, 2, 1);
        graph4.addEdge(1, 3, 1);
        graph4.addEdge(1, 4, 1);
        graph4.addEdge(1, 5, 1);
        graph4.addEdge(1, 6, 1);
        graph4.addEdge(1, 7, 1);
    }

    @Test
    void testNumNodes() {
        assertEquals(0, graph.numNodes());
        assertEquals(0, graph2.numNodes());
        assertEquals(0, graph3.numNodes());
        assertEquals(0, graph4.numNodes());
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        assertEquals(4, graph.numNodes());
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);
        graph2.addNode(4);
        assertEquals(4, graph2.numNodes());
        graph3.addNode(1);
        graph3.addNode(2);
        graph3.addNode(3);
        graph3.addNode(4);
        assertEquals(4, graph3.numNodes());
        graph4.addNode(1);
        graph4.addNode(2);
        graph4.addNode(3);
        graph4.addNode(4);
        assertEquals(4, graph4.numNodes());

    }

    @Test
    void testNumEdges() {
        assertEquals(0, graph.numEdges());
        assertEquals(0, graph2.numEdges());
        assertEquals(0, graph3.numEdges());
        assertEquals(0, graph4.numEdges());
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        assertEquals(3, graph.numEdges());
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);
        graph2.addNode(4);
        graph2.addEdge(1, 2, 1);
        graph2.addEdge(1, 3, 1);
        graph2.addEdge(1, 4, 1);
        assertEquals(3, graph2.numEdges());
        graph3.addNode(1);
        graph3.addNode(2);
        graph3.addNode(3);
        graph3.addNode(4);
        graph3.addEdge(1, 2, 1);
        graph3.addEdge(1, 3, 1);
        graph3.addEdge(1, 4, 1);
        assertEquals(3, graph3.numEdges());
        graph4.addNode(1);
        graph4.addNode(2);
        graph4.addNode(3);
        graph4.addNode(4);
        graph4.addEdge(1, 2, 1);
        graph4.addEdge(1, 3, 1);
        graph4.addEdge(1, 4, 1);
        assertEquals(3, graph4.numEdges());
    }

    @Test
    void testGetNodes() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        assertTrue(graph.getNodes().contains(1));
        assertTrue(graph.getNodes().contains(2));
        assertTrue(graph.getNodes().contains(3));
        assertTrue(graph.getNodes().contains(4));
        assertFalse(graph.getNodes().contains(5));
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);
        graph2.addNode(4);
        assertTrue(graph2.getNodes().contains(1));
        assertTrue(graph2.getNodes().contains(2));
        assertTrue(graph2.getNodes().contains(3));
        assertTrue(graph2.getNodes().contains(4));
        assertFalse(graph2.getNodes().contains(5));
        graph3.addNode(1);
        graph3.addNode(2);
        graph3.addNode(3);
        graph3.addNode(4);
        assertTrue(graph3.getNodes().contains(1));
        assertTrue(graph3.getNodes().contains(2));
        assertTrue(graph3.getNodes().contains(3));
        assertTrue(graph3.getNodes().contains(4));
        assertFalse(graph3.getNodes().contains(5));
        graph4.addNode(1);
        graph4.addNode(2);
        graph4.addNode(3);
        graph4.addNode(4);
        assertTrue(graph4.getNodes().contains(1));
        assertTrue(graph4.getNodes().contains(2));
        assertTrue(graph4.getNodes().contains(3));
        assertTrue(graph4.getNodes().contains(4));
        assertFalse(graph4.getNodes().contains(5));
    }

    @Test
    void testGetEdges() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        assertEquals(3, graph.getEdges().size() / 2);
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);
        graph2.addNode(4);
        graph2.addEdge(1, 2, 1);
        graph2.addEdge(1, 3, 1);
        graph2.addEdge(1, 4, 1);
        assertEquals(3, graph2.getEdges().size() / 2);
        graph3.addNode(1);
        graph3.addNode(2);
        graph3.addNode(3);
        graph3.addNode(4);
        graph3.addEdge(1, 2, 1);
        graph3.addEdge(1, 3, 1);
        graph3.addEdge(1, 4, 1);
        assertEquals(3, graph3.getEdges().size());
        graph4.addNode(1);
        graph4.addNode(2);
        graph4.addNode(3);
        graph4.addNode(4);
        graph4.addEdge(1, 2, 1);
        graph4.addEdge(1, 3, 1);
        graph4.addEdge(1, 4, 1);
        assertEquals(3, graph4.getEdges().size());
    }


    @Test
    void testGetNeighbours() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        assertTrue(graph.getNeighbours(1).contains(2));
        assertTrue(graph.getNeighbours(1).contains(3));
        assertTrue(graph.getNeighbours(1).contains(4));
        assertFalse(graph.getNeighbours(1).contains(5));
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);
        graph2.addNode(4);
        graph2.addEdge(1, 2, 1);
        graph2.addEdge(1, 3, 1);
        graph2.addEdge(1, 4, 1);
        assertTrue(graph2.getNeighbours(1).contains(2));
        assertTrue(graph2.getNeighbours(1).contains(3));
        assertTrue(graph2.getNeighbours(1).contains(4));
        assertFalse(graph2.getNeighbours(1).contains(5));
        graph3.addNode(1);
        graph3.addNode(2);
        graph3.addNode(3);
        graph3.addNode(4);
        graph3.addEdge(1, 2, 1);
        graph3.addEdge(1, 3, 1);
        graph3.addEdge(1, 4, 1);
        assertTrue(graph3.getNeighbours(1).contains(2));
        assertTrue(graph3.getNeighbours(1).contains(3));
        assertTrue(graph3.getNeighbours(1).contains(4));
        assertFalse(graph3.getNeighbours(1).contains(5));
        graph4.addNode(1);
        graph4.addNode(2);
        graph4.addNode(3);
        graph4.addNode(4);
        graph4.addEdge(1, 2, 1);
        graph4.addEdge(1, 3, 1);
        graph4.addEdge(1, 4, 1);
        assertTrue(graph4.getNeighbours(1).contains(2));
        assertTrue(graph4.getNeighbours(1).contains(3));
        assertTrue(graph4.getNeighbours(1).contains(4));
        assertFalse(graph4.getNeighbours(1).contains(5));
    }

    @Test
    void testGetLabel() {
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);
        graph2.addNode(4);
        graph2.addEdge(1, 2, 1);
        graph2.addEdge(1, 3, 2);
        graph2.addEdge(1, 4, 3);
        assertEquals(1, graph2.getLabel(1, 2));
        assertEquals(2, graph2.getLabel(1, 3));
        assertEquals(3, graph2.getLabel(1, 4));
        graph4.addNode(1);
        graph4.addNode(2);
        graph4.addNode(3);
        graph4.addNode(4);
        graph4.addEdge(1, 2, 1);
        graph4.addEdge(1, 3, 2);
        graph4.addEdge(1, 4, 3);
        assertEquals(1, graph4.getLabel(1, 2));
        assertEquals(2, graph4.getLabel(1, 3));
        assertEquals(3, graph4.getLabel(1, 4));
    }

    @Test
    void testIsDirected() {
        assertFalse(graph.isDirected());
        assertFalse(graph2.isDirected());
        assertTrue(graph3.isDirected());
        assertTrue(graph4.isDirected());
    }

    @Test
    void testIsLabelled() {
        assertFalse(graph.isLabelled());
        assertTrue(graph2.isLabelled());
        assertFalse(graph3.isLabelled());
        assertTrue(graph4.isLabelled());
    }
}