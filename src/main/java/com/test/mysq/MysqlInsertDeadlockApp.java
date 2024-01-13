package com.test.mysq;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.google.common.collect.Lists;
import com.test.entity.SmsTo;
import com.test.jdbc.JDBCUtils;
import com.test.jdbc.MysqlConstants;
import com.test.jdbc.SQLUtils;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class MysqlInsertDeadlockApp {
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


    private static final Executor executors = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        executors.execute(() -> batchInsert());
        executors.execute(() -> batchInsert());
        executors.execute(() -> batchInsert());
        executors.execute(() -> batchInsert());
    }

    private static void batchInsert() {
        List<SmsTo> list = new ArrayList<>();
        int size = 100000;
        for (int i = 0; i < size; i++) {
            list.add(SmsTo.buildRandomSmsTo());
        }
        List<List<SmsTo>> partition = Lists.partition(list, 200);
        for (List<SmsTo> toList : partition) {
            try {
                String sql = SQLUtils.generateBatchInsertSQL(toList);
                Connection connnection = dataSource.getConnection();
                // 3. 创建Statement对象执行查询
                Statement statement = connnection.createStatement();
                // 4. 执行查询并处理结果
                int resultSet = statement.executeUpdate(sql);
                log.info("处理结果,result:{}", resultSet);
                statement.close();
            } catch (Exception e) {
                log.error("出现错误", e);
            }
        }
    }
}
