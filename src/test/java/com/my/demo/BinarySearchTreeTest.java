package com.my.demo;

import com.my.demo.datastructure.tree.BinarySearchTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyl
 * @create 2021-04-15
 * @version: branch_member_20210407_v4_3_2
 */
public class BinarySearchTreeTest {

    private static Integer[] arrays = new Integer[]{10, 8, 3, 12, 9, 4, 5, 7, 1,11, 17};

    @Test
    public void testEach() {
        BinarySearchTree<Integer> mSearchTree = new BinarySearchTree<>();
        for (Integer data : arrays) {
            mSearchTree.insert(data);
        }

        List<Integer> list = new ArrayList<>();
        // 打印二叉树的三种遍历顺序
        mSearchTree.inOrderTraveral(mSearchTree.getRoot(), list);
        System.out.println(list);
    }

    @Test
    public void testFindMax() {
        BinarySearchTree<Integer> mSearchTree = new BinarySearchTree<>();
        for (Integer data : arrays) {
            mSearchTree.insert(data);
        }
        System.out.println(mSearchTree.findMax());
    }

    @Test
    public void testDelete() {
        BinarySearchTree<Integer> mSearchTree = new BinarySearchTree<>();
        for (Integer data : arrays) {
            mSearchTree.insert(data);
        }
        List<Integer> list = new ArrayList<>();
        // 打印二叉树的三种遍历顺序
        mSearchTree.inOrderTraveral(mSearchTree.getRoot(), list);
        System.out.println(list);

        mSearchTree.delete(10);
        // 打印二叉树的三种遍历顺序
        list.clear();
        mSearchTree.inOrderTraveral(mSearchTree.getRoot(), list);
        System.out.println(list);
    }

    @Test
    public void testHeight() {
        BinarySearchTree<Integer> mSearchTree = new BinarySearchTree<>();
        for (Integer data : arrays) {
            mSearchTree.insert(data);
        }
        System.out.println(mSearchTree.getTreeHeight());
        mSearchTree.delete(4);
        System.out.println(mSearchTree.getTreeHeight());
    }
}
