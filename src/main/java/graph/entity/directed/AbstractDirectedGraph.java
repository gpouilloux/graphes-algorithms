package graph.entity.directed;

import graph.entity.impl.AbstractGraph;
import graph.util.BinaryHeap;

import java.util.*;
import java.util.Map.Entry;

public abstract class AbstractDirectedGraph extends AbstractGraph implements IDirectedGraph {

    /**
     * Generate a random directed graph
     * @param order the number of vertexes
     * @param nbEdges the number of edges
     * @return the random directed graph generated
     */
    public static int[][] getRandomDirectedGraph(int order, int nbEdges) {

        // Initialize the adjacency matrix with no edges
        int[][] adjacencyMatrix = new int[order][order];
        for(int i=0; i<order; i++) {
            for(int j=0; j<order; j++) {
                adjacencyMatrix[i][j] = O;
            }
        }

	    // Grab all possible edges
        List<Entry<Integer, Integer>> edges = new ArrayList<>();
        for(int i = 0; i< order; i++) {
            for(int j = 0; j< order; j++) {
                if(i != j)
                    edges.add(new AbstractMap.SimpleEntry<>(i, j));
            }
        }

	    // Randomly choose few edges
        List<Entry<Integer, Integer>> selectedEdges = new ArrayList<>();
        int nbEdgesPossible = edges.size();
        for(int i=0; i<nbEdges; i++) {
            int edgeToRemove = (int) Math.round(Math.random() * (--nbEdgesPossible));
            selectedEdges.add(edges.remove(edgeToRemove));
        }

	    // Update the adjacency matrix
        for(Map.Entry<Integer, Integer> edge : selectedEdges) {
	        int cost = (int) Math.round(Math.random() * MAX_COST) + 1;
	        adjacencyMatrix[edge.getKey()][edge.getValue()] = cost;
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
            for(int neighbor : this.getSuccessors(vertex)) {
                if(Boolean.FALSE.equals(mark.get(neighbor))) {
                    mark.set(neighbor, Boolean.TRUE);
                    minDistance.set(neighbor, minDistance.get(vertex)+1);
                    toVisit.add(neighbor);
                }
            }
        }

        return minDistance;
    }


    // FIXME il faut récupérer toutes les composantes fortements connexes (voir explorerGraph())
    @Override
    public List<Integer> depthFirstSearch(int baseVertex) {
        this.initializeTime();
        List<Boolean> mark = new ArrayList<>(Collections.nCopies(this.getOrder(), Boolean.FALSE));
        mark.set(baseVertex, Boolean.TRUE);

        Stack<Integer> toVisit = new Stack<>();
        toVisit.push(baseVertex);

        while(!toVisit.isEmpty()) {
            int vertex = toVisit.pop();
            start[vertex] = time++;
            for (int neighbor : this.getSuccessors(vertex)) {
                if (Boolean.FALSE.equals(mark.get(neighbor))) {
                    mark.set(neighbor, Boolean.TRUE);
                    toVisit.push(neighbor);
                }
            }
            end[vertex] = time++;
        }

        return null;
    }


    // FIXME does not seem to work, need to focus on undirected graph first
    @Override
    public BinaryHeap prim(int baseVertex) {
        return null;
    }

	// TODO floyd for directed graphs
	@Override
	public int[][] floyd() {
		return null;
	}
}
