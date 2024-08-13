package com.company.dm.kafka;

/**
 * @DEPICT
 * @Author Jw
 * @DATA 2021年10月20日-11:34
 * @VERSION 1.0
 **/
public class RespCode {
    /**
     * 成功
     */
    public static final int CODE_00000 = 200;

    /**
     * 未知错误，请联系技术客服
     */
    public static final int CODE_00001 = 10001;

    /**
     * 未知的方法名
     */
    public static final int CODE_00002 = 10002;

    /**
     * 请求方式错误
     */
    public static final int CODE_00003 = 10003;

    /**
     * 参数非法
     */
    public static final int CODE_00004 = 10004;

    /**
     * timestamp已过期
     */
    public static final int CODE_00005 = 10005;

    /**
     * sign错误
     */
    public static final int CODE_00006 = 10006;

    /**
     * 重复提交
     */
    public static final int CODE_00007 = 10007;

    /**
     * 操作频繁
     */
    public static final int CODE_00008 = 10008;

    /**
     * 请求的xml格式不对
     */
    public static final int CODE_00011 = 10011;

    /**
     * 不支持get请求，请使用post
     */
    public static final int CODE_00012 = 10012;

    /**
     * url格式不正确
     */
    public static final int CODE_00013 = 10013;

    /**
     * 时间戳超出有效时间范围
     */
    public static final int CODE_00015 = 10015;

    /**
     * 请求json格式不对
     */
    public static final int CODE_00016 = 10016;

    /**
     * 数据库操作失败
     */
    public static final int CODE_00017 = 10017;

    /**
     * 参数为空
     */
    public static final int CODE_00018 = 10018;

    /**
     * 订单已存在
     */
    public static final int CODE_00019 = 10019;

    /**
     * 用户不存在
     */
    public static final int CODE_00020 = 10020;

    /**
     * client账号余额不足
     */
    public static final int CODE_00021 = 10021;

    /**
     * 操作频繁
     */
    public static final int CODE_00022 = 10022;

    /**
     * 开发者余额不足
     */
    public static final int CODE_00023 = 10023;

    /**
     * 手机格式不对
     */
    public static final int CODE_00025 = 10025;

    /**
     * 手机号存在
     */
    public static final int CODE_00026 = 10026;

    /**
     * 子账号名称已存在
     */
    public static final int CODE_00027 = 10027;

    /**
     * 子账号名称过长
     */
    public static final int CODE_00028 = 10028;

    /**
     * 回调开发者服务器异常
     */
    public static final int CODE_00029 = 10029;

    /**
     * 回调地址为空
     */
    public static final int CODE_00030 = 10030;

    /**
     * appId为空或者没有传值
     */
    public static final int CODE_00031 = 10031;

    /**
     * 主叫号码为空或者没有传值
     */
    public static final int CODE_00032 = 10032;

    /**
     * 被叫号码为空或者没有传值
     */
    public static final int CODE_00033 = 10033;

    /**
     * 子账号为空或者没有传值
     */
    public static final int CODE_00034 = 10034;

    /**
     * 主叫号码和被叫号码相同
     */
    public static final int CODE_00035 = 10035;

    /**
     * 验证码格式不对（4-8位数字）
     */
    public static final int CODE_00036 = 10036;

    /**
     * limit格式不对
     */
    public static final int CODE_00037 = 10037;

    /**
     * start格式不对
     */
    public static final int CODE_00038 = 10038;

    /**
     * 验证码为空或者缺少此参数
     */
    public static final int CODE_00039 = 10039;

    /**
     * 用户名或者密码错误
     */
    public static final int CODE_00040 = 10040;

    /**
     * 话单下载的token不存在
     */
    public static final int CODE_00044 = 10044;

    /**
     * 话单下载的token失效
     */
    public static final int CODE_00045 = 10045;

    /**
     * 短信或者语音验证码错误
     */
    public static final int CODE_00050 = 10050;

