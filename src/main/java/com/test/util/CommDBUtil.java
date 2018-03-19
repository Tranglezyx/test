package com.test.util;

import com.test.annotation.Column;
import com.test.annotation.GeneratedValue;
import com.test.annotation.Id;
import com.test.annotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by w-zhouyunxiang on 2017/6/30.
 */
public class CommDBUtil {
    static String driver = "com.mysql.jdbc.Driver";
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static <T> List<T> commSelect(Connection conn,T object,int page,int pageSize) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, ClassNotFoundException, InstantiationException {
        StringBuilder selectSql = new StringBuilder("select ");
        Map<String,String> columnMap = CommDBUtil.getAnnotationColumn(object);
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder whereSql = new StringBuilder(" where 1 = 1");
        List<String> methodList = new ArrayList();
        List<Object> methodResultList = new ArrayList();
        List<T> selectList = new ArrayList();
        for(Field field : fields){
            String fieldName = field.getName();
            String s = columnMap.get(fieldName);
            if(s != null){
                selectSql.append(s + " as " + fieldName + ",");
                methodList.add(fieldName);
                char c = field.getName().charAt(0);
                String methodName = makeGetMethod(fieldName);
                Method method = object.getClass().getDeclaredMethod(methodName);
                Object o = method.invoke(object);
                if(o != null){
                    whereSql.append(" and " + s + " = ?");
                    methodResultList.add(o);
                }
            }
        }
        selectSql = new StringBuilder(selectSql.substring(0,selectSql.length() - 1));
        selectSql.append(" from " + getAnnotationTableName(object));
        selectSql.append(whereSql);
        PreparedStatement pstmt = conn.prepareStatement(selectSql.toString());
        for(int i = 1;i <= methodResultList.size();i++){
            pstmt.setObject(i,methodResultList.get(i - 1));
        }
        ResultSet resultSet = pstmt.executeQuery();
        while(resultSet.next()){
            Object o = Class.forName(object.getClass().getName()).newInstance();
            for(String s : methodList){
                String methodName = makeSetMethod(s);
                Object temp = resultSet.getObject(s);
                Method method = o.getClass().getDeclaredMethod(methodName,temp.getClass());
                method.invoke(o,temp);
            }
            selectList.add((T)o);
        }
        selectSql.append(" limit " + (page - 1 ) * pageSize + "," + pageSize);
        System.out.println(selectSql);
        return selectList;
    }

    public static int commInsert(Statement statement,Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException {
        StringBuilder insertSql = new StringBuilder("insert into ");
        insertSql.append(CommDBUtil.getAnnotationTableName(object));
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String,String> columnMap = CommDBUtil.getAnnotationColumn(object);
        insertSql.append("(");
        StringBuilder valuesSql = new StringBuilder("values(");
        for(Field field : fields){
            String fieldName = field.getName();
            String s = columnMap.get(fieldName);
            if(!(field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(GeneratedValue.class))){
                if(s != null){
                    insertSql.append("`" + s + "`,");
                    char c = field.getName().charAt(0);
                    String methodName = makeGetMethod(fieldName);
                    Method method = object.getClass().getDeclaredMethod(methodName);
                    Object temp = method.invoke(object);
                    if(temp instanceof Date){
                        valuesSql.append("'" + format.format(temp) + "',");
                    }else{
                        valuesSql.append("'" + temp + "',");
                    }
                }
            }
        }
        String temp = insertSql.substring(0,insertSql.length() - 1);
        insertSql = new StringBuilder(temp);
        insertSql.append(") ");

        temp = valuesSql.substring(0,valuesSql.length() - 1);
        valuesSql = new StringBuilder(temp);
        valuesSql.append(")");
        insertSql.append(valuesSql);
        System.out.println(insertSql);
        int result = statement.execute(insertSql.toString()) ? 1 : 0;
        return result;
    }

    public static String makeSetMethod(String fieldName){
        char c = fieldName.charAt(0);
        String methodName = "set" + Character.toUpperCase(c) + fieldName.substring(1);
        return methodName;
    }


    public static String makeGetMethod(String fieldName){
        char c = fieldName.charAt(0);
        String methodName = "get" + Character.toUpperCase(c) + fieldName.substring(1);
        return methodName;
    }

    public static Map<String,String> getAnnotationColumn(Object object){
        Map<String,String> map = new HashMap<String, String>();
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field field : fields){
            if(field.isAnnotationPresent(Column.class)){
                Column column = field.getAnnotation(Column.class);
                map.put(field.getName(),column.value());
            }
        }
        return map;
    }

    public static String getAnnotationTableName(Object object){
        Table table = (Table) object.getClass().getAnnotation(Table.class);
        return table.value();
    }

    public static Connection getConn(String url,String user,String password) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName(driver);
        conn = DriverManager.getConnection(url,user,password);
        return conn;
    }

    public static void close(ResultSet set, Statement statement, Connection connection){
        try{
            if(set != null){
                set.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
