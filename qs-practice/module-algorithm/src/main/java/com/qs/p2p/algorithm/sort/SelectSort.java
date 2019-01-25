package com.qs.p2p.algorithm.sort;

/**
 * 选择排序
 */
public class SelectSort {

    /**
     * 设数组为a[0…n-1]。
     * 1.初始时，数组全为无序区为a[0..n-1]。令i=0
     * 2.在无序区a[i…n-1]中选取一个最小的元素，将其与a[i]交换。交换之后a[0…i]就形成了一个有序区。
     * 3.i++并重复第二步直到i==n-1。排序完成。
     */
    public static void selectSort(Integer[] array) {
        Integer minIndex;
        for (int i = 0; i < array.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
