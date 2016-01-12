package graph.entity.undirected;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractUndirectedGraph implements IUndirectedGraph {

	/**
	 * Number of vertex
	 */
	protected int order;
	
	/**
	 * Number of edges
	 */
	protected int nbEdges;


	/**
	 * Génère un graphe non orienté de manière aléatoire
	 * @param order l'odre du graphe (i.e nombre de sommets)
	 * @param nbEdges le nombre d'arêtes
	 * @return le graphe non orienté aléatoire
	 */
	public static int[][] getRandomUndirectedGraph(int order, int nbEdges) {

		int[][] adjacencyMatrix = new int[order][order];

		// Récupération de toutes les arêtes
		List<Entry<Integer, Integer>> edges = new ArrayList<>();
		for(int i = 0; i< order; i++) {
			for(int j = i+1; j< order; j++) {
				edges.add(new AbstractMap.SimpleEntry<>(i, j));
			}
		}

		// Epurage pour sélection de seulement nbAretes arêtes
		List<Entry<Integer, Integer>> selectedEdges = new ArrayList<>();
		int nbEdgesPossible = edges.size();
		for(int i=0; i<nbEdges; i++) {
			int edgeToRemove = (int) Math.round(Math.random() * (--nbEdgesPossible));
			selectedEdges.add(edges.remove(edgeToRemove));
		}

		// Mise à jour de la matrice d'adjacence
		for(Map.Entry<Integer, Integer> edge : selectedEdges) {
			adjacencyMatrix[edge.getKey()][edge.getValue()] = 1;
			adjacencyMatrix[edge.getValue()][edge.getKey()] = 1;
		}

		return adjacencyMatrix;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int ordre) {
		this.order = ordre;
	}

	public int getNbEdges() {
		return nbEdges;
	}

	public void setNbEdges(int nbEdges) {
		this.nbEdges = nbEdges;
	}

}
