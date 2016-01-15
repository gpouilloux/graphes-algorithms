package graph.entity.directed.impl;

import graph.entity.directed.AbstractDirectedGraph;
import graph.util.ListConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class IncidentMatrixDirectedGraph extends AbstractDirectedGraph {

	private int[][] incidentMatrix;
	private int[][] adjacencyMatrix;

	/**
	 * Constructeur d'une matrice d'incidence à partir d'une matrice d'adjacence générée aléatoirement
	 * 
	 * @param order le nombre de sommets
	 * @param nbEdges le nombre d'arêtes
	 */
	public IncidentMatrixDirectedGraph(int order, int nbEdges) {
		this.order = order;
		this.nbEdges = nbEdges;
		this.incidentMatrix = new int[order][nbEdges];

		this.adjacencyMatrix = AbstractDirectedGraph.getRandomDirectedGraph(order, nbEdges);

		int remainingEdges = nbEdges; // remaining edges to look for

		// convert the adjacency matrix into an incident matrix
		for(int i=0; i<order; i++) {
			for(int j=0; j<order; j++) {
				if(this.adjacencyMatrix[i][j] == 1) {
					this.incidentMatrix[i][nbEdges - remainingEdges] = 1;
					this.incidentMatrix[j][nbEdges - remainingEdges] = -1;
					remainingEdges--;
				}
			}
		}
	}

	/**
	 * Constructeur d'une matrice d'incidence à partir d'une liste d'adjacence
	 * Complexité au pire cas = number of vertexes * (number of edges / 2) = n*(m/2)
	 * 
	 * @param adjacencyList la liste d'adjacence
	 */
	public IncidentMatrixDirectedGraph(AdjacencyListDirectedGraph adjacencyList) {


		this.order = adjacencyList.getOrder();
		this.nbEdges = adjacencyList.getNbEdges();

		int[][] incidentMatrix = new int[this.order][this.nbEdges];

		int vertexNumber = 0;
		int remainingEdges = nbEdges;

		for(Entry<Integer, List<Integer>> e : adjacencyList.getAdjacencyList()) { // § number of vertexes
			for(Integer successor : e.getValue()) { // $ number of edges / 2
				incidentMatrix[vertexNumber][nbEdges - remainingEdges] = 1;
				incidentMatrix[successor][nbEdges - remainingEdges] = -1;
				remainingEdges--;
			}
			vertexNumber++;
		}

		this.incidentMatrix = incidentMatrix;
	}

	@Override
	public int[][] getGraph() {
		return this.adjacencyMatrix;
	}

	@Override
	public int[] getSuccessors(int x) {
		List<Integer> successors = new ArrayList<>();

		int[] edges = this.incidentMatrix[x];
		for(int i=0; i<edges.length; i++) {
			if(edges[i] == 1) {
				for(int j=0; j<this.order; j++) {
					if(this.incidentMatrix[j][i] == -1) {
						successors.add(new Integer(j));
					}
				}
			}
		}

		return ListConverter.toArray(successors);
	}

	@Override
	public int[] getPredecessors(int x) {
		List<Integer> predecessors = new ArrayList<>();

		int[] edges = this.incidentMatrix[x];
		for(int i=0; i<edges.length; i++) {
			if(edges[i] == -1) {
				for(int j=0; j<this.order; j++) {
					if(this.incidentMatrix[j][i] == 1) {
						predecessors.add(j);
					}
				}
			}
		}

		return ListConverter.toArray(predecessors);
	}

	@Override
	public boolean isEdge(int x, int y) {
		int[] edgesOfX = this.incidentMatrix[x];
		int[] edgesOfY = this.incidentMatrix[y];
		boolean isEdge = false;
		int i=0;

		while(!isEdge && i < edgesOfX.length) {
			if(edgesOfX[i] == 1 && edgesOfY[i] == -1) {
				isEdge = true;
			}
			i++;
		}

		return isEdge;
	}

	@Override
	public int addVertex() {
		int[][] newIncidentMatrix = new int[this.order+1][this.nbEdges];

		for(int i=0; i<this.order; i++) {
			for(int j=0; j<this.nbEdges; j++) {
				newIncidentMatrix[i][j] = this.incidentMatrix[i][j];
			}
		}

		this.incidentMatrix = newIncidentMatrix;

		return (++this.order);
	}

	@Override
	public void removeArc(int x, int y) {
		int[] edgesOfX = this.incidentMatrix[x];
		int[] edgesOfY = this.incidentMatrix[y];

		boolean edgeFounded = false;
		int columnEdge=0; // to keep the column id to remove

		while(!edgeFounded && columnEdge < edgesOfX.length) {
			if(edgesOfX[columnEdge] == 1 && edgesOfY[columnEdge] == -1) {
				edgeFounded = true;
			} else {
				columnEdge++;
			}
		}

		if(edgeFounded) {
			int[][] newIncidentMatrix = new int[this.order][--this.nbEdges];
			int originalJ;

			for(int i=0; i<this.order; i++) {
				originalJ = 0;
				for(int j=0; j<this.nbEdges; j++) {
					if(j == columnEdge) {
						originalJ++;
					} 
					newIncidentMatrix[i][j] = this.incidentMatrix[i][originalJ];
					originalJ++;
				}
			}

			this.incidentMatrix = newIncidentMatrix;
		}
	}

	@Override
	public void addArc(int x, int y) {
		int[][] newIncidentMatrix = new int[this.order][this.nbEdges+1];

		for(int i=0; i<this.order; i++) {
			for(int j=0; j<this.nbEdges; j++) {
				newIncidentMatrix[i][j] = this.incidentMatrix[i][j];
			}
		}
		newIncidentMatrix[x][this.nbEdges] = 1;
		newIncidentMatrix[y][this.nbEdges] = -1;

		this.incidentMatrix = newIncidentMatrix;
		this.nbEdges++;
	}

	@Override
	public void display() {
		for(int i=0; i<this.order; i++) {
			for(int j=0; j<this.nbEdges; j++) {
				System.out.print(this.incidentMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public IncidentMatrixDirectedGraph inverse() {
		// TODO
		return null;
	}

}
