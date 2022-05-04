package com.deanwangpro;

import java.util.Scanner;

public class ModifiedNumbersAndQueries {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        while (count-- > 0) {
            int l = scanner.nextInt();
            int r = scanner.nextInt();
            System.out.println(count(l, r));
        }
    }

    public static long count(int l, int r) {
        long sum = 0;
        for (int i = l; i <= r; i++) {
            sum += sumPrimeFactors(i);
        }
        return sum;
    }

    public static long sumPrimeFactors(int n) {
        long sum = 0;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            int pre = 0;
            while (n % i == 0) {
                if (pre != i) {
                    sum += i;
                    pre = i;
                }
                n = n / i;
            }
        }
        if (n > 1) {
            // n it self
            sum += n;
        }
        return sum;
    }

    public static boolean isPrime(int a) {
        if (a == 1) {
            return true;
        }
        if (a < 2) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(a); i++) {
                if (a % i == 0) {
                    return false;
                }
            }
        }
        return false;
    }

}
