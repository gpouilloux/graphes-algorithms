package graph.entity.undirected.impl;

import graph.entity.undirected.AbstractUndirectedGraph;
import graph.util.ListConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Incident matrix representing undirect graphs
 *
 * @author Guillaume Pouilloux
 *
 */
public class IncidentMatrixUndirectedGraph extends AbstractUndirectedGraph {

    private int[][] incidentMatrix;
    private int[][] adjacencyMatrix;

    /**
     * Constructeur d'une matrice d'incidence à partir d'une matrice d'adjacence générée aléatoirement
     *
     * @param order le nombre de sommets
     * @param nbEdges le nombre d'arêtes
     */
    public IncidentMatrixUndirectedGraph(int order, int nbEdges) {
        this.order = order;
        this.nbEdges = nbEdges;
        this.incidentMatrix = new int[order][nbEdges];

        this.adjacencyMatrix = AbstractUndirectedGraph.getRandomUndirectedGraph(order, nbEdges);

        int remainingEdges = nbEdges; // remaining edges to look for

        // convert the adjacency matrix into an incident matrix
        for(int i=0; i<order; i++) {
            for(int j=i+1; j<order; j++) {
                if(this.adjacencyMatrix[i][j] != O) {
                    this.incidentMatrix[i][nbEdges - remainingEdges] = this.adjacencyMatrix[i][j];
                    this.incidentMatrix[j][nbEdges - remainingEdges] = this.adjacencyMatrix[i][j];
                    remainingEdges--;
                }
            }
        }
    }

    /**
     * Constructeur d'une matrice d'incidence à partir d'une liste d'adjacence
     * Complexité au pire cas = number of vertices * (number of edges / 2) = n*(m/2)
     *
     * @param adjacencyList la liste d'adjacence
     */
    public IncidentMatrixUndirectedGraph(AdjacencyListUndirectedGraph adjacencyList) {
        this.order = adjacencyList.getOrder();
        this.nbEdges = adjacencyList.getNbEdges();
	    this.adjacencyMatrix = adjacencyList.getGraph();

	    // Initialize the incident matrix with no edjes
        int[][] incidentMatrix = new int[this.order][this.nbEdges];
        for(int i=0; i<this.order; i++) {
	        for(int j=0; j<this.nbEdges; j++) {
		        incidentMatrix[i][j] = O;
	        }
        }

        int vertexNumber = 0;
        int remainingEdges = nbEdges;

        for(Entry<Integer, List<Integer>> e : adjacencyList.getAdjacencyList()) { // § number of vertices
            for(Integer neighbor : e.getValue()) { // $ number of edges / 2
	            int cost = adjacencyMatrix[vertexNumber][neighbor];
                incidentMatrix[vertexNumber][nbEdges - remainingEdges] = cost;
                incidentMatrix[neighbor][nbEdges - remainingEdges] = cost;
                remainingEdges--;

                // remove same edge as the graph is undirected
                adjacencyList.getAdjacencyList().get(neighbor).getValue().remove(new Integer(vertexNumber));
            }
            vertexNumber++;
        }

        this.incidentMatrix = incidentMatrix;
    }

    public IncidentMatrixUndirectedGraph(IncidentMatrixUndirectedGraph incidentMatrix) {
        this.order = incidentMatrix.getOrder();
        this.nbEdges = incidentMatrix.getNbEdges();
        this.incidentMatrix = incidentMatrix.getIncidentMatrix();
        this.adjacencyMatrix = incidentMatrix.getGraph();
    }


    @Override
    public int[][] getGraph() {
        return this.adjacencyMatrix;
    }

    @Override
    public int[] getNeighbors(int x) {
        List<Integer> neighbors = new ArrayList<>();

        int[] edges = this.incidentMatrix[x];
        for(int i=0; i<edges.length; i++) {
            if(edges[i] != O) {
                for(int j=0; j<this.order; j++) {
                    if(this.incidentMatrix[j][i] == edges[i] && j != x) {
                        neighbors.add(j);
                    }
                }
            }
        }

        return ListConverter.toArray(neighbors);
    }

    @Override
    public boolean isEdge(int x, int y) {
        int[] edgesOfX = this.incidentMatrix[x];
        int[] edgesOfY = this.incidentMatrix[y];
        boolean isEdge = false;
        int i=0;

        while(!isEdge && i < edgesOfX.length) {
            if(edgesOfX[i] != Integer.MAX_VALUE && edgesOfX[i] == edgesOfY[i]) {
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
    public void removeEdge(int x, int y) {
        int[] edgesOfX = this.incidentMatrix[x];
        int[] edgesOfY = this.incidentMatrix[y];

        boolean edgeFounded = false;
        int columnEdge=0; // to keep the column id to remove

        while(!edgeFounded && columnEdge < edgesOfX.length) {
            if(edgesOfX[columnEdge] != O && edgesOfX[columnEdge] == edgesOfY[columnEdge]) {
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
    public void addEdge(int x, int y, int cost) {
        int[][] newIncidentMatrix = new int[this.order][this.nbEdges+1];

        for(int i=0; i<this.order; i++) {
            for(int j=0; j<this.nbEdges; j++) {
                newIncidentMatrix[i][j] = this.incidentMatrix[i][j];
            }
        }
        newIncidentMatrix[x][this.nbEdges] = cost;
        newIncidentMatrix[y][this.nbEdges] = cost;

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
    public IncidentMatrixUndirectedGraph inverse() {

        int[][] inverseIncidentMatrix = new int[this.getOrder()][this.getNbEdges()];

        for(int i=0; i<this.getOrder(); i++) {
            for(int j=0; j<this.getNbEdges(); j++) {
                if(this.incidentMatrix[i][j] != O) {
                    inverseIncidentMatrix[i][j] = -this.incidentMatrix[i][j];
                } else {
                    inverseIncidentMatrix[i][j] = O;
                }
            }
        }

        IncidentMatrixUndirectedGraph inverse = new IncidentMatrixUndirectedGraph(this);
        inverse.setIncidentMatrix(inverseIncidentMatrix);
        return inverse;
    }

    public int[][] getIncidentMatrix() {
        return incidentMatrix;
    }

    public void setIncidentMatrix(int[][] incidentMatrix) {
        this.incidentMatrix = incidentMatrix;
    }

}
