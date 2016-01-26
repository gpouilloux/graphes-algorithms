package graph.entity.undirected;

import graph.entity.IGraph;

/**
 * Interface for the manipulation of undirected graphs
 * @author gpouillo
 *
 */
public interface IUndirectedGraph extends IGraph {

	/**
	 * Returns the vertex's neighbors
	 *
	 * @param x the vertex's id
	 * @return an array containing the id of all the neighbors
	 */
	int[] getNeighbors(int x);

	/**
	 * Remove the edge (x,y) from the graph
	 *
	 * @param x the first vertex's id
	 * @param y the second vertex's id
	 */
	void removeEdge(int x, int y);

	/**
	 * Add the edge (x,y) into the graph
	 *
	 * @param x the first vertex's id
	 * @param y the second vertex's id
	 * @param cost the cost to put on the edge
	 */
	void addEdge(int x, int y, int cost);

}
