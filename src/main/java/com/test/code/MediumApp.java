package com.test.code;

import com.test.code.entity.ListNode;
import com.test.code.solution.MediumSolution;

import java.util.ArrayList;
import java.util.List;

public class MediumApp {

    public static void main(String[] args) {
        List<ListNode> nodeList = new ArrayList<>();
        nodeList.add(new ListNode(1));
        nodeList.add(new ListNode(2));
        nodeList.add(new ListNode(3));
        nodeList.add(new ListNode(4));
        nodeList.add(new ListNode(5));
        nodeList.add(new ListNode(6));
        for (int i = 0; i < nodeList.size() - 1; i++) {
            nodeList.get(i).next = nodeList.get(i + 1);
        }
        
    }
}
