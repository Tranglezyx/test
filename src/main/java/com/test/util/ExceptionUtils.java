package com.test.util;

public class ExceptionUtils {

    /**
     * 根据异常返回执行出错的堆栈信息
     *
     * @param e
     * @return
     */
    public static String getErrorMessageByException(Exception e) {
        if(e != null){
            //记录错误信息
            StringBuilder errorMessage = new StringBuilder("整体错误信息为：" + e.getMessage() + "\r\n");
            //得到错误堆栈信息
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (int i = stackTrace.length - 1; i >= 0; i--) {
                StackTraceElement stack = stackTrace[i];
                errorMessage.append("类:")
                        .append(stack.getClassName())
                        .append(" 方法:")
                        .append(stack.getMethodName())
                        .append(" 行数:")
                        .append(stack.getLineNumber())
                        .append(" 执行出错;")
                        .append("\r\n");
            }
            return errorMessage.toString();
        }else{
            return "Exception对象为null！无法返回错误堆栈信息！";
        }
    }
}
