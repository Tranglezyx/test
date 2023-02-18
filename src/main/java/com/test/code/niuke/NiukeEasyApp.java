package com.test.code.niuke;

import com.test.code.entity.ListNode;
import com.test.code.util.LinkedListUtils;

public class NiukeEasyApp {

    public static void main(String[] args) {
        ListNode listNode = LinkedListUtils.generateLinkedList(1,2,3,4);
        LinkedListUtils.printLinkedList(listNode);
        ListNode listNode1 = new NiukeEasySolution().ReverseList(listNode);
        LinkedListUtils.printLinkedList(listNode1);
    }
}
