package com.mp5a5.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SortFunction {


    @Test
    public void test() {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        System.out.println(Arrays.toString(bucketSort(array)));
    }

    public int[] bubbleSort(int[] srcArray) {
        int border = srcArray.length - 1;
        int lastChangeIndex = 0;
        for (int i = 0; i < srcArray.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < border; j++) {
                if (srcArray[j] > srcArray[j + 1]) {
                    int temp = srcArray[j];
                    srcArray[j] = srcArray[j + 1];
                    srcArray[j + 1] = temp;
                    isSorted = false;
                    lastChangeIndex = j;
                }
            }
            border = lastChangeIndex;
            if (isSorted) break;
        }
        return srcArray;
    }

    public int[] quickSort(int[] srcArray) {
        return quickSort(srcArray, 0, srcArray.length - 1);
    }

    private int[] quickSort(int[] array, int left, int right) {
        if (left > right) return array;
        int i = left;
        int j = right;
        int pivot = array[left];
        while (i < j) {
            while (i < j && pivot < array[j]) j--;
            array[i] = array[j];
            while (i < j && array[i] < pivot) i++;
            array[j] = array[i];
        }
        array[i] = pivot;
        quickSort(array, left, i - 1);
        quickSort(array, i + 1, right);
        return array;
    }

    public int[] heapSort(int[] srcArray) {
        // 构建堆
        // 最后一个非叶子结点 srcArray.length / 2 - 1
        for (int i = srcArray.length / 2 - 1; i >= 0; i--) {
            adjustHeap(srcArray, i, srcArray.length);
        }

        for (int i = srcArray.length - 1; i > 0; i--) {
            int temp = srcArray[i];
            srcArray[i] = srcArray[0];
            srcArray[0] = temp;
            adjustHeap(srcArray, 0, i);
        }
        return srcArray;
    }

    // 将一个数组，调整成一个大顶堆

    /**
     * @param arr    待调整数组
     * @param i      非叶子结点在数组中的索引
     * @param length 表示有多少个元素继续调整，在不断地减少
     */
    private void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { // 左子节点小于右子节点的值
                k++;//指向右子节点
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;//i指向k，继续循环遍历
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到调整后的位置，由于k已经指向了i所以直接赋值
    }

    public int[] bucketSort(int[] srcArray) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int value : srcArray) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        int bucketNum = (max - min) / srcArray.length + 1;
        List<List<Integer>> bucketList = new LinkedList<>();
        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<Integer>());
        }
        for (int i = 0; i < srcArray.length; i++) {
            int index = (srcArray[i] - min) / srcArray.length;
            bucketList.get(index).add(srcArray[i]);
        }
        for (List<Integer> list : bucketList) {
            Collections.sort(list);
        }
        int index = 0;
        for (List<Integer> list : bucketList) {
            for (int value : list) {
                srcArray[index++] = value;
            }
        }
        return srcArray;
    }
}
