package graph.entity.directed.impl;

import graph.entity.directed.AbstractDirectedGraph;
import graph.util.ListConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Adjacency Matrix representing directed graph
 *
 * @author Guillaume Pouilloux
 */
public class AdjacencyMatrixDirectedGraph extends AbstractDirectedGraph {

    private int[][] adjacencyMatrix;

	public AdjacencyMatrixDirectedGraph(int order, int nbEdges) {
		this.order = order;
		this.nbEdges = nbEdges;
		this.adjacencyMatrix = AbstractDirectedGraph.getRandomDirectedGraph(order, nbEdges);
	}

	public AdjacencyMatrixDirectedGraph(int order, int nbEdges, int[][] adjacencyMatrix) {
		this.order = order;
		this.nbEdges = nbEdges;
		this.adjacencyMatrix = adjacencyMatrix;
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
			if(this.adjacencyMatrix[x][i] != O) {
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
			if(this.adjacencyMatrix[i][x] != O) {
				predecessors.add(i);
			}
		}

		// converting the list to an array of int
		return ListConverter.toArray(predecessors);
	}

	@Override
	public boolean isEdge(int x, int y) {
		return this.adjacencyMatrix[x][y] != O;
	}

	@Override
	public int addVertex() {
		int[][] newAdjacencyMatrix = new int[this.order+1][this.order+1];

		for(int i=0; i<this.order; i++) {
			for(int j=0; j<this.order; j++) {
				newAdjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}

		this.adjacencyMatrix = newAdjacencyMatrix;

		return (++this.order);
	}

	@Override
	public void removeArc(int x, int y) {
		this.adjacencyMatrix[x][y] = O;
		this.nbEdges--;
	}

	@Override
	public void addArc(int x, int y, int cost) {
		this.adjacencyMatrix[x][y] = cost;
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

	@Override
	public AdjacencyMatrixDirectedGraph inverse() {

        int[][] inverseAdjacencyMatrix = new int[this.getOrder()][this.getOrder()];

        for(int i=0; i<this.order; i++) {
            for(int j=0; j<this.order; j++) {
                inverseAdjacencyMatrix[i][j] = this.getAdjacencyMatrix()[j][i];
            }
        }

		return new AdjacencyMatrixDirectedGraph(this.getOrder(), this.getNbEdges(), inverseAdjacencyMatrix);
	}

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }
}