    /**
     * 显示号码与被叫号码一样，不允许呼叫
     */
    public static final int CODE_00051 = 10051;

    /**
     * 回拨主叫号码格式错误
     */
    public static final int CODE_00052 = 10052;

    /**
     * 被叫号码格式错误
     */
    public static final int CODE_00053 = 10053;

    /**
     * 显号格式错误
     */
    public static final int CODE_00054 = 10054;

    /**
     * 应用不包含此子账号
     */
    public static final int CODE_00055 = 10055;

    /**
     * 开发者不包含此应用
     */
    public static final int CODE_00056 = 10056;

    /**
     * 签名需报备
     */
    public static final int CODE_00057 = 10057;

    /**
     * 子账号只允许发移动号码
     */
    public static final int CODE_00058 = 10058;

    /**
     * 子账号只允许发电信号码
     */
    public static final int CODE_00059 = 10059;

    /**
     * 子账号只允许发联通号码
     */
    public static final int CODE_00060 = 10060;

    /**
     * 目标号码号段错误
     */
    public static final int CODE_00061 = 10061;

    /**
     * developerId 请求错误
     */
    public static final int CODE_00062 = 10062;

    /**
     * app未上线
     */
    public static final int CODE_00063 = 10063;

    /**
     * 请求Content-Type错误
     */
    public static final int CODE_00064 = 10064;

    /**
     * 请求Accept错误
     */
    public static final int CODE_00065 = 10065;

    /**
     * 开发者余额已被冻结
     */
    public static final int CODE_00066 = 10066;

    /**
     * 手机号未绑定
     */
    public static final int CODE_00070 = 10070;

    /**
     * 通知类型已停用或者未创建
     */
    public static final int CODE_00071 = 10071;

    /**
     * balance格式不对（必须为大于等于0的double）
     */
    public static final int CODE_00072 = 10072;

    /**
     * charge格式不对（必须为大于等于0的double）
     */
    public static final int CODE_00073 = 10073;

    /**
     * 主叫和子账户绑定的手机号不相同
     */
    public static final int CODE_00074 = 10074;

    /**
     * 子账户没有绑定手机号
     */
    public static final int CODE_00075 = 10075;

    /**
     * 时间格式不正确
     */
    public static final int CODE_00076 = 10076;

    /**
     * 开始时间必需小于结束时间
     */
    public static final int CODE_00077 = 10077;

    /**
     * 开始时间和結束時間必須是同一天
     */
    public static final int CODE_00078 = 10078;

    /**
     * 服务器内部异常
     */
    public static final int CODE_00079 = 10079;

    /**
     * 子账号不存在
     */
    public static final int CODE_00080 = 10080;

    /**
     * 通知计费系统失败
     */
    public static final int CODE_00081 = 10081;

    /**
     * 参数校验失败
     */
    public static final int CODE_00082 = 10082;

    /**
     * 充值失败
     */
    public static final int CODE_00083 = 10083;

    /**
     * 子账号没有托管 不能进行充值
     */
    public static final int CODE_00084 = 10084;

    /**
     * 开发者不包含子帐号
     */
    public static final int CODE_00085 = 10085;

    /**
     * DEMO不能进行充值
     */
    public static final int CODE_00086 = 10086;

    /**
     * IQ类型错误
     */
    public static final int CODE_00087 = 10087;

    /**
     * 回调地址为空
     */
    public static final int CODE_00090 = 10090;

    /**
     * 没有语音
     */
    public static final int CODE_00091 = 10091;

    /**
     * 没有这个语音文件或者审核没通过
     */
    public static final int CODE_00093 = 10093;

    /**
     * 每批发送的手机号数量不得超过限制
     */
    public static final int CODE_00094 = 10094;

    /**
     * 未开通邮件短信功能
     */
    public static final int CODE_00095 = 10095;

    /**
     * 邮件模板未审核通过
     */
    public static final int CODE_00096 = 10096;

