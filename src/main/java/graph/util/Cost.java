package graph.util;

import graph.entity.IGraph;

/**
 * Utility class for the generation of random cost matrix
 * @author mclaveau
 */
// FIXME terminates dependencies on this class and remove it!
@Deprecated
public class Cost {

    private Cost() {
        // not allowed to instantiate this util class
    }

    public static int[][] getCostMatrix(IGraph graph, int maxCost) {
        int ordre = graph.getGraph().length;
        int[][] costMatrix = new int[ordre][ordre];

        for(int i = 0 ; i < ordre ; i++) {
            for(int j = 0 ; j < ordre ; j++) {
                if (i != j) {
                    if (graph.getGraph()[i][j] == 1) {
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
