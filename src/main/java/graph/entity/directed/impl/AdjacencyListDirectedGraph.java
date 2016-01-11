package graph.entity.directed.impl;

import graph.entity.directed.AbstractDirectedGraph;
import graph.util.ListConverter;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class AdjacencyListDirectedGraph extends AbstractDirectedGraph {

	private List<Entry<Integer, List<Integer>>> adjacencyList = new ArrayList<Entry<Integer, List<Integer>>>();
	private AdjacencyMatrixDirectedGraph adjacencyMatrix;

	public AdjacencyListDirectedGraph(AdjacencyMatrixDirectedGraph adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix; // still useful to backup the adjacency matrix
		this.order = adjacencyMatrix.getOrder();
		this.nbEdges = adjacencyMatrix.getNbEdges();
		int succInc = 0; // enable count of vertex's successors

		// build the adjacency list by iterating through each vertex and looking for its successors (i.e neighbors)
		for(int i=0; i<this.order; i++) {
			int[] successors = adjacencyMatrix.getSuccessors(i);

			adjacencyList.add(new AbstractMap.SimpleEntry<Integer, List<Integer>>(succInc, ListConverter.toList(successors)));
			succInc+=successors.length;
		}
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
			if(vertex.getValue().contains(new Integer(x))) {
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
		this.adjacencyList.add(new AbstractMap.SimpleEntry<Integer, List<Integer>>(++this.order, new ArrayList<>()));
		return this.order;
	}

	@Override
	public void removeArc(int x, int y) {
		this.adjacencyList.get(x).getValue().remove(new Integer(y));
		this.nbEdges--;
	}

	@Override
	public void addArc(int x, int y) {
		this.adjacencyList.get(x).getValue().add(new Integer(y));
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
	
    public List<Entry<Integer, List<Integer>>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(List<Entry<Integer, List<Integer>>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

}
