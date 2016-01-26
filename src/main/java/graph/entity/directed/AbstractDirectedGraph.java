package graph.entity.directed;

import graph.entity.tree.BinaryHeap;
import graph.util.ListConverter;

import java.util.*;
import java.util.Map.Entry;

import static java.util.Comparator.comparingInt;

public abstract class AbstractDirectedGraph implements IDirectedGraph {

    protected int order;

    protected int nbEdges;

    private int[] start;
    private int[] end;
    private int time;

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

    private void initializeTime() {
        this.time = 0;
        this.start = new int[this.getOrder()];
        this.end = new int[this.getOrder()];
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

    Comparator<Map.Entry<Integer, Integer>> byMapValues = (left, right) -> left.getValue().compareTo(right.getValue());

    @Override
    public List<IDirectedGraph> computeConnectedGraphs() {
        int baseVertex = (int)(Math.random() * this.getOrder()); // choose base vertex randomly
        this.depthFirstSearch(baseVertex);

        IDirectedGraph inverse = this.inverse();

        List<Map.Entry<Integer, Integer>> endMap = new ArrayList<>();
        for(int i=0; i<end.length; i++) {
            endMap.add(new AbstractMap.SimpleEntry<>(i, end[i]));
        }

        // reverse order for array end
        Collections.sort(endMap, byMapValues.reversed());

        for(Map.Entry<Integer, Integer> end : endMap) {
            inverse.depthFirstSearch(end.getKey());
        }

        return null;
    }

    /**
     * Finds the minimum element in a weightArray of Integer and removes it
     *
     * @param weightArray the weightArray of Integer
     * @param vertexes the vertexes
     * @return the minimum element in the weightArray
     */
    private Integer peekMinElement(int[] weightArray, List<Integer> vertexes) {
        Integer min = weightArray[0];
        Integer minElt = vertexes.get(0);
        for(int i=1; i<vertexes.size(); i++) {
            Integer elt = vertexes.get(i);
            if(weightArray[i] < min) {
                min = weightArray[i];
                minElt = elt;
            }
        }

        vertexes.remove(minElt);
        return minElt;
    }

    /**
     * Prim algorithm with BFS method
     *
     * @param baseVertex le sommet de base
     * @param cout matrice des couts
     */
    public BinaryHeap prim(int baseVertex, int[][] cout) {
        List<Integer> successors = ListConverter.toList(this.getSuccessors(baseVertex));
        int predecessors[] = new int[this.getOrder()];
        int weights[] = new int[this.getOrder()];

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
