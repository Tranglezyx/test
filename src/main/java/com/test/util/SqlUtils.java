package com.test.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlUtils {

    /**
     * 根据表名获取sql中对应表名+别名
     *
     * @param sql
     * @param tableName
     * @return
     * @throws JSQLParserException
     */
    public static Table getTableAndAliasFromTableName(String sql,String tableName) throws JSQLParserException {
        Map<String,Table> tableNameAndAliasMap = getTableNameAndAliasFromSql(sql);
        return tableNameAndAliasMap.get(tableName);
    }

    /**
     * 从sql中获得所有sql查询的<表名,表名+别名>的map结构
     *
     * @param sql
     * @return
     * @throws JSQLParserException
     */
    public static Map<String,Table> getTableNameAndAliasFromSql(String sql) throws JSQLParserException {
        Map<String,Table> tableNameAndAliasMap = new HashMap<>();
        List<Table> tableList = getAllTableFromSql(sql);
        if(tableList != null && tableList.size() != 0){
            tableList.forEach(table -> {
                tableNameAndAliasMap.put(table.getName(),table);
            });
        }
        return tableNameAndAliasMap;
    }

    /**
     * 获得sql中所有的表对象
     *
     * @param sql
     * @return
     * @throws JSQLParserException
     */
    public static List<Table> getAllTableFromSql(String sql) throws JSQLParserException {
        List<Table> tableList = new ArrayList<>();
        Object obj = CCJSqlParserUtil.parse(sql);
        Select select = null;
        PlainSelect plainSelect = null;
        Table fromTable = null;
        if(obj instanceof Select){
            select = (Select)obj;
        }
        obj = select.getSelectBody();
        if(obj instanceof PlainSelect){
            plainSelect = (PlainSelect)obj;
        }
        obj = plainSelect.getFromItem();
        if(obj instanceof Table){
            // 获得sql where之后的第一个表对象
            fromTable = (Table)obj;
            tableList.add(fromTable);
        }
        // 获得所有的join表对象
        List<Table> joinTableList = getAllJoinsTableFromSelect(plainSelect);
        tableList.addAll(joinTableList);
        return tableList;
    }

    /**
     * 获得所有的join表对象
     *
     * @param plainSelect
     * @return
     */
    public static List<Table> getAllJoinsTableFromSelect(PlainSelect plainSelect){
        List<Table> tableList = new ArrayList<>();
        List<Join> joinList = plainSelect.getJoins();
        if(joinList != null){
            joinList.forEach(join -> {
                FromItem fromItem = join.getRightItem();
                Table table = null;
                if(fromItem instanceof Table){
                    table = (Table)fromItem;
                    tableList.add(table);
                }
            });
        }
        return tableList;
    }
}