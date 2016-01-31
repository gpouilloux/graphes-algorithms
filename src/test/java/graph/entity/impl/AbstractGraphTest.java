package graph.entity.impl;

import graph.entity.directed.impl.AdjacencyListDirectedGraph;
import graph.entity.directed.impl.AdjacencyMatrixDirectedGraph;
import graph.entity.undirected.impl.AdjacencyMatrixUndirectedGraph;
import graph.util.BinaryHeap;
import org.junit.Before;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import static graph.util.Constantes.O;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

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
		List<Integer> visitedVertices = this.abstractGraph.breadthFirstSearch(0);

		List<Integer> expectedVisitedVertices = Arrays.asList(0, 1, 2, 3);

		assertThat(visitedVertices, equalTo(expectedVisitedVertices));
	}

	@Test
	public void testDFS() {
		List<Integer> visitedVertices = this.abstractGraph.depthFirstSearch(0);

		List<Integer> expectedVisitedVertices = Arrays.asList(0, 2, 3, 1);

		assertThat(visitedVertices, equalTo(expectedVisitedVertices));
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

		this.abstractGraph = new AdjacencyMatrixUndirectedGraph(3, 4, adjacencyMatrixFloyd);
		int[][] v = this.abstractGraph.floyd();

		int[][] expectedDistances = {
				{ 0, 2, 5, },
				{ 2, 0, 3, },
				{ 5, 3, 0  }
		};

		assertThat(v, equalTo(expectedDistances));
	}

	@Test
	public void testBellman() {
		int[][] adjacencyMatrixBellMan = {
				{ O, 4, 2, O, },
				{ O, O, O, -2, },
				{ O, O, O, 1, },
				{ O, O, O, O, }
		};

		this.abstractGraph = new AdjacencyMatrixUndirectedGraph(4, 4, adjacencyMatrixBellMan);
		int[] distance = this.abstractGraph.bellman(0);

		int[] expectedDistance = {0, 4, 2, 2};

		assertThat(distance, equalTo(expectedDistance));
	}

	@Test
	public void testBellmanWithMoreEdges() {
		int[][] adjacencyMatrixBellMan = {
				{ O, 4, O, O, 3, O },
				{ O, O, O, 3, O, 8 },
				{ O, O, O, O, O, O },
				{ O, O, O, O, 2, O },
				{ O, O, -5, O, O, 4 },
				{ O, O, O, O, O, O }
		};

		this.abstractGraph = new AdjacencyMatrixUndirectedGraph(6, 8, adjacencyMatrixBellMan);
		int[] distance = this.abstractGraph.bellman(0);

		int[] expectedDistance = {0, 4, -2, 7, 3, 7};

		assertThat(distance, equalTo(expectedDistance));
	}

	@Test
	public void testBellmanWithNegativeCycle() {
		int[][] adjacencyMatrixBellMan = {
				{ O, 4, O, O, 3, O },
				{ O, O, O, 3, O, 8 },
				{ O, 4, O, O, O, O },
				{ O, O, O, O, 2, O },
				{ O, O, -5, O, O, 4 },
				{ O, O, O, O, O, O }
		};

		this.abstractGraph = new AdjacencyMatrixUndirectedGraph(6, 8, adjacencyMatrixBellMan);

		try {
			this.abstractGraph.bellman(0);
			fail();
		} catch(Error e) {
			assertThat(e.getLocalizedMessage(), equalTo("Graph contains a negative-weight cycle"));
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

	// Performance test regarding the different data structure

	private AbstractGraph abstractGraphPerformance;
	private static final int orderPerformance = 6;
	private static final int nbEdgesPerformance = 7;
	private static final int baseVertexPerformance = 0;
	private static final int[][] adjacencyMatrixBellManPerformance = {
			{ O, 4, O, O, 3, O },
			{ O, O, O, 3, O, 8 },
			{ O, O, O, O, O, O },
			{ O, O, O, O, 2, O },
			{ O, O, -5, O, O, 4 },
			{ O, O, O, O, O, O }
	};

	@Test
	public void testBellmanDirectedAdjacencyMatrix() {
		long startTime = System.nanoTime();

		this.abstractGraphPerformance = new AdjacencyMatrixDirectedGraph(orderPerformance, nbEdgesPerformance
				, adjacencyMatrixBellManPerformance);

		this.abstractGraphPerformance.bellman(baseVertexPerformance);

		System.out.println(MessageFormat.format("Bellman - Adjacency matrix : {0} microseconds",
				(System.nanoTime() - startTime)/1000));
	}

	@Test
	public void testBellmanDirectedAdjacencyList() {
		long startTime = System.nanoTime();

		this.abstractGraphPerformance = new AdjacencyListDirectedGraph(new AdjacencyMatrixDirectedGraph(orderPerformance,
				nbEdgesPerformance, adjacencyMatrixBellManPerformance));

		this.abstractGraphPerformance.bellman(baseVertexPerformance);

		System.out.println(MessageFormat.format("Bellman - Adjacency list : {0} microseconds",
				(System.nanoTime() - startTime)/1000));
	}

}
