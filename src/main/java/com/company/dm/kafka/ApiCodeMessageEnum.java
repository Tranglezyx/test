package com.company.dm.kafka;

/**
 * @author w_jiangpengfei
 * @version v 0.1 2018/12/7 Date 12:24 w_jiangpengfei Exp $
 */
public enum ApiCodeMessageEnum {
    //异常枚举类
    CODE_00000(RespCode.CODE_00000, "请求成功。"),
    CODE_00001(RespCode.CODE_00001, "未知错误，请联系技术客服。"),
    CODE_00002(RespCode.CODE_00002, "未知的方法名"),
    CODE_00003(RespCode.CODE_00003, "请求方式错误"),
    CODE_00004(RespCode.CODE_00004, "参数非法"),
    CODE_00005(RespCode.CODE_00005, "timestamp已过期"),
    CODE_00006(RespCode.CODE_00006, "sign错误"),
    CODE_00007(RespCode.CODE_00007, "重复提交"),
    CODE_00008(RespCode.CODE_00008, "操作频繁"),
    CODE_00011(RespCode.CODE_00011, "请求的xml格式不对"),
    CODE_00012(RespCode.CODE_00012, "不支持get请求，请使用post"),
    CODE_00013(RespCode.CODE_00013, "请求url格式不正确，正确格式请参考http://www.qingmayun.com/restjieshao.html"),
    CODE_00015(RespCode.CODE_00015, "时间戳超出有效时间范围"),
    CODE_00016(RespCode.CODE_00016, "请求json格式不对"),
    CODE_00017(RespCode.CODE_00017, "数据库操作失败"),
    CODE_00018(RespCode.CODE_00018, "参数为空"),
    CODE_00019(RespCode.CODE_00019, "订单已存在"),
    CODE_00020(RespCode.CODE_00020, "用户不存在"),
    CODE_00021(RespCode.CODE_00021, "业务账号名余额不足"),
    CODE_00022(RespCode.CODE_00022, "操作频繁"),
    CODE_00023(RespCode.CODE_00023, "开发者余额不足"),
    CODE_00025(RespCode.CODE_00025, "手机格式不对"),
    CODE_00026(RespCode.CODE_00026, "手机号存在"),
    CODE_00027(RespCode.CODE_00027, "业务账号名名称已存在"),
    CODE_00028(RespCode.CODE_00028, "业务账号名名称过长"),
    CODE_00029(RespCode.CODE_00029, "回调开发者服务器异常"),
    CODE_00030(RespCode.CODE_00030, "回调地址为空"),
    CODE_00031(RespCode.CODE_00031, "appId为空或者没有传值"),
    CODE_00032(RespCode.CODE_00032, "主叫号码为空或者没有传值"),
    CODE_00033(RespCode.CODE_00033, "被叫号码为空或者没有传值"),
    CODE_00034(RespCode.CODE_00034, "业务账号名为空或者业务账号名错误"),
    CODE_00035(RespCode.CODE_00035, "主叫号码和被叫号码相同"),
    CODE_00036(RespCode.CODE_00036, "验证码格式不对（4-8位数字）"),
    CODE_00037(RespCode.CODE_00037, "limit格式不对"),
    CODE_00038(RespCode.CODE_00038, "start格式不对"),
    CODE_00039(RespCode.CODE_00039, "验证码为空或者缺少此参数"),
    CODE_00040(RespCode.CODE_00040, "用户名或者密码错误"),
    CODE_00050(RespCode.CODE_00050, "短信或者语音验证码错误"),
    CODE_00051(RespCode.CODE_00051, "显示号码与被叫号码一样，不允许呼叫"),
    CODE_00052(RespCode.CODE_00052, "回拨主叫号码格式错误"),
    CODE_00053(RespCode.CODE_00053, "被叫号码格式错误"),
    CODE_00054(RespCode.CODE_00054, "显号格式错误"),
    CODE_00055(RespCode.CODE_00055, "应用不包含此业务账号名"),
    CODE_00056(RespCode.CODE_00056, "开发者不包含此应用"),
    CODE_00057(RespCode.CODE_00057, "签码限制"),
    CODE_00058(RespCode.CODE_00058, "业务账号名只允许发移动号码"),
    CODE_00059(RespCode.CODE_00059, "业务账号名只允许发电信号码"),
    CODE_00060(RespCode.CODE_00060, "业务账号名只允许发联通号码"),
    CODE_00061(RespCode.CODE_00061, "目标号码号码段错误"),
    CODE_00062(RespCode.CODE_00062, "developerId 请求错误"),
    CODE_00063(RespCode.CODE_00063, "app未上线"),
    CODE_00064(RespCode.CODE_00064, "请求Content-Type错误"),
    CODE_00065(RespCode.CODE_00065, "请求Accept错误"),
    CODE_00066(RespCode.CODE_00066, "开发者余额已被冻结"),
    CODE_00070(RespCode.CODE_00070, "手机号未绑定"),
    CODE_00071(RespCode.CODE_00071, "通知类型已停用或者未创建"),
    CODE_00072(RespCode.CODE_00072, "balance格式不对（必须为大于等于0的double）"),
    CODE_00073(RespCode.CODE_00073, "charge格式不对（必须为大于等于0的double）"),
    CODE_00074(RespCode.CODE_00074, "主叫和子账户绑定的手机号不相同"),
    CODE_00075(RespCode.CODE_00075, "子账户没有绑定手机号"),
    CODE_00076(RespCode.CODE_00076, "时间格式不对"),
    CODE_00077(RespCode.CODE_00077, "开始时间小于结束时间"),
    CODE_00078(RespCode.CODE_00078, "开始时间和結束時間必須是同一天"),
    CODE_00079(RespCode.CODE_00079, "服务器内部异常"),
    CODE_00080(RespCode.CODE_00080, "业务账号名不存在"),
    CODE_00081(RespCode.CODE_00081, "通知计费系统失败"),
    CODE_00082(RespCode.CODE_00082, "参数校验失败"),
    CODE_00083(RespCode.CODE_00083, "充值失败"),
    CODE_00084(RespCode.CODE_00084, "业务账号名没有托管 不能进行充值"),
    CODE_00085(RespCode.CODE_00085, "开发者不包含子帐号"),
    CODE_00086(RespCode.CODE_00086, "DEMO不能进行充值"),
    CODE_00087(RespCode.CODE_00087, "IQ类型错误"),
    CODE_00090(RespCode.CODE_00090, "回调地址为空"),
    CODE_00091(RespCode.CODE_00091, "没有语音"),
    CODE_00093(RespCode.CODE_00093, "没有这个语音文件或者审核没通过"),
    CODE_00094(RespCode.CODE_00094, "每批手机号数量不得超过限制"),
    CODE_00095(RespCode.CODE_00095, "未开通邮件短信功能"),
    CODE_00096(RespCode.CODE_00096, "邮件模板未审核通过"),
    CODE_00097(RespCode.CODE_00097, "邮件模板未启用"),
    CODE_00098(RespCode.CODE_00098, "手机号超过营销多日发送频次限制"),
    CODE_00099(RespCode.CODE_00099, "相同的应用每天只能给同一手机号发送n条不同的内容"),
    CODE_00100(RespCode.CODE_00100, "匹配到关键字黑名单"),
    CODE_00101(RespCode.CODE_00101, "配置短信端口号失败"),
    CODE_00102(RespCode.CODE_00102, "一个开发者只能配置一个端口"),
    CODE_00103(RespCode.CODE_00103, "未查询到数据"),
    CODE_00104(RespCode.CODE_00104, "相同的应用当天给同一手机号发送短信的条数小于等于n"),
    CODE_00105(RespCode.CODE_00105, "本开发者只能发短信给移动手机"),
    CODE_00106(RespCode.CODE_00106, "时间戳(timestamp)参数为空"),
    CODE_00107(RespCode.CODE_00107, "签名(sig)参数为空"),
    CODE_00108(RespCode.CODE_00108, "时间戳(timestamp)格式错误"),
    CODE_00109(RespCode.CODE_00109, "业务账号名已被关闭"),
    CODE_00110(RespCode.CODE_00110, "解析post数据失败，post数据不符合格式要求"),
    CODE_00111(RespCode.CODE_00111, "匹配到黑名单"),
    CODE_00112(RespCode.CODE_00112, "accountSid参数为空"),
    CODE_00113(RespCode.CODE_00113, "短信内容和模板匹配度过低"),
    CODE_00114(RespCode.CODE_00114, "clientNumber参数为空"),
    CODE_00115(RespCode.CODE_00115, "charge参数为空"),
    CODE_00116(RespCode.CODE_00116, "charge格式不对，不能解析成double"),
    CODE_00117(RespCode.CODE_00117, "fromTime参数为空"),
    CODE_00118(RespCode.CODE_00118, "toTime参数为空"),
    CODE_00119(RespCode.CODE_00119, "fromTime参数格式不正确"),
    CODE_00120(RespCode.CODE_00120, "toTime参数格式不正确"),
    CODE_00121(RespCode.CODE_00121, "定时发送时间戳格式错误(毫秒,格式:1547005945480)"),
    CODE_00122(RespCode.CODE_00122, "date参数为空"),
    CODE_00123(RespCode.CODE_00123, "date的值不在指定范围内"),
    CODE_00124(RespCode.CODE_00124, "定时发送时间不能大于7天"),
    CODE_00125(RespCode.CODE_00125, "定时发送时间已过期（允许当前时间前5分钟内）"),
    CODE_00126(RespCode.CODE_00126, "to参数为空"),
    CODE_00127(RespCode.CODE_00127, "param参数个数不匹配"),
    CODE_00128(RespCode.CODE_00128, "templateId参数为空"),
    CODE_00129(RespCode.CODE_00129, "模板类型错误"),
    CODE_00130(RespCode.CODE_00130, "serviceType参数为空"),
    CODE_00131(RespCode.CODE_00131, "短信内容为空"),
    CODE_00132(RespCode.CODE_00132, "本接口的邮件短信业务只能发送移动手机"),
    CODE_00133(RespCode.CODE_00133, "错误的短信类型"),
    CODE_00134(RespCode.CODE_00134, "没有和内容匹配的模板"),
    CODE_00135(RespCode.CODE_00135, "短信类型和短信内容不匹配"),
    CODE_00136(RespCode.CODE_00136, "开发者不能调用此接口"),
    CODE_00137(RespCode.CODE_00137, "没有权限自定义邮件内容"),
    CODE_00138(RespCode.CODE_00138, "短信没有签名不能发送"),
    CODE_00139(RespCode.CODE_00139, "短信签名已进入黑名单不能发送"),
    CODE_00140(RespCode.CODE_00140, "邮件短信发送间隔太小"),
    CODE_00141(RespCode.CODE_00141, "一小时内发送给单个手机次数超过限制"),
    CODE_00142(RespCode.CODE_00142, "一天内发送给单个手机次数超过限制"),
    CODE_00143(RespCode.CODE_00143, "含有非法关键字"),
    CODE_00144(RespCode.CODE_00144, "mobile参数为空"),
    CODE_00145(RespCode.CODE_00145, "超过每日发送额度"),
    CODE_00146(RespCode.CODE_00146, "超过每秒请求发送速度限制"),
    CODE_00147(RespCode.CODE_00147, "被叫次数超限"),
    CODE_00148(RespCode.CODE_00148, "主叫次数超限"),
    CODE_00149(RespCode.CODE_00149, "流量包大小格式错误"),
    CODE_00150(RespCode.CODE_00150, "找不到匹配的流量包"),
    CODE_00151(RespCode.CODE_00151, "该签名下的手机号码黑名单"),
    CODE_00152(RespCode.CODE_00152, "端口号已被关闭"),
    CODE_00153(RespCode.CODE_00153, "未知的手机号运营商"),
    CODE_00154(RespCode.CODE_00154, "开发者无权限给此号码发短信"),
    CODE_00155(RespCode.CODE_00155, "待审核模板不能修改或删除"),
    CODE_00156(RespCode.CODE_00156, "packageId为空或者没有传值"),
    CODE_00157(RespCode.CODE_00157, "packageId不存在"),
    CODE_00158(RespCode.CODE_00158, "不允许发验证码"),
    CODE_00159(RespCode.CODE_00159, "超过每秒发送频率限制"),
    CODE_00160(RespCode.CODE_00160, "没有发送会员通知推广类短信权限"),
    CODE_00161(RespCode.CODE_00161, "短信签名没有报备"),
    CODE_00162(RespCode.CODE_00162, "没有发送营销短信权限"),
    CODE_00163(RespCode.CODE_00163, "会员营销短信内容必须包含拒收"),
    CODE_00164(RespCode.CODE_00164, "端口号非法"),
    CODE_00165(RespCode.CODE_00165, "关键字等待审核"),
    CODE_00166(RespCode.CODE_00166, "IP非法"),
    CODE_00167(RespCode.CODE_00167, "TemplateId错误"),
    CODE_00168(RespCode.CODE_00168, "TemplateId未审核或未启用或不通过"),
    CODE_00169(RespCode.CODE_00169, "param参数错误"),
    CODE_00171(RespCode.CODE_00171, "变量长度超长"),
    CODE_00172(RespCode.CODE_00172, "短信内容长度超长"),
    CODE_00173(RespCode.CODE_00173, "变量内容不能含有中文"),
    CODE_00174(RespCode.CODE_00174, "一分钟内下发短信超过次数限制"),
    CODE_00175(RespCode.CODE_00175, "不完整的长短信"),
    CODE_00176(RespCode.CODE_00176, "IP已被锁定"),
    CODE_00177(RespCode.CODE_00177, "templateId和smsContent只能填一项"),
    CODE_00178(RespCode.CODE_00178, "账号需先认证，请用户中心上传资料"),
    CODE_00179(RespCode.CODE_00179, "发送短信需要先认证"),
    CODE_00180(RespCode.CODE_00180, "变量内容不允许包含链接，请修改变量或联系在线客服"),
    CODE_00181(RespCode.CODE_00181, "账号已停用"),
    CODE_00182(RespCode.CODE_00182, "业务账号名已停用"),
    CODE_00183(RespCode.CODE_00183, "业务账号名缺少配置"),
    CODE_00184(RespCode.CODE_00184, "签名不能少于两个字"),
    CODE_00185(RespCode.CODE_00185, "签名太长"),
    CODE_00186(RespCode.CODE_00186, "审核不通过"),
    CODE_00187(RespCode.CODE_00187, "取消发送"),
    CODE_00188(RespCode.CODE_00188, "缺少参数content或content为空"),
    CODE_00189(RespCode.CODE_00189, "参数content请求体过大"),
    CODE_00190(RespCode.CODE_00190, "参数content请求体错误，无法解析"),
    CODE_00191(RespCode.CODE_00191, "发送短信必须认证信息安全责任书"),
    CODE_00192(RespCode.CODE_00192, "号码检测服务器异常"),
    CODE_00193(RespCode.CODE_00193, "拒绝发送"),
    CODE_00194(RespCode.CODE_00194, "签名限制"),
    CODE_00195(RespCode.CODE_00195, "扩展码错误"),
    CODE_00196(RespCode.CODE_00196, "号码段错误"),
    CODE_00197(RespCode.CODE_00197, "内容包含过滤词"),
    CODE_00198(RespCode.CODE_00198, "短信模板状态为拒绝"),
    CODE_00199(RespCode.CODE_00199, "自动审核失败"),
    CODE_00200(RespCode.CODE_00200, "人工审核拒绝"),
    CODE_00201(RespCode.CODE_00201, "公钥为空，请联系管理员并提供公钥"),
    CODE_00202(RespCode.CODE_00202, "批次发送量不能超过5W条"),
    CODE_00203(RespCode.CODE_00203, "未匹配到模板运营商"),
    CODE_00204(RespCode.CODE_00204, "内容重复"),
    CODE_00205(RespCode.CODE_00205, "模板不存在"),
    CODE_00206(RespCode.CODE_00206, "模板已停用"),
    CODE_00207(RespCode.CODE_00207, "模板报备未成功"),
    CODE_00208(RespCode.CODE_00208, "动态黑名单服务返回错误"),
    CODE_00209(RespCode.CODE_00209, "用户名已存在"),
    CODE_00210(RespCode.CODE_00210, "数据已存在"),
    CODE_00211(RespCode.CODE_00211, "正在使用中，无法禁用"),
    CODE_00212(RespCode.CODE_00212, "未匹配到关键字路由运营商"),
    CODE_00213(RespCode.CODE_00213, "渠道名已存在"),
    CODE_00214(RespCode.CODE_00214, "数据不存在"),
    CODE_00215(RespCode.CODE_00215, "请求的IP地址非法"),
    CODE_00216(RespCode.CODE_00216, "签名未审核通过"),
    CODE_00217(RespCode.CODE_00217, "拒收类型必选"),

