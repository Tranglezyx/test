package com.test.db.jdbc;

import org.apache.log4j.Logger;

import java.sql.*;

/**
 * @author trangle
 */
public class JDBCUtils {

    /**
     * 使用log4j记录日志
     */
    private static Logger logger = Logger.getLogger(JDBCUtils.class);

    /**
     * 连接驱动
     */
    private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    /**
     * 连接路径
     */
    private static  String URL = "jdbc:sqlserver://db.srm.lkk.com:1433;databaseName=hzero_file";

    /**
     * 用户名
     */
    private static String USERNAME = "lkksrm";

    /**
     * 密码
     */
    private static String PASSWORD = "1n1tPass";

    /**
     * 使用mysql参数
     */
    public static void useMysqlConstants(){
        DRIVER = MysqlConstants.DRIVER;
        URL = MysqlConstants.URL;
        USERNAME = MysqlConstants.USERNAME;
        PASSWORD = MysqlConstants.PASSWORD;
    }

    //静态代码块
    static {
        try {
            // 加载驱动
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回connection
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    /**
     * 关闭
     *
     * @param rs
     * @param ps
     * @param conn
     */
    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("关闭ResultSet失败");
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("关闭PreparedStatement失败");
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                logger.error("关闭Connection失败");
            }
        }
    }
}
