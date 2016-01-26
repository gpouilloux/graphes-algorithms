package graph.entity.undirected.impl;

import graph.entity.undirected.AbstractUndirectedGraph;
import graph.util.ListConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * Représentation d'un graphe avec une matrice d'incidence
 *
 * @author gpouillo
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
                if(this.adjacencyMatrix[i][j] == 1) {
                    this.incidentMatrix[i][nbEdges - remainingEdges] = 1;
                    this.incidentMatrix[j][nbEdges - remainingEdges] = 1;
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
    public IncidentMatrixUndirectedGraph(AdjacencyListUndirectedGraph adjacencyList) {


        this.order = adjacencyList.getOrder();
        this.nbEdges = adjacencyList.getNbEdges();

        int[][] incidentMatrix = new int[this.order][this.nbEdges];

        int vertexNumber = 0;
        int remainingEdges = nbEdges;

        for(Entry<Integer, List<Integer>> e : adjacencyList.getAdjacencyList()) { // § number of vertexes
            for(Integer neighbor : e.getValue()) { // $ number of edges / 2
                incidentMatrix[vertexNumber][nbEdges - remainingEdges] = 1;
                incidentMatrix[neighbor][nbEdges - remainingEdges] = 1;
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
            if(edges[i] == 1) {
                for(int j=0; j<this.order; j++) {
                    if(this.incidentMatrix[j][i] == 1 && j != x) {
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
            if(edgesOfX[i] == 1 && edgesOfY[i] == 1) {
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
            if(edgesOfX[columnEdge] == 1 && edgesOfY[columnEdge] == 1) {
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
    public void addEdge(int x, int y) {
        int[][] newIncidentMatrix = new int[this.order][this.nbEdges+1];

        for(int i=0; i<this.order; i++) {
            for(int j=0; j<this.nbEdges; j++) {
                newIncidentMatrix[i][j] = this.incidentMatrix[i][j];
            }
        }
        newIncidentMatrix[x][this.nbEdges] = 1;
        newIncidentMatrix[y][this.nbEdges] = 1;

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
                if(this.incidentMatrix[i][j] == 1) {
                    inverseIncidentMatrix[i][j] = -1;
                } else if(this.incidentMatrix[i][j] == -1) {
                    inverseIncidentMatrix[i][j] = 1;
                } else {
                    inverseIncidentMatrix[i][j] = 0;
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
