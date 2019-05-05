package com.example.sort;

public class SimpleSorting {
    public static void insertionSort(int[] a) {
        long startTime = System.currentTimeMillis();
        int insertIndex, insertElement;
        for (int i = 1; i < a.length; i++) { //外层循环，默认第一个元素有序，从第二个元素开始，时间复杂度N
            insertIndex = i - 1; //插入的位置，默认有序数列的最后一个元素的位置
            insertElement = a[i]; //新插入的元素，默认外层循环的元素
            while (insertIndex >= 0 && a[insertIndex] > insertElement) { //内层循环，只要新元素比待插入位置的元素小就继续，时间复杂度N
                a[insertIndex + 1] = a[insertIndex]; //比待插入元素大的元素后移一位
                insertIndex--; //插入位置前移一位
            }
            a[insertIndex + 1] = insertElement; //内层循环结束，把新元素放到插入位置后面
        }
        System.out.println("随机数插入排序耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    public static void selectionSort(int[] a) {
        long startTime = System.currentTimeMillis();
        int minIndex, temp;
        for (int i = 0; i < a.length - 1; i++) { //外层循环，从无序区第一个元素开始到数组倒数第二个元素，时间复杂度N
            minIndex = i; //每次外层循环假设无序区第一个元素是最小元素
            for (int j = i + 1; j < a.length; j++) {    //内层循环，从假设的最小元素的后一个位置开始，到数组最后一个元素，时间复杂度N
                if (a[j] < a[minIndex]) { //判断内层循环的元素是否小于假设的最小元素
                    minIndex = j; //如果比最小元素小，标记该元素的位置为新的最小元素的位置，内层循环完毕，会找出无序区的最小值
                }
            }
            temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;    //无序区真正最小值和第一个元素交换位置，下一次循环无序区从下一个值开始
        }
        System.out.println("随机数选择排序耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }


    public static void bubbleSort(int[] a) {
        long startTime = System.currentTimeMillis();
        int temp;
        for (int i = 0; i < a.length - 1; i++) { //外层循环，从数组第一个元素到倒数第二个元素，时间复杂度为N
            for (int j = 0; j < a.length - 1 - i; j++) { //内层循环，从数组第一个元素到剩余的元素（减去有序区的元素）
                if (a[j] > a[j + 1]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp; //相邻元素只要前面的比后面的大，就交换位置
                }
            }
        }
        System.out.println("随机数冒泡排序耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }
}
