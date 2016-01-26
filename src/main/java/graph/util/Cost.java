package graph.util;

import graph.entity.directed.AbstractDirectedGraph;
import graph.entity.directed.impl.AdjacencyListDirectedGraph;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe permettant la manipulation des matrices de coûts
 * @author mclaveau
 */
public class Cost {

    int[][] costMatrix;

    public Cost(AbstractDirectedGraph directedGraph, int maxCost){
        int ordre = directedGraph.getGraph().length;
        int[][] costMatrixTmp = new int[ordre][ordre];

        for(int i = 0 ; i < ordre ; i++) {
            for(int j = 0 ; j < ordre ; j++) {
                if (i != j) {
                    if (directedGraph.getGraph()[i][j] == 1) {
                        if (costMatrixTmp[j][i] > 0) {
                            costMatrixTmp[i][j] = costMatrixTmp[j][i];
                        } else {
                            costMatrixTmp[i][j] = (int) Math.round(Math.random() * maxCost);
                        }
                    }
                }
            }
        }

         this.costMatrix = costMatrixTmp;

    }

    public int[][] getCostMatrix() {
        return costMatrix;
    }


}
