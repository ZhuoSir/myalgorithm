package com.chen.leetcode.tree;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class $331 {

    public boolean isValidSerialization(String preorder) {
        int n = preorder.length();
        Deque<Integer> stack = new LinkedList<Integer>();
        int i = 0;
        stack.push(1);
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            char c = preorder.charAt(i);
            if (c == '#') {
                int top = stack.pop() - 1;
                if (top > 0) stack.push(top);
                i++;
            } else if (c == ',') {
                i++;
            } else {
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int top = stack.pop() - 1;
                if (top > 0) stack.push(top);
                stack.push(2);
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
//        System.out.println(isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
//        System.out.println(isValidSerialization("1,#"));
        System.out.println(isValidSerialization("9,#,#,1"));
    }
}
