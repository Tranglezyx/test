package com.test.code.util;

import com.test.code.entity.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * @author trangle
 */
public class TreeUtils {

    /**
     * 按照层次遍历的方式构建树
     *
     * @param values
     */
    public static TreeNode generateTree(Integer... values) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                queue.offer(null);
            } else {
                queue.offer(new Integer(values[i]));
            }
        }
        return generateTree(queue);
    }

    /**
     * 按照层次遍历的方式构建树
     *
     * @param valueQueue
     */
    public static TreeNode generateTree(Queue<Integer> valueQueue) {
        if (valueQueue == null || valueQueue.size() == 0) {
            return null;
        }
        TreeNode root = new TreeNode(valueQueue.poll());
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!valueQueue.isEmpty()) {
            LinkedList tempList = new LinkedList();
            while (!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.poll();
                if (valueQueue.size() >= 2) {
                    node.left = new TreeNode(valueQueue.poll());
                    node.right = new TreeNode(valueQueue.poll());
                    tempList.offer(node.left);
                    tempList.offer(node.right);
                } else if (valueQueue.size() == 1) {
                    node.left = new TreeNode(valueQueue.poll());
                    tempList.offer(node.left);
                }
            }
            nodeQueue = tempList;
        }
        return root;
    }

    /**
     * 前序遍历
     *
     * @param treeNode
     * @param valueList
     */
    public static void preOrder(TreeNode treeNode, List<Integer> valueList) {
        if (treeNode == null) {
            return;
        }
        valueList.add(treeNode.val);
        preOrder(treeNode.left, valueList);
        preOrder(treeNode.right, valueList);
    }

    /**
     * 中序遍历
     *
     * @param treeNode
     * @param valueList
     */
    public static void inOrder(TreeNode treeNode, List<Integer> valueList) {
        if (treeNode == null) {
            return;
        }
        inOrder(treeNode.left, valueList);
        valueList.add(treeNode.val);
        inOrder(treeNode.right, valueList);
    }

    /**
     * 后序遍历
     *
     * @param treeNode
     * @param valueList
     */
    public static void postOrder(TreeNode treeNode, List<Integer> valueList) {
        if (treeNode == null) {
            return;
        }
        postOrder(treeNode.left, valueList);
        postOrder(treeNode.right, valueList);
        valueList.add(treeNode.val);
    }

    /**
     * 层次遍历，采取广度搜索
     *
     * @param treeNode
     * @param valueList
     */
    public static void BFS(TreeNode treeNode, List<Integer> valueList) {
        Queue<TreeNode> queue = new LinkedList();
        if (treeNode != null) {
            queue.offer(treeNode);
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            valueList.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 判断二叉树是否镜像对称
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return symmetric(root.left, root.right);
    }

    /**
     * 判断二叉树是否镜像对称
     *
     * @param leftNode
     * @param rightNode
     * @return
     */
    public static boolean symmetric(TreeNode leftNode, TreeNode rightNode) {
        Boolean x = isNull(leftNode, rightNode);
        if (x != null) return x;
        if (!Objects.equals(leftNode.val, rightNode.val)) {
            return false;
        }
        return symmetric(leftNode.left, rightNode.right) && symmetric(leftNode.right, rightNode.left);
    }

    /**
     * 相同的树
     * <p>
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     *
     * @param leftNode
     * @param rightNode
     * @return
     */
    public static boolean isSameTree(TreeNode leftNode, TreeNode rightNode) {
        Boolean x = isNull(leftNode, rightNode);
        if (x != null) return x;
        if (leftNode.val == rightNode.val && isSameTree(leftNode.left, rightNode.left) && isSameTree(leftNode.right, rightNode.right)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否为空
     *
     * @param leftNode
     * @param rightNode
     * @return
     */
    private static Boolean isNull(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode == null || rightNode == null) {
            return false;
        }
        return null;
    }
}
