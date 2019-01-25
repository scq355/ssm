package com.qs.p2p.algorithm.sort;

/**
 * 希尔排序
 */
public class ShellSort {

    /**
     * 先将整个待排元素序列分割成若干个子序列（由相隔某个“增量”的元素组成的）分别进行直接插入排序，
     * 然后依次缩减增量再进行排序，待整个序列中的元素基本有序（增量足够小）时，
     * 再对全体元素进行一次直接插入排序。因为直接插入排序在元素基本有序的情况下（接近最好情况），
     * 效率是很高的，因此希尔排序在时间效率上比前两种方法有较大提高。
     */
    public static void shellSort(Integer[] array) {
        int i, j, gap;
        for(gap = array.length / 2; gap > 0; gap /= 2) {
            for (i = 0; i < gap; i++) {
                for (j = i + gap; j < array.length; j += gap) {
                    if (array[j] < array[j - gap]) {
                        int temp = array[j];
                        int k = j - gap;
                        while (k >= 0 && array[k] > temp) {
                            array[k + gap] = array[k];
                            k -= gap;
                        }
                        array[k + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 以第二次排序为例，原来是每次从1A到1E，从2A到2E，可以改成从1B开始，
     * 先和1A比较，然后取2B与2A比较，再取1C与前面自己组内的数据比较…….。
     * 这种每次从数组第gap个元素开始，每个元素与自己组内的数据进行直接插入排序显然也是正确的
     */
    public static void shellSortII(Integer array[]) {
        int j, gap;
        for (gap = array.length / 2; gap > 0; gap /= 2) {
            for (j = gap; j < array.length; j++) {
                if (array[j] < array[j - gap]) {
                    int temp = array[j];
                    int k = j - gap;
                    while (k >= 0 && array[k] > temp) {
                        array[k + gap] = array[k];
                        k -= gap;
                    }
                    array[k + gap] = temp;
                }
            }
        }
    }

    /**
     * 将直接插入排序部分用 白话经典算法系列之二
     * 直接插入排序的三种实现 中直接插入排序的第三种方法来改写
     */
    public static void shellSortIII(Integer array[]) {
        int i, j, gap;
        for (gap = array.length / 2; gap > 0; gap /= 2) {
            for (i = gap; i < array.length; i++) {
                for (j = i - gap; j >= 0 && array[j] > array[j + gap]; j -= gap) {
                    int temp = array[j];
                    array[j] = array[j + gap];
                    array[j + gap] = temp;
                }
            }
        }
    }
}
