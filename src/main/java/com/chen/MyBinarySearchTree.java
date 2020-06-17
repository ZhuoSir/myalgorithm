package com.chen;

import java.util.*;

public class MyBinarySearchTree {

    private MyBinaryNode root;

    public MyBinarySearchTree() {
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
        } else if (element > node.element){
            node.rightNode = insert(element, node.rightNode);
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
    public List<Integer> print() {
        return print(root);
    }

    public List<Integer> print(MyBinaryNode node) {

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
     * @param pRoot
     * @return
     */
    public boolean isSymmetrical() {
        if (root == null)
            return true;

        else
            return isSymmetrical(root.leftNode, root.rightNode);
    }


    public boolean isSymmetrical(MyBinaryNode left, MyBinaryNode right) {
        if (left == null || right == null) {
            return false;
        }

        return (left.element == right.element)
                && isSymmetrical(left.leftNode, right.rightNode)
                && isSymmetrical(left.rightNode, right.leftNode);
    }



    public static void main(String[] args) {

        MyBinarySearchTree myBinarySearchTree = new MyBinarySearchTree();

        Random random = new Random(3255);
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(1000);
            myBinarySearchTree.insert(r);
        }

//        int max = myBinarySearchTree.findMax();
//        int min = myBinarySearchTree.findMin();
//
//        System.out.println(max);
//        System.out.println(min);
//
//        System.out.println(myBinarySearchTree.nodeCount());
//        System.out.println(myBinarySearchTree.depth());
//
//
//        System.out.println(myBinarySearchTree.remove(7));
//        System.out.println(myBinarySearchTree.nodeCount());

//        System.out.println(myBinarySearchTree.inorderRecursion());
//        System.out.println(myBinarySearchTree.remove(641));

//        myBinarySearchTree.reverse();
//        System.out.println(myBinarySearchTree.inorderRecursion());
//
//        System.out.println(myBinarySearchTree.preOrderRecursion());
//
//        System.out.println(myBinarySearchTree.postOrderRecursion());

        System.out.println(myBinarySearchTree.print());

        System.out.println(myBinarySearchTree.isSymmetrical());
    }

}
