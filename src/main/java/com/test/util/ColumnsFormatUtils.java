package com.test.util;

public class ColumnsFormatUtils {

    /**
     * 将一个字符串从java命名方式转换成数据库的驼峰命名方式，如taxSubjectName转换成tax_Subject_Name
     * @param str
     * @return
     */
    public static String javaTypeToDbType(String str){
        char[] cs = str.toCharArray();
        String s = "";
        for(int i = 0;i < cs.length;i++){
            char c = cs[i];
            int temp = (int)c;
            if(temp >= 65 && temp <= 90){
                s += "_";
            }
            s += c;
        }
        return s;
    }

    /**
     * 将数据库带下划线形式的字段转化为Java dto类型的字段，如PUSH_ITF_FLAG，转化后的结果为pushItfFlag
     *
     * @author zhouyx
     * @param dbColumnsTypeName
     * @return
     */
    public static String dbColumnsTypeToJavaType(String dbColumnsTypeName){
        String javaTypeName = "";
        String[] str = dbColumnsTypeName.split("_");
        for(int i = 0;i < str.length;i++){
            if(i != 0){
                javaTypeName = javaTypeName + makeFirstToUpperCase(str[i]);
            }else{
                javaTypeName = javaTypeName + str[i].toLowerCase();
            }
        }
        return javaTypeName;
    }

    /**
     * 将形参首字母大写,其他字母全部小写返回,如参数为NAME，name，返回结果为Name
     *
     * @param s
     * @return
     */
    public static String makeFirstToUpperCase(String s){
        s = s.toLowerCase();
        char c = s.charAt(0);
        return Character.toUpperCase(c) + s.substring(1);
    }

    /**
     * 将形参转换为set方法,如productName或者ProductName,转化后返回的结果为setProductName
     * @Author yunxiang.zhou01@hand-china.com
     * @param fieldName
     * @return
     */
    public static String makeSetMethod(String fieldName) {
        char c = fieldName.charAt(0);
        String methodName = "set" + Character.toUpperCase(c) + fieldName.substring(1);
        return methodName;
    }

    /**
     * 将形参转换为get方法,如productName或者ProductName,转化后返回的结果为getProductName
     * @Author yunxiang.zhou01@hand-china.com
     * @param fieldName
     * @return
     */
    public static String makeGetMethod(String fieldName) {
        char c = fieldName.charAt(0);
        String methodName = "get" + Character.toUpperCase(c) + fieldName.substring(1);
        return methodName;
    }

    /**
     * 根据数据库类型的格式，转化成java命名规范的set方法,如参数为 PRODUCT_NAME，返回值为 setProductName
     * @Author yunxiang.zhou01@hand-china.com
     * @param columnsName
     * @return
     */
    public static String makeSetMethodByColumns(String columnsName){
        String s = dbColumnsTypeToJavaType(columnsName);
        return makeSetMethod(s);
    }

    /**
     * 根据数据库类型的格式，转化成java命名规范的set方法,如参数为 PRODUCT_NAME，返回值为 getProductName
     * @Author yunxiang.zhou01@hand-china.com
     * @param columnsName
     * @return
     */
    public static String makeGetMethodByColumns(String columnsName){
        String s = dbColumnsTypeToJavaType(columnsName);
        return makeGetMethod(s);
    }
}
