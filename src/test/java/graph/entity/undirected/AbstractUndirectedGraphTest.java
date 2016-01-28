package graph.entity.undirected;

import graph.entity.undirected.impl.AdjacencyMatrixUndirectedGraph;
import graph.util.BinaryHeap;
import org.junit.Before;
import org.junit.Test;

/**
 * Test of {AbstractUndirectedGraph}
 */
public class AbstractUndirectedGraphTest {

	private int[][] adjacencyMatrix = {
			{ 0, 2, 2, 3, },
			{ 2, 0, 0, 2, },
			{ 2, 0, 0, 1, },
			{ 3, 2, 1, 0 }
	};

    private AbstractUndirectedGraph undirectedGraph;

    /**
     * Initialize test cases with an adjacency matrix
     */
    @Before
    public void initialize() {
        this.undirectedGraph = new AdjacencyMatrixUndirectedGraph(4, 5, this.adjacencyMatrix);
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
        // TODO define an expected result so we can assert something
	    BinaryHeap bh = this.undirectedGraph.prim(0);
        bh.display();
    }
}
