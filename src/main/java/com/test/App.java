package com.test;

import com.test.dto.Users;
import com.test.util.CommUtils;
import com.test.util.SqlUtils;
import io.github.biezhi.wechat.WeChatBot;
import io.github.biezhi.wechat.api.annotation.Bind;
import io.github.biezhi.wechat.api.constant.Config;
import io.github.biezhi.wechat.api.enums.MsgType;
import io.github.biezhi.wechat.api.model.WeChatMessage;
import io.github.biezhi.wechat.utils.StringUtils;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;

import java.io.*;
import java.math.BigDecimal;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @author Trangle
 * Hello world!
 */
public class App /*extends WeChatBot*/ {

//    public App(Config config){
//        super(config);
//    }

    public static void main(String[] args) throws InterruptedException, SocketException, UnknownHostException, JSQLParserException {
//        Doing say = new Say();
//        InvocationHandler handler = new InvocationHandlerUtils(say);
//        Doing proxy = (Doing) Proxy.newProxyInstance(say.getClass().getClassLoader(),say.getClass().getInterfaces(),handler);
//        proxy.say("hello world");

        CCJSqlParserManager parserManager = new CCJSqlParserManager();

        String sql = "SELECT\n" +
                "            ut.unit_name,\n" +
                "            et.employee_name,\n" +
                "            tt.task_number,\n" +
                "            tt.task_description,\n" +
                "            tt.state\n" +
                "        FROM\n" +
                "            unit_test ut,\n" +
                "            employee_test et,\n" +
                "            todo_task tt\n" +
                "        WHERE\n" +
                "            ut.unit_id = et.unit_id\n" +
                "        AND et.employee_id = tt.employee_id";

        String sql1 = "SELECT\n" +
                "            id,\n" +
                "            employee_id,\n" +
                "            state,\n" +
                "            task_number,\n" +
                "            task_description,\n" +
                "            object_version_number\n" +
                "        FROM\n" +
                "            todo_task\n" +
                "        WHERE\n" +
                "            state = ?";

        String sql2 = "SELECT\n" +
                "        qpe.EMPLOYEE_NAME,\n" +
                "        qpe.CARD_NUMBER,\n" +
                "        (\n" +
                "        SELECT\n" +
                "        meaning\n" +
                "        FROM\n" +
                "        sys_code_b scb,\n" +
                "        sys_code_value_b scvb\n" +
                "        WHERE\n" +
                "        scb.`CODE` = 'QF.TAX.CARD_TYPE'\n" +
                "        AND scb.CODE_ID = scvb.CODE_ID\n" +
                "        AND scvb.`VALUE` = qtpf.CARD_TYPE\n" +
                "        ) CARD_TYPE,\n" +
                "        (\n" +
                "        SELECT\n" +
                "        meaning\n" +
                "        FROM\n" +
                "        sys_code_b scb,\n" +
                "        sys_code_value_b scvb\n" +
                "        WHERE\n" +
                "        scb.`CODE` = 'QF.TAX.NATIONAL'\n" +
                "        AND scb.CODE_ID = scvb.CODE_ID\n" +
                "        AND scvb.`VALUE` = qtpf.NATIONAL\n" +
                "        ) NATIONAL,\n" +
                "        qtid.IIT_DECLARE_ID,\n" +
                "        (\n" +
                "        SELECT\n" +
                "        meaning\n" +
                "        FROM\n" +
                "        sys_code_b scb,\n" +
                "        sys_code_value_b scvb\n" +
                "        WHERE\n" +
                "        scb.`CODE` = 'QF.TAX.IIT_STATUS'\n" +
                "        AND scb.CODE_ID = scvb.CODE_ID\n" +
                "        AND scvb.`VALUE` = qtid.IIT_STATUS\n" +
                "        ) IIT_STATUS,\n" +
                "        qtid.EMPLOYEE_ID,\n" +
                "        (\n" +
                "        SELECT\n" +
                "        meaning\n" +
                "        FROM\n" +
                "        sys_code_b scb,\n" +
                "        sys_code_value_b scvb\n" +
                "        WHERE\n" +
                "        scb.`CODE` = 'QF.TAX.INCOME_ITEM'\n" +
                "        AND scb.CODE_ID = scvb.CODE_ID\n" +
                "        AND scvb.`VALUE` = qtid.INCOME_ITEM\n" +
                "        ) INCOME_ITEM,\n" +
                "        qtid.TAX_PERIOD,\n" +
                "        qtid.INCOME_TIME_FROM,\n" +
                "        qtid.INCOME_TIME_TO,\n" +
                "        qtid.INCOME_AMOUNT,\n" +
                "        qtid.RETIREMENT_INSURANCE,\n" +
                "        qtid.MEDICAL_INSURANCE,\n" +
                "        qtid.UNEMPLOY_INSURANCE,\n" +
                "        qtid.HOUSING_FUND,\n" +
                "        qtid.TAX_FREE_INCOME,\n" +
                "        qtid.PROPERTY_VALUE,\n" +
                "        qtid.DEDUCTIBLE_TAXES,\n" +
                "        qtid.ANNUITY,\n" +
                "        qtid.BUS_HEALTH_INSURANCE,\n" +
                "        qtid.INVEST_DEDUCTION,\n" +
                "        qtid.OTHER_DEDUCTIONS,\n" +
                "        qtid.BEFORE_TAX_DEDUCTION,\n" +
                "        qtid.EXEMPTION_AMOUNT,\n" +
                "        qtid.DONATION_DEDUCTION,\n" +
                "        qtid.TAXABLE_INCOME,\n" +
                "        qtid.IIT_RATE,\n" +
                "        qtid.QUICK_DEDUCTIONS,\n" +
                "        qtid.IIT_TAX,\n" +
                "        qtid.PAYABLE_TAX,\n" +
                "        qtid.PAID_TAX,\n" +
                "        qtid.SHOULD_PAY_TAX,\n" +
                "        qtid.RELIEF_TAX,\n" +
                "        qtid.DESCRIPTION,\n" +
                "        qtid.OBJECT_VERSION_NUMBER\n" +
                "        FROM\n" +
                "        qf_tax_iit_declare qtid\n" +
                "        INNER JOIN qf_pub_employee as qpe ON qtid.EMPLOYEE_ID = qpe.EMPLOYEE_ID\n" +
                "        LEFT JOIN qf_tax_personal_foreign as qtpf ON qtid.EMPLOYEE_ID = qtpf.EMPLOYEE_ID\n" +
                "        AND qtpf.`STATUS` = 'Y'\n" +
                "        LEFT JOIN hscs_pub_company_b hpcb ON qpe.OWNED_COMPANY_CODE = hpcb.company_code\n" +
                "        where 1 = 1";

        String s = "com.example.permission.infra.mapper.TaskMapper.selectUnitTask";

        String[] t = s.split(".");

        System.out.println();
    }


//    @Bind(msgType = MsgType.TEXT)
//    public void handleText(WeChatMessage message) {
//        if (StringUtils.isNotEmpty(message.getName())) {
//            System.out.println("接收到 [{}] 的消息: {}" + "\t" + message.getName() + "\t" +  message.getText());
//            this.sendMsg(message.getFromUserName(), "自动回复: " + message.getText());
//        }
//    }
}


