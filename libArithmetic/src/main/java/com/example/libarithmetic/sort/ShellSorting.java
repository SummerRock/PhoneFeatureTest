package com.example.libarithmetic.sort;

public class ShellSorting {
    public static void shellSort(int[] a) {
        long startTime = System.currentTimeMillis();
        int h = 1; //希尔排序是使间隔为h的元素有序
        int temp;
        while (h < a.length / 3) { //while循环，扩大h
            h = 3 * h + 1; //这里用3倍作为希尔排序的间隔，是常用的值，加1是为了防止排序的都是3的倍数
        }
        while (h >= 1) { //while循环让h从大到小插入排序
            for (int i = h; i < a.length; i++) {  //从h位置开始，对整个数组遍历，i为插入元素的位置
                for (int j = i; j >= h && a[j] < a[j - h]; j -= h) { //从i开始，前面每隔h位与i进行进行比较，如果a[j]比a[j-h]小则交换
                    temp = a[j - h];
                    a[j - h] = a[j];
                    a[j] = temp;
                }
            }
            h = h / 3; //更大间隔的插入完成，缩小插入间隔
        }
        System.out.println("希尔排序随机数耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    public static void shellSort2(int[] a) {
        long startTime = System.currentTimeMillis();
        int h = 1; //希尔排序是使间隔为h的元素有序
        int insertIndex, insertElement;
        while (h < a.length / 3) { //while循环，扩大h
            h = 3 * h + 1; //这里用3倍作为希尔排序的间隔，是常用的值，加1是为了防止排序的都是3的倍数
        }
        while (h >= 1) { //while循环让h从大到小插入排序
            for (int i = h; i < a.length; i++) {  //从h位置开始，对整个数组遍历，i为插入元素的位置
                insertIndex = i - h; //插入的位置，默认前面间隔h的位置
                insertElement = a[i]; //新插入的元素，默认外层循环的最后一个元素
                while (insertIndex >= 0 && a[insertIndex] > insertElement) { //内层循环，只要新元素比待插入位置的元素小就继续
                    a[insertIndex + h] = a[insertIndex]; //比待插入元素大的元素后移h位
                    insertIndex -= h; //插入位置前移h位
                }
                a[insertIndex + h] = insertElement; //内层循环结束，把新元素放到插入位置后面
            }
            h = h / 3; //更大间隔的插入完成，缩小插入间隔
        }
        System.out.println("希尔排序随机数耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }
}
