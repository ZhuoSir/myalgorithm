package com.chen;

/**
 * avl树，二叉平衡树
 *
 * */
public class MyAVLTree {

    private MyAVLNode root;

    public void insert(int element) {
        root = insert(element, root);
    }

    public MyAVLNode insert(int element, MyAVLNode node) {

        if (node == null) {
            return new MyAVLNode(element, null, null);
        }

        if (element < node.element) {
            node.leftNode  = insert(element, node.leftNode);

            //平衡性被破坏，需要平衡调整
            if (getHeight(node.leftNode) - getHeight(node.rightNode) > 1) {
                if (element < node.leftNode.element) { //插入节点在失衡节点的左子树的左边

                    node = LLRotate(node);  //左左旋转
                } else if (element > node.leftNode.element) {  //插入节点在失衡节点的左子树的右侧

                    node = LRRotate(node);  //左右旋转
                }
            }

        } else if (element > node.element) {
            node.rightNode = insert(element, node.rightNode);

            //平衡性被破坏，需要平衡调整
            if (getHeight(node.rightNode) - getHeight(node.leftNode) > 1) {
                if (element < node.rightNode.element) { //插入节点在失衡节点的右子树的左边

                    node = RLRotate(node);  //右左旋转
                } else if (element > node.rightNode.element) {  //插入节点在失衡节点的右子树的右边

                    node = RRRotate(node);  //右右旋转
                }
            }
        }

        node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
        return node;
    }

    public int getHeight(MyAVLNode p) {
        return p == null ? -1 : p.height;
    }


    public void print() {
        print(root);
    }

    public void print(MyAVLNode node) {

        if (node == null)
            return;

        System.out.println(node.element);
        if (node.leftNode != null) {
            System.out.println("left : ");
            print(node.leftNode);
        }
        if (node.rightNode != null) {
            System.out.println("right : ");
            print(node.rightNode);
        }
    }


    /**
     * 右右旋转
     *
     * */
    private MyAVLNode RRRotate(MyAVLNode node) {
        MyAVLNode pNode = node.rightNode;

        node.rightNode = pNode.leftNode;
        pNode.leftNode = node;

        node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
        pNode.height = Math.max(pNode.leftNode.height, node.height) + 1;

        return pNode;
    }

    /**
     * 右左旋转
     *
     * */
    private MyAVLNode RLRotate(MyAVLNode node) {
        node.rightNode = LLRotate(node);
        return RRRotate(node);
    }

    /**
     * 左右旋转
     *
     * */
    private MyAVLNode LRRotate(MyAVLNode node) {
        node.leftNode = RRRotate(node);
        return LLRotate(node);
    }

    /**
     * 左左旋转
     *
     * */
    private MyAVLNode LLRotate(MyAVLNode node) {
        MyAVLNode pNode = node.leftNode;

        node.leftNode = pNode.rightNode;
        pNode.rightNode = node;

        node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;
        pNode.height = Math.max(pNode.leftNode.height, node.height) + 1;

        return pNode;
    }
}