package com.test.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Invocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SqlUtils {

    private SqlUtils() {

    }

    private static final char POINT = '.';
    private static final int ARGS_LENGTH_FOUR = 4;
    private static final String SELECT = "SELECT * FROM ";
    private static final String WHERE = " WHERE";
    private static final String AND = " AND";

    /**
     * 根据数据屏蔽规则生成屏蔽拦截后的sql
     *
     * @param statement statement
     * @param tableListMap 规则
     * @return 新sql
     */
    public static String generateNewSql(Statement statement, Map<Table, List<String>> tableListMap)
            throws JSQLParserException {
        if (statement == null || MapUtils.isEmpty(tableListMap)) {
            return null;
        }
        if (statement instanceof Select) {
            Select select = (Select) statement;
            handleSelectBody(select.getSelectBody(), tableListMap);
        }
        return statement.toString();
    }

    /**
     * 处理selectBody
     *
     * @param selectBody selectBody
     * @param tableListMap 数据屏蔽规则
     */
    private static void handleSelectBody(SelectBody selectBody, Map<Table, List<String>> tableListMap)
            throws JSQLParserException {
        if (selectBody instanceof PlainSelect) {
            handlePlainSelect((PlainSelect) selectBody, tableListMap);
        } else if (selectBody instanceof SetOperationList) {
            SetOperationList list = (SetOperationList) selectBody;
            for (int i = 0; i < list.getSelects().size(); i++) {
                handlePlainSelect((PlainSelect) list.getSelects().get(i), tableListMap);
            }
        }
    }

    /**
     * 处理plainSelect,仅处理from之后直接带表名的，如果含有子查询的则不进行处理
     *
     * @param plainSelect plainSelect
     * @param tableListMap 数据屏蔽规则
     */
    private static void handlePlainSelect(PlainSelect plainSelect, Map<Table, List<String>> tableListMap)
            throws JSQLParserException {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table) {
            Table table = (Table) fromItem;
            List<String> sqlList = tableListMap.get(table);
            if (CollectionUtils.isNotEmpty(sqlList)) {
                plainSelect.setFromItem(generateSubSelect(table, sqlList));
            }
        }
        List<Join> joinList = plainSelect.getJoins();
        if (CollectionUtils.isNotEmpty(joinList)) {
            for (Join join : joinList) {
                FromItem item = join.getRightItem();
                if (item instanceof Table) {
                    // 同样不处理join里的子查询
                    Table table = (Table) item;
                    List<String> sqlList = tableListMap.get(table);
                    if (CollectionUtils.isNotEmpty(sqlList)) {
                        join.setRightItem(generateSubSelect(table, sqlList));
                    }
                }
            }
        }
    }

    /**
     * 根据Table和屏蔽规则生成一个子查询
     *
     * @param table table
     * @param sqlList 当前表的数据屏蔽规则
     * @return subSelect
     */
    private static SubSelect generateSubSelect(Table table, List<String> sqlList) throws JSQLParserException {
        PlainSelect plainSelect = (PlainSelect) generatePlainSelect(table, sqlList).getSelectBody();
        SubSelect subSelect = new SubSelect();
        subSelect.setSelectBody(plainSelect);
        if (table.getAlias() != null) {
            subSelect.setAlias(table.getAlias());
        } else {
            Alias alias = new Alias(table.getName());
            alias.setUseAs(false);
            subSelect.setAlias(alias);
        }
        return subSelect;
    }

    /**
     * 生成一个查询select
     *
     * @param table 表名
     * @param sqlList 数据屏蔽规则
     * @return select
     * @throws JSQLParserException
     */
    private static Select generatePlainSelect(Table table, List<String> sqlList) throws JSQLParserException {
        return (Select) CCJSqlParserUtil.parse(generateSubSelectSql(table, sqlList));
    }

    /**
     * 根据表名和表名维护的数据屏蔽规则生成拼接好的sql
     *
     * @param table 表
     * @param sqlList 维护的sqlList
     * @return sql
     */
    private static String generateSubSelectSql(Table table, List<String> sqlList) {
        StringBuilder newSql = new StringBuilder(SELECT + table.getName());
        if (CollectionUtils.isNotEmpty(sqlList)) {
            newSql = newSql.append(WHERE);
            for (String sql : sqlList) {
                newSql = newSql.append(" " + handleSql(sql) + AND);
            }
        }
        return newSql.toString().substring(0, newSql.length() - AND.length());
    }

    /**
     * 解析替代sql中存在的#{}表达式所指代的动态值，如果表达式存在值，则替换，否则不替换
     *
     * @param sql sql
     * @return sql
     */
    private static String handleSql(String sql) {
        List<String> fieldList = StringUtils.getFieldList(sql);
        if (CollectionUtils.isNotEmpty(fieldList)) {
            for (String field : fieldList) {
                // 获得UserInfo中field值
                Object value = DynamicValueUtils.getValue(StringUtils.getField(field));
                if(value == null){
                    value = field;
                }
                sql = org.apache.commons.lang3.StringUtils.replace(sql, field, StringUtils.generateValueString(value));
            }
        }
        return sql;
    }

    /**
     * 获取BoundSql
     *
     * @param invocation
     * @return BoundSql
     */
    public static BoundSql getBoundSql(Invocation invocation) {
        if (invocation == null) {
            return null;
        } else {
            Object[] args = invocation.getArgs();
            MappedStatement ms = (MappedStatement) args[0];
            Object parameter = args[1];
            BoundSql boundSql;
            // 由于逻辑关系，只会进入一次
            if (args.length == ARGS_LENGTH_FOUR) {
                // 4 个参数时
                boundSql = ms.getBoundSql(parameter);
            } else {
                // 6 个参数时
                boundSql = (BoundSql) args[5];
            }
            return boundSql;
        }
    }

    /**
     * 获取mapper中的sqlId
     *
     * @param invocation
     * @return sqlId
     */
    public static String getSqlId(Invocation invocation) {
        if (invocation == null) {
            return null;
        } else {
            String fullSqlId = ((MappedStatement) invocation.getArgs()[0]).getId();
            return fullSqlId;
        }
    }

    /**
     * 根据sql获取sql中被查询的表名（表名仅限于FROM之后WHERE之前的表名,且不包括子查询的表名）
     *
     * @param sql sql
     * @return tableList
     * @throws JSQLParserException
     */
    public static List<Table> getAllSelectTable(String sql) throws JSQLParserException {
        List<PlainSelect> selectList = generateSelectList(sql);
        return getAllSelectTable(selectList);
    }

    /**
     * 根据sql获取sql中被查询的表名（表名仅限于FROM之后WHERE之前的表名,且不包括子查询里的表名）
     *
     * @param statement sql
     * @return tableList
     * @throws JSQLParserException
     */
    public static List<Table> getAllSelectTable(Statement statement) {
        List<PlainSelect> selectList = generateSelectList(statement);
        return getAllSelectTable(selectList);
    }

    /**
     * 获得sql中的所有查询对象
     *
     * @param sql sql
     * @return selectList
     * @throws JSQLParserException
     */
    private static List<PlainSelect> generateSelectList(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        return generateSelectList(statement);
    }

    /**
     * 获得sql中的所有查询对象
     *
     * @param statement statement
     * @return selectList
     * @throws JSQLParserException
     */
    private static List<PlainSelect> generateSelectList(Statement statement) {
        if (statement instanceof Select) {
            List<PlainSelect> selectList = new ArrayList<>();
            Select select = (Select) statement;
            Object obj = select.getSelectBody();
            if (obj instanceof PlainSelect) {
                selectList.add((PlainSelect) obj);
            } else if (obj instanceof SetOperationList) {
                SetOperationList setOperationList = (SetOperationList) obj;
                List<SelectBody> selectBodyList = setOperationList.getSelects();
                if (CollectionUtils.isNotEmpty(selectBodyList)) {
                    selectBodyList.forEach(selectBody -> {
                        if (selectBody instanceof PlainSelect) {
                            selectList.add((PlainSelect) selectBody);
                        }
                    });
                }
            }
            return selectList;
        }
        return Collections.emptyList();
    }

    /**
     * 获取PlainSelect对象的表名
     *
     * @param selectList
     * @return tableList
     */
    private static List<Table> getAllSelectTable(List<PlainSelect> selectList) {
        if (CollectionUtils.isNotEmpty(selectList)) {
            List<Table> tableList = new ArrayList<>();
            selectList.forEach(select -> tableList.addAll(getAllSelectTable(select)));
            return tableList;
        }else {
            return Collections.emptyList();
        }
    }

    /**
     * 获得一个查询中所有查询的表
     *
     * @param select
     * @return tableList
     */
    private static List<Table> getAllSelectTable(PlainSelect select) {
        if (select != null) {
            List<Table> tableList = new ArrayList<>();
            Object fromItem = select.getFromItem();
            if (fromItem instanceof Table) {
                tableList.add((Table) fromItem);
            }
            List<Join> joinList = select.getJoins();
            if (CollectionUtils.isNotEmpty(joinList)) {
                joinList.forEach(join -> {
                    FromItem item = join.getRightItem();
                    if (item instanceof Table) {
                        tableList.add((Table) item);
                    }
                });
            }
            return tableList;
        }
        return Collections.emptyList();
    }
}
