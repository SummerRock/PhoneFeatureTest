package com.example.sort;

public class QuickSorting {
    public static void quickSort(int[] a) {
        long startTime = System.currentTimeMillis();
        sort(a, 0, a.length - 1);
        System.out.println("随机数快速排序耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    /**
     * 递归进行快速排序
     */
    private static void sort(int[] a, int left, int right) {
        if (right <= left) { //排序从左到右，确保右边比左边大
            return;
        }
        int j = partition(a, left, right); //切分
        sort(a, left, j - 1); //将左半边排序
        sort(a, j + 1, right); //将右半边排序
    }

    /**
     * 快速排序切分
     */
    private static int partition(int[] a, int left, int right) {
        int i = left, j = right + 1; //左右扫描指针
        int v = a[left]; //选取切分元素，这里选第一个，实际数据可以自行挑选
        while (true) {
            while (a[++i] < v) { //a[i]<v时增大i，只要比v小继续往右扫描
                if (i == right) { //扫描到右边则终止
                    break;
                }
            }
            while (v < a[--j]) { //a[j]>v时减小j，只要比v大继续往左扫描
                if (j == left) { //扫描到左边则终止
                    break;
                }
            }
            if (i >= j) { //如果左右指针交叉，终止循环
                break;
            }
            exch(a, i, j); //不满足上述条件（左边比v大，右边比v小，指针未交叉），左右元素交换位置
        }
        exch(a, left, j); //将切分元素v放入正确的位置
        return j; //a[left..j-1]<=a[j]<=a[j+1..right]，并返回j
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
