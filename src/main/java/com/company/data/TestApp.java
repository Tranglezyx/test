package com.company.data;

import com.test.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestApp {

    public static void main(String[] args) {
        Object object = MyySubmitDataJSON.getTemplateData();
        processProjectManagerInfo((Map) object, (Map)((Map) MyySubmitDataJSON.getSubmitData()).get("businessData"));
        System.out.println(object);
    }

    private static void processProjectManagerInfo(Map newTemplateData, Map submitNewData) {
        if (newTemplateData != null && submitNewData != null) {
            List<Map<String, Object>> projectManagerInfo = new ArrayList<>();
            List<Map<String, Object>> submitProjectManagerInfo = (List<Map<String, Object>>) submitNewData.get("d_supplier_project_manager");
            List<Map<String, Object>> submitManagerAreaInfo = (List<Map<String, Object>>) submitNewData.get("d_supplier_manager_type_area");
            if (CollectionUtils.isNotEmpty(submitManagerAreaInfo) && CollectionUtils.isNotEmpty(submitProjectManagerInfo)) {
                // 根据项目经理id分组
                Map<String, List<Map<String, Object>>> projectManagerMap = submitProjectManagerInfo.stream().collect(Collectors.groupingBy(o -> String.valueOf(o.get("id"))));
                for (Map<String, Object> managerAreaMap : submitManagerAreaInfo) {
                    Map<String, Object> newManagerAreaMap = new LinkedHashMap<>();
                    String managerId = managerAreaMap.get("manager_id") == null ? null : String.valueOf(managerAreaMap.get("manager_id"));
                    if (org.apache.commons.lang3.StringUtils.isEmpty(managerId)) {
                        throw new RuntimeException("项目经理ID不能为空");
                    }
                    // 用项目经理id取值，获得项目经理数据
                    Map<String, Object> projectManager = CollectionUtils.isNotEmpty(projectManagerMap.get(managerId)) ? projectManagerMap.get(managerId).get(0) : null;
                    // 将外网传入的数据存入并转成驼峰形式
                    underLineToHumpAndPut(managerAreaMap, newManagerAreaMap);
                    underLineToHumpAndPut(projectManager, newManagerAreaMap);
                    projectManagerInfo.add(newManagerAreaMap);
                }
            }
            if (newTemplateData.get("projectManager") instanceof Map) {
                ((Map) newTemplateData.get("projectManager")).put("projectManager", projectManagerInfo);
            }
        }
    }

    private static void underLineToHumpAndPut(Map<String, Object> managerAreaMap, Map<String, Object> newManagerAreaMap) {
        for (Map.Entry<String, Object> entry : managerAreaMap.entrySet()) {
            if (!"id".equals(entry.getKey())) {
                newManagerAreaMap.put(StringUtils.underlineToHump(entry.getKey()), entry.getValue());
            }
        }
    }
}
