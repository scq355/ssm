package com.qs.p2p;

import com.qs.p2p.algorithm.sort.ShellSort;
import org.junit.Test;

public class ShellSortTest {
    @Test
    public void testSort() {
        Integer[] array_start = new Integer[] {12, 2, 23, 3, 13, 4, 43, 10, 6, 79};
		ShellSort.shellSort(array_start);
		ShellSort.shellSortII(array_start);
        ShellSort.shellSortIII(array_start);
        for (Integer num : array_start) {
            System.out.print(num + " ");
        }
    }
}
