package graph.entity.directed;

import graph.entity.impl.AbstractGraph;
import graph.util.BinaryHeap;
import graph.util.ListConverter;

import java.util.*;
import java.util.Map.Entry;

/**
 * Abstract class representing directed graph
 */
public abstract class AbstractDirectedGraph extends AbstractGraph implements IDirectedGraph {

    /**
     * Generate a random directed graph
     * @param order the number of vertices
     * @param nbEdges the number of edges
     * @return the random directed graph generated
     */
    public static int[][] getRandomDirectedGraph(int order, int nbEdges) {

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
            for(int j = 0; j< order; j++) {
                if(i != j)
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
        }

        return adjacencyMatrix;
    }

}
