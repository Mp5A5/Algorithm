package com.mp5a5.sort;

import org.junit.Test;

import java.util.Arrays;

public class SortTest {


    @Test
    public void test() {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        System.out.println(Arrays.toString(heapSort(array)));
    }

    public int[] bubbleSort(int[] srcArray) {
        int sortedBorder = srcArray.length - 1;
        int lastChangeIndex = 0;
        for (int i = 0; i < srcArray.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < sortedBorder; j++) {
                if (srcArray[j] > srcArray[j + 1]) {
                    int temp = srcArray[j];
                    srcArray[j] = srcArray[j + 1];
                    srcArray[j + 1] = temp;
                    isSorted = false;
                    lastChangeIndex = j;
                }
            }
            sortedBorder = lastChangeIndex;
            if (isSorted) break;
            ;
        }
        return srcArray;
    }

    public int[] quickSort(int[] srcArray) {
        return quickSort(srcArray, 0, srcArray.length - 1);
    }

    private int[] quickSort(int[] srcArray, int left, int right) {
        if (left > right) return srcArray;
        int i = left;
        int j = right;
        int pivot = srcArray[left];
        while (i < j) {
            while (i < j && pivot < srcArray[j]) j--;
            srcArray[i] = srcArray[j];
            while (i < j && srcArray[i] < pivot) i++;
            srcArray[j] = srcArray[i];
        }
        srcArray[i] = pivot;
        quickSort(srcArray, left, i - 1);
        quickSort(srcArray, i + 1, right);
        return srcArray;
    }


    public int[] heapSort(int[] srcArray) {
        for (int i = srcArray.length / 2 - 1; i >= 0; i--) {
            adjustHeap(srcArray, i, srcArray.length);
        }

        for (int j = srcArray.length - 1; j > 0; j--) {
            int temp = srcArray[0];
            srcArray[0] = srcArray[j];
            srcArray[j] = temp;
            adjustHeap(srcArray, 0, j);
        }
        return srcArray;
    }


    private void adjustHeap(int[] srcArray, int i, int length) {
        int temp = srcArray[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && srcArray[k] < srcArray[k + 1]) {
                k++;
            }
            if (temp < srcArray[k]) {
                srcArray[i] = srcArray[k];
                i = k;
            }
        }
        srcArray[i] = temp;
    }
}
