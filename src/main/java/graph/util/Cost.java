package graph.util;

import graph.entity.directed.AbstractDirectedGraph;

/**
 * Classe permettant la manipulation des matrices de coûts
 * @author mclaveau
 */
public class Cost {


    public static int[][] getCostMatrix(AbstractDirectedGraph directedGraph, int maxCost) {
        int ordre = directedGraph.getGraph().length;
        int[][] costMatrix = new int[ordre][ordre];

        for(int i = 0 ; i < ordre ; i++) {
            for(int j = 0 ; j < ordre ; j++) {
                if (i != j) {
                    if (directedGraph.getGraph()[i][j] == 1) {
                        if (costMatrix[j][i] > 0) {
                            costMatrix[i][j] = costMatrix[j][i];
                        } else {
                            costMatrix[i][j] = (int) Math.round(Math.random() * maxCost);
                        }
                    }
                }
            }
        }

        return costMatrix;
    }
}
