package tests;

import graph.entity.undirected.impl.AdjacencyListUndirectedGraph;
import graph.entity.undirected.impl.AdjacencyMatrixUndirectedGraph;
import graph.entity.undirected.impl.IncidentMatrixUndirectedGraph;

// TODO convert to JUnit test
public class TestUndirected {
	public static void main(String[] args) {
		int ordre = 4;

		AdjacencyMatrixUndirectedGraph amug = new AdjacencyMatrixUndirectedGraph(ordre, 3);
		
		System.out.println("== Affichage matrice d'adjacence ==");
		amug.display();
		
		System.out.println("== Affichage neighbors ==");
		int[] neighbors = amug.getNeighbors(1);
		for(int i=0; i<neighbors.length; i++) {
			System.out.print(neighbors[i] + " ");
		}
		System.out.println();
		
		System.out.println("== Check if it is an edge ==");
		System.out.println("result = " + amug.isEdge(0, 2));
		
		System.out.println("== Add a new vertex ==");
		System.out.println("result = " + amug.addVertex());
		
		System.out.println("== Add a new edge == ");
		amug.addEdge(ordre, 2);
		
		System.out.println("== Affichage matrice d'adjacence ==");
		amug.display();
		
		System.out.println("== Convert to adjacency list ==");
	    AdjacencyListUndirectedGraph alug = new AdjacencyListUndirectedGraph(amug);
	    alug.display();
	    
		System.out.println("== Check if it is an edge ==");
		System.out.println("result = " + alug.isEdge(0, 2));
		
		System.out.println("== Add a new vertex ==");
		System.out.println("result = " + alug.addVertex());
		
		System.out.println("== Add a new edge == ");
		alug.addEdge(alug.getOrder()-1, 1);

		System.out.println("== Affichage liste d'adjacence ==");
		alug.display();
		
		System.out.println("== Create new incident matrix ==");
		IncidentMatrixUndirectedGraph imug = new IncidentMatrixUndirectedGraph(ordre, 3);
		imug.display();
		
		System.out.println("== Get neighbors ==");
		imug.getNeighbors(1);
		
		System.out.println("== Check if it is an edge ==");
		System.out.println("result = " + imug.isEdge(0, 2));
		
		System.out.println("== Add a new vertex ==");
		System.out.println("result = " + imug.addVertex());
		
		imug.display();
		
		System.out.println("== Remove an edge ==");
		imug.removeEdge(0, 2);
		
		imug.display();
		
		System.out.println("== Add an edge ==");
		imug.addEdge(0, 2);
		
		imug.display();
		
		System.out.println("== New adjacency list and try to create an incident matrix from it ==");
		amug.display();
		System.out.println();
	    AdjacencyListUndirectedGraph alug2 = new AdjacencyListUndirectedGraph(amug);
	    alug2.display();
	    System.out.println();
	    IncidentMatrixUndirectedGraph imug2 = new IncidentMatrixUndirectedGraph(alug2);
	    imug2.display();


	}
}