    //用户中心对接字段校验等异常枚举
    CODE_00300(RespCode.CODE_00300, "userId不能为空"),
    CODE_00301(RespCode.CODE_00301, "扩展码不能为空"),
    CODE_00302(RespCode.CODE_00302, "业务类型不能为空"),
    CODE_00303(RespCode.CODE_00303, "apiId不能为空"),
    CODE_00304(RespCode.CODE_00304, "发送目标和内容不能为空"),
    CODE_00305(RespCode.CODE_00305, "签名不能为空、空格或者包含【】字符"),
    CODE_00306(RespCode.CODE_00306, "标题不能为空"),
    CODE_00307(RespCode.CODE_00307, "模板类型不能为空"),
    CODE_00308(RespCode.CODE_00308, "账户名不能为空"),
    CODE_00309(RespCode.CODE_00309, "userId不能有中文或者特殊字符且长度1-20位"),
    CODE_00310(RespCode.CODE_00310, "业务账号名名长度6-20位"),
    CODE_00311(RespCode.CODE_00311, "密码需为数字及字符且长度8-20位"),
    CODE_00312(RespCode.CODE_00312, "扩展码不能包含中文或者特殊字符且长度1-20位"),
    CODE_00313(RespCode.CODE_00313, "签码类型不正确"),
    CODE_00314(RespCode.CODE_00314, "业务类型不正确"),
    CODE_00315(RespCode.CODE_00315, "推送地址格式不正确，请填写完整域名：http://www.baidu.com"),
    CODE_00316(RespCode.CODE_00316, "apiId只能为1-20位的数字"),
    CODE_00317(RespCode.CODE_00317, "密码不能为空"),
    CODE_00318(RespCode.CODE_00318, "签名长度1-20位"),
    CODE_00319(RespCode.CODE_00319, "标题长度不能超过50个字"),
    CODE_00320(RespCode.CODE_00320, "模板类型不正确"),
    CODE_00321(RespCode.CODE_00321, "模板类型不能包含中文或者特殊字符长度1位"),
    CODE_00322(RespCode.CODE_00322, "目标手机号码格式不正确"),
    CODE_00323(RespCode.CODE_00323, "用户账号不能为空"),
    CODE_00324(RespCode.CODE_00324, "用户名不能为空"),
    CODE_00325(RespCode.CODE_00325, "付费类型不能为空"),
    CODE_00326(RespCode.CODE_00326, "用户账号不能包含中文或者特殊字符且长度1-20位"),
    CODE_00327(RespCode.CODE_00327, "用户名称不能包含中文或者特殊字符且长度1-20位"),
    CODE_00328(RespCode.CODE_00328, "付费类型不正确"),
    CODE_00329(RespCode.CODE_00329, "账户名已经存在"),
    CODE_00330(RespCode.CODE_00330, "获取用户信息失败"),
    CODE_00331(RespCode.CODE_00331, "业务账号名不存在"),
    CODE_00332(RespCode.CODE_00332, "账户不正确或者付费类型不正确"),
    CODE_00333(RespCode.CODE_00333, "文件大小不能为空且大小和图片大小一致"),
    CODE_00334(RespCode.CODE_00334, "文件类型不能为空"),
    CODE_00335(RespCode.CODE_00335, "内容或者图片地址不能为空"),
    CODE_00336(RespCode.CODE_00336, "文件大小不能超过2000kb"),
    CODE_00337(RespCode.CODE_00337, "文件类型不正确"),
    CODE_00338(RespCode.CODE_00338, "内容或者url长度过长"),
    CODE_00339(RespCode.CODE_00339, "文件名不能超过500字符"),
    CODE_00340(RespCode.CODE_00340, "模板Id不能为空"),
    CODE_00341(RespCode.CODE_00341, "业务账号名不能包含中文"),
    CODE_00342(RespCode.CODE_00342, "业务账号名不能包含特殊字符"),
    CODE_00343(RespCode.CODE_00343, "参数转换错误"),
    CODE_00344(RespCode.CODE_00344, "签名无需报备"),
    CODE_00345(RespCode.CODE_00345, "密码不能为中文"),
    CODE_00346(RespCode.CODE_00346, "视频短信账号不能使用签码限制/区域轮号/签码配对"),
    CODE_00347(RespCode.CODE_00347, "签码类型错误"),
    CODE_00348(RespCode.CODE_00348, "签码不能包含中文"),
    CODE_00349(RespCode.CODE_00349, "签码不能包含特殊字符"),
    CODE_00350(RespCode.CODE_00350, "签码不能重复"),
    CODE_00351(RespCode.CODE_00351, "业务账号名重复"),
    CODE_00352(RespCode.CODE_00352, "apiId与api账名不匹配"),
    CODE_00353(RespCode.CODE_00353, "账号名密码不匹配"),
    CODE_00354(RespCode.CODE_00354, "手机号码重复"),
    CODE_00355(RespCode.CODE_00355, "短信内容不能为空"),
    CODE_00356(RespCode.CODE_00356, "短信签名不能为空"),
    CODE_00357(RespCode.CODE_00357, "业务账号类型不正确"),
    CODE_00358(RespCode.CODE_00358, "扣量率只能填写0-100的整数"),
    CODE_00359(RespCode.CODE_00359, "运营商数量目前不能大于3个"),
    CODE_00360(RespCode.CODE_00360, "运营商错误"),
    CODE_00361(RespCode.CODE_00361, "扣量率参数不正确，请按正确格式填写！"),
    CODE_00362(RespCode.CODE_00362, "模板内容不能为空"),
    CODE_00363(RespCode.CODE_00363, "API子账号不存在"),
    CODE_00364(RespCode.CODE_00364, "不能重复设置相同运营商扣量率"),
    CODE_00447(RespCode.CODE_00447, "content字段不能为空"),
    CODE_00448(RespCode.CODE_00448, "签名为空"),
    CODE_00449(RespCode.CODE_00449, "内容长度超出限制"),
    CODE_00450(RespCode.CODE_00450, "签码格式错误"),
    CODE_00451(RespCode.CODE_00451, "子账户id与子账户名称不匹配"),
    CODE_00452(RespCode.CODE_00452, "平台账号存在"),
    CODE_00501(RespCode.CODE_00501, "签名无需报备"),
    CODE_00502(RespCode.CODE_00502, "发送条数超过每日限额"),
    CODE_00503(RespCode.CODE_00503, "这个时间段不能发送短信"),
    CODE_00504(RespCode.CODE_00504, "地区屏蔽"),
    CODE_00505(RespCode.CODE_00505, "普通业务账号未配置{1}运营商"),
    CODE_00505_1(RespCode.CODE_00505, "普通业务账号未配置移动运营商"),
    CODE_00505_2(RespCode.CODE_00505, "普通业务账号未配置电信运营商"),
    CODE_00505_3(RespCode.CODE_00505, "普通业务账号未配置联通运营商"),
    CODE_00506(RespCode.CODE_00506, "扩展码生成失败"),
    CODE_00507(RespCode.CODE_00507, "无可用子码"),
    CODE_00508(RespCode.CODE_00508, "变量内容不合法"),
    CODE_00509(RespCode.CODE_00509, "该账号未成功开通发短信业务"),

    CODE_00510(10509, "短信内容超长"),
    CODE_00511(10511, "变量超长"),



    // 以下为 INCREMENT 错误码 8 开头被占用了
    NBSV_180001(180001, "参数错误"),


    CODE_00600(RespCode.CODE_00600, "生成短链失败"),


    ;
    /**
     * value
     */
    private final int code;
    /**
     * message
     */
    private final String message;

    /**
     * 私有构造方法
     *
     * @param code
     * @param message
     */
    ApiCodeMessageEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiCodeMessageEnum getApiCodeMessage(int code) {

        for (ApiCodeMessageEnum en : ApiCodeMessageEnum.values()) {
            if (en.getCode() == code) {
                return en;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ApiCodeMessageEnum getEnumCode(String msg) {
        if (null == msg) {
            return null;
        }
        for (ApiCodeMessageEnum value : ApiCodeMessageEnum.values()) {
            if (msg.equals(value))
                return value;
        }
        return null;
    }

    public static ApiCodeMessageEnum getEnumCodeType(int type) {
        if (type == 1) {
            return CODE_00203;
        } else {
            return CODE_00212;
        }
    }
}