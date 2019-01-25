package com.qs.p2p.algorithm.sort;

/**
 * 插入排序
 */
public class InsertSort {

    /**
     * 设数组为a[0…n-1]。
     * 1.初始时，a[0]自成1个有序区，无序区为a[1..n-1]。令i=1
     * 2.将a[i]并入当前的有序区a[0…i-1]中形成a[0…i]的有序区间。
     * 3.i++并重复第二步直到i==n-1。排序完成。
     */
    public static void insertSort(Integer[] array) {
        int j, k;
        for (int i = 1; i < array.length; i++) {
            for (j = i - 1; j >= 0 ; j--) {
                if (array[j] < array[i]) {
                    break;
                }
            }
            if (j != i - 1) {
                int temp = array[i];
                for (k = i - 1; k > j; k--) {
                    array[k + 1] = array[k];
                }
                array[k + 1] = temp;
            }
        }
    }

    /**
     * 将搜索和数据后移这二个步骤合并。即每次a[i]先和前面一个数据a[i-1]比较，
     * 如果a[i] > a[i-1]说明a[0…i]也是有序的，无须调整。否则就令j=i-1,temp=a[i]。
     * 然后一边将数据a[j]向后移动一边向前搜索，当有数据a[j]<a[i]时停止并将temp放到a[j + 1]处
     */
    public static void insertSortII(Integer[] array) {
        int i, j;
        for (i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                int temp = array[i];
                for (j = i - 1; j >= 0 && array[j] > temp; j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }
        }
    }

    /**
     * 再对将a[j]插入到前面a[0…j-1]的有序区间所用的方法进行改写，
     * 用数据交换代替数据后移。如果a[j]前一个数据a[j-1] > a[j]，就交换a[j]和a[j-1]，
     * 再j--直到a[j-1] <= a[j]。这样也可以实现将一个新数据新并入到有序区间
     */
    public static void insertSortIII(Integer[] array) {
        int i, j;
        for (i = 1; i < array.length; i++) {
            for (j = i - 1; j >= 0 && array[j] > array[j + 1]; j--) {
                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
}
