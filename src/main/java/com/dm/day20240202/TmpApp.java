package com.dm.day20240202;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TmpApp {

    public static void main(String[] args) throws IOException {
        UserListener userListener = new UserListener();
        EasyExcel.read(new File("D:\\data\\account\\customer\\customer_developer.csv"), CustomerDTO.class, userListener).excelType(ExcelTypeEnum.CSV).charset(StandardCharsets.UTF_8).sheet().headRowNumber(0).doRead();

        for (int i = 0; i < userListener.getList().size(); i++) {
            if (i == 0) {
                FileUtils.write(new File("D:\\data\\account\\customer\\customer-new.txt"), "id,platform_id,developer_id,dev_phone,new_password,new_md5\n", "utf-8", true);
                continue;
            }
            CustomerDTO user = userListener.getList().get(i);
            String id = user.getId();
            String platformId = user.getPlatform_id();
            String developerId = user.getDeveloper_id();
            String devName = user.getDev_name();
            String devPassword = user.getDev_password();
            String devPhone = user.getDev_phone();
            String newPassword = UUID.randomUUID().toString().substring(20);
            String newMD5 = SecureUtil.md5(newPassword);
            String format = StrUtil.format("{},{},{},{},{},{}\n", id, platformId, developerId,devPhone, newPassword,newMD5);
            String simple = StrUtil.format("{},{}\n", devPhone, newPassword);
            String sqlTemplate = "update `customer_developer` set dev_password = '{}' where id = {};\n";
            String updateSql = StrUtil.format(sqlTemplate,newMD5,id);
            FileUtils.write(new File("D:\\data\\account\\customer\\customer-new.txt"), format, "utf-8", true);
            FileUtils.write(new File("D:\\data\\account\\customer\\customer-simple.txt"), simple, "utf-8", true);
            FileUtils.write(new File("D:\\data\\account\\customer\\customer-updateSql.sql"), updateSql, "utf-8", true);
        }
    }

    /**
     * 用户中心登录加密方法
     *
     * @param credentials
     * @param saltSource
     * @return
     */
    private String md5(String credentials,String saltSource){
        ByteSource salt = new Md5Hash(saltSource);
        return new SimpleHash("MD5", credentials, salt, 1024).toString();
    }
}

@Data
class UserListener extends AnalysisEventListener<CustomerDTO> {

    private List<CustomerDTO> list = new ArrayList<>();

    @Override
    public void invoke(CustomerDTO user, AnalysisContext analysisContext) {
        list.add(user);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}