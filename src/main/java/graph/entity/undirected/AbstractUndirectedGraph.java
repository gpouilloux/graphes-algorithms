package graph.entity.undirected;

import graph.entity.impl.AbstractGraph;
import graph.util.BinaryHeap;
import graph.util.ListConverter;

import java.util.*;
import java.util.Map.Entry;

public abstract class AbstractUndirectedGraph extends AbstractGraph implements IUndirectedGraph {

	/**
	 * Generate a random undirected graph
	 * @param order the number of vertexes
	 * @param nbEdges the number of edges
	 * @return the random undirected graph generated
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
			int cost = (int) Math.round(Math.random() * MAX_COST) + 1;
			adjacencyMatrix[edge.getKey()][edge.getValue()] = cost;
			adjacencyMatrix[edge.getValue()][edge.getKey()] = cost;
		}

		return adjacencyMatrix;
	}

	@Override
	public List<Integer> breadthFirstSearch(int baseVertex) {
		List<Boolean> mark = new ArrayList<>(Collections.nCopies(this.getOrder(), Boolean.FALSE));
		mark.set(baseVertex, Boolean.TRUE);

		List<Integer> minDistance = new ArrayList<>(Collections.nCopies(this.getOrder(), 0));

		Queue<Integer> toVisit = new LinkedList<>();
		toVisit.add(baseVertex);

		while(!toVisit.isEmpty()) {
			int vertex = toVisit.poll();
			for(int neighbor : this.getNeighbors(vertex)) {
				if(Boolean.FALSE.equals(mark.get(neighbor))) {
					mark.set(neighbor, Boolean.TRUE);
					minDistance.set(neighbor, minDistance.get(vertex)+1);
					toVisit.add(neighbor);
				}
			}
		}

		return minDistance;
	}

	@Override
	public List<Integer> depthFirstSearch(int baseVertex) {
		this.initializeTime();
		List<Boolean> mark = new ArrayList<>(Collections.nCopies(this.getOrder(), Boolean.FALSE));
		List<Integer> vertexesVisited = new ArrayList<>();
		mark.set(baseVertex, Boolean.TRUE);

		Stack<Integer> toVisit = new Stack<>();
		toVisit.push(baseVertex);

		while(!toVisit.isEmpty()) {
			int vertex = toVisit.pop();
			vertexesVisited.add(vertex);
			start[vertex] = time++;
			for (int neighbor : this.getNeighbors(vertex)) {
				if (Boolean.FALSE.equals(mark.get(neighbor))) {
					mark.set(neighbor, Boolean.TRUE);
					toVisit.push(neighbor);
				}
			}
			end[vertex] = time++;
		}

		return vertexesVisited;
	}


	@Override
	public BinaryHeap prim(int baseVertex) {
		List<Integer> neighbors = ListConverter.toList(this.getNeighbors(baseVertex));
		int predecessors[] = new int[this.getOrder()];
		int weights[] = new int[this.getOrder()];
		int cout[][] = this.getGraph();

		// initialization
		for(int i = 0; i< weights.length; i++) {
			if(neighbors.contains(i)) {
				predecessors[i] = baseVertex;
				weights[i] = cout[baseVertex][i];
			} else {
				predecessors[i] = -1; // -1 is like nil :-)
				weights[i] = Integer.MAX_VALUE;
			}
		}
		weights[baseVertex] = 0;

		// get ready to walk through all the vertexes except the base one
		List<Integer> vertexes = new ArrayList<>();
		for(int i=0; i<this.getOrder(); i++) {
			if(i != baseVertex) {
				vertexes.add(i);
			}
		}

		// initialize the binary heap with the base vertex
		BinaryHeap binaryHeap = new BinaryHeap(new int[]{weights[baseVertex]}, new int[]{baseVertex});

		// keep iterating while we have vertexes to discover
		while(!vertexes.isEmpty()) {
			int vertex = this.peekMinElement(weights, vertexes); // get vertex with lowest weight
			binaryHeap.insert(weights[vertex], vertex); // insert in the binary heap
			int succs[] = this.getNeighbors(vertex);
			for(int i=0; i<succs.length; i++) {
				int succ = succs[i];
				if(vertexes.contains(succ) && weights[succ] > cout[vertex][succ]) {
					weights[succ] = cout[vertex][succ];
					predecessors[succ] = vertex;
				}
			}
		}

		return binaryHeap;
	}

	@Override
	public int[][] floyd() {
		// initialization
		int n = this.getOrder();
		int[][] p = new int[n][n];
		int[][] v = new int[n][n];
		int[][] cost = this.getGraph();

		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i == j) {
					v[i][j] = 0;
					p[i][j] = i;
				} else {
					v[i][j] = Integer.MAX_VALUE;
					p[i][j] = 0;
				}
			}
			int[] neighbors = this.getNeighbors(i);
			for(int neighbor : neighbors) {
				v[i][neighbor] = cost[i][neighbor];
				p[i][neighbor] = i;
			}
		}

		// computing successive matrices
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(v[i][k] + v[k][j] < v[i][j]) {
						v[i][j] = v[i][k] + v[k][j];
						p[i][j] = p[k][j];
					}
				}
			}
		}

		return p;
	}
}
