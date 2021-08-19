package com.my.demo.datastructure.tree;

import java.util.List;

/** 二叉搜索树
 * @author hyl
 * @create 2021-04-14
 * @version: branch_member_20210407_v4_3_2
 * 参考   https://juejin.cn/post/6844903506847989774
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public BinarySearchTree() {
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * 获得树高度
     * @return
     */
    public int getTreeHeight() {
        if (this.root == null) {
            return 0;
        }
        return getTreeHeight(this.root);
    }

    private int getTreeHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getTreeHeight(node.leftChild);
        int rightHeight = getTreeHeight(node.rightChild);
        int max = leftHeight > rightHeight ? leftHeight : rightHeight;
        // 得到左右子树中较大的返回.
        return max + 1;
    }

    /**
     * 从树中删除值为value 的特定结点
     *
     * @param value
     */
    public void delete(T value) {
        if (this.root == null || value == null) {
            return;
        }
        root = delete(this.root, value);
    }

    private TreeNode<T> delete(TreeNode<T> node, T value) {
        if (null == node) {
            return node;
        }
        if (compare(node, value) < 0) {
            node.leftChild = delete(node.leftChild, value);
        } else if (compare(node, value) > 0) {
            node.rightChild = delete(node.rightChild, value);
        } else {
            if (node.leftChild != null && node.rightChild != null) {// 被删除的结点，包含左右子树
                T temp = findMin(node.rightChild); // 得到右子树的最小值
                node.data = temp; //右子树最小值替换当前结点
                node.rightChild = delete(node.rightChild, temp); // 从右子树删除这个最小值的结点
            } else {// 被删除的结点，包含一个子树或没有子树
                if (node.leftChild != null) {
                    node = node.leftChild;
                } else {
                    node = node.rightChild;
                }
            }
        }
        return node;
    }

    /**
     * 查找最小值
     * @return
     */
    public T findMin() {
        if (this.root == null || this.root.data == null) {
            return null;
        }
        return findMin(this.root);
    }

    private T findMin(TreeNode<T> node) {
        TreeNode<T> temp = node;
        while (null != temp.leftChild) {
            temp = temp.leftChild;
        }
        return temp.data;
    }

    /**
     * 查找最大值
     * @return
     */
    public T findMax() {
        if (this.root == null || this.root.data == null) {
            return null;
        }
        return findMax(this.root);
    }

    private T findMax(TreeNode<T> node) {
        TreeNode<T> temp = node;
        while (null != temp.rightChild) {
            temp = temp.rightChild;
        }
        return temp.data;
    }

    /**
     * 二叉树中序遍历
     * @param node
     * @param list
     */
    public void inOrderTraveral(TreeNode<T> node, List list) {
        if (node == null) {
            return;
        }
        inOrderTraveral(node.leftChild, list);
        list.add(node.data);
        inOrderTraveral(node.rightChild, list);
    }


    /**
     * 二叉搜索树插入
     */
    public void insert(T value) {
        if (null == value) {
            return;
        }
        root = insert(this.root, value);
    }

    private TreeNode<T> insert(TreeNode<T> node, T value) {
        if (node == null) {
            return new TreeNode<>(value);
        } else {
            int c = compare(node, value);
            if (c <= 0) {
                node.leftChild = insert(node.leftChild, value);
            } else if (c > 0) {
                node.rightChild = insert(node.rightChild, value);
            }
        }
        return node;
    }

    private int compare(TreeNode<T> node, T value) {
        return value.compareTo(node.data);
    }


    /**
     * 二叉搜索树树结点定义
     * @param <T>
     */
    class TreeNode<T extends Comparable<T>> {
        private T data;
        private TreeNode<T> leftChild;
        private TreeNode<T> rightChild;

        public TreeNode(T data) {
            this(null, data, null);
        }

        public TreeNode(TreeNode<T> leftChild, T data, TreeNode<T> rightChild) {
            this.leftChild = leftChild;
            this.data = data;
            this.rightChild = rightChild;
        }
    }
}
