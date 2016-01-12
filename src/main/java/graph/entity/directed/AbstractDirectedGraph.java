package graph.entity.directed;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public abstract class AbstractDirectedGraph implements IDirectedGraph {

	protected int order;
	
	protected int nbEdges;

	public static int[][] getRandomDirectedGraph(int ordre, int nbEdges) {
		int[][] adjacencyMatrix = new int[ordre][ordre];

		// Récupération de toutes les arêtes
		List<Entry<Integer, Integer>> edges = new ArrayList<>();
		for(int i=0; i<ordre; i++) {
			for(int j=0; j<ordre; j++) {
				if(i != j)
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
		}

		return adjacencyMatrix;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getNbEdges() {
		return nbEdges;
	}

	public void setNbEdges(int nbEdges) {
		this.nbEdges = nbEdges;
	}
}
