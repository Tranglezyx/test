package com.test;

import java.sql.*;
import java.time.LocalDate;
import java.util.Random;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) {
        int i = 0;
        System.out.println(i++);
        System.out.println(++i);
    }

    public static void insert(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://trangle:3306/trangle?user=root&password=Trangle";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =  DriverManager.getConnection(url);
            stmt = conn.createStatement();
            for (int i = 0; i < 35; i++) {
                long start = System.currentTimeMillis();
                stmt.execute(sql());
                System.out.println("第"+ i + "次执行完成...耗时:" + (System.currentTimeMillis() - start) + "ms");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动异常");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库异常");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }
                if (stmt != null){
                    stmt.close();
                }
                if (conn != null){
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static String sql() {
        Random random = new Random();
        LocalDate now = LocalDate.now();
        String name = "name";
        StringBuilder str = new StringBuilder("insert into big_data_user(`name`,`birthday`) values");
        int length = 3000;
        for (int i = 0; i < length; i++) {
            String date = now.minusDays(random.nextInt(20000)).toString();
            str.append("('");
            str.append(name);
            str.append("','");
            str.append(date);
            str.append("')");
            if (i != length - 1) {
                str.append(",");
            }
        }
        return str.toString();
    }
}