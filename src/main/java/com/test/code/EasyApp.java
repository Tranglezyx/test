package com.test.code;

import com.test.code.entity.TreeNode;
import com.test.code.solution.EasySolution;
import com.test.code.util.TreeUtils;

import java.io.IOException;

/**
 * @author trangle
 */
public class EasyApp {

    public static void main(String[] args) throws IOException {
        TreeNode root = TreeUtils.generateTree(1,null,2,3,4,null,5,null,6,7,8);
        System.out.println(TreeUtils.getTreeDepth(root));
        System.out.println(TreeUtils.getTreeMinDepth(root));
        TreeUtils.show(root);
    }
}
