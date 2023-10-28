package com.test.string;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class StringApp {

    public static void main(String[] args) {
        String template = "{2}【旦米科技】{1}你好请于{0}点{1}到五楼报道{2}";
        String content = "【旦米科技】你好请于5点十分到五楼报道阿斯顿撒旦撒旦撒";
        log.info("{}", getParamValues(template, content, ","));
    }

    private static String getParamValues(String smsTemplate, String content, String variableSeparator) {
        String vars = "";
        String temp = "";
        // 匹配以 {数字分割}
        String[] arr = smsTemplate.split("\\{\\d+\\}");
        if (null == variableSeparator) {
            variableSeparator = "";//DictCache.DICT_CACHE_MAP.get(DictCache.SMS_VARIABLE_SEPARATOR);
        }

        for (int i = 0; i < arr.length; i++) {
            String str = "";
            temp += arr[i];
            if (i < arr.length - 1) {
                if (i == arr.length - 2) {
                    int start = temp.length();
                    int end = content.lastIndexOf(arr[i + 1]);
                    str = content.substring(start, end);
                } else {
                    int start = temp.length();
                    int end = content.indexOf(arr[i + 1], start);
                    str = content.substring(start, end);
                }
            } else {
                str = content.substring(temp.length());
            }
            temp += str;
            vars += (str.equals("") ? "" : str + variableSeparator);
        }
        if (StringUtils.isNotBlank(vars)) {
            vars = vars.substring(0, vars.length() - variableSeparator.length());
        }
        return vars;
    }
}
