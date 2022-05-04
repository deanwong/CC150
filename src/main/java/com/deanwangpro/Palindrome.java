package com.deanwangpro;

import java.util.Scanner;

public class Palindrome {
    public static boolean isPalindrome(String a) {
        char[] chars = a.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i <= j) {
            if (isAlphaNum(chars[i]) && isAlphaNum(chars[j])) {
                if (Character.toLowerCase(chars[i]) != Character.toLowerCase(chars[j])) {
                    return false;
                }
                i++;
                j--;
            } else if (!isAlphaNum(chars[i])) {
                i++;
            } else if (!isAlphaNum(chars[j])) {
                j--;
            }
        }
        return true;
    }

    private static boolean isAlphaNum(char c) {
        if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        int l = scanner.nextInt();
        scanner.nextLine();
        while (l-- > 0) {
            String s = scanner.nextLine();
            System.out.println(s);
            if (isPalindrome(s)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
