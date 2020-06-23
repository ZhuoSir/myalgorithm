package com.chen;

import org.junit.Test;

import java.util.*;

public class MyBinaryTree {

    private MyBinaryNode root;

    public MyBinaryTree() {
        root = null;
    }


    public Integer findMax() {
        MyBinaryNode max = findMax(root);
        return max != null ? max.element : null;
    }

    public Integer findMin() {
        MyBinaryNode min = findMin(root);
        return min != null ? min.element : null;
    }

    public int nodeCount() {
        return nodeCount(root);
    }

    public int nodeCount(MyBinaryNode myBinaryNode) {
        if (myBinaryNode == null) {
            return 0;
        } else {
            return nodeCount(myBinaryNode.leftNode) + nodeCount(myBinaryNode.rightNode) + 1;
        }
    }


    public int depth() {
        return depth(root);
    }

    public int depth(MyBinaryNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth  = depth(node.leftNode);
        int rightDepth = depth(node.rightNode);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public MyBinaryNode findMin(MyBinaryNode node) {

        if (node == null) {
            return null;
        }

        else if (node.leftNode == null) {
            return node;
        }

        return findMin(node.leftNode);
    }


    public MyBinaryNode findMax(MyBinaryNode node) {

        if (node == null) {
            return null;
        }

        else if (node.rightNode == null) {
            return node;
        }

        return findMax(node.rightNode);
    }

    public void insert(int element) {
        root = insert(element, root);
    }

    public MyBinaryNode insert(int element, MyBinaryNode node) {

        if (node == null) {
            return new MyBinaryNode(element, null, null);
        }

        if (element < node.element) {
            node.leftNode  = insert(element, node.leftNode);
//            node.leftNode.parentNode = node;
        } else if (element > node.element){
            node.rightNode = insert(element, node.rightNode);
//            node.rightNode.parentNode = node;
        }
        return node;
    }


    public MyBinaryNode remove(int element, MyBinaryNode node) {
        if (node == null) {
            return node;
        }

        int compare = element - node.element;
        if (compare < 0)
            node.leftNode = remove(element, node.leftNode);
        else if (compare > 0)
            node.rightNode = remove(element, node.rightNode);
        else if (node.leftNode != null && node.rightNode != null) {
            node.element = findMin(node.rightNode).element;
            node.rightNode = remove(node.element, node.rightNode);
        }
        else
            node = (node.leftNode != null) ? node.leftNode : node.rightNode;

        return node;
    }

    public boolean remove(int element) {
        return (remove(element, root) != null);
    }

    /**
     * 二叉树的翻转
     *
     *
     * */
    public void reverse() {
        reverse(root);
    }

    public void reverse(MyBinaryNode node) {

        if (node == null)
            return;

        if (node.leftNode == null && node.rightNode == null)
            return;

        MyBinaryNode temp = node.leftNode;
        node.leftNode = node.rightNode;
        node.rightNode = temp;

        reverse(node.leftNode);
        reverse(node.rightNode);
    }


    /**
     * 找出二叉树中的第 K 大节点
     *
     * */
    public Integer numberthInOrderRecursion(int key) {
        List<Integer> list = inorderRecursion();
        if (list != null && !list.isEmpty() && list.size() > key - 1)
            return list.get(key-1);
        else
            return null;
    }


    /**
     * 中序遍历
     *
     * */
    public List<Integer> inorderRecursion() {
        return inorderRecursion(root);
    }

    public List<Integer> inorderRecursion(MyBinaryNode node) {
        if (node == null)
            return null;

        List<Integer> left = inorderRecursion(node.leftNode);
        Integer element = node.element;
        List<Integer> right = inorderRecursion(node.rightNode);

        List<Integer> ret = new ArrayList<Integer>();
        if (left != null)
            ret.addAll(left);

        if (element != null)
            ret.add(element);

        if (right != null)
            ret.addAll(right);

        return ret;
    }

    /**
     * 前序遍历
     *
     * */
    public List<Integer> preOrderRecursion() {
        return preOrderRecursion(root);
    }

    public List<Integer> preOrderRecursion(MyBinaryNode node) {
        if (node == null)
            return null;

        Integer element = node.element;
        List<Integer> left = preOrderRecursion(node.leftNode);
        List<Integer> right = preOrderRecursion(node.rightNode);

        List<Integer> ret = new ArrayList<Integer>();
        if (element != null)
            ret.add(element);

        if (left != null)
            ret.addAll(left);

        if (right != null)
            ret.addAll(right);

        return ret;
    }


    public List<Integer> postOrderRecursion() {
        return postOrderRecursion(root);
    }

    public List<Integer> postOrderRecursion(MyBinaryNode node) {
        if (node == null)
            return null;

        List<Integer> left = postOrderRecursion(node.leftNode);
        List<Integer> right = postOrderRecursion(node.rightNode);
        Integer element = node.element;

        List<Integer> ret = new ArrayList<Integer>();
        if (left != null)
            ret.addAll(left);

        if (right != null)
            ret.addAll(right);

        if (element != null)
            ret.add(element);

        return ret;
    }


    /**
     * 从上到下遍历树的每一层节点打印出来；
     *
     * */
    public List<Integer> topToBottomOrderRecursion() {
        return topToBottomOrderRecursion(root);
    }

    public List<Integer> topToBottomOrderRecursion(MyBinaryNode node) {

        if (node == null) {
            return null;
        }

        List<Integer> array = new ArrayList<Integer>();
        Queue<MyBinaryNode> queue = new LinkedList<MyBinaryNode>();

        queue.add(node);
        while (!queue.isEmpty()) {

            MyBinaryNode node1 = queue.poll();
            if (node1.leftNode != null) {
                queue.add(node1.leftNode);
            }

            if (node1.rightNode != null) {
                queue.add(node1.rightNode);
            }

            array.add(node1.element);
        }
        return array;
    }

    /**
     * 判断已知的一个二叉树是否是对称的。
     *
     * 算法思想是：首先判断这棵树是否为空树，如果空树则直接返回true
     *
     * 如果不为空，则在进行分类：case1：节点的左右子树为空，则直接返回true
     *
     * case2：节点的左右子树有一个为空，则直接返回false
     *
     * case3：节点的左右子树均不为空，
     * 则判断节点的左右子节点的值是否相等并且判断左节点的子左节点和右节点的右子节点是否对称还有左节点的右子节点和右节点的左子节点是否对称
     *
     * @return
     */
    public boolean isSymmetrical() {
        if (root == null)
            return true;

        else
            return isSymmetrical(root.leftNode, root.rightNode);
    }


    public boolean isSymmetrical(MyBinaryNode left, MyBinaryNode right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null ) {
            return false;
        }

        return (left.element == right.element)
                && isSymmetrical(left.leftNode, right.rightNode)
                && isSymmetrical(left.rightNode, right.leftNode);
    }


    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     *
     *
     * 1.从第0位开始，找到第一位比根节点大的元素，记录此位置i。在此位置之前都属于左子树（此时已经断定左子树都小于根节点）
     * 2.检查右子树是否都大于跟节点（从第i位开始，到根节点前）
     * 3.判断左右子树是否都属于二叉搜索树。
     * */
    public boolean verifySequenceIsBstPostOrder(Integer[] array) {
        if (array.length == 0)
            return false;

        return judge(array, 0, array.length - 1);
    }