    /**
     * 邮件模板未启用
     */
    public static final int CODE_00097 = 10097;

    /**
     * 同一手机号每天只能发送n条相同的内容
     */
    public static final int CODE_00098 = 10098;

    /**
     * 相同的应用每天只能给同一手机号发送n条不同的内容
     */
    public static final int CODE_00099 = 10099;

    /**
     * 短信内容不能含有非法关键字/审核不通过
     */
    public static final int CODE_00100 = 10100;

    /**
     * 配置短信端口号失败
     */
    public static final int CODE_00101 = 10101;

    /**
     * 一个开发者只能配置一个端口
     */
    public static final int CODE_00102 = 10102;

    /**
     * 邮件模板不存在
     */
    public static final int CODE_00103 = 10103;

    /**
     * 相同的应用当天给同一手机号发送短信的条数小于等于n
     */
    public static final int CODE_00104 = 10104;

    /**
     * 本开发者只能发短信给移动手机
     */
    public static final int CODE_00105 = 10105;

    /**
     * 时间戳(timestamp)参数为空
     */
    public static final int CODE_00106 = 10106;

    /**
     * 签名(sig)参数为空
     */
    public static final int CODE_00107 = 10107;

    /**
     * 时间戳(timestamp)格式错误
     */
    public static final int CODE_00108 = 10108;

    /**
     * 子账号已被关闭
     */
    public static final int CODE_00109 = 10109;

    /**
     * 解析post数据失败，post数据不符合格式要求
     */
    public static final int CODE_00110 = 101;

    /**
     * 匹配到黑名单
     */
    public static final int CODE_00111 = 10111;

    /**
     * accountSid参数为空
     */
    public static final int CODE_00112 = 10112;

    /**
     * 匹配度不足
     */
    public static final int CODE_00113 = 10113;

    /**
     * clientNumber参数为空
     */
    public static final int CODE_00114 = 10114;

    /**
     * charge参数为空
     */
    public static final int CODE_00115 = 10115;

    /**
     * charge格式不对，不能解析成double
     */
    public static final int CODE_00116 = 10116;

    /**
     * fromTime参数为空
     */
    public static final int CODE_00117 = 10117;

    /**
     * toTime参数为空
     */
    public static final int CODE_00118 = 10118;

    /**
     * fromTime参数格式不正确
     */
    public static final int CODE_00119 = 10119;

    /**
     * toTime参数格式不正确
     */
    public static final int CODE_00120 = 10120;

    /**
     * 定时发送时间戳格式错误(毫秒,格式:1547005945480)
     */
    public static final int CODE_00121 = 10121;

    /**
     * date参数为空
     */
    public static final int CODE_00122 = 10122;

    /**
     * date的值不在指定范围内
     */
    public static final int CODE_00123 = 10123;

    /**
     * 没有查询到话单（所以没有生成下载地址）
     */
    public static final int CODE_00124 = 10124;

    /**
     * emailTemplateId参数为空
     */
    public static final int CODE_00125 = 10125;

    /**
     * to参数为空
     */
    public static final int CODE_00126 = 10126;

    /**
     * param参数个数不匹配
     */
    public static final int CODE_00127 = 10127;

    /**
     * templateId参数为空
     */
    public static final int CODE_00128 = 10128;

    /**
     * 模板类型错误
     */
    public static final int CODE_00129 = 10129;

    /**
     * serviceType参数为空
     */
    public static final int CODE_00130 = 10130;

    /**
     * content参数为空
     */
    public static final int CODE_00131 = 10131;

    /**
     * 本接口的邮件短信业务只能发送移动手机
     */
    public static final int CODE_00132 = 10132;

    /**
     * 错误的业务类型
     */
    public static final int CODE_00133 = 10133;

    /**
     * 没有和内容匹配的模板
     */
    public static final int CODE_00134 = 10134;

    /**
     * 应用没有属于指定类型业务并且已审核通过、已启用的模板
     */
    public static final int CODE_00135 = 10135;

