package com.test.util.collection;

import java.util.LinkedList;

/**
 * @author trangle
 */
public class LinkedListApp {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);
        System.out.println(linkedList.peek());
        System.out.println(linkedList.poll());
        System.out.println(linkedList.peek());
        System.out.println(linkedList.pop());
        System.out.println(linkedList.getLast());

    }
}
