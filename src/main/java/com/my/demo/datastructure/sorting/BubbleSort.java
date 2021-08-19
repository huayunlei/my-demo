package com.my.demo.datastructure.sorting;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author 72084513
 * @create 2021-05-28
 */
public class BubbleSort {
    public static void sort1(int array[]) {
        int length = array.length - 1;
        for (int i = 0; i < length ; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //因为有元素进行交换，所以不是有序的，标记变为false
                    isSorted = false;
                }
            }

            if (isSorted) {
                break;
            }
        }
    }

    public static void sort2(int array[]) {
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        int length = array.length - 1;
        for (int i = 0; i < length ; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //因为有元素进行交换，所以不是有序的，标记变为false
                    isSorted = false;
                    // 更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }

            if (isSorted) {
                break;
            }
            sortBorder = lastExchangeIndex;
        }
    }

    /**
     * 鸡尾酒排序
     * @param array
     */
    public static void sort3(int array[]) {
        for (int i = 0; i < array.length/2 ; i++) {
            //有序标记，每一轮的初始值都是true
            boolean isSorted = true;
            //奇数轮，从左向右比较和交换
            for (int j = i; j < array.length - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    //因为有元素进行交换，所以不是有序的，标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

            // 在偶数轮之前，将isSorted重新标记为true
            isSorted = true;
            //偶数轮，从右向左比较和交换
            for (int j = array.length - i - 1; j > i; j--) {
                if (array[j] < array[j-1]) {
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    //因为有元素进行交换，所以不是有序的，标记变为false
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        int[] array1 = new int[]{5,8,6,3,9,2,1,7};
        sort1(array1);
        System.out.println(Arrays.toString(array1));

        int[] array2 = new int[]{3,4,2,1,5,6,7,8};
        sort2(array2);
        System.out.println(Arrays.toString(array2));

        int[] array3 = new int[]{2,3,4,5,6,7,8,1};
        sort3(array3);
        System.out.println(Arrays.toString(array3));
    }
}
