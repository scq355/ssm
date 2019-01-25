package com.qs.p2p.algorithm.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    /**
     * 设数组长度为N。
     * 1．比较相邻的前后二个数据，如果前面数据大于后面的数据，就将二个数据交换。
     * 2．这样对数组的第0个数据到N-1个数据进行一次遍历后，最大的一个数据就“沉”到数组第N-1个位置。
     * 3．N=N-1，如果N不为0就重复前面二步，否则排序完成。
     */
    public static void bubbleSortI(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - 1; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }


    /**
     * 下面对其进行优化，设置一个标志，如果这一趟发生了交换，则为true，否则为false。明显如果有一
     * 趟没有发生交换，说明排序已经完成
     */
    public static void bubbleSortII(Integer[] array) {
        boolean flag = true;
        int count = array.length;
        while (flag) {
            flag = false;
            for (int i = 1; i < count; i++) {
                if (array[i - 1] > array[i]) {
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    flag = true;
                }
            }
            count--;
        }
    }
    /**
     * 再做进一步的优化。如果有100个数的数组，仅前面10个无序，后面90个都已排好序且都大于前面10个数字，
     * 那么在第一趟遍历后，最后发生交换的位置必定小于10，且这个位置之后的数据必定已经有序了，记录下这
     * 位置，第二次只要从数组头部遍历到这个位置就可以了。
     */
    public static void bubbleSortIII(Integer[] array) {
        int flag = array.length;
        int count;
        while (flag > 0) {
            count = flag;
            flag = 0;
            for (int i = 1; i < count; i++) {
                if (array[i - 1] > array[i]) {
                    int temp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = temp;
                    flag = i;
                }
            }
        }
    }
}