    public boolean judge(Integer[] array, int start, int end) {
        if (start >= end) {
            return true;
        }

        int i, j;
        if ((end - start) <= 1) {
            return true;
        }

        for (i = start; i < end; i++) {
            if (array[i] > array[end]) {
                break;
            }
        }

        for (j = i; j < end; j++) {
            if (array[j] < array[end]) {
                return false;
            }
        }

        boolean left = false;
        boolean right = false;

        if (i > 0) {
            left = judge(array, start, i - 1);
        }
        if (i < array.length - 1) {
            right = judge(array, i, end-1);
        }

        return left && right;
    }

    /**
     * 二叉树的序列化
     *
     *
     * */
    public String serialize() {
        return serialize(root);
    }

    public String serialize(MyBinaryNode node) {
        StringBuilder stringBuilder = new StringBuilder();

        if (node == null)
            return stringBuilder.append("#,").toString();

        stringBuilder.append(node.element + ",");
        stringBuilder.append(serialize(node.leftNode));
        stringBuilder.append(serialize(node.rightNode));

        return stringBuilder.toString();
    }

    int index = -1;

    /**
     * 二叉树的反序列化
     *
     *
     * */
    public void deserialize(String serialize) {
        String[] strings = serialize.split(",");
        index = -1;
        root = deserialize(strings);
    }

    public MyBinaryNode deserialize(String[] strings) {
        index++;
        MyBinaryNode node = null;
        if (!strings[index].equals("#")) {
            node = new MyBinaryNode(Integer.valueOf(strings[index]));
            node.leftNode = deserialize(strings);
            node.rightNode = deserialize(strings);
        }

        return node;
    }


    public static void main(String[] args) {

        MyBinaryTree myBinaryTree = new MyBinaryTree();

        Random random = new Random(3255);
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(1000);
            myBinaryTree.insert(r);
        }

//        int max = myBinaryTree.findMax();
//        int min = myBinaryTree.findMin();
//
//        System.out.println(max);
//        System.out.println(min);
//
//        System.out.println(myBinaryTree.nodeCount());
//        System.out.println(myBinaryTree.depth());
//
//
//        System.out.println(myBinaryTree.remove(7));
//        System.out.println(myBinaryTree.nodeCount());

//        System.out.println(myBinaryTree.inorderRecursion());
//        System.out.println(myBinaryTree.remove(641));

//        myBinaryTree.reverse();
//        System.out.println(myBinaryTree.inorderRecursion());
//
//        System.out.println(myBinaryTree.preOrderRecursion());
//
//        System.out.println(myBinaryTree.postOrderRecursion());
//
//        List<Integer> postOrder = myBinaryTree.postOrderRecursion();
//        System.out.println(postOrder);
//
//        Integer[] array = new Integer[postOrder.size()];
//        for (int i = 0; i<postOrder.size(); i++) {
//            array[i] = postOrder.get(i);
//        }
//
//        System.out.println(myBinaryTree.verifySequenceIsBstPostOrder(array));


//        System.out.println(myBinaryTree.serialize());
//        String str = myBinaryTree.serialize();
//
//        MyBinaryTree myBinarySearchTree1 = new MyBinaryTree();
//        myBinarySearchTree1.deserialize(str);
//
//        System.out.println(myBinarySearchTree1.serialize());

        System.out.println(myBinaryTree.inorderRecursion());
        System.out.println(myBinaryTree.numberthInOrderRecursion(10));
        System.out.println(myBinaryTree.depth());
    }

    @Test
    public void test1() {

        MyBinaryTree myBinaryTree = new MyBinaryTree();
        myBinaryTree.insert(5);
        myBinaryTree.insert(1);
        myBinaryTree.insert(7);

        myBinaryTree.remove(1);
    }
}
