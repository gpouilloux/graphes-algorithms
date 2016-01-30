package graph.entity.undirected;

import graph.entity.undirected.impl.AdjacencyMatrixUndirectedGraph;
import graph.util.BinaryHeap;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static graph.util.Constantes.O;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Test of {AbstractUndirectedGraph}
 *
 * @author Guillaume Pouilloux
 */
public class AbstractUndirectedGraphTest {

	private int[][] adjacencyMatrix = {
			{ O, 2, 2, O, },
			{ 2, O, O, 2, },
			{ 2, O, O, 1, },
			{ O, 2, 1, O }
	};

    private AbstractUndirectedGraph undirectedGraph;

    /**
     * Initialize test cases with an adjacency matrix
     */
    @Before
    public void initialize() {
        this.undirectedGraph = new AdjacencyMatrixUndirectedGraph(4, 5, this.adjacencyMatrix);
    }

}
