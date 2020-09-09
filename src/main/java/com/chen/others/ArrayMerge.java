package com.chen.others;

import org.junit.Test;

import java.util.Arrays;

/**
 * 两个有序数组合并；
 *
 *
 * */
public class ArrayMerge {


    public int[] merge(int[] a, int[] b) {

        int[] r = new int[a.length + b.length];

        for (int i = 0, j = 0, c = 0; c < r.length; c++) {

            if (i == a.length) {
                r[c] = b[j];
                j++;
                continue;
            }

            if (j == b.length) {
                r[c] = a[i];
                i++;
                continue;
            }

            r[c] = Math.min(a[i], b[j]);
            if (r[c] == a[i]) {
                i++;
            } else {
                j++;
            }
        }

        return r;
    }

    @Test
    public void test1() {

        int[] a = {1,3,5,7};
        int[] b = {1,4,6,8};

        int[] r = merge(a, b);
        for (int i = 0; i < r.length; i++) {
            System.out.print(r[i] + " ");
        }
    }
}
