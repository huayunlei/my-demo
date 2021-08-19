package com.my.demo.datastructure.tree;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 二叉树
 *
 * @author hyl
 * @create 2021-03-17
 * @version: branch_member_20210305_v4_3_1
 */
public class BinaryTree {

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4}));
        TreeNode node = createBinaryTree(inputList);
        List<Integer> list = new ArrayList<>();
        preOrderTraveral(node, list);
        System.out.println(list);

        list.clear();
        inOrderTraveral(node, list);
        System.out.println(list);

        list.clear();
        postOrderTraveral(node, list);
        System.out.println(list);
    }

    private static class TreeNode {
        private int data;
        private TreeNode leftNode;
        private TreeNode rightNode;

        TreeNode(int data) {
            this.data = data;
        }
    }

    /**
     * 构建二叉树
     * @param inputList
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        if (CollectionUtils.isEmpty(inputList)) {
            return null;
        }

        TreeNode treeNode = null;
        Integer data = inputList.removeFirst();
        if (null != data) {
            treeNode = new TreeNode(data);
            treeNode.leftNode = createBinaryTree(inputList);
            treeNode.rightNode = createBinaryTree(inputList);
        }

        return treeNode;
    }

    /**
     * 二叉树前序遍历
     * @param node
     * @param list
     */
    public static void preOrderTraveral(TreeNode node, List<Integer> list){
        if (null == node) {
            return;
        }
        list.add(node.data);
        preOrderTraveral(node.leftNode, list);
        preOrderTraveral(node.rightNode, list);
    }

    /**
     * 二叉树中序遍历
     * @param node
     * @param list
     */
    public static void inOrderTraveral(TreeNode node, List<Integer> list){
        if (null == node) {
            return;
        }
        inOrderTraveral(node.leftNode, list);
        list.add(node.data);
        inOrderTraveral(node.rightNode, list);
    }

    /**
     * 二叉树后序遍历
     * @param node
     * @param list
     */
    public static void postOrderTraveral(TreeNode node, List<Integer> list){
        if (null == node) {
            return;
        }
        postOrderTraveral(node.leftNode, list);
        postOrderTraveral(node.rightNode, list);
        list.add(node.data);
    }



}
