package graph.entity.tree;

import graph.util.ListConverter;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test the tree representation
 */
public class TreeTest {

    /**
     * Test data
     */
    private static final int[] VALUES = {1, 1, 5, 6, 7, 3, 2, 0, 3};

    private Tree tree;

    /**
     * Initialize test cases
     */
    @Before
    public void initialize() {
        this.tree = new Tree(VALUES);
    }

    @Test
    public void testInsert() {
        final int element = 4;

        this.tree.insert(element);

        List<Integer> expectedList = ListConverter.toList(VALUES);
        expectedList.add(element);
        int[] expected = ListConverter.toArray(expectedList);

        assertThat(this.tree, equalTo(new Tree(expected)));
    }

    @Test
    public void testRemoveSmallestElement() {
        this.tree.removeSmallestElement();

        int[] expected = ListConverter.toList(VALUES).stream().filter(e -> e != 0).mapToInt(i -> i).toArray();

        assertThat(this.tree, equalTo(new Tree(expected)));
    }


}
