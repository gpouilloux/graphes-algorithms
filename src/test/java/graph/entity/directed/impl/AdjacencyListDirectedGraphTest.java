package graph.entity.directed.impl;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

/**
 * Test class for adjacency list representing directed graphs
 */
public class AdjacencyListDirectedGraphTest {

    private AdjacencyListDirectedGraph adjacencyList;

    @Before
    public void initialize() {
        int[][] adjacencyMatrix = {
                { 0, 0, 0, 0, },
                { 1, 0, 0, 0, },
                { 1, 0, 0, 0, },
                { 0, 1, 0, 0 }
        };

        this.adjacencyList = new AdjacencyListDirectedGraph(new AdjacencyMatrixDirectedGraph(4, 3, adjacencyMatrix));
    }

    @Test
    public void testGetSuccessors() {
        int[] successors = this.adjacencyList.getSuccessors(1);

        assertThat(successors, not(nullValue()));
        assertThat(successors.length, equalTo(1));
        assertThat(successors[0], equalTo(0));
    }

    @Test
    public void testGetPredecessors() {
        int[] predecessors = this.adjacencyList.getPredecessors(1);

        assertThat(predecessors, not(nullValue()));
        assertThat(predecessors.length, equalTo(1));
        assertThat(predecessors[0], equalTo(3));
    }

    @Test
    public void testIsEdge() {
        boolean isEdge = this.adjacencyList.isEdge(1, 0);

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
    public void testAddArc() {
        final int x = 2;
        final int y = 3;

        assertThat(this.adjacencyList.isEdge(x, y), equalTo(Boolean.FALSE));

        this.adjacencyList.addArc(x, y);

        assertThat(this.adjacencyList.isEdge(x, y), equalTo(Boolean.TRUE));
    }

    @Test
    public void testRemoveArc() {
        final int x = 1;
        final int y = 0;

        this.adjacencyList.removeArc(x, y);

        assertThat(this.adjacencyList.isEdge(x, y), equalTo(Boolean.FALSE));
    }
}
