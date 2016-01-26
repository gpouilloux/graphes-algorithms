package graph.entity.undirected.impl;

import graph.util.BinaryHeap;
import graph.entity.undirected.AbstractUndirectedGraph;
import graph.util.Cost;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of {AbstractUndirectedGraph}
 */
public class AbstractUndirectedGraphTest {


    /**
     * Maximum cost to set on an edge
     */
    public static final int MAX_COST = 10;

    private AbstractUndirectedGraph undirectedGraph;

    /**
     * Initialize test cases with an adjacency matrix
     */
    @Before
    public void initialize() {
        int[][] adjacencyMatrix = {
                { 0, 1, 1, 1, },
                { 1, 0, 0, 1, },
                { 1, 0, 0, 1, },
                { 1, 1, 1, 0 }
        };

        this.undirectedGraph = new AdjacencyMatrixUndirectedGraph(4, 3, adjacencyMatrix);
    }

    @Test
    public void testBFS() {
        // TODO test BFS
        this.undirectedGraph.breadthFirstSearch(0);
    }

    @Test
    public void testDFS() {
        // TODO test DFS
        this.undirectedGraph.depthFirstSearch(0);
    }

    @Test
    public void testPrim() {
        int[][] cost = Cost.getCostMatrix(this.undirectedGraph, MAX_COST);
        // TODO test prim algorithm
        BinaryHeap bh = this.undirectedGraph.prim(0, cost);
        bh.display();
    }
}
