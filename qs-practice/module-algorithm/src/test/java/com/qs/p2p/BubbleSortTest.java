package com.qs.p2p;

import com.qs.p2p.algorithm.sort.BubbleSort;
import org.junit.Test;

public class BubbleSortTest {

    @Test
    public void testSort() {
        Integer[] array_start = new Integer[] {12, 2, 23, 3, 13, 4, 43, 10, 6, 79};
        BubbleSort.bubbleSortI(array_start);
		BubbleSort.bubbleSortII(array_start);
        BubbleSort.bubbleSortIII(array_start);
        for (Integer num : array_start) {
            System.out.print(num + " ");
        }
    }
}
