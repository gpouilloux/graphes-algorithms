package graph.entity.directed;

import graph.entity.impl.AbstractGraph;
import graph.util.BinaryHeap;
import graph.util.ListConverter;

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
        int[][] adjacencyMatrix = new int[order][order];

        // Récupération de toutes les arêtes
        List<Entry<Integer, Integer>> edges = new ArrayList<>();
        for(int i = 0; i< order; i++) {
            for(int j = 0; j< order; j++) {
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
    // TODO stocker start & end
    @Override
    public void depthFirstSearch(int baseVertex) {
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
    }


    // FIXME does not seem to work, need to focus on undirected graph first
    @Override
    public BinaryHeap prim(int baseVertex) {
        List<Integer> successors = ListConverter.toList(this.getSuccessors(baseVertex));
        int predecessors[] = new int[this.getOrder()];
        int weights[] = new int[this.getOrder()];
        int cout[][] = this.getGraph();

        // initialization
        for(int i = 0; i< weights.length; i++) {
            if(successors.contains(i)) {
                predecessors[i] = baseVertex;
                weights[i] = cout[baseVertex][i];
            } else {
                predecessors[i] = -1; // -1 is like nil :-)
                weights[i] = Integer.MAX_VALUE;
            }
        }
        weights[baseVertex] = 0;

        // pour le parcours de tous les sommets sauf celui de base
        List<Integer> vertexes = new ArrayList<>();
        for(int i=0; i<this.getOrder(); i++) {
            if(i != baseVertex) {
                vertexes.add(i);
            }
        }

        BinaryHeap binaryHeap = new BinaryHeap(new int[]{weights[baseVertex]}, new int[]{baseVertex});


        while(!vertexes.isEmpty()) {
            int vertex = this.peekMinElement(weights, vertexes); // get vertex with lowest weight
            binaryHeap.insert(weights[vertex], vertex); // insert in the binary heap
            int succ[] = this.getSuccessors(vertex);
            for(int i=0; i<succ.length; i++) {
                if(vertexes.contains(i) && weights[i] > cout[vertex][i]) {
                    weights[i] = cout[vertex][i];
                    predecessors[i] = vertex;
                }
            }
        }

        return binaryHeap;
    }

}
