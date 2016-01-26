package graph.util;

import java.util.Arrays;

/**
 * BinaryHeap ; compact representation
 * Each vertex has an int value and a weight
 */
public class BinaryHeap {

//    Chaque case de weights représente un sommet de l'arbre possédant une valeur (son poidsà
//
//    Les fils de weights[i] sont weights[2i+1] et weights[2i+2]
//    Le père de weights[i] est weights[(i-1)/2]

    private int[] weights;
    private int[] indexes;

    /**
     * Build a tree from an array of weights
     * @param weights list of weights
     * @param indexes list of indexes
     */
    public BinaryHeap(int[] weights, int[] indexes) {
        this.weights = new int[weights.length];
        this.indexes = new int[indexes.length];

        this.weights[0] = weights[0];
        this.indexes[0] = indexes[0];
        for(int i=1; i<weights.length; i++) {
            this.weights[i] = weights[i];
            this.indexes[i] = indexes[i];
            // Added value goes up until it is higher than its parent
            boolean treeIsOrdered = this.weights[i] >= this.weights[(i-1)/2];
            int leafId = i;
            while(!treeIsOrdered) {
                if(this.weights[leafId] < this.weights[(leafId-1)/2]) {
                    this.swapVertexes(leafId, (leafId-1)/ 2);
                    leafId = (leafId - 1) / 2;
                } else {
                    treeIsOrdered = Boolean.TRUE;
                }
            }
        }
    }

    /**
     * Swap two weights with their indexes
     * @param i one vertex
     * @param j another vertex
     */
    private void swapVertexes(int i, int j) {
        int tmp = this.weights[i];
        int tmpIndex = this.indexes[i];
        this.weights[i] = this.weights[j];
        this.indexes[i] = this.indexes[j];
        this.weights[j] = tmp;
        this.indexes[j] = tmpIndex;
    }

    /**
     * Insert an index in {BinaryHeap}
     *
     * @param weight the weight
     * @param index the index
     */
    public void insert(int weight, int index) {
        // 1. Add the index in the first leaf available
        int[] newWeights = new int[this.weights.length+1];
        System.arraycopy(this.weights, 0, newWeights, 0, this.weights.length);
        int leafId = this.weights.length;
        int[] newIndexes = new int[this.indexes.length+1];
        System.arraycopy(this.indexes, 0, newIndexes, 0, this.indexes.length);

        newWeights[leafId] = weight;
        newIndexes[leafId] = index;
        this.weights = newWeights;
        this.indexes = newIndexes;

        // 2. The index goes up until its parent is lower or equal
        boolean treeIsOrdered = weights[leafId] >= weights[(leafId-1)/2];
        while(!treeIsOrdered) {
            if(weights[leafId] < this.weights[(leafId-1)/2]) {
                this.swapVertexes(leafId, (leafId-1)/ 2);
                leafId = (leafId-1)/2;
            } else {
                treeIsOrdered = Boolean.TRUE;
            }
        }
    }

    /**
     * Remove the smallest element in {BinaryHeap}
     */
    public void removeSmallestElement() {
        // 1. Permute root with last leaf
        this.swapVertexes(0, this.weights.length-1);

        // 2. Copy weights array without last element
        int[] newWeights = new int[this.weights.length-1];
        System.arraycopy(this.weights, 0, newWeights, 0, this.weights.length-1);
        int[] newIndexes = new int[this.indexes.length-1];
        System.arraycopy(this.indexes, 0, newIndexes, 0, this.indexes.length-1);

        // 3. Permuted last leaf goes down until all its children are greater
        this.weights = newWeights;
        this.indexes = newIndexes;
        int leafId = 0;
        boolean treeIsOrdered = Boolean.FALSE;
        while(!treeIsOrdered) {
            if(this.hasChildren(leafId)) {
                if (this.weights[leafId] > this.weights[2 * leafId + 1]) {
                    this.swapVertexes(leafId, 2 * leafId + 1);
                    leafId = 2 * leafId + 1;
                } else if (this.weights[leafId] > this.weights[2 * leafId + 2]) {
                    this.swapVertexes(leafId, 2 * leafId + 2);
                    leafId = 2 * leafId + 2;
                } else {
                    treeIsOrdered = Boolean.TRUE;
                }
            } else {
                treeIsOrdered = Boolean.TRUE;
            }
        }
    }

    /**
     * Check if a vertex has children or not
     * @param i the vertex
     * @return true if the vertex has children, false otherwise
     */
    private boolean hasChildren(int i) {
        return 2*i+1 < this.weights.length && 2*i+2 < this.weights.length;
    }

    public void display() {
        // TODO
    }

    public int[] getWeights() {
        return weights;
    }

    public void setWeights(int[] weights) {
        this.weights = weights;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryHeap binaryHeap = (BinaryHeap) o;

        return Arrays.equals(this.weights, binaryHeap.weights) &&
                Arrays.equals(this.indexes, binaryHeap.indexes);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.weights);
    }

}
