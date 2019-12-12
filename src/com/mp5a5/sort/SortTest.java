package com.mp5a5.sort;

import org.junit.Test;

import java.util.Arrays;

public class SortTest {


    @Test
    public void test() {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        System.out.println(Arrays.toString(heapSort(array)));
    }

    public int[] heapSort(int[] srcArray) {
        // 构建最大堆
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


    public void adjustHeap(int[] array, int i, int len) {
        int temp = array[i];
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
            if (k + 1 < len && array[k + 1] > array[k]) {
                k++;
            }
            if (array[k] > temp) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }


}
