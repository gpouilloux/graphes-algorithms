package graph.entity.undirected.impl;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Test class for adjacency matrix representing undirected graphs
 */
public class AdjacencyMatrixUndirectedGraphTest {

    private AdjacencyMatrixUndirectedGraph adjacencyMatrix;

    @Before
    public void initialize() {
        int[][] adjacencyMatrix = {
                { 0, 0, 3, 1, },
                { 0, 0, 0, 2, },
                { 3, 0, 0, 0, },
                { 1, 2, 0, 0 }
        };

        this.adjacencyMatrix = new AdjacencyMatrixUndirectedGraph(4, 3, adjacencyMatrix);
    }

    @Test
    public void testGetNeighbors() {
        int[] neighbors = this.adjacencyMatrix.getNeighbors(1);

        assertThat(neighbors, not(nullValue()));
        assertThat(neighbors.length, equalTo(1));
        assertThat(neighbors[0], equalTo(3));
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
    public void testAddEdge() {
        final int x = 1;
        final int y = 2;

        assertThat(this.adjacencyMatrix.isEdge(x, y), equalTo(Boolean.FALSE));

        this.adjacencyMatrix.addEdge(x, y, 5);

        assertThat(this.adjacencyMatrix.isEdge(x, y), equalTo(Boolean.TRUE));

    }


    @Test
    public void testRemoveEdge() {
        final int x = 0;
        final int y = 2;

        this.adjacencyMatrix.removeEdge(x, y);

        assertThat(this.adjacencyMatrix.isEdge(x, y), equalTo(Boolean.FALSE));
    }
}
