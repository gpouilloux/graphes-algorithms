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

    private AdjacencyListDirectedGraph aldg;

    @Before
    public void initialize() {
        int[][] adjacencyMatrix = {
                { 0, 0, 0, 0, },
                { 0, 0, 1, 0, },
                { 1, 1, 0, 0, },
                { 0, 0, 0, 0 }
        };

        this.aldg = new AdjacencyListDirectedGraph(new AdjacencyMatrixDirectedGraph(4, 3, adjacencyMatrix));
    }

    @Test
    public void testGetSuccessors() {
        int[] successors = this.aldg.getSuccessors(1);

        assertThat(successors, not(nullValue()));
        assertThat(successors.length, equalTo(1));
        assertThat(successors[0], equalTo(2));
    }
}
