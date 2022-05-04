package com.deanwangpro;

import java.math.BigInteger;

/**
 * Created by wangding on 12/11/2016.
 */
public class ASC2Bin {

    private static int DEFAULT_LENGTH = 8;

    public static String asc2bin(String str) {
        StringBuilder binaryString = new StringBuilder(8 * str.length());
        for (int i = 0; i < str.length(); i++) {
            try {
                int ascInt = (int) str.charAt(i);
                StringBuilder temp = new StringBuilder(Integer.toBinaryString(ascInt));
                String bin = null;
                if (temp.length() <= DEFAULT_LENGTH) {
                    while (temp.length() < DEFAULT_LENGTH) {
                        temp.insert(0, "0");
                    }
                    bin = temp.toString();
                } else if (temp.length() > DEFAULT_LENGTH) {
                    continue;
                }
                binaryString.append(bin);
            } catch (NumberFormatException nfe) {
                continue;
            }
        }
        return binaryString.toString();
    }

    public static String bin2asc(String str) {
        StringBuilder sb = new StringBuilder();

        //10010001100101 split into 8 characters
        for (int i = 0; i < str.length() - 1; i += DEFAULT_LENGTH) {

            //grab the hex in pairs
            String output = str.substring(i, (i + DEFAULT_LENGTH));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 2);
            //convert the decimal to character
            sb.append((char) decimal);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String binary = asc2bin("Hello world!");
        System.out.println("binary : " + binary);
        String asc = bin2asc(binary);
        System.out.println("ASC : " + asc);
    }

}
