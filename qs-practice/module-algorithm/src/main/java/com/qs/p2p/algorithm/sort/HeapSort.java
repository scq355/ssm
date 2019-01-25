package com.qs.p2p.algorithm.sort;

/**
 * 堆排序
 */
public class HeapSort {

    public static void sort(Integer[] array){
        // 1.构建大顶堆
        for(int i = array.length / 2 - 1;i >= 0; i--){
            // 从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(array, i, array.length);
        }
        // 2.调整堆结构 + 交换堆顶元素与末尾元素
        for(int j = array.length - 1; j > 0; j--){
            swap(array,0, j); // 将堆顶元素与末尾元素进行交换
            adjustHeap(array,0, j); // 重新对堆进行调整
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     */
    public static void adjustHeap(Integer[] array,int i,int length){
        Integer temp = array[i]; // 先取出当前元素i
        for(int k = i * 2 + 1; k < length; k = k * 2 + 1) { // 从i结点的左子结点开始，也就是2i+1处开始
            if(k + 1 < length && array[k] < array[k + 1]){ // 如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(array[k] > temp) { // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp; // 将temp值放到最终的位置
    }

    /**
     * 交换元素
     */
    public static void swap(Integer[] array,int a ,int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
