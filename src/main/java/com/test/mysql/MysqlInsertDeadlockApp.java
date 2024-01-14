package com.test.mysql;

import cn.hutool.db.Session;
import com.google.common.collect.Lists;
import com.test.entity.SmsTo;
import com.test.jdbc.SQLUtils;
import com.test.mapper.SmsToMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
public class MysqlInsertDeadlockApp {

    private static final Executor executors = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception {
        String resource = "mybatis.xml"; // 替换为实际路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        onlyBatchInsertUpdateOnDuplicateKeyTest(sqlSessionFactory);
    }

    /**
     * 只有insert，测试结果表明不会死锁
     *
     * @param sqlSessionFactory
     */
    private static void onlyBatchInsertUpdateOnDuplicateKeyTest(SqlSessionFactory sqlSessionFactory) {
        executors.execute(() -> batchInsertUpdateOnDuplicateKey(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsertUpdateOnDuplicateKey(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsertUpdateOnDuplicateKey(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsertUpdateOnDuplicateKey(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsertUpdateOnDuplicateKey(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsertUpdateOnDuplicateKey(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsertUpdateOnDuplicateKey(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsertUpdateOnDuplicateKey(buildSmsToList(), sqlSessionFactory));
    }

    /**
     * 只有insert，测试结果表明不会死锁
     *
     * @param sqlSessionFactory
     */
    private static void onlyBatchInsertTest(SqlSessionFactory sqlSessionFactory) {
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
        executors.execute(() -> batchInsert(buildSmsToList(), sqlSessionFactory));
    }

    private static List<SmsTo> buildSmsToList() {
        List<SmsTo> list = new ArrayList<>();
        int size = 100000;
        for (int i = 0; i < size; i++) {
            list.add(SmsTo.buildRandomSmsTo());
        }
        return list;
    }

    private static void batchInsert(List<SmsTo> list) {
        List<List<SmsTo>> partition = Lists.partition(list, 200);
        for (List<SmsTo> toList : partition) {
            try {
                log.info("toList size:{}", toList.size());
                String sql = SQLUtils.generateBatchInsertSQL(toList);
                Connection connnection = DruidUtils.getDataSource().getConnection();
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

    private static void batchInsertUpdateOnDuplicateKey(List<SmsTo> list, SqlSessionFactory sqlSessionFactory) {
        List<List<SmsTo>> partition = Lists.partition(list, 200);
        for (List<SmsTo> toList : partition) {
            try {
                // 开启 session
                SqlSession session = sqlSessionFactory.openSession();
                SmsToMapper smsToMapper = session.getMapper(SmsToMapper.class);
                int resultSet = smsToMapper.batchInsertUpdateOnDuplicateKey(toList);
                log.info("处理结果,result:{}", resultSet);
                session.commit();
                session.close();
            } catch (Exception e) {
                log.error("出现错误", e);
            }
        }
    }

    private static void batchInsert(List<SmsTo> list, SqlSessionFactory sqlSessionFactory) {
        List<List<SmsTo>> partition = Lists.partition(list, 200);
        for (List<SmsTo> toList : partition) {
            try {
                // 开启 session
                SqlSession session = sqlSessionFactory.openSession();
                SmsToMapper smsToMapper = session.getMapper(SmsToMapper.class);
                int resultSet = smsToMapper.batchInsert(toList);
                log.info("处理结果,result:{}", resultSet);
                session.commit();
                session.close();
            } catch (Exception e) {
                log.error("出现错误", e);
            }
        }
    }
}
