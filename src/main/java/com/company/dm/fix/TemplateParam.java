package com.company.dm.fix;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/10/15 17:22
 * @Description:
 */
@Data
public class TemplateParam {

    private String id;
    private String template_content;
    private String sms_sign_id;
    private String product_id;
    private String template_name;

    private Integer param_index;
    private String param_name;
    private Integer param_type;
    private Integer param_length;

    public Map<String,Object> toMap(){
        Map<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("paramIndex",param_index);
        objectObjectHashMap.put("paramName",param_name);
        objectObjectHashMap.put("paramType",param_type);
        objectObjectHashMap.put("paramLength",param_length);
        return objectObjectHashMap;
    }
}
