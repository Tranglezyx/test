package com.test.code;

import com.test.code.entity.ListNode;
import com.test.code.solution.EasySolution;
import com.test.code.util.LinkedListUtils;

import java.io.IOException;
import java.util.LinkedList;

/**
 * @author trangle
 */
public class EasyApp {

    public static void main(String[] args) throws IOException {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new EasySolution().maxSubArray2(nums));
    }

}
