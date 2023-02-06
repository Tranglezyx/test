package com.test.code.niuke;

import com.test.code.entity.ListNode;

public class NiukeEasySolution {

    public ListNode ReverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