    /**
     * 开发者不能调用此接口
     */
    public static final int CODE_00136 = 10136;

    /**
     * 没有权限自定义邮件内容
     */
    public static final int CODE_00137 = 10137;

    /**
     * 短信没有签名不能发送
     */
    public static final int CODE_00138 = 10138;

    /**
     * 短信签名已进入黑名单不能发送
     */
    public static final int CODE_00139 = 10139;

    /**
     * 两条短信间隔时间太短
     */
    public static final int CODE_00140 = 10140;

    /**
     * 一小时内下发短信超过数量
     */
    public static final int CODE_00141 = 10141;

    /**
     * 一天内下发短信超过数量
     */
    public static final int CODE_00142 = 10142;

    /**
     * 含有非法字符
     */
    public static final int CODE_00143 = 10143;

    /**
     * mobile参数为空
     */
    public static final int CODE_00144 = 10144;

    /**
     * 新手机号和旧手机号相同，不必修改
     */
    public static final int CODE_00145 = 10145;

    /**
     * minutes格式不对（必须为大于等于0的double）
     */
    public static final int CODE_00146 = 10146;

    /**
     * 被叫次数超限
     */
    public static final int CODE_00147 = 10147;

    /**
     * 主叫次数超限
     */
    public static final int CODE_00148 = 10148;

    /**
     * 流量包大小格式错误
     */
    public static final int CODE_00149 = 10149;

    /**
     * 找不到匹配的流量包
     */
    public static final int CODE_00150 = 10150;

    /**
     * 该签名下的手机号码黑名单
     */
    public static final int CODE_00151 = 10151;

    /**
     * 端口号已被关闭
     */
    public static final int CODE_00152 = 10152;

    /**
     * 未知的手机号运营商
     */
    public static final int CODE_00153 = 10153;

    /**
     * 开发者无权限给此号码发短信
     */
    public static final int CODE_00154 = 10154;

    /**
     * 流量充值提交失败
     */
    public static final int CODE_00155 = 10155;

    /**
     * packageId为空或者没有传值
     */
    public static final int CODE_00156 = 10156;

    /**
     * packageId不存在
     */
    public static final int CODE_00157 = 10157;

    /**
     * 不允许发验证码
     */
    public static final int CODE_00158 = 10158;

    /**
     * 超过频率限制(从彩讯通道504-每秒下发超过100条)
     */
    public static final int CODE_00159 = 10159;

    /**
     * 不允许发会员通知推广类短信
     */
    public static final int CODE_00160 = 10160;

    /**
     * 签名没有报备
     */
    public static final int CODE_00161 = 10161;

    /**
     * 不允许发营销短信
     */
    public static final int CODE_00162 = 10162;

    /**
     * 必须包含退订
     */
    public static final int CODE_00163 = 10163;

    /**
     * 非法号段
     */
    public static final int CODE_00164 = 10164;

    /**
     * 关键字等待审核
     */
    public static final int CODE_00165 = 10165;
    /**
     * IP非法
     */
    public static final int CODE_00166 = 10166;

    /**
     * TemplateId错误
     */
    public static final int CODE_00167 = 10167;

    /**
     * TemplateId未审核或未启用
     */
    public static final int CODE_00168 = 10168;

    /**
     * param参数错误
     */
    public static final int CODE_00169 = 10169;

    /**
     * 短信内容含有商业关键字审核不通过
     */
    public static final int CODE_00170 = 10170;

    /**
     * 变量长度超长(变量长度为10个字符)针对验证码类短信
     */
    public static final int CODE_00171 = 10171;

    /**
     * 短信内容长度超长(总长度不能超过350个字符)
     */
    public static final int CODE_00172 = 10172;
    /**
     * 变量内容不能含有中文,针对验证码类短信
     */
    public static final int CODE_00173 = 10173;

    /**
     * 一分钟内下发短信超过次数限制
     */
    public static final int CODE_00174 = 10174;

