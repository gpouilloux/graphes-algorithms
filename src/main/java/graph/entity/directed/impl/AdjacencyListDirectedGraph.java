package graph.entity.directed.impl;

import graph.entity.directed.AbstractDirectedGraph;
import graph.util.ListConverter;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

public class AdjacencyListDirectedGraph extends AbstractDirectedGraph {

	private List<Entry<Integer, List<Integer>>> adjacencyList = new ArrayList<>();
	private AdjacencyMatrixDirectedGraph adjacencyMatrix;


	public AdjacencyListDirectedGraph(AdjacencyMatrixDirectedGraph adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix; // still useful to backup the adjacency matrix
		this.order = adjacencyMatrix.getOrder();
		this.nbEdges = adjacencyMatrix.getNbEdges();
		int succInc = 0; // enable count of vertex's successors

		// build the adjacency list by iterating through each vertex and looking for its successors (i.e neighbors)
		for(int i=0; i<this.order; i++) {
			int[] successors = adjacencyMatrix.getSuccessors(i);

			adjacencyList.add(new AbstractMap.SimpleEntry<>(succInc, ListConverter.toList(successors)));
			succInc+=successors.length;
		}
	}

	public AdjacencyListDirectedGraph(AdjacencyListDirectedGraph adjacencyList) {
		this.adjacencyMatrix = adjacencyList.getAdjacencyMatrix();
		this.order = adjacencyList.getOrder();
		this.nbEdges = adjacencyList.getNbEdges();
		this.adjacencyList = adjacencyList.getAdjacencyList();
	}

	@Override
	public int[][] getGraph() {
		return this.adjacencyMatrix.getGraph();
	}

	@Override
	public int[] getSuccessors(int x) {
		return ListConverter.toArray(this.adjacencyList.get(x).getValue());
	}

	@Override
	public int[] getPredecessors(int x) {
		List<Integer> predecessors = new ArrayList<>();
		
		for(int i=0; i<this.adjacencyList.size(); i++) {
			Entry<Integer, List<Integer>> vertex = this.adjacencyList.get(i);
			if(vertex.getValue().contains(x)) {
				predecessors.add(i);
			}
		}
		
		return ListConverter.toArray(predecessors);
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
	public void removeArc(int x, int y) {
		this.adjacencyList.get(x).getValue().remove(new Integer(y));
		this.nbEdges--;
	}

	@Override
	public void addArc(int x, int y, int cost) {
		this.adjacencyList.get(x).getValue().add(y);
		this.nbEdges++;
	}

	@Override
	public void display() {
		for(Entry<Integer, List<Integer>> node : this.adjacencyList) {
			System.out.print(node.getKey() + " ==> ");
			node.getValue().stream().forEach(s -> System.out.print(s + " "));
			System.out.println();
		}
	}

	@Override
	public AdjacencyListDirectedGraph inverse() {
		// private List<Entry<Integer, List<Integer>>> adjacencyList = new ArrayList<>();


		List<Integer> inverseVertexes = new ArrayList<>(Collections.nCopies(this.getOrder(), 0));


		// Compute the number of successors for each vertexes
		for(int i=0; i<this.adjacencyList.size(); i++) {
			Entry<Integer, List<Integer>> vertex = this.adjacencyList.get(i);
			vertex.getValue().forEach(s -> inverseVertexes.set(s, inverseVertexes.get(s)+1));
		}

		// Sum the number of successors
		int succInc = 0; // enable count of vertex's successors
		for(int i = 0; i< inverseVertexes.size(); i++) {
			succInc += inverseVertexes.get(i);
			inverseVertexes.set(i, succInc - inverseVertexes.get(i));
		}

		// Initialize the inverse graph
		List<Entry<Integer, List<Integer>>> inverseAdjacencyList = new ArrayList<>();
		for(Integer vertex : inverseVertexes) {
			inverseAdjacencyList.add(new AbstractMap.SimpleEntry<>(vertex, new ArrayList<>(vertex)));
		}

		// Feed the inverse graph
		for(int i=0; i<this.adjacencyList.size(); i++) {
			final int vertexId = i;
			ListConverter.toList(this.getSuccessors(vertexId)).forEach(s -> inverseAdjacencyList.get(s).getValue().add(vertexId));
		}

		AdjacencyListDirectedGraph inverseGraph = new AdjacencyListDirectedGraph(this);
		inverseGraph.setAdjacencyList(inverseAdjacencyList);
		return inverseGraph;
	}

	public List<Entry<Integer, List<Integer>>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(List<Entry<Integer, List<Integer>>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public AdjacencyMatrixDirectedGraph getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public void setAdjacencyMatrix(AdjacencyMatrixDirectedGraph adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

}
