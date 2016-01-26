package graph.entity;

import graph.util.BinaryHeap;

import java.util.List;

/**
 * Interface for the manipulation of a graph
 */
public interface IGraph {
    /**
     * Return the graph as an adjacency matrix
     * @return the graph as an adjacency matrix
     */
    int[][] getGraph();

    /**
     * Check whether an arc is an edge or not
     * @param x the source of the arc
     * @param y the target of the arc
     * @return true if it's an arc, otherwise false
     */
    boolean isEdge(int x, int y);

    /**
     * Add a vertex to the graph
     * @return the id of the added vertex
     */
    int addVertex();

    /**
     * Display the graph
     */
    void display();

    /**
     * Return the inverse of the graph
     * @return the inverse of the graph
     */
    IGraph inverse();

    /**
     * Walk through the graph using BFS method (parcours en largeur)
     *
     * @param baseVertex the vertex we use to start the walk
     * @return the minimum distance of each vertex against the starting one
     */
    List<Integer> breadthFirstSearch(int baseVertex);

    /**
     * Walk through the graph using DFS method (parcours en profondeur)
     * Imperative version
     *
     * @param baseVertex the vertex we use to start the walk
     */
    void depthFirstSearch(int baseVertex);

    /**
     * Compute all connected graphs
     * 1. Execute exploreGraph() and memorize end[]
     * 2. Inverse graph (see lesson p.32)
     * 3. Execute exploreGraph() by calling edges decrease order by end[]
     * @return list of connected graphs
     */
    List<IGraph> computeConnectedGraphs();

    /**
     * Prim algorithm with BFS method
     *
     * @param baseVertex the base vertex used when starting the walk
     */
    BinaryHeap prim(int baseVertex);
}
