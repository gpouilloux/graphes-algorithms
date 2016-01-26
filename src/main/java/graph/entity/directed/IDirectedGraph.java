package graph.entity.directed;

import java.util.List;

/**
 * Interface de manipulation des graphes orientés
 * @author gpouillo
 *
 */
public interface IDirectedGraph {

    /**
     * Return the graph as an adjacency matrix
     * @return the graph as an adjacency matrix
     */
	int[][] getGraph();

    /**
     * Grab successors of the vertex x
     * @param x the vertex
     * @return its successors
     */
	int[] getSuccessors(int x);

    /**
     * Grab predecessors of the vertex x
     * @param x the vertex
     * @return its predecessors
     */
	int[] getPredecessors(int x);

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
     * Remove an arc from the directed graph
     * @param x the source of the arc
     * @param y the target of the arc
     */
	void removeArc(int x, int y);

    /**
     * Add an arc to the directed graph
     * @param x the source of the arc
     * @param y the target of the arc
     */
	void addArc(int x, int y);

    /**
     * Display the directed graph
     */
	void display();

    /**
     * Return the inverse of the directed graph
     * @return the inverse of the directed graph
     */
	IDirectedGraph inverse();

    /**
     * Walk through the graph using BFS method (parcours en largeur)
     *
     * @param baseVertex the vertex we use to start the walk
     * @return the minimum distance of each vertex against the starting one
     */
    List<Integer> breadthFirstSearch(int baseVertex);

    /**
     * Walk through the graph using DFS method (parcours en profondeur)
     * Imperative version
     *
     * @param baseVertex the vertex we use to start the walk
     */
    void depthFirstSearch(int baseVertex);

    /**
     * Compute all connected graphs
     * 1. Execute exploreGraph() and memorize end[]
     * 2. Inverse graph (see lesson p.32)
     * 3. Execute exploreGraph() by calling edges decrease order by end[]
     * @return list of connected graphs
     */
    List<IDirectedGraph> computeConnectedGraphs();
	
}