    /**
     * 不完整的长短信(用于我们的CMPP网关)
     */
    public static final int CODE_00175 = 10175;

    /**
     * ip已锁定
     */
    public static final int CODE_00176 = 10176;

    /**
     * ip已锁定
     */
    public static final int CODE_00212 = 10212;

    /**
     * accountSid已锁定
     */
    public static final int CODE_00177 = 10177;

    /**
     * 无语音验证码权限
     */
    public static final int CODE_00178 = 10178;

    /**
     * 发送会员营销短信需要先认证
     */
    public static final int CODE_00179 = 10179;

    /**
     * 行业短信变量中不应许带链接关键字
     */
    public static final int CODE_00180 = 10180;

    /**
     * 账号已停用
     */
    public static final int CODE_00181 = 10181;

    /**
     * 子账号停用
     */
    public static final int CODE_00182 = 10182;

    /**
     * 子账号缺少配置
     */
    public static final int CODE_00183 = 10183;

    /**
     * 签名不能少于两个字
     */
    public static final int CODE_00184 = 10184;

    /**
     * 签名太长
     */
    public static final int CODE_00185 = 10185;

    /**
     * 审核不通过
     */
    public static final int CODE_00186 = 10186;

    /**
     * 用户取消
     */
    public static final int CODE_00187 = 10187;

    /**
     * 缺少批量请求内容或内容为空
     */
    public static final int CODE_00188 = 10188;

    /**
     * 缺少参数content或content为空
     */
    public static final int CODE_00189 = 10189;

    /**
     * 参数content请求体错误，无法解析
     */
    public static final int CODE_00190 = 10190;

    /**
     * 发送短信必须认证信息安全责任书
     */
    public static final int CODE_00191 = 10191;

    /**
     * 号码检测服务器异常
     */
    public static final int CODE_00192 = 10192;

    /**
     * 拒绝发送
     */
    public static final int CODE_00193 = 10193;

    /**
     * 签名限制
     */
    public static final int CODE_00194 = 10194;

    /**
     * 扩展码错误
     */
    public static final int CODE_00195 = 10195;

    /**
     * 号码段错误
     */
    public static final int CODE_00196 = 10196;

    /**
     * 内容包含过滤词
     */
    public static final int CODE_00197 = 10197;

    /**
     * 短信模板状态为拒绝
     */
    public static final int CODE_00198 = 10198;

    /**
     * 短信模板状态为拒绝
     */
    public static final int CODE_00199 = 10199;

    /**
     * 人工审核拒绝
     */
    public static final int CODE_00200 = 10200;

    /**
     * 公钥为空
     */
    public static final int CODE_00201 = 10201;

    /**
     * 批次发送量不能超过5W条
     */
    public static final int CODE_00202 = 10202;

    /**
     * 未匹配到模板运营商
     */
    public static final int CODE_00203 = 10203;

    /**
     * 内容重复
     */
    public static final int CODE_00204 = 10204;

    /**
     * 模板不存在
     */
    public static final int CODE_00205 = 10205;

    /**
     * 模板已停用
     */
    public static final int CODE_00206 = 10206;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00207 = 10207;

    /**
     * 动态黑名单服务返回错误
     */
    public static final int CODE_00208 = 10208;

    /**
     * 用户名已存在
     */
    public static final int CODE_00209 = 10209;

    /**
     * 数据已存在
     */
    public static final int CODE_00210 = 102;

    /**
     * 正在使用中，无法禁用
     */
    public static final int CODE_00211 = 10211;

    /**
     * 渠道名已存在
     */
    public static final int CODE_00213 = 10213;

