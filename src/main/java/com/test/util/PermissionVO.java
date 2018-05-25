package com.test.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionVO {

    private String tableName;
    private String sqlId;
    private String rangeField;
    private String matchType;
    private String ruleCode;
    private String ruleName;
    private String permissionFieldValue;
    private String permissionFieldSqlValue;
    private String assignField;
    private Long assignFieldValue;

    /**
     * 以层级值类型_层级值_屏蔽表名称作为key的形式返回一个map
     *
     * @return
     */
    public static Map<String,List<PermissionVO>> permissionMap(){
        Map<String,List<PermissionVO>> permissionMap = new HashMap<>();
        List<PermissionVO> permissionVOList = permissionList();
        for (PermissionVO permissionVO : permissionVOList) {
            // 用权限层级信息 + 层级值 + 表名做key
            String assignFieldAndValue = permissionVO.getAssignField() + "_" +permissionVO.getAssignFieldValue() + "_" +permissionVO.getTableName();
            List<PermissionVO> tempList = permissionMap.get(assignFieldAndValue);
            if(tempList == null){
                tempList = new ArrayList<>();
            }
            tempList.add(permissionVO);
            permissionMap.put(assignFieldAndValue,tempList);
        }
        return permissionMap;
    }

    private static List<PermissionVO> permissionList(){
        List<PermissionVO> permissionVOList = new ArrayList<>();
        PermissionVO permissionVO = new PermissionVO("unit_test","selectUnitTask","unit_name","","test","测试","技术部","","ROLE",1L);
        PermissionVO permissionVO1 = new PermissionVO("unit_test","selectUnitTask","unit_name",null,"test","测试","财务部","","USER",1L);
        PermissionVO permissionVO2 = new PermissionVO("todo_task","","state",null,"ddd","dddceshi","SUCCESS","","ROLE",2L);

        permissionVOList.add(permissionVO);
        permissionVOList.add(permissionVO1);
        permissionVOList.add(permissionVO2);
        return permissionVOList;
    }

    public PermissionVO(){

    }

    public PermissionVO(String tableName, String sqlId, String rangeField, String matchType, String ruleCode, String ruleName, String permissionFieldValue, String permissionFieldSqlValue, String assignField, Long assignFieldValue) {
        this.tableName = tableName;
        this.sqlId = sqlId;
        this.rangeField = rangeField;
        this.matchType = matchType;
        this.ruleCode = ruleCode;
        this.ruleName = ruleName;
        this.permissionFieldValue = permissionFieldValue;
        this.permissionFieldSqlValue = permissionFieldSqlValue;
        this.assignField = assignField;
        this.assignFieldValue = assignFieldValue;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getRangeField() {
        return rangeField;
    }

    public void setRangeField(String rangeField) {
        this.rangeField = rangeField;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getPermissionFieldValue() {
        return permissionFieldValue;
    }

    public void setPermissionFieldValue(String permissionFieldValue) {
        this.permissionFieldValue = permissionFieldValue;
    }

    public String getPermissionFieldSqlValue() {
        return permissionFieldSqlValue;
    }

    public void setPermissionFieldSqlValue(String permissionFieldSqlValue) {
        this.permissionFieldSqlValue = permissionFieldSqlValue;
    }

    public String getAssignField() {
        return assignField;
    }

    public void setAssignField(String assignField) {
        this.assignField = assignField;
    }

    public Long getAssignFieldValue() {
        return assignFieldValue;
    }

    public void setAssignFieldValue(Long assignFieldValue) {
        this.assignFieldValue = assignFieldValue;
    }
}
