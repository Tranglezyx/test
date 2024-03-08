package com.code.util;

import com.code.entity.ListNode;

/**
 * @author trangle
 */
public class LinkedListUtils {

    /**
     * 生成链表
     *
     * @param nums
     * @return
     */
    public static ListNode generateLinkedList(int... nums) {
        ListNode firstNode = new ListNode(nums[0]);
        ListNode node = firstNode;
        for (int i = 1; i < nums.length; i++) {
            ListNode tmpNode = new ListNode(nums[i]);
            node.next = tmpNode;
            node = tmpNode;
        }
        return firstNode;
    }

    /**
     * 打印单项链表
     *
     * @param listNode
     */
    public static void printLinkedList(ListNode listNode) {
        if (listNode == null) {
            return;
        }
        while (listNode != null) {
            System.out.print(listNode.val);
            if (listNode.next != null) {
                System.out.print(" -> ");
            }
            listNode = listNode.next;
        }
        System.out.println();
    }
}