    /**
     * 数据不存在
     */
    public static final int CODE_00214 = 10214;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00300 = 10300;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00301 = 10301;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00302 = 10302;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00303 = 10303;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00304 = 10304;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00305 = 10305;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00306 = 10306;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00307 = 10307;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00308 = 10308;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00309 = 10309;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00310 = 10310;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00311 = 10311;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00312 = 10312;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00313 = 10313;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00314 = 10314;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00315 = 10315;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00316 = 10316;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00317 = 10317;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00318 = 10318;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00319 = 10319;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00320 = 10320;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00321 = 10321;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00322 = 10322;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00323 = 10323;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00324 = 10324;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00325 = 10325;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00326 = 10326;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00327 = 10327;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00328 = 10328;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00329 = 10329;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00330 = 10330;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00331 = 10331;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00332 = 10332;

    /**
     * 模板报备未成功
     */
    public static final int CODE_00333 = 10333;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00334 = 10334;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00335 = 10335;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00336 = 10336;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00337 = 10337;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00338 = 10338;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00339 = 10339;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00340 = 10340;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00341 = 10341;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00342 = 10342;
    /**
     * 模板报备未成功
     */
    public static final int CODE_00343 = 10343;


    public static final int CODE_00344 = 10344;

    public static final int CODE_00345 = 10345;

    public static final int CODE_00346 = 10346;

    public static final int CODE_00347 = 10347;

    public static final int CODE_00348 = 10348;

    public static final int CODE_00349 = 10349;

    public static final int CODE_00350 = 10350;
    public static final int CODE_00351 = 10351;
    public static final int CODE_00352 = 10352;
    public static final int CODE_00353 = 10353;
    public static final int CODE_00354 = 10354;
    public static final int CODE_00355 = 10355;
    public static final int CODE_00356 = 10356;
    public static final int CODE_00357 = 10357;
    public static final int CODE_00358 = 10358;
    public static final int CODE_00359 = 10359;
    public static final int CODE_00360 = 10360;
    public static final int CODE_00361 = 10361;
    public static final int CODE_00362 = 10362;
    public static final int CODE_00363 = 10363;
    public static final int CODE_00364 = 10364;

    public static final int CODE_00447 = 10447;
    public static final int CODE_00448 = 10448;
    public static final int CODE_00449 = 10449;
    public static final int CODE_00450 = 10450;
    public static final int CODE_00451 = 10451;
    public static final int CODE_00452 = 10452;

    public static final int CODE_00501 = 10501;

    /**
     * 发送条数超过每日限额
     */
    public static final int CODE_00502 = 10502;

    /**
     * 这个时间段不能发送短信
     */
    public static final int CODE_00503 = 10503;

    /**
     * 地区屏蔽
     */
    public static final int CODE_00504 = 10504;



    /**
     * 生成短链失败
     */
    public static final int CODE_00600 = 10600;








    public static final int MANAGE_180001 = 180001;


    /**
     * 参数不可为空
     */
    public static final int MANAGE_190001 = 190001;
    /**
     * 数据已存在
     */
    public static final int MANAGE_190002 = 190002;
    /**
     * 数据不存在
     */
    public static final int MANAGE_190003 = 190003;

    /**
     * 重复提交
     */
    public static final int MANAGE_190004 = 190004;




    /**
     * 查询通道配置异常
     */
    public static final int MANAGE_CHANNEL_190101 = 190101;
    public static final int MANAGE_CHANNEL_190102 = 190102;
    public static final int MANAGE_CHANNEL_190103 = 190103;
    public static final int MANAGE_CHANNEL_190104 = 190104;
    public static final int MANAGE_CHANNEL_190105 = 190105;
    public static final int MANAGE_CHANNEL_190106 = 190106;
    public static final int MANAGE_CHANNEL_190107 = 190107;
    public static final int MANAGE_CHANNEL_190108 = 190108;

    public static final int CODE_00215 = 10215;
    public static final int CODE_00216 = 10216;
    public static final int CODE_00217 = 10217;
    public static final int CODE_00218 = 10218;
    public static final int CODE_00505 = 10505;
    public static final int CODE_00506 = 10506;
    public static final int CODE_00507 = 10507;
    public static final int CODE_00508 = 10508;
    public static final int CODE_00509 = 10509;
}
