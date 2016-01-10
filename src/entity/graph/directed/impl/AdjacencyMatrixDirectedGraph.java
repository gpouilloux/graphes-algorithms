package entity.graph.directed.impl;

import java.util.ArrayList;
import java.util.List;

import entity.graph.directed.AbstractDirectedGraph;
import util.ListConverter;

public class AdjacencyMatrixDirectedGraph extends AbstractDirectedGraph {

	private int[][] adjacencyMatrix;

	public AdjacencyMatrixDirectedGraph(int order, int nbEdges) {
		this.order = order;
		this.nbEdges = nbEdges;
		this.adjacencyMatrix = AbstractDirectedGraph.getRandomDirectedGraph(order, nbEdges);
	}

	@Override
	public int[][] getGraph() {
		return this.adjacencyMatrix;
	}

	@Override
	public int[] getSuccessors(int x) {
		// grabbing the successors of the vertex x
		List<Integer> successors = new ArrayList<>();
		for(int i=0; i<this.order; i++) {
			if(this.adjacencyMatrix[x][i] == 1) {
				successors.add(i);
			}
		}

		// converting the list to an array of int
		return ListConverter.toArray(successors);
	}

	@Override
	public int[] getPredecessors(int x) {
		// grabbing the predecessors of the vertex x
		List<Integer> predecessors = new ArrayList<>();
		for(int i=0; i<this.order; i++) {
			if(this.adjacencyMatrix[i][x] == 1) {
				predecessors.add(i);
			}
		}

		// converting the list to an array of int
		return ListConverter.toArray(predecessors);
	}

	@Override
	public boolean isEdge(int x, int y) {
		return this.adjacencyMatrix[x][y] == 1;
	}

	@Override
	public int addVertex() {
		int[][] newAdjacencyMatrix = new int[this.order+1][this.order+1];

		for(int i=0; i<this.order; i++) {
			for(int j=i+1; j<this.order; j++) {
				newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
				newAdjacencyMatrix[j][i] = adjacencyMatrix[j][i];
			}
		}

		this.adjacencyMatrix = newAdjacencyMatrix;

		return (++this.order);
	}

	@Override
	public void removeArc(int x, int y) {
		this.adjacencyMatrix[x][y] = 0;
		this.nbEdges--;
	}

	@Override
	public void addArc(int x, int y) {
		this.adjacencyMatrix[x][y] = 1;
		this.nbEdges++;		
	}

	@Override
	public void display() {
		for(int i=0; i<this.order; i++) {
			for(int j=0; j<this.order; j++) {
				System.out.print(this.adjacencyMatrix[i][j] + " ");
			}
			System.out.println("");
		}
	}

}
