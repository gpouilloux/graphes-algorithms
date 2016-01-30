package graph.entity;

import graph.util.BinaryHeap;

import java.util.List;

/**
 * Interface for the manipulation of a graph
 *
 * @author Guillaume Pouilloux
 */
public interface IGraph {
    /**
     * Return the graph as an adjacency matrix
     * @return the graph as an adjacency matrix
     */
    int[][] getGraph();

	/**
	 * Returns the vertex's neighbors
	 *
	 * @param x the vertex's id
	 * @return an array containing the id of all the neighbors
	 */
	int[] getNeighbors(int x);

    /**
     * Check whether an arc is an edge or not
     * @param x the source of the arc
     * @param y the target of the arc
     * @return true if it's an arc, otherwise false
     */
    boolean isEdge(int x, int y);

    /**
     * Add a vertex to the graph
     * @return the id of the added vertex
     */
    int addVertex();

    /**
     * Display the graph
     */
    void display();

    /**
     * Return the inverse of the graph
     * @return the inverse of the graph
     */
    IGraph inverse();

    /**
     * Walk through the graph using BFS method (parcours en largeur)
     *
     * @param baseVertex the vertex we use to start the walk
     * @return the list of visited vertices following the order
     */
    List<Integer> breadthFirstSearch(int baseVertex);

    /**
     * Walk through the graph using DFS method (parcours en profondeur)
     * Imperative version
     *
     * @param baseVertex the vertex we use to start the walk
     * @return the list of visited vertices following the order
     */
    List<Integer> depthFirstSearch(int baseVertex);

    /**
     * Compute all connected graphs
     * 1. Execute exploreGraph() and memorize end[]
     * 2. Inverse graph (see lesson p.32)
     * 3. Execute exploreGraph() by calling edges decrease order by end[]
     * @return list of connected graphs
     */
    List<List<Integer>> computeConnectedGraphs();

    /**
     * Prim algorithm with BFS method
     *
     * @param baseVertex the base vertex used when starting the walk
     * @return the binary heap
     */
    BinaryHeap prim(int baseVertex);

	/**
	 * Floyd algorithm and computation of transitive closure
	 *
	 * @return the matrix of minimum distances between each vertex
	 */
    int[][] floyd();

	/**
	 * Bellman algorithm and computation of minimal distance from a base vertex
	 * (see https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm)
	 *
	 * @param baseVertex the base vertex
	 * @return the minimal distance between the base vertex and all other vertices
	 */
	int[] bellman(int baseVertex);
}
