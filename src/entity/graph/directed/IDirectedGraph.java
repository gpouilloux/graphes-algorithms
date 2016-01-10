package entity.graph.directed;

/**
 * Interface de manipulation des graphes orientés
 * @author gpouillo
 *
 */
public interface IDirectedGraph {

	int[][] getGraph();
	
	int[] getSuccessors(int x);
	
	int[] getPredecessors(int x);
	
	boolean isEdge(int x, int y);
	
	int addVertex();
	
	void removeArc(int x, int y);
	
	void addArc(int x, int y);
	
	void display();
	
}
