package entity.graph.undirected.impl;

import java.util.ArrayList;
import java.util.List;

import entity.graph.undirected.AbstractUndirectedGraph;
import util.ListConverter;

public class AdjacencyMatrixUndirectedGraph extends AbstractUndirectedGraph {

	private int[][] adjacencyMatrix;
	
	public AdjacencyMatrixUndirectedGraph(int ordre, int nbEdges) {
		this.order = ordre;
		this.nbEdges = nbEdges;
		this.adjacencyMatrix = AbstractUndirectedGraph.getRandomUndirectedGraph(ordre, nbEdges);
	}

	@Override
	public int[][] getGraph() {
		return this.adjacencyMatrix;
	}

	@Override
	public int[] getNeighbors(int x) {
		// grabbing the neighbors of the vertex x
		List<Integer> neighbors = new ArrayList<>();
		for(int i=0; i<this.order; i++) {
			if(this.adjacencyMatrix[x][i] == 1) {
				neighbors.add(i);
			}
		}
		
		// converting the list to an array of int
		return ListConverter.toArray(neighbors);
		
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
	public void removeEdge(int x, int y) {
		this.adjacencyMatrix[x][y] = 0;
		this.adjacencyMatrix[y][x] = 0;
		this.nbEdges--;
	}

	@Override
	public void addEdge(int x, int y) {
		this.adjacencyMatrix[x][y] = 1;
		this.adjacencyMatrix[y][x] = 1;
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
