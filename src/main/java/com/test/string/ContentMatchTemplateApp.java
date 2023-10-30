package com.test.string;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class ContentMatchTemplateApp {

    public static void main(String[] args) {
//        String template = "{2}【旦米科技】{1}你好请于{0}点{1}到五楼报道{2}";
//        String content = "【旦米科技】你好请于5点十分到五楼报道阿斯顿撒旦撒旦撒";

        String template = "【旦米科技】你好请于点到五楼报道{2}";
        String content = "【旦米科技】你好请于点到五楼报道fgsdfgs";
        log.info("{}", stringMatchingWithSort(content, template));
    }

    /**
     * 字符串方式匹配模板
     *
     * @param templateContent
     * @param smsContent
     * @return 是否能匹配上这个占位符
     */
    private static boolean stringMatchingWithSort(String smsContent, String templateContent) {
        String[] split = templateContent.split("\\{");
        if (split.length == 1) {
            return split[0].equals(smsContent);
        }
        int lastIndex = -1;
        for (int i = 0; i < split.length; i++) {
            String tmpStr = split[i];
            if (i == 0) {
                boolean startsWith = smsContent.startsWith(tmpStr);
                if (startsWith) {
                    continue;
                } else {
                    return false;
                }
            }
            if (tmpStr.contains("}")) {
                tmpStr = tmpStr.substring(tmpStr.indexOf("}") + 1);
            }
            if (StringUtils.isEmpty(tmpStr)) {
                continue;
            }
            if (i == split.length - 1) {
                return smsContent.endsWith(tmpStr);
            }
            int index = smsContent.indexOf(tmpStr);
            if (index <= lastIndex) {
                return false;
            }
            lastIndex = index;
        }
        return true;
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
