package graph.entity.undirected;

/**
 * Interface de manipulation de graphes non orientés
 * @author gpouillo
 *
 */
public interface IUndirectedGraph {

	/**
	 * Récupération de la matrice d'adjacence
	 * @return la matrice d'adjacence associée au graphe
	 */
	int[][] getGraph();
	
	/**
	 * Récupère les voisins d'un sommet
	 * @param x l'identifiant du sommet
	 * @return le tableau des identifiants des sommets voisins du sommet x
	 */
	int[] getNeighbors(int x);
	
	/**
	 * Vérifie si deux sommets sont reliés par une arête
	 * @param x l'identifiant du premier sommet
	 * @param y l'identifiant du second sommet
	 * @return vrai ssi (x,y) est une arête du graphe
	 */
	boolean isEdge(int x, int y);
	
	/**
	 * Ajoute un sommet au graphe et retourne son identifiant
	 * @return l'identifiant du sommet ajouté
	 */
	int addVertex();
	
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
	
	/**
	 * Affiche le graphe avec la représentation implémentée
	 */
	void display();
	
}
