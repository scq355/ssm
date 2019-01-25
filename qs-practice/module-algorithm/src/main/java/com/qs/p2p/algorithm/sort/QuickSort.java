package com.qs.p2p.algorithm.sort;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * @param left  左指针
     * @param right 右指针
     */
    public static void quickSort(Integer[] array, int left, int right) {
        if (left < right) {
            //获取枢纽值，并将其放在当前待处理序列末尾
            dealPivot(array, left, right);
            //枢纽值被放在序列末尾
            int pivot = right - 1;
            //左指针
            int i = left;
            //右指针
            int j = right - 1;
            while (true) {
                while (array[++i] < array[pivot]) {
                }
                while (j > left && array[--j] > array[pivot]) {
                }
                if (i < j) {
                    swap(array, i, j);
                } else {
                    break;
                }
            }
            if (i < right) {
                swap(array, i, right - 1);
            }
            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        }

    }

    /**
     * 处理枢纽值
     */
    public static void dealPivot(Integer[] array, int left, int right) {
        int mid = (left + right) / 2;
        if (array[left] > array[mid]) {
            swap(array, left, mid);
        }
        if (array[left] > array[right]) {
            swap(array, left, right);
        }
        if (array[right] < array[mid]) {
            swap(array, right, mid);
        }
        swap(array, right - 1, mid);
    }

    /**
     * 交换元素通用处理
     */
    private static void swap(Integer[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
