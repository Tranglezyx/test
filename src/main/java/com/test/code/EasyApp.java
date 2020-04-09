package com.test.code;

import com.test.code.entity.TreeNode;
import com.test.code.solution.EasySolution;
import com.test.code.util.TreeUtils;

import java.io.IOException;
import java.util.UUID;

/**
 * @author trangle
 */
public class EasyApp {

    public static void main(String[] args) throws IOException {
        TreeNode root = TreeUtils.generateTree(1,2,2,3,3,3,3,4,4,4,4,4,4,null,null,5,5);
        System.out.println(TreeUtils.getTreeMaxDepth(root));
        System.out.println(TreeUtils.getTreeMinDepth(root));
        TreeUtils.show(root);
        System.out.println(TreeUtils.isBalanced(root));
        System.out.println(UUID.randomUUID().toString());
    }
}
