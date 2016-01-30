package graph.entity.undirected;

import graph.entity.impl.AbstractGraph;
import graph.util.BinaryHeap;
import graph.util.ListConverter;

import java.util.*;
import java.util.Map.Entry;

/**
 * Abstract class representing undirected graph
 *
 * @author Guillaume Pouilloux
 */
public abstract class AbstractUndirectedGraph extends AbstractGraph implements IUndirectedGraph {

	/**
	 * Generate a random undirected graph
	 * @param order the number of vertexes
	 * @param nbEdges the number of edges
	 * @return the random undirected graph generated
	 */
	public static int[][] getRandomUndirectedGraph(int order, int nbEdges) {

		// Initialize the adjacency matrix with no edges
		int[][] adjacencyMatrix = new int[order][order];
		for(int i=0; i<order; i++) {
			for(int j=0; j<order; j++) {
				adjacencyMatrix[i][j] = O;
			}
		}

		// Grab all possible edges
		List<Entry<Integer, Integer>> edges = new ArrayList<>();
		for(int i = 0; i< order; i++) {
			for(int j = i+1; j< order; j++) {
				edges.add(new AbstractMap.SimpleEntry<>(i, j));
			}
		}

		// Randomly choose few edges
		List<Entry<Integer, Integer>> selectedEdges = new ArrayList<>();
		int nbEdgesPossible = edges.size();
		for(int i=0; i<nbEdges; i++) {
			int edgeToRemove = (int) Math.round(Math.random() * (--nbEdgesPossible));
			selectedEdges.add(edges.remove(edgeToRemove));
		}

		// Update the adjacency matrix
		for(Map.Entry<Integer, Integer> edge : selectedEdges) {
			int cost = (int) Math.round(Math.random() * MAX_COST) + 1;
			adjacencyMatrix[edge.getKey()][edge.getValue()] = cost;
			adjacencyMatrix[edge.getValue()][edge.getKey()] = cost;
		}

		return adjacencyMatrix;
	}

}
