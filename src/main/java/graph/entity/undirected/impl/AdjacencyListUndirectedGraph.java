package graph.entity.undirected.impl;

import graph.entity.undirected.AbstractUndirectedGraph;
import graph.util.ListConverter;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

/**
 * Adjacency list representing undirected graphs
 *
 * @author Guillaume Pouilloux
 */
public class AdjacencyListUndirectedGraph extends AbstractUndirectedGraph {

	private List<Entry<Integer, List<Integer>>> adjacencyList = new ArrayList<>();
	private AdjacencyMatrixUndirectedGraph adjacencyMatrix;

	public AdjacencyListUndirectedGraph(AdjacencyMatrixUndirectedGraph adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix; // still useful to backup the adjacency matrix
		this.order = adjacencyMatrix.getOrder();
		this.nbEdges = adjacencyMatrix.getNbEdges();
		int succInc = 0; // enable count of vertex's successors

		// build the adjacency list by iterating through each vertex and looking for its successors (i.e neighbors)
		for(int i=0; i<this.order; i++) {
			int[] neighbors = adjacencyMatrix.getNeighbors(i);

			adjacencyList.add(new AbstractMap.SimpleEntry<>(succInc, ListConverter.toList(neighbors)));
			succInc+=neighbors.length;
		}
	}

	@Override
	public int[][] getGraph() {
		return this.adjacencyMatrix.getGraph();
	}

	@Override
	public int[] getNeighbors(int x) {
		return ListConverter.toArray(this.adjacencyList.get(x).getValue());
	}

	@Override
	public boolean isEdge(int x, int y) {
		return this.adjacencyList.get(x).getValue().contains(y);
	}

	@Override
	public int addVertex() {
		this.adjacencyList.add(new AbstractMap.SimpleEntry<>(++this.order, new ArrayList<>()));
		return this.order;
	}

	@Override
	public void removeEdge(int x, int y) {
		this.adjacencyList.get(x).getValue().remove(new Integer(y));
		this.nbEdges--;
	}

	@Override
	public void addEdge(int x, int y, int cost) {
		this.adjacencyList.get(x).getValue().add(y);
		this.nbEdges++;
	}

	@Override
	public AdjacencyListUndirectedGraph inverse() {
		List<Integer> inverseVertices = new ArrayList<>(Collections.nCopies(this.getOrder(), 0));


		// Compute the number of successors for each vertices
		for(int i=0; i<this.adjacencyList.size(); i++) {
			Entry<Integer, List<Integer>> vertex = this.adjacencyList.get(i);
			vertex.getValue().forEach(s -> inverseVertices.set(s, inverseVertices.get(s)+1));
		}

		// Sum the number of successors
		int succInc = 0; // enable count of vertex's successors
		for(int i = 0; i< inverseVertices.size(); i++) {
			succInc += inverseVertices.get(i);
			inverseVertices.set(i, succInc - inverseVertices.get(i));
		}

		// Initialize the inverse graph
		List<Entry<Integer, List<Integer>>> inverseAdjacencyList = new ArrayList<>();
		for(Integer vertex : inverseVertices) {
			inverseAdjacencyList.add(new AbstractMap.SimpleEntry<>(vertex, new ArrayList<>(vertex)));
		}

		// Feed the inverse graph
		for(int i=0; i<this.adjacencyList.size(); i++) {
			final int vertexId = i;
			ListConverter.toList(this.getNeighbors(vertexId)).forEach(s -> inverseAdjacencyList.get(s).getValue().add(vertexId));
		}

        AdjacencyListUndirectedGraph inverseGraph = this;
		inverseGraph.setAdjacencyList(inverseAdjacencyList);
		return inverseGraph;
	}

	@Override
	public void display() {
		for(Entry<Integer, List<Integer>> node : this.adjacencyList) {
			System.out.print(node.getKey() + " ==> ");	    	
			node.getValue().stream().forEach(s -> System.out.print(s + " "));
			System.out.println();
		}
	}

	public List<Entry<Integer, List<Integer>>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(List<Entry<Integer, List<Integer>>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

}
