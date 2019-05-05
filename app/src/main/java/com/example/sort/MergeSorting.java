package com.example.sort;

public class MergeSorting {
    private static int[] aux; //归并所需的辅助数组

    public static void mergeSort(int[] a) {
        long startTime = System.currentTimeMillis();
        aux = new int[a.length];
        sort(a, 0, a.length - 1); //开始递归排序
        System.out.println("随机数归并排序耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    /**
     * 递归的排序归并
     */
    private static void sort(int[] a, int left, int right) {
        if (right <= left) { //排序从左到右，确保右边比左边大
            return;
        }
        int mid = (left + right) / 2; //找出中间位置
        sort(a, left, mid); //将左半边排序
        sort(a, mid + 1, right); //将右半边排序
        merge(a, left, mid, right); //归并结果
    }

    /**
     * 原地归并方法
     */
    private static void merge(int[] a, int left, int mid, int right) { //将a[left..mid]和a[mid+1..right]归并
        int i = left, j = mid + 1;  //左右半边起始位置
        for (int k = left; k <= right; k++) { //将a[left..right]复制到aux[left..right]
            aux[k] = a[k];
        }
        for (int k = left; k <= right; k++) { //归并回到a[left..right]
            if (i > mid) {  //i比mid大代表左半边用完，只有右半边有元素
                a[k] = aux[j++]; //右边元素给a[k]
            } else if (j > right) { //j比right大代表右半边用完，只有左半边有元素
                a[k] = aux[i++]; //左边元素给a[k]
            } else if (aux[j] < aux[i]) { //如果右边元素大于左边
                a[k] = aux[j++]; //右边元素给a[k]
            } else {    //否则左边大于等于右边
                a[k] = aux[i++]; //左边元素给a[k]
            }
        }
    }
}
