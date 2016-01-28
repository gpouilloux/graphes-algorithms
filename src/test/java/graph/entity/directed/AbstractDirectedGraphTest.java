package graph.entity.directed;

import graph.entity.directed.impl.AdjacencyMatrixDirectedGraph;
import graph.util.BinaryHeap;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of {AbstractDirectedGraph}
 */
public class AbstractDirectedGraphTest {

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
        // TODO test prim algorithm
        BinaryHeap bh = this.directedGraph.prim(0);
    }
}
