package com.example.sort;

public class HeapSorting {
    public static void heapSort(int[] a) {
        long startTime = System.currentTimeMillis();
        int N = a.length;
        for (int k = N / 2 - 1; k >= 0; k--) { //for循环用来构造堆，最终生成最大堆
            sink(a, k, N - 1);
        }
        for (int i = N - 1; i >= 1; i--) { //从数组最后一个元素开始向前循环
            exch(a, 0, i); //堆顶a[0]与堆的最后一个元素a[i]交换位置，下一轮循环i--会去掉最后一个元素到有序区，减小新堆
            sink(a, 0, i); //i已经--了，剩下的元素重新生成为最大堆
        }
        System.out.println("随机数堆排序耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    /**
     * 从上至下堆有序化
     */
    private static void sink(int[] a, int k, int length) {
        while (2 * (k + 1) <= length) { //length为这次排序的堆的最后一个元素索引
            int j = 2 * k;
            if (j < length && a[j] < a[j + 1]) { //j<N保证j+1不越界，a[j]和a[j+1]是a[K]的左右子节点，这里是为了选取两个子节点较大的一个，a[j]大取a[j],a[j]小取a[j++]
                j++;
            }
            if (a[k] >= a[j]) { //如果父节点大于等于值大的子节点，堆有序，终止循环
                break;
            }
            exch(a, k, j); //交换值大的子节点和父节点的值，达到堆有序
            k = j; //子节点，作为下一个循环的父节点，继续下沉
        }
    }

    /**
     * 交换两个元素
     */
    private static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
