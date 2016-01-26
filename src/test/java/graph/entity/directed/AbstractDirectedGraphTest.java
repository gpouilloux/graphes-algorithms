package graph.entity.directed;

import graph.entity.directed.impl.AdjacencyMatrixDirectedGraph;
import graph.util.BinaryHeap;
import graph.util.Cost;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of {AbstractDirectedGraph}
 */
public class AbstractDirectedGraphTest {


    /**
     * Maximum cost to set on an edge
     */
    public static final int MAX_COST = 10;

    private AbstractDirectedGraph directedGraph;

    /**
     * Initialize test cases with an adjacency matrix
     */
    @Before
    public void initialize() {
        // FIXME find a proper adjacency matrix
        int[][] adjacencyMatrix = {
                { 0, 1, 0, 0, },
                { 0, 0, 1, 0, },
                { 0, 0, 0, 1, },
                { 0, 0, 0, 0 }
        };

        this.directedGraph = new AdjacencyMatrixDirectedGraph(4, 3, adjacencyMatrix);
    }

    @Test
    public void testBFS() {
        // TODO test BFS
        this.directedGraph.breadthFirstSearch(0);
    }

    @Test
    public void testDFS() {
        // TODO test DFS
        this.directedGraph.depthFirstSearch(0);
    }

    @Test
    public void testPrim() {
        int[][] cost = Cost.getCostMatrix(this.directedGraph, MAX_COST);
        // TODO test prim algorithm
        BinaryHeap bh = this.directedGraph.prim(0, cost);
        bh.display();
    }
}
