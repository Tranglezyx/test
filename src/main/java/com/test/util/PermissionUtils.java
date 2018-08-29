package com.test.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.schema.Table;

import java.util.*;

public class PermissionUtils {

    private static Long ROLE_ID = 2L;

    private static Long USER_ID = 1L;

    // 屏蔽层级值-角色
    private static final String ASSIGN_FIELD_ROLE = "ROLE";

    // 屏蔽层级值-用户
    private static final String ASSIGN_FIELD_USER = "USER";

    // 自定义sql屏蔽特征码
    private static final String RANGE_TYPE = "__SQL";
    
    public static String getPermissionSql(String sql,String id) throws JSQLParserException {
        String permissionSql = null;
//        List<Table> tableList = SqlUtils.getAllTableFromSql(sql);
//        // 获取sqlId
//        String sqlId = id.substring(id.lastIndexOf(".") + 1,id.length());
//        // 获得当前用户、角色和当前sql配置的所有数据屏蔽权限信息
//        Map<Table,List<PermissionVO>> permissionTableMap = getAllPermissionVOMap(ROLE_ID,USER_ID,sqlId,tableList);
//        permissionSql = parseSql(sql,permissionTableMap);
        return permissionSql;
    }

    /**
     * 根据sql和数据屏蔽权限信息处理sql
     *
     * @param sql
     * @param permissionTableMap
     * @return
     */
    private static String parseSql(String sql,Map<Table,List<PermissionVO>> permissionTableMap){
        if(permissionTableMap != null && permissionTableMap.size() != 0){
            Set<Map.Entry<Table,List<PermissionVO>>> tableEntrySet = permissionTableMap.entrySet();
            Map<Table,String> tableSqlMap = new HashMap<>();
            for (Map.Entry<Table, List<PermissionVO>> tableEntry : tableEntrySet) {
                // 当前表对象以及替换的sql值
                tableSqlMap.put(tableEntry.getKey(),getSqlByPermissionVO(tableEntry.getKey(),tableEntry.getValue()));
            }
            Set<Map.Entry<Table,String>> tableSqlSet = tableSqlMap.entrySet();
            for (Map.Entry<Table, String> entry : tableSqlSet) {
                Table table = entry.getKey();
                Alias alias = table.getAlias();
                String aliasName = alias == null ? "" : alias.toString();
                // 表名+别名
                String tableAndAlias = table.getName() + aliasName;
                // 获取当前sql中需要被替换的位置
                int index = sql.indexOf(tableAndAlias);
                if(index == -1){
                    // 如果存在的别名使用的是小写的as
                    aliasName = alias == null ? "" : " as " + alias.getName();
                    tableAndAlias = table.getName() + aliasName;
                    index = sql.indexOf(tableAndAlias);
                }
                if(index == -1){
                    throw new RuntimeException("don't have as or AS in sql");
                }
                sql = replaceTableByPermissionSql(sql, tableAndAlias, index, entry.getValue());
            }
        }
        return sql;
    }

    /**
     * 组装sql
     *
     * @param sql
     * @param tableAndAlias
     * @param index
     * @param permissionSql
     * @return
     */
    private static String replaceTableByPermissionSql(String sql,String tableAndAlias,int index,String permissionSql){
        String startSql = sql.substring(0,index);
        String endSql = sql.substring(index + tableAndAlias.length());
        String newSql = startSql + permissionSql + endSql;
        return newSql;
    }

