package com.test.db.jdbc;

import com.test.db.utils.JDBCUtils;

import java.sql.*;

/**
 * @author trangle
 */
public class App {

    public static void main(String[] args) throws SQLException {
//        testMysqlBatchInsert();
        testSqlserverBatchInsert();
    }

    public static void testSqlserverBatchInsert() throws SQLException {
        String insertSql = "insert into test_1(code,num) values ('aa','aa'),('bb','bb'),('cc','cc')";
        JDBCUtils.useMysqlConstants();
        Connection conn = JDBCUtils.getConnnection();
        PreparedStatement pstm = conn.prepareStatement(insertSql);
        pstm.execute(insertSql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = pstm.getGeneratedKeys();
        while (rs.next()) {
            Object value = rs.getObject(1);
            System.out.println(value);
        }
        JDBCUtils.close(rs, pstm, conn);
    }

    public static void testMysqlBatchInsert() throws SQLException {
        String insertSql = "insert into test_1(code,num) values (?,?),(?,?),(?,?)";

        // jdbc测试代码
        JDBCUtils.useMysqlConstants();
        Connection conn = JDBCUtils.getConnnection();
        PreparedStatement pstm = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
        pstm.setString(1, "name1");
        pstm.setString(2, "email1");


        pstm.setString(3, "name2");
        pstm.setString(4, "email2");

        pstm.setString(5, "name2");
        pstm.setString(6, "email2");

        pstm.addBatch();
        pstm.executeBatch();

        ResultSet rs = pstm.getGeneratedKeys();
        while (rs.next()) {
            Object value = rs.getObject(1);
            System.out.println(value);
        }
        JDBCUtils.close(rs, pstm, conn);
    }
}
