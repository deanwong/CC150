package com.deanwangpro;

import java.util.Scanner;

public class PainterPartitionProblem {

    private static int sum(int arr[], int from, int to) {
        int result = 0;
        for (int i = from; i <= to; i++) {
            result += arr[i];
        }
        return result;
    }

    private static int partition(int arr[], int n, int k) {
        if (k == 1) {
            return sum(arr, 0, n - 1);
        }
        if (n == 1) {
            return arr[0];
        }
        int lowestCost = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            //sum 是 计算 i + 1 到 n 的值，递归计算 (0, i-1)的最大值
            int max = Math.max(partition(arr, i, k - 1), sum(arr, i, n - 1));
            lowestCost = Math.min(lowestCost, max);
        }
        return lowestCost;
    }

    public static void main(String args[]) {
        /**
         * 2
         * 2 4
         * 10 10 10 10
         * 2 4
         * 10 20 30 40
         */
        Scanner ab = new Scanner(System.in);
        int l = ab.nextInt();
        while (l-- > 0) {
            int k = ab.nextInt();
            int n = ab.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = ab.nextInt();
            }
            System.out.println(partition2(arr, arr.length, k));
        }
    }


    private static int getMaxBoard(int arr[]) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private static int getTotal(int arr[]) {
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        return total;
    }

    private static int getMinPainters(int arr[], int maxLen) {
        int num = 1;
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
            if (total > maxLen) {
                num++;
                total = arr[i];
            }
        }
        return num;
    }

    private static int partition2(int arr[], int n, int k) {
        int lo = getMaxBoard(arr);
        int hi = getTotal(arr);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int painters = getMinPainters(arr, mid);
            // discard upper half, otherwise discard lo
            if (painters <= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

}
