package graph.entity.directed;

import graph.entity.IGraph;

/**
 * Interface for the manipulation of directed graph
 *
 * @author Guillaume Pouilloux
 *
 */
public interface IDirectedGraph extends IGraph {

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
     * Remove an arc from the directed graph
     * @param x the source of the arc
     * @param y the target of the arc
     */
	void removeArc(int x, int y);

    /**
     * Add an arc to the directed graph
     * @param x the source of the arc
     * @param y the target of the arc
     * @param cost the cost on the arc
     */
	void addArc(int x, int y, int cost);

	@Override
	default int[] getNeighbors(int x) {
		return this.getSuccessors(x);
	}
}
