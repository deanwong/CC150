package com.deanwangpro;

import java.util.Scanner;

public class CountOfStrings {

    public static void main(String[] args) {
        Scanner ab = new Scanner(System.in);
        int l = ab.nextInt();
        while (l-- > 0) {
            int count = ab.nextInt();
            System.out.println(counter(count, 1, 2));
        }
    }

    private static int counter(int count, int bcount, int ccount) {
        if (bcount < 0 || ccount < 0) {
            return 0;
        }
        if (count == 0) {
            return 1;
        }
        int res = counter(count - 1, bcount, ccount);
        res += counter(count - 1, bcount - 1, ccount);
        res += counter(count - 1, bcount, ccount - 1);
        return res;
    }

}
