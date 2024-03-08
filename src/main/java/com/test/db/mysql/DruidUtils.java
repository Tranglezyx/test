package com.test.db.mysql;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.test.db.jdbc.MysqlConstants;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DruidUtils {

    private static final Map<String, Object> prop = new HashMap<>();
    private static DataSource dataSource;

    static {
        prop.put("driverClassName", MysqlConstants.DRIVER);
        prop.put("url", MysqlConstants.URL);
        prop.put("username", MysqlConstants.USERNAME);
        prop.put("password", MysqlConstants.PASSWORD);
        prop.put("initialSize", "20");
        prop.put("maxActive", "20");
        try {
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            log.error("创建数据源失败:", e);
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}
