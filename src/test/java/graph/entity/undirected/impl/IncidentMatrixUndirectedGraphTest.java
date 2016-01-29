package graph.entity.undirected.impl;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static graph.util.Constantes.O;


/**
 * Test class for incident matrix representing undirected graphs
 */
public class IncidentMatrixUndirectedGraphTest {

    private IncidentMatrixUndirectedGraph incidentMatrix;

    @Before
    public void initialize() {
        int[][] adjacencyMatrix = {
                { O, O, 2, 1, },
                { O, O, O, 3, },
                { 2, O, O, O, },
                { 1, 3, O, O }
        };

        this.incidentMatrix = new IncidentMatrixUndirectedGraph(new AdjacencyListUndirectedGraph(
                new AdjacencyMatrixUndirectedGraph(4, 3, adjacencyMatrix)));
    }

    @Test
    public void testGetNeighbors() {
        int[] neighbors = this.incidentMatrix.getNeighbors(1);

        assertThat(neighbors, not(nullValue()));
        assertThat(neighbors.length, equalTo(1));
        assertThat(neighbors[0], equalTo(3));
    }

    @Test
    public void testIsEdge() {
        boolean isEdge = this.incidentMatrix.isEdge(0, 2);

        assertThat(isEdge, equalTo(Boolean.TRUE));
    }

    @Test
    public void testIsEdgeKO() {
        boolean isEdge = this.incidentMatrix.isEdge(1, 2);

        assertThat(isEdge, equalTo(Boolean.FALSE));
    }

    @Test
    public void testAddVertex() {
        int originalOrder = this.incidentMatrix.getOrder();
        this.incidentMatrix.addVertex();

        assertThat(this.incidentMatrix.getOrder(), equalTo(originalOrder+1));
    }

    @Test
    public void testAddEdge() {
        final int x = 1;
        final int y = 2;

        assertThat(this.incidentMatrix.isEdge(x, y), equalTo(Boolean.FALSE));

        this.incidentMatrix.addEdge(x, y, 5);

        assertThat(this.incidentMatrix.isEdge(x, y), equalTo(Boolean.TRUE));

    }


    @Test
    public void testRemoveEdge() {
        final int x = 0;
        final int y = 2;

        this.incidentMatrix.removeEdge(x, y);

        assertThat(this.incidentMatrix.isEdge(x, y), equalTo(Boolean.FALSE));
    }
}
