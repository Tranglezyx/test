package com.test.util.collection;

import com.test.domain.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
@Slf4j
public class ArrayListTest {

    public static List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        test(list);
        log.info("{}", list);
        test3(list);
        log.info("{}", list);
    }

    public static void test(List<Integer> list) {
        list = test1(list);
        log.info("{}", list.size());
    }

    public static void test3(List<Integer> list) {
        list = test2(list);
        log.info("{}", list.size());
    }

    public static List<Integer> test1(List<Integer> list) {
        List<Integer> tmpList = new ArrayList<>();
        tmpList.add(3);
        return tmpList;
    }

    public static List<Integer> test2(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next == 1) {
                iterator.remove();
            }
        }
        return list;
    }

    public static void sortedTest() {
        initUserList();
//        userList = userList.stream().sorted(Comparator.comparing(User::getMoney)).collect(Collectors.toList());
    }

    public static void filterTest() {
        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("C");
        stringList.add("D");
        stringList.add("E");
        stringList = stringList.stream().filter(s -> !s.equals("A")).collect(Collectors.toList());
        System.out.println(stringList);
    }

    public static void initUserList() {
        userList.add(new User("111", new BigDecimal(111)));
        userList.add(new User("222", new BigDecimal(222)));
        userList.add(new User("666", new BigDecimal(666)));
        userList.add(new User("555", new BigDecimal(555)));
        userList.add(new User("333", new BigDecimal(333)));
    }
}
