package tests;

import entity.graph.directed.impl.AdjacencyListDirectedGraph;
import entity.graph.directed.impl.AdjacencyMatrixDirectedGraph;
import entity.graph.directed.impl.IncidentMatrixDirectedGraph;
import util.ListConverter;

// TODO convert to JUnit test
public class TestDirected {
	public static void main(String[] args) {
		int ordre = 4;

		AdjacencyMatrixDirectedGraph amdg = new AdjacencyMatrixDirectedGraph(ordre, 3);

		System.out.println("== Affichage matrice d'adjacence ==");
		amdg.display();

		System.out.println("== Affichage successors ==");
		int[] successors = amdg.getSuccessors(1);
		for(int i=0; i<successors.length; i++) {
			System.out.print(successors[i] + " ");
		}
		System.out.println();
		
		System.out.println("== Affichage predecessors ==");
		int[] predecessors = amdg.getPredecessors(1);
		for(int i=0; i<predecessors.length; i++) {
			System.out.print(predecessors[i] + " ");
		}
		System.out.println();


		System.out.println("== Check if it is an edge ==");
		System.out.println("result = " + amdg.isEdge(0, 2));

		System.out.println("== Add a new vertex ==");
		System.out.println("result = " + amdg.addVertex());

		System.out.println("== Add a new arc == ");
		amdg.addArc(ordre, 2);

		System.out.println("== Affichage matrice d'adjacence ==");
		amdg.display();
		
		System.out.println("== Convert to adjacency list ==");
	    AdjacencyListDirectedGraph aldg = new AdjacencyListDirectedGraph(amdg);
	    aldg.display();
	    
		System.out.println("== Check if it is an edge ==");
		System.out.println("result = " + aldg.isEdge(0, 2));
		
		System.out.println("== Add a new vertex ==");
		System.out.println("result = " + aldg.addVertex());
		
		System.out.println("== Add a new arc == ");
		aldg.addArc(aldg.getOrder()-1, 1);

		System.out.println("== Affichage liste d'adjacence ==");
		aldg.display();

		
		System.out.println("== Create new incident matrix ==");
		IncidentMatrixDirectedGraph imdg = new IncidentMatrixDirectedGraph(ordre, 3);
		imdg.display();
		
		System.out.println("== Get successors ==");
		int[] successors2 = imdg.getSuccessors(1);
		ListConverter.toList(successors2).stream().forEach(System.out::println);
		
		System.out.println("== Check if it is an edge ==");
		System.out.println("result = " + imdg.isEdge(0, 2));
		
		System.out.println("== Add a new vertex ==");
		System.out.println("result = " + imdg.addVertex());
		
		imdg.display();
		
		System.out.println("== Remove an arc ==");
		imdg.removeArc(0, 2);
		
		imdg.display();
		
		System.out.println("== Add an arc ==");
		imdg.addArc(0, 2);
		
		imdg.display();
		
		System.out.println("== New adjacency list and try to create an incident matrix from it ==");
		amdg.display();
		System.out.println();
	    AdjacencyListDirectedGraph aldg2 = new AdjacencyListDirectedGraph(amdg);
	    aldg2.display();
	    System.out.println();
	    IncidentMatrixDirectedGraph imdg2 = new IncidentMatrixDirectedGraph(aldg2);
	    imdg2.display();


	}
}
