package com.company.dm.password;

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
        customerUpdatePassword();
//        userUpdatePassword();
    }

    private static void customerUpdatePassword() throws IOException {
        CustomerListener customerListener = new CustomerListener();
        String basicPath = "D:\\data\\account\\customer\\";
        EasyExcel.read(new File(basicPath + "customer_developer.csv"), CustomerDTO.class, customerListener).excelType(ExcelTypeEnum.CSV).charset(StandardCharsets.UTF_8).sheet().headRowNumber(0).doRead();

        for (int i = 0; i < customerListener.getList().size(); i++) {
            if (i == 0) {
                FileUtils.write(new File(basicPath + "customer-new.txt"), "id,platform_id,developer_id,dev_phone,new_password,new_md5\n", "utf-8", true);
                continue;
            }
            CustomerDTO user = customerListener.getList().get(i);
            String id = user.getId();
            String platformId = user.getPlatform_id();
            String developerId = user.getDeveloper_id();
            String devName = user.getDev_name();
            String devPassword = user.getDev_password();
            String devPhone = user.getDev_phone();
            String newPassword = UUID.randomUUID().toString().substring(20);
            String newMD5 = SecureUtil.md5(newPassword);
            String format = StrUtil.format("{},{},{},{},{},{}\n", id, platformId, developerId, devPhone, newPassword, newMD5);
            String simple = StrUtil.format("{},{}\n", devPhone, newPassword);
            String sqlTemplate = "update `customer_developer` set dev_password = '{}' where id = {} and dev_phone = '{}';\n";
            String updateSql = StrUtil.format(sqlTemplate, newMD5, id, devPhone);
            FileUtils.write(new File(basicPath + "customer-new.txt"), format, "utf-8", true);
            FileUtils.write(new File(basicPath + "customer-simple.txt"), simple, "utf-8", true);
            FileUtils.write(new File(basicPath + "customer-updateSql.sql"), updateSql, "utf-8", true);
        }
    }

    private static void userUpdatePassword() throws IOException {
        UserListener userListener = new UserListener();
        String basicPath = "D:\\data\\account\\user\\";
        EasyExcel.read(new File(basicPath + "user.csv"), UserDTO.class, userListener).excelType(ExcelTypeEnum.CSV).charset(StandardCharsets.UTF_8).sheet().headRowNumber(0).doRead();

        for (int i = 0; i < userListener.getList().size(); i++) {
            if (i == 0) {
                FileUtils.write(new File("D:\\data\\account\\customer\\user-new.txt"), "id,user_id,username,password,salt,mobile,new_password,new_md5\n", "utf-8", true);
                continue;
            }
            UserDTO user = userListener.getList().get(i);
            String id = user.getId();
            String userId = user.getUserId();
            String username = user.getUsername();
            String password = user.getPassword();
            String salt = user.getSalt();
            String mobile = user.getMobile();
            String newPassword = UUID.randomUUID().toString().substring(20);
            String newMD5 = md5(newPassword, salt);
            String format = StrUtil.format("{},{},{},{},{},{},{},{}\n", id, userId, username, password, salt, mobile, newPassword, newMD5);
            String simple = StrUtil.format("{},{}\n", mobile, newPassword);
            String sqlTemplate = "update `user` set password = '{}' where id = {} and mobile = '{}';\n";
            String updateSql = StrUtil.format(sqlTemplate, newMD5, id, mobile);
            FileUtils.write(new File(basicPath + "user-new.txt"), format, "utf-8", true);
            FileUtils.write(new File(basicPath + "user-simple.txt"), simple, "utf-8", true);
            FileUtils.write(new File(basicPath + "user-updateSql.sql"), updateSql, "utf-8", true);
        }
    }

    /**
     * 用户中心登录加密方法
     *
     * @param credentials
     * @param saltSource
     * @return
     */
    private static String md5(String credentials, String saltSource) {
        ByteSource salt = new Md5Hash(saltSource);
        return new SimpleHash("MD5", credentials, salt, 1024).toString();
    }
}

@Data
class CustomerListener extends AnalysisEventListener<CustomerDTO> {

    private List<CustomerDTO> list = new ArrayList<>();

    @Override
    public void invoke(CustomerDTO user, AnalysisContext analysisContext) {
        list.add(user);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

@Data
class UserListener extends AnalysisEventListener<UserDTO> {

    private List<UserDTO> list = new ArrayList<>();

    @Override
    public void invoke(UserDTO user, AnalysisContext analysisContext) {
        list.add(user);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}