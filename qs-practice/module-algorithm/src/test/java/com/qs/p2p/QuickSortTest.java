package com.qs.p2p;

import com.qs.p2p.algorithm.sort.QuickSort;
import org.junit.Test;

public class QuickSortTest {
    @Test
    public void testSort() {
        Integer[] array_start = new Integer[] {12, 2, 23, 3, 13, 4, 43, 10, 6, 79};
        QuickSort.quickSort(array_start, 0, array_start.length - 1);
        for (Integer num : array_start) {
            System.out.print(num + " ");
        }
    }
}
