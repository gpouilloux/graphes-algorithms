package graph.entity.directed.impl;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static graph.util.Constantes.O;

/**
 * Test class for incident matrix representing directed graphs
 *
 * @author Guillaume Pouilloux
 */
public class IncidentMatrixDirectedGraphTest {

    private IncidentMatrixDirectedGraph incidentMatrix;

    @Before
    public void initialize() {
        int[][] adjacencyMatrix = {
                { O, 1, 1, O, },
                { O, O, O, O, },
                { O, 1, O, O, },
                { O, O, O, O }
        };

        this.incidentMatrix = new IncidentMatrixDirectedGraph(new AdjacencyListDirectedGraph(new AdjacencyMatrixDirectedGraph(4, 3, adjacencyMatrix)));
    }

    @Test
    public void testGetSuccessors() {
        int[] successors = this.incidentMatrix.getSuccessors(2);

        assertThat(successors, not(nullValue()));
        assertThat(successors.length, equalTo(1));
        assertThat(successors[0], equalTo(1));
    }

    @Test
    public void testGetPredecessors() {
        int[] predecessors = this.incidentMatrix.getPredecessors(2);

        assertThat(predecessors, not(nullValue()));
        assertThat(predecessors.length, equalTo(1));
        assertThat(predecessors[0], equalTo(0));
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
    public void testAddArc() {
        final int x = 2;
        final int y = 3;

        assertThat(this.incidentMatrix.isEdge(x, y), equalTo(Boolean.FALSE));

        this.incidentMatrix.addArc(x, y, 5);

        assertThat(this.incidentMatrix.isEdge(x, y), equalTo(Boolean.TRUE));
    }

    @Test
    public void testRemoveArc() {
        final int x = 0;
        final int y = 1;

        this.incidentMatrix.removeArc(x, y);

        assertThat(this.incidentMatrix.isEdge(x, y), equalTo(Boolean.FALSE));
    }

    @Test
    public void testInverse() {
        IncidentMatrixDirectedGraph result = this.incidentMatrix.inverse();

        int[][] adjacencyMatrix = {
                { O, O, O, O, },
                { 1, O, 1, O, },
                { 1, O, O, O, },
                { O, O, O, O }
        };

        IncidentMatrixDirectedGraph expected = new IncidentMatrixDirectedGraph(new AdjacencyListDirectedGraph(new AdjacencyMatrixDirectedGraph(4, 3, adjacencyMatrix)));

        assertThat(result.getGraph(), equalTo(expected.getGraph()));
    }


}
