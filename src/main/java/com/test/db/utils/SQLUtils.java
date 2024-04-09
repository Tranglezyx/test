package com.test.db.utils;

import cn.hutool.core.util.StrUtil;
import com.test.annotation.Table;
import com.test.domain.entity.SmsTo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class SQLUtils {

    public static void main(String[] args) throws IllegalAccessException {
        String s = generateBatchInsertSQL(Arrays.asList(new SmsTo(),new SmsTo()));
        System.out.println(s);
    }

    public static <T> String generateBatchInsertSQL(List<T> list) throws IllegalAccessException {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        Object object = list.get(0);
        Class<?> clazz = object.getClass();
        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.value();
        Field[] allFields = FieldUtils.getAllFields(clazz);
        String sqlTemplate = "insert into {}({}) values {}";
        StringBuilder fieldNameStr = new StringBuilder();
        for (int i = 0; i < allFields.length; i++) {
            fieldNameStr.append(StrUtil.toUnderlineCase(allFields[i].getName()));
            if (i < allFields.length - 1) {
                fieldNameStr.append(",");
            }
        }
        StringBuilder fieldValueStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            fieldValueStr.append("(");
            for (int j = 0; j < allFields.length; j++) {
                Object item = list.get(i);
                Field field = allFields[j];
                Object value = FieldUtils.readDeclaredField(item, field.getName(), true);
                if(value instanceof String){
                    fieldValueStr.append("'");
                    fieldValueStr.append(value);
                    fieldValueStr.append("'");
                }else{
                    fieldValueStr.append(value);
                }
                if (j < allFields.length - 1) {
                    fieldValueStr.append(",");
                }
            }
            fieldValueStr.append(")");
            if (i < list.size() - 1) {
                fieldValueStr.append(",");
            }
        }
        return StrUtil.format(sqlTemplate, tableName, fieldNameStr.toString(), fieldValueStr.toString());
    }
}
