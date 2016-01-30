package graph.entity.directed;

import graph.entity.directed.impl.AdjacencyMatrixDirectedGraph;
import org.junit.Before;

import static graph.util.Constantes.O;

/**
 * Test of {AbstractDirectedGraph}
 *
 * @author Guillaume Pouilloux
 */
public class AbstractDirectedGraphTest {

    private AbstractDirectedGraph directedGraph;

    /**
     * Initialize test cases with an adjacency matrix
     */
    @Before
    public void initialize() {
        int[][] adjacencyMatrix = {
                { O, 2, 2, O, },
                { O, O, O, 2, },
                { O, O, O, 1, },
                { O, O, O, O }
        };

        this.directedGraph = new AdjacencyMatrixDirectedGraph(4, 4, adjacencyMatrix);
    }

}
