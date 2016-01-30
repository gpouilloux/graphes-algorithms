package graph.entity.directed.impl;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static graph.util.Constantes.O;

/**
 * Test class for adjacency matrix representing directed graphs
 *
 * @author Guillaume Pouilloux
 */
public class AdjacencyMatrixDirectedGraphTest {

    private AdjacencyMatrixDirectedGraph adjacencyMatrix;

    @Before
    public void initialize() {
        int[][] adjacencyMatrix = {
                { O, 1, 1, O, },
                { O, O, O, O, },
                { O, 1, O, O, },
                { O, O, O, O }
        };

        this.adjacencyMatrix = new AdjacencyMatrixDirectedGraph(4, 3, adjacencyMatrix);
    }

    @Test
    public void testGetSuccessors() {
        int[] successors = this.adjacencyMatrix.getSuccessors(2);

        assertThat(successors, not(nullValue()));
        assertThat(successors.length, equalTo(1));
        assertThat(successors[0], equalTo(1));
    }

    @Test
    public void testGetPredecessors() {
        int[] predecessors = this.adjacencyMatrix.getPredecessors(2);

        assertThat(predecessors, not(nullValue()));
        assertThat(predecessors.length, equalTo(1));
        assertThat(predecessors[0], equalTo(0));
    }

    @Test
    public void testIsEdge() {
        boolean isEdge = this.adjacencyMatrix.isEdge(0, 2);

        assertThat(isEdge, equalTo(Boolean.TRUE));
    }

    @Test
    public void testIsEdgeKO() {
        boolean isEdge = this.adjacencyMatrix.isEdge(1, 2);

        assertThat(isEdge, equalTo(Boolean.FALSE));
    }

    @Test
    public void testAddVertex() {
        int originalOrder = this.adjacencyMatrix.getOrder();
        this.adjacencyMatrix.addVertex();

        assertThat(this.adjacencyMatrix.getOrder(), equalTo(originalOrder+1));
    }

    @Test
    public void testAddArc() {
        final int x = 2;
        final int y = 3;

        assertThat(this.adjacencyMatrix.isEdge(x, y), equalTo(Boolean.FALSE));

        this.adjacencyMatrix.addArc(x, y, 5);

        assertThat(this.adjacencyMatrix.isEdge(x, y), equalTo(Boolean.TRUE));
    }

    @Test
    public void testRemoveArc() {
        final int x = 0;
        final int y = 1;

        this.adjacencyMatrix.removeArc(x, y);

        assertThat(this.adjacencyMatrix.isEdge(x, y), equalTo(Boolean.FALSE));
    }

    @Test
    public void testInverse() {
        AdjacencyMatrixDirectedGraph result = this.adjacencyMatrix.inverse();

        int[][] adjacencyMatrix = {
                { O, O, O, O, },
                { 1, O, 1, O, },
                { 1, O, O, O, },
                { O, O, O, O }
        };

        AdjacencyMatrixDirectedGraph expected = new AdjacencyMatrixDirectedGraph(4, 3, adjacencyMatrix);

        assertThat(result.getAdjacencyMatrix(), equalTo(expected.getAdjacencyMatrix()));
    }


}
