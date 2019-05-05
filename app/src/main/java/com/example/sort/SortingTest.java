package com.example.sort;

/**
 * Copy from https://github.com/cxzl/SortingTest
 */
public class SortingTest {
    public static void main(String[] args) {
        int dataSize = 10000; //默认10000个数排序，可以随意修改数据规模
        int[] a = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            a[i] = (int) (1 + Math.random() * dataSize);
        }
        //SimpleSorting.insertionSort(a);
        //SimpleSorting.selectionSort(a);
        //SimpleSorting.bubbleSort(a);
        //ShellSorting.shellSort(a);
        //ShellSorting.shellSort2(a);
        HeapSorting.heapSort(a);
        //MergeSorting.mergeSort(a);
        //QuickSorting.quickSort(a);
    }
}
