package graph.entity.impl;

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
 * Test methods implemented in {AbstractGraph}
 *
 * @author Guillaume Pouilloux
 */
public class AbstractGraphTest {

	private AbstractGraph abstractGraph;

	/**
	 * Initialize test cases with an adjacency matrix
	 */
	@Before
	public void initialize() {

		int[][] adjacencyMatrix = {
				{ O, 2, 2, O, },
				{ 2, O, O, 2, },
				{ 2, O, O, 1, },
				{ O, 2, 1, O }
		};

		this.abstractGraph = new AdjacencyMatrixUndirectedGraph(4, 4, adjacencyMatrix);
	}

	@Test
	public void testBFS() {
		List<Integer> visitedVertexes = this.abstractGraph.breadthFirstSearch(0);

		List<Integer> expectedVisitedVertexes = Arrays.asList(0, 1, 2, 3);

		assertThat(visitedVertexes, equalTo(expectedVisitedVertexes));
	}

	@Test
	public void testDFS() {
		List<Integer> visitedVertexes = this.abstractGraph.depthFirstSearch(0);

		List<Integer> expectedVisitedVertexes = Arrays.asList(0, 2, 3, 1);

		assertThat(visitedVertexes, equalTo(expectedVisitedVertexes));
	}


	@Test
	public void testPrim() {
		BinaryHeap bh = this.abstractGraph.prim(0);

		int[] expectedWeights = {0, 1, 2, 2};
		int[] expectedIndexes = {0, 3, 2, 1};
		BinaryHeap expected = new BinaryHeap(expectedWeights, expectedIndexes);

		assertThat(bh, equalTo(expected));
	}

	@Test
	public void testFloyd() {
		int[][] adjacencyMatrixFloyd = {
				{ O, 2, O, },
				{ 2, O, 3, },
				{ O, 3, O  }
		};

		// TODO check the result of the floyd method
		this.abstractGraph = new AdjacencyMatrixUndirectedGraph(3, 2, adjacencyMatrixFloyd);
		int[][] p = this.abstractGraph.floyd();
		for (int[] row : p) {
			for (int cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println("");
		}
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
