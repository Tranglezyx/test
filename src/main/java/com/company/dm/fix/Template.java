package com.company.dm.fix;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhouyunxiang
 * @Date: 2025/10/11 16:18
 * @Description:
 */
@Data
@AllArgsConstructor
public class Template {

    private String id;
    private String content;

    public static Template get(String line, String splitSymbol) {
        line = line.replaceAll("\r\n", "");
        String[] split = line.split(splitSymbol);
        String id = split[0];
        String content = split[1];
        return new Template(id, content);
    }

    public static void main(String[] args) {
        String template = "select user_id, product_id, sign_content, template_id from t_sms_send_{}_{}\n" +
                "    WHERE template_id NOT IN (\n" +
                "              SELECT template_id\n" +
                "              FROM smsg_template t\n" +
                "              WHERE template_content = '{1}'\n" +
                "          )\n" +
                "      AND template_id != 0";

        LocalDate start = LocalDate.of(2025, 9, 1);
        LocalDate end = LocalDate.of(2025, 10, 1);

        List<String> list = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        while (start.compareTo(end) < 0) {
//            for (int i = 0; i < 10; i++) {
                String format = StrUtil.format(template, start.format(formatter), 8);
                list.add(format);
//            }
            start = start.plusDays(1);
        }
        System.out.println(list.stream().collect(Collectors.joining("\n union all \n")));
    }
}
