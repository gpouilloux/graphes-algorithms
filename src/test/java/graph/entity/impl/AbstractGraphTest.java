package graph.entity.impl;

import graph.entity.undirected.impl.AdjacencyMatrixUndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Test methods implemented in {AbstractGraph}
 */
public class AbstractGraphTest {

	private AbstractGraph abstractGraph;

	/**
	 * Initialize test cases with an adjacency matrix
	 */
	@Before
	public void initialize() {

		int[][] adjacencyMatrix = {
				{ 0, 0, 1, 1, },
				{ 0, 0, 0, 0, },
				{ 1, 0, 0, 0, },
				{ 1, 0, 0, 0 }
		};

		this.abstractGraph = new AdjacencyMatrixUndirectedGraph(4, 3, adjacencyMatrix);
	}


	@Test
	public void testComputeConnectedGraphsWithAdjacencyMatrix() {
		List<List<Integer>> connectedGraphs = this.abstractGraph.computeConnectedGraphs();
		for(List<Integer> g : connectedGraphs) {
			System.out.println("== Graph ==");
			for(Integer i : g) {
				System.out.println(i + " ");
			}
		}
	}
}
