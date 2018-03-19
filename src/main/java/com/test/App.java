package com.test;



import com.sun.xml.internal.bind.v2.util.QNameMap;
import com.test.dto.Users;
import com.test.proxy.InvocationHandlerUtils;
import com.test.util.ColumnsFormatUtils;
import com.test.util.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @author Trangle
 * Hello world!
 */
public class App
{
    public static void main(String[] args) throws ParseException {
        Set<Users> usersSet = new HashSet<>();
        Date date = new Date();
        Users u1 = new Users();
        u1.setUserName("Trangle");
        u1.setPassword("Trangle");
        u1.setCreationDate(date);
        u1.setUserId(10000);
        Users u2 = new Users();
        u2.setUserName("Trangle2");
        u2.setPassword("Trangle");
        u2.setCreationDate(date);
        u2.setUserId(10000);
        usersSet.add(u1);
        usersSet.add(u2);
        System.out.println(usersSet.size());

        List<Users> u = new ArrayList<>();
        u.addAll(usersSet);
        System.out.println(u.size());
        Iterator<Users> iterator = u.iterator();
        while(iterator.hasNext()){
            Users next = iterator.next();
            if(next.getUserName().equals("Trangle")){
                iterator.remove();
            }
        }
        System.out.println(u.size());
        for(Users users : u){
            if(users.getUserName().equals("Trangle")){
                u.remove(users);
            }
        }
        System.out.println(u.size());

        Map<String,Users> usersMap = new HashMap<>();
        usersMap.put(u1.getUserName(),u1);
        usersMap.put(u2.getUserName(),u2);
//        Set<QNameMap.Entry<String,Users>> usersEntry =  usersMap.entrySet();
    }

    public static int factors(int num,int k){
        int count = 0;
        if(num % k != 0 || k == 1 || k == 0) {
            return count;
        }
        while(num > 1){
            num = num / k;
            count++;
            if(num % k != 0){
                return count;
            }
        }
        return count;
    }
}
