package com.chen.others;

import org.junit.Test;

public class T2 {

    public char find(char[] char1, char[] char2) {
        int right = char2.length;
        int left = 0;
        int mid = 0;
        while (left != right) {
            mid = left + (right - left) / 2;
            if ((char1[mid] ^ char2[mid]) == 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return char1[left];
    }

    @Test
    public void test() {
        char[] char1 = new char[]{'e', 'a', 'b', 'c', 'd', 'f', 'g'};
        char[] char2 = new char[]{'a', 'b', 'c', 'd', 'f', 'g'};
        System.out.println(find(char1, char2));
    }

    @Test
    public void test1() {
        char a = 'a';
        char b = 'a';
        System.out.println(a ^ b);
    }
}
