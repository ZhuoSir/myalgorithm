package com.chen.others;

import java.util.ArrayList;
import java.util.List;

/**
 * 两个有序数组查找相同合集；
 * */
public class T1 {
    private static int binarySearch(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }

    private static int[] retainAll(int[] numsA, int[] numsB) {
        int i = 0;
        List<Integer> res = new ArrayList<>();
        while (i < numsA.length) {
            int index = binarySearch(numsB, numsA[i]);
            if (index >= numsB.length) {
                break;
            }
            if (numsA[i] == numsB[index]) {
                res.add(numsA[i++]);
            } else {
                i = binarySearch(numsA, numsB[index]);
            }
        }

        int[] ret = new int[res.size()];
        for (int j = 0; j < res.size(); j++) {
            ret[j] = res.get(j);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {1, 2, 3, 8};
        int[] C = {9, 10};
        int[] D = {1, 2, 3, 4, 5};
        int[] E = {1, 1, 1, 1};
        int[] res = retainAll(A, B);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}


