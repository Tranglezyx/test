package com.test.code.util;

import com.test.code.entity.TreeNode;

import java.sql.SQLOutput;
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
                    if (node.left != null) {
                        tempList.offer(node.left);
                    }
                    if (node.right != null) {
                        tempList.offer(node.right);
                    }
                } else if (valueQueue.size() == 1) {
                    node.left = new TreeNode(valueQueue.poll());
                    if (node.left != null) {
                        tempList.offer(node.left);
                    }
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

    /**
     * 是否是高度平衡的二叉树
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        return false;
    }

    /**
     * 用于获得树的层数
     *
     * @param root
     * @return
     */
    public static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }

    public static int getTreeMinDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.min(getTreeMinDepth(root.left), getTreeMinDepth(root.right)));
    }

    /**
     * 输出一棵树
     *
     * @param root
     */
    public static void show(TreeNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line : res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }

    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }
}
