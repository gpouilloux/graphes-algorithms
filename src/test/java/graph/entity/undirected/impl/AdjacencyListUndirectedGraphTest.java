package graph.entity.undirected.impl;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static graph.util.Constantes.O;


/**
 * Test class for adjacency list representing undirected graphs
 */
public class AdjacencyListUndirectedGraphTest {

    private AdjacencyListUndirectedGraph adjacencyList;

    @Before
    public void initialize() {
        int[][] adjacencyMatrix = {
                { O, O, 4, 2, },
                { O, O, O, 3, },
                { 4, O, O, O, },
                { 2, 3, O, O }
        };

        this.adjacencyList = new AdjacencyListUndirectedGraph(new AdjacencyMatrixUndirectedGraph(4, 3, adjacencyMatrix));
    }

    @Test
    public void testGetNeighbors() {
        int[] neighbors = this.adjacencyList.getNeighbors(1);

        assertThat(neighbors, not(nullValue()));
        assertThat(neighbors.length, equalTo(1));
        assertThat(neighbors[0], equalTo(3));
    }

    @Test
    public void testIsEdge() {
        boolean isEdge = this.adjacencyList.isEdge(0, 2);

        assertThat(isEdge, equalTo(Boolean.TRUE));
    }

    @Test
    public void testIsEdgeKO() {
        boolean isEdge = this.adjacencyList.isEdge(1, 2);

        assertThat(isEdge, equalTo(Boolean.FALSE));
    }

    @Test
    public void testAddVertex() {
        int originalOrder = this.adjacencyList.getOrder();
        this.adjacencyList.addVertex();

        assertThat(this.adjacencyList.getOrder(), equalTo(originalOrder+1));
    }

    @Test
    public void testAddEdge() {
        final int x = 1;
        final int y = 2;

        assertThat(this.adjacencyList.isEdge(x, y), equalTo(Boolean.FALSE));

        this.adjacencyList.addEdge(x, y, 5);

        assertThat(this.adjacencyList.isEdge(x, y), equalTo(Boolean.TRUE));

    }


    @Test
    public void testRemoveEdge() {
        final int x = 0;
        final int y = 2;

        this.adjacencyList.removeEdge(x, y);

        assertThat(this.adjacencyList.isEdge(x, y), equalTo(Boolean.FALSE));
    }
}
