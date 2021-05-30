package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

public class BubbleTest {
    @Test
    public void when_notSortedArrayPassedToSort_then_arraysSortedAsc(){
        int[] array = new int[]{5, 14, -2, 20, 32, 0};
        Bubble.sort(array);
        int[] expectedArray = new int[]{-2, 0, 5, 14, 20, 32};
        Assert.assertEquals(array.length, expectedArray.length);
        Assert.assertArrayEquals(expectedArray, array);
    }
}
