package com.mp5a5.sort;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

public class Sort {


    @Test
    public void test() {
        int[] array = new int[]{5, 8, 6, 3, 9, 2, 1, 7};
        System.out.println(Arrays.toString(heapSort(array)));
    }

    public int[] bubbleSort(int[] srcArray) {
        int lastChangeIndex = 0;
        int sortBorder = srcArray.length - 1;
        for (int i = 0; i < srcArray.length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (srcArray[j] > srcArray[j + 1]) {
                    int temp = srcArray[j];
                    srcArray[j] = srcArray[j + 1];
                    srcArray[j + 1] = temp;
                    lastChangeIndex = j;
                    isSorted = false;
                }
            }
            sortBorder = lastChangeIndex;
            if (isSorted) break;
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
            while (i < j && srcArray[j] > pivot) j--;
            srcArray[i] = srcArray[j];
            while (i < j && srcArray[i] < pivot) i++;
            srcArray[j] = srcArray[i];
        }
        srcArray[i] = pivot;
        quickSort(srcArray, right, i - 1);
        quickSort(srcArray, i + 1, right);
        return srcArray;
    }


    public int[] bucketSort(int[] srcArray) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : srcArray) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int bucketNum = (max - min) / srcArray.length + 1;

        List<LinkedList<Integer>> bucketList = new ArrayList<>();

        for (int i = 0; i < bucketNum; i++) {
            bucketList.add(new LinkedList<>());
        }

        for (int i = 0; i < srcArray.length; i++) {
            int index = (srcArray[i] - min) / srcArray.length;
            bucketList.get(index).add(srcArray[i]);
        }

        for (LinkedList<Integer> list : bucketList) {
            Collections.sort(list);
        }

        int index = 0;
        for (LinkedList<Integer> list : bucketList) {
            for (Integer value : list) {
                srcArray[index++] = value;
            }
        }

        return srcArray;
    }


    public int[] heapSort(int[] srcArray) {
        int len = srcArray.length;
        buildMaxHeap(srcArray, len);
        for (int i = len - 1; i > 0; i--) {
            swap(srcArray, 0, i);
            len--;
            heapify(srcArray, 0, len);
        }
        return srcArray;
    }

    private void buildMaxHeap(int[] srcArray, int len) {
        for (int i = (int) Math.round(len / 2); i >= 0; i--) {
            heapify(srcArray, i, len);
        }
        BigDecimal decimal = new BigDecimal(13.14);
        decimal= decimal.setScale(2,4);
        float v = decimal.floatValue();
    }

    private void heapify(int[] srcArray, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && srcArray[left] > srcArray[largest]) {
            largest = left;
        }
        if (right < len && srcArray[right] > srcArray[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(srcArray, i, largest);
            heapify(srcArray, largest, len);
        }
    }

    public void swap(int[] srcArray, int i, int largest) {
        int temp = srcArray[i];
        srcArray[i] = srcArray[largest];
        srcArray[largest] = temp;
    }

}
