package graph.entity.impl;

import graph.entity.IGraph;

import java.util.*;

/**
 * Abstract class implementing common methods for directed and undirected graphs
 */
public abstract class AbstractGraph implements IGraph {

    protected int order;

    protected int nbEdges;

    // Store time info while walking through the graph
    protected int[] start;
    protected int[] end;
    protected int time;

    @Override
    public List<IGraph> computeConnectedGraphs() {
        int baseVertex = (int)(Math.random() * this.getOrder()); // choose base vertex randomly
        this.depthFirstSearch(baseVertex);

        IGraph inverse = this.inverse();

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
     * Initialize time info stored when walking through the graph
     */
    protected void initializeTime() {
        this.time = 0;
        this.start = new int[this.getOrder()];
        this.end = new int[this.getOrder()];
    }


    /**
     * Finds the minimum element in a weightArray of Integer and removes it
     *
     * @param weightArray the weightArray of Integer
     * @param vertexes the vertexes
     * @return the minimum element in the weightArray
     */
    protected Integer peekMinElement(int[] weightArray, List<Integer> vertexes) {
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
     * Useful comparator used to compare two map entries by their value
     */
    Comparator<Map.Entry<Integer, Integer>> byMapValues = (left, right) -> left.getValue().compareTo(right.getValue());

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
