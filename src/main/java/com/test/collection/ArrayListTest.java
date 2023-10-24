package com.test.collection;

import com.test.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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
        initUserList();
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            if (next.getUserName().equals("111")) {
                iterator.remove();
            }
        }
        log.info("{}", userList);
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
