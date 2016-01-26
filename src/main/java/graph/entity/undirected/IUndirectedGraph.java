package graph.entity.undirected;

import graph.entity.IGraph;

/**
 * Interface for the manipulation of undirected graphs
 * @author gpouillo
 *
 */
public interface IUndirectedGraph extends IGraph {
	
	/**
	 * Récupère les voisins d'un sommet
	 * @param x l'identifiant du sommet
	 * @return le tableau des identifiants des sommets voisins du sommet x
	 */
	int[] getNeighbors(int x);
	
	/**
	 * Supprime l'arête (x,y) du graphe si elle existe
	 * @param x l'identifiant du premier sommet
	 * @param y l'identifiant du second sommet
	 */
	void removeEdge(int x, int y);
	
	/**
	 * Ajoute l'arête (x,y) au graphe
	 * @param x l'identifiant du premier sommet
	 * @param y l'identifiant du second sommet
	 */
	void addEdge(int x, int y);

}