    /**
     * 根据数据屏蔽设置信息得到当前拦截的表被处理之后得到的sql数据
     *
     * @param table
     * @param permissionVOList
     * @return
     */
    private static String getSqlByPermissionVO(Table table,List<PermissionVO> permissionVOList){
        String sql = null;
        if(permissionVOList != null){
            StringBuilder sb = new StringBuilder();
            sb = sb.append("(SELECT * FROM " + table.getName());
            sb = sb.append(" WHERE 1 = 1 ");
            // 判断当前表是否有多个拦截
            boolean hasManyTable = permissionVOList.size() > 1;
            // 当当前表只有一个拦截时，判断这个拦截是自定义sql还是其他
            boolean hasCustomSqlOnOneTablePermission = false;
            if(!hasManyTable){
                hasCustomSqlOnOneTablePermission = permissionVOList.get(0).getPermissionFieldSqlValue() != null && permissionVOList.get(0).getPermissionFieldSqlValue().length() != 0;
            }
            if(hasCustomSqlOnOneTablePermission){
                // 直接处理只有一个拦截且是自定义sql的情况
                sb = sb.append("AND " + permissionVOList.get(0).getPermissionFieldSqlValue() + ")");
            }else{
                // 表有多个拦截的情况
                StringBuilder conditionSb = getConditionSqlByPermission(permissionVOList);
                sb = sb.append("AND " + conditionSb.substring(0,conditionSb.length() - 4) + ")");
            }
            if(table.getAlias() != null){
                // 如果有别名，则加上别名
                sb = sb.append(table.getAlias().toString());
            }else{
                // 否则就用原表名做别名
                sb = sb.append(" " + table.getName());
            }
            return sb.toString();
        }
        return sql;
    }

    /**
     * 将数据权限拦截信息组成sql条件语句，注意，返回多了个 空格+AND，需要做字符串截断处理
     *
     * @param permissionVOList
     * @return
     */
    private static StringBuilder getConditionSqlByPermission(List<PermissionVO> permissionVOList){
        StringBuilder sb = new StringBuilder("");
        // 得到sql需要被拦截的字段和值组成的map
        Map<String,List<String>> conditionMap = getConditionSqlValueByPermission(permissionVOList);
        if(conditionMap == null || conditionMap.size() == 0){
            return sb;
        }
        List<String> customSqlList = conditionMap.remove(RANGE_TYPE);
        Set<Map.Entry<String,List<String>>> entrySet = conditionMap.entrySet();
        for (Map.Entry<String, List<String>> conditionEntry : entrySet) {
            // 遍历生成sql
            sb = sb.append(" " + conditionEntry.getKey() + " IN (");
            List<String> valueList = conditionEntry.getValue();
            for (int i = 0; i < valueList.size(); i++) {
                String value = valueList.get(i);
                sb = sb.append("'" + value + "'");
                if(i != valueList.size() - 1){
                    sb = sb.append(",");
                }else{
                    sb = sb.append(")");
                }
            }
            sb = sb.append(" AND");
        }
        if(customSqlList != null && customSqlList.size() != 0){
            for(String customSql : customSqlList){
                sb = sb.append(" " + customSql.trim());
                sb = sb.append(" AND");
            }
        }
        return sb;
    }

    /**
     * 得到拦截范围和值的情况，自定义sql的key为__SQL，值为自定义sql
     *
     * @param permissionVOList
     * @return
     */
    private static Map<String,List<String>> getConditionSqlValueByPermission(List<PermissionVO> permissionVOList){
        Map<String,List<String>> conditionMap = new HashMap<>();
        permissionVOList.forEach(permissionVO -> {
            String rangeField = permissionVO.getRangeField();
            if(rangeField == null){
                // 自定义sql  使用__sql表示自定义sql
                rangeField = RANGE_TYPE;
            }
            List<String> valueList = conditionMap.get(rangeField);
            if(valueList == null){
                valueList = new ArrayList<>();
            }
            if(RANGE_TYPE.equals(rangeField)){
                valueList.add(permissionVO.getPermissionFieldSqlValue());
            }else{
                valueList.add(permissionVO.getPermissionFieldValue());
            }
            conditionMap.put(rangeField,valueList);
        });
        return conditionMap;
    }

