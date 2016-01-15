package graph.entity.directed;

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
	
}
