package graph.entity.tree;

import java.util.Arrays;

/**
 * Tree ; compact representation
 * Each vertex has an int value
 */
public class Tree {

//    Chaque case de vertexes représente un sommet de l'arbre possédant une valeur
//
//    Les fils de vertexes[i] sont vertexes[2i+1] et vertexes[2i+2]
//    Le père de vertexes[i] est vertexes[(i-1)/2]

    private int[] vertexes;

    /**
     * Build a tree from an array of values
     * @param values
     */
    public Tree(int[] values) {
        this.vertexes = new int[values.length];

        this.vertexes[0] = values[0];
        for(int i=1; i<values.length; i++) {
            this.vertexes[i] = values[i];
            // Added value goes up until it is higher than its parent
            boolean treeIsOrdered = vertexes[i] >= this.vertexes[(i-1)/2];
            int leafId = i;
            while(!treeIsOrdered) {
                if(vertexes[leafId] < this.vertexes[(leafId-1)/2]) {
                    this.swapVertexes(leafId, (leafId-1)/ 2);
                    leafId = (leafId - 1) / 2;
                } else {
                    treeIsOrdered = Boolean.TRUE;
                }
            }
        }
    }

    /**
     * Swap two vertexes
     * @param i one vertex
     * @param j another vertex
     */
    private void swapVertexes(int i, int j) {
        int tmp = this.vertexes[i];
        this.vertexes[i] = this.vertexes[j];
        this.vertexes[j] = tmp;
    }

    /**
     * Insert an element in {Tree}
     *
     * @param element the element
     */
    public void insert(int element) {
        // 1. Add the element in the first leaf available
        int[] newVertexes = new int[this.vertexes.length+1];
        System.arraycopy(this.vertexes, 0, newVertexes, 0, this.vertexes.length);
        int leafId = this.vertexes.length;
        newVertexes[leafId] = element;
        this.vertexes = newVertexes;

        // 2. The element goes up until its parent is lower or equal
        boolean treeIsOrdered = vertexes[leafId] >= vertexes[(leafId-1)/2];
        while(!treeIsOrdered) {
            if(vertexes[leafId] < this.vertexes[(leafId-1)/2]) {
                this.swapVertexes(leafId, (leafId-1)/ 2);
                leafId = (leafId-1)/2;
            } else {
                treeIsOrdered = Boolean.TRUE;
            }
        }
    }

    /**
     * Remove the smallest element in {Tree}
     */
    public void removeSmallestElement() {
        // 1. Permute root with last leaf
        this.swapVertexes(0, this.vertexes.length-1);

        // 2. Copy vertexes array without last element
        int[] newVertexes = new int[this.vertexes.length-1];
        System.arraycopy(this.vertexes, 0, newVertexes, 0, this.vertexes.length-1);

        // 3. Permuted last leaf goes down until all its children are greater
        this.vertexes = newVertexes;
        int leafId = 0;
        boolean treeIsOrdered = Boolean.FALSE;
        while(!treeIsOrdered) {
            if(this.hasChildren(leafId)) {
                if (this.vertexes[leafId] > this.vertexes[2 * leafId + 1]) {
                    this.swapVertexes(leafId, 2 * leafId + 1);
                    leafId = 2 * leafId + 1;
                } else if (this.vertexes[leafId] > this.vertexes[2 * leafId + 2]) {
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

    public void prim() {

    }

    /**
     * Check if a vertex has children or not
     * @param i the vertex
     * @return true if the vertex has children, false otherwise
     */
    private boolean hasChildren(int i) {
        return 2*i+1 < this.vertexes.length && 2*i+2 < this.vertexes.length;
    }

    public void display() {
        // TODO
    }

    public int[] getVertexes() {
        return vertexes;
    }

    public void setVertexes(int[] vertexes) {
        this.vertexes = vertexes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tree tree = (Tree) o;

        return Arrays.equals(this.vertexes, tree.vertexes);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.vertexes);
    }

}
