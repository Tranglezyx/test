package com.test.collection;

import com.test.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
public class ArrayListTest {

    public static List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
//        initUserList();
//        List<User> collect = userList.stream().filter(item -> item.getMoney().compareTo(new BigDecimal(333)) <= 0).collect(Collectors.toList());
//        for (User user : collect) {
//            user.setMoney(user.getMoney().add(new BigDecimal(100)));
//        }
//        System.out.println(collect);
//        System.out.println(userList);

        // 需要补充的汇率数据
        LocalDate start = LocalDate.of(2023, 5, 1);
        LocalDate end = LocalDate.now();
        while (start.isBefore(end)) {
            System.out.println(start);
            start = start.plusDays(1);
        }
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
