package com.test.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author trangle
 */
public class ArrayListTest {

    public static List<String> stringList = new ArrayList<>();

    public static void main(String[] args) {
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("D");
        stringList.add("E");
        Iterator<String> iterator = stringList.iterator();
        while(iterator.hasNext()){
            String s = iterator.next();
            if("C".equals(s)){
                iterator.remove();
            }
        }
        iterator = stringList.iterator();
        while(iterator.hasNext()){
            String s = iterator.next();
            System.out.println(s);
        }
    }
}
