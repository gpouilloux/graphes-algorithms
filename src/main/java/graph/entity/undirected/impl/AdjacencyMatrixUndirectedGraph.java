package graph.entity.undirected.impl;

import graph.entity.undirected.AbstractUndirectedGraph;
import graph.util.ListConverter;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyMatrixUndirectedGraph extends AbstractUndirectedGraph {

	private int[][] adjacencyMatrix;
	
	public AdjacencyMatrixUndirectedGraph(int ordre, int nbEdges) {
		this.order = ordre;
		this.nbEdges = nbEdges;
		this.adjacencyMatrix = AbstractUndirectedGraph.getRandomUndirectedGraph(ordre, nbEdges);
	}

	public AdjacencyMatrixUndirectedGraph(int order, int nbEdges, int[][] adjacencyMatrix) {
		this.order = order;
		this.nbEdges = nbEdges;
		this.adjacencyMatrix = adjacencyMatrix;
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
			if(this.isEdge(x, i)) {
				neighbors.add(i);
			}
		}
		
		// converting the list to an array of int
		return ListConverter.toArray(neighbors);
		
	}

	@Override
	public boolean isEdge(int x, int y) {
		return this.adjacencyMatrix[x][y] > 0;
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
	public void addEdge(int x, int y, int cost) {
		this.adjacencyMatrix[x][y] = cost;
		this.adjacencyMatrix[y][x] = cost;
		this.nbEdges++;
	}

	@Override
	public AdjacencyMatrixUndirectedGraph inverse() {

		int[][] inverseAdjacencyMatrix = new int[this.getOrder()][this.getOrder()];

		for(int i=0; i<this.order; i++) {
			for(int j=0; j<this.order; j++) {
				inverseAdjacencyMatrix[i][j] = this.getAdjacencyMatrix()[j][i];
			}
		}

		return new AdjacencyMatrixUndirectedGraph(this.getOrder(), this.getNbEdges(), inverseAdjacencyMatrix);
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

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
	

}
