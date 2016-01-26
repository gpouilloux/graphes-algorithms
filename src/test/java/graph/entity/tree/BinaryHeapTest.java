package graph.entity.tree;

import graph.util.BinaryHeap;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static graph.util.ListConverter.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test the binaryHeap representation
 */
public class BinaryHeapTest {

    /**
     * Test data
     */
    private static final int[] WEIGHTS = {1, 1, 5, 6, 7, 3, 2, 0, 3};
    private static final int[] INDEXES = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    private BinaryHeap binaryHeap;

    /**
     * Initialize test cases
     */
    @Before
    public void initialize() {
        this.binaryHeap = new BinaryHeap(WEIGHTS, INDEXES);
    }

    @Test
    public void testInsert() {
        final int weight = 5;
        final int index = INDEXES.length;

        this.binaryHeap.insert(weight, index);

        List<Integer> expectedWeight = toList(WEIGHTS);
        expectedWeight.add(weight);
        List<Integer> expectedIndex = toList(INDEXES);
        expectedIndex.add(index);

        assertThat(this.binaryHeap, equalTo(new BinaryHeap(toArray(expectedWeight), toArray(expectedIndex))));
    }

    @Test
    public void testRemoveSmallestElement() {
        this.binaryHeap.removeSmallestElement();

        int[] expectedWeight = toList(WEIGHTS).stream().filter(e -> e != 0).mapToInt(i -> i).toArray();
        int[] expectedIndex = toList(INDEXES).stream().filter(e -> e != 7).mapToInt(i -> i).toArray();


        assertThat(this.binaryHeap, equalTo(new BinaryHeap(expectedWeight, expectedIndex)));
    }
}
