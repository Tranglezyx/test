package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.entity.User;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws JsonProcessingException, IllegalAccessException, NoSuchFieldException, ParseException {
        List<User> userList = User.getDefaultUserListInfo();
//        System.out.println(userList.stream().collect(Collectors.toMap(User::getUserName, e -> e)));

//        System.out.println(userList.stream().map(User::getUserName).collect(Collectors.joining(",")));
//        System.out.println(userList.stream().collect(Collectors.toMap(User::getUserName,User::getMoney)));
//        System.out.println(userList.stream().map(User::getUserName).collect(Collectors.toList()));
//        System.out.println(userList.stream().collect(Collectors.groupingBy(User::getUserName)));
        System.out.println(userList.stream()
                .filter(user -> "qqq".equals(user.getUserName()))
                .collect(Collectors.toList()));
        System.out.println(userList);
//        List<User> tempList = new ArrayList<>();
//        tempList.stream().collect(Collectors.groupingBy(User::getUserName));

//        System.out.println(FieldUtils.getField(User.class, "userName", true).isAnnotationPresent(Column.class));
//        System.out.println(UUID.randomUUID().toString());

//        System.out.println(AnnotationUtils.getAnnotationValue(User.class, "userName", Column.class));
//        AnnotationUtils.updateFieldAnnotationValue(User.class, "userName", Column.class, "value", "qqq");
//        System.out.println(AnnotationUtils.getAnnotationValue(User.class, "userName", Column.class));

//        Map<String, Object> map = new HashMap<>();
//        map.put("11", new Date());
//        map.put("22", null);
//        System.out.println(JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd HH:mm", SerializerFeature.WriteMapNullValue));
//        System.out.println(UUID.randomUUID());
//        List<String> stringList = Arrays.asList("2019-11", "2019-12", "2020-01");
//        Date startDate;
//        int length;
//        if (CollectionUtils.isNotEmpty(stringList)) {
//            startDate = DateUtils.parseDate(stringList.get(0), "yyyy-MM");
//            length = 12 - stringList.size();
//        } else {
//            startDate = new Date();
//            length = 12;
//        }
//
//        for (int i = length; i > 0; i--) {
////            System.out.println(DateFormatUtils.format(DateUtils.addMonths(startDate, 0 - i), "yyyy-MM"));
//        }
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("qqq");
//        sb.append("qqq");
//        sb.append("qqq");
//        sb.append("qqq");
//        System.out.println(sb);

//        Calendar now = Calendar.getInstance();
//        now.setTime(new Date());
//        now.set(Calendar.DAY_OF_MONTH, 1);
//        now.set(Calendar.HOUR_OF_DAY, 0);
//        now.set(Calendar.MINUTE, 0);
//        now.set(Calendar.SECOND, 0);
//        now.set(Calendar.MILLISECOND, 0);
//        Date beginOfMon = now.getTime();
//        System.out.println(DateFormatUtils.format(beginOfMon, "yyyy-MM-dd HH:mm:ss:SSS"));
//        System.out.println(DateFormatUtils.format(DateUtils.addYears(beginOfMon,-1), "yyyy-MM-dd HH:mm:ss:SSS"));

    }


}