    /**
     * 得到当前用户以及表所有的数据屏蔽设置权限数据
     *
     * @param roleId
     * @param userId
     * @param sqlId
     * @param tableList
     * @return
     */
    private static Map<Table,List<PermissionVO>> getAllPermissionVOMap(Long roleId,Long userId,String sqlId,List<Table> tableList){
        Map<Table,List<PermissionVO>> permissionTableMap = new HashMap<>();
        Map<String,List<PermissionVO>> permissionMap = PermissionVO.permissionMap();
        if(tableList != null && tableList.size() != 0){
            for (Table table : tableList) {
                String roleKey = ASSIGN_FIELD_ROLE + "_" +roleId + "_" + table.getName();
                String userKey = ASSIGN_FIELD_USER + "_" +userId + "_" + table.getName();
                // 得到当前用户、角色的数据屏蔽对象
                List<PermissionVO> permissionRoleList = permissionMap.get(roleKey);
                List<PermissionVO> permissionUserList = permissionMap.get(userKey);
                // 将不适用于当前sqlId的数据屏蔽对象排除
                permissionRoleList = getPermissionListBySqlId(sqlId,permissionRoleList);
                permissionUserList = getPermissionListBySqlId(sqlId,permissionUserList);

                if(permissionRoleList != null && permissionRoleList.size() != 0){
                    permissionTableMap.put(table,permissionRoleList);
                }
                if(permissionUserList != null && permissionUserList.size() != 0){
                    if (permissionTableMap.get(table) == null) {
                        permissionTableMap.put(table, permissionUserList);
                    } else {
                        permissionTableMap.get(table).addAll(permissionUserList);
                    }
                }
            }
        }
        return permissionTableMap;
    }

    /**
     * 将当前用户、角色对应的数据屏蔽权限数据做进一步筛选，筛选掉sqlId对应不上的，仅保留sqlId为null或者sqlId能对应上当前需要处理的sqlId
     *
     * @param sqlId
     * @param permissionVOList
     * @return
     */
    private static List<PermissionVO> getPermissionListBySqlId(String sqlId,List<PermissionVO> permissionVOList){
        if(permissionVOList != null &&permissionVOList.size() != 0){
            List<PermissionVO> tempPermissionList = new ArrayList<>();
            permissionVOList.forEach(permissionVO -> {
                String permissionSqlId = permissionVO.getSqlId();
                // 如果权限中的sqlId为空，表示拦截当前表所有sql
                if(permissionSqlId == null || sqlId.equals(permissionSqlId) || "".equals(permissionSqlId.trim())){
                    tempPermissionList.add(permissionVO);
                }
            });
            return tempPermissionList;
        }
        return null;
    }

    /**
     * 得到当前用户对应的角色设置的数据屏蔽权限数据
     *
     * @param ROLE_ID
     * @param tableList
     * @param permissionMap
     * @return
     */
    private List<PermissionVO> getPermissionVOListByROLE_ID(Long ROLE_ID,List<Table> tableList,Map<String,List<PermissionVO>> permissionMap){
        List<PermissionVO> permissionVOList = getPermissionVOListById(ASSIGN_FIELD_ROLE,ROLE_ID,tableList,permissionMap);
        return permissionVOList;
    }

    /**
     * 得到当前用户id设置的数据屏蔽权限数据
     *
     * @param USER_ID
     * @param tableList
     * @param permissionMap
     * @return
     */
    private List<PermissionVO> getPermissionVOListByUSER_ID(Long USER_ID,List<Table> tableList,Map<String,List<PermissionVO>> permissionMap){
        List<PermissionVO> permissionVOList = getPermissionVOListById(ASSIGN_FIELD_USER,USER_ID,tableList,permissionMap);
        return permissionVOList;
    }

    /**
     * 从permissionMap获取permissionList,用于准备数据屏蔽数据
     *
     * @param assignField
     * @param id
     * @param tableList
     * @param permissionMap
     * @return
     */
    private List<PermissionVO> getPermissionVOListById(String assignField,Long id,List<Table> tableList,Map<String,List<PermissionVO>> permissionMap){
        List<PermissionVO> permissionVOList = new ArrayList<>();
        tableList.forEach(table -> {
            String key = assignField + "_" + id + "_" + table.getName();
            List<PermissionVO> tempPermissionVOList = permissionMap.get(key);
            if(tempPermissionVOList != null){
                permissionVOList.addAll(tempPermissionVOList);
            }
        });
        return permissionVOList;
    }
}
