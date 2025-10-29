package com.test.util.string;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsContentParser {

    private static final Set<String> VALID_AREA_CODES = new HashSet<>(Arrays.asList(
            "010", "020", "021", "022", "023", "024", "025", "027", "028", "029",
            "0310", "0311", "0312", "0313", "0314", "0315", "0316", "0317", "0318", "0319",
            "0335", "0349", "0350", "0351", "0352", "0353", "0354", "0355", "0356", "0357", "0358", "0359",
            "0370", "0371", "0372", "0373", "0374", "0375", "0376", "0377", "0378", "0379",
            "0391", "0392", "0393", "0394", "0395", "0396", "0398",
            "0411", "0412", "0413", "0414", "0415", "0416", "0417", "0418", "0419",
            "0421", "0427", "0428", "0429",
            "0431", "0432", "0433", "0434", "0435", "0436", "0437", "0438", "0439",
            "0440", "0450", "0451", "0452", "0453", "0454", "0455", "0456", "0457", "0458", "0459",
            "0464", "0467", "0468", "0469",
            "0470", "0471", "0472", "0473", "0474", "0475", "0476", "0477", "0478", "0479",
            "0482", "0510", "0511", "0512", "0513", "0514", "0515", "0516", "0517", "0518", "0519",
            "0523", "0530", "0531", "0532", "0533", "0534", "0535", "0536", "0537", "0538", "0539",
            "0543", "0546", "0550", "0551", "0552", "0553", "0554", "0555", "0556", "0557", "0558", "0559",
            "0561", "0562", "0563", "0564", "0565", "0566",
            "0570", "0571", "0572", "0573", "0574", "0575", "0576", "0577", "0578", "0579", "0580",
            "0591", "0592", "0593", "0594", "0595", "0596", "0597", "0598", "0599",
            "0701", "0710", "0711", "0712", "0713", "0714", "0715", "0716", "0717", "0718", "0719",
            "0722", "0724", "0728",
            "0730", "0731", "0732", "0733", "0734", "0735", "0736", "0737", "0738", "0739",
            "0743", "0744", "0745", "0746",
            "0750", "0751", "0752", "0753", "0754", "0755", "0756", "0757", "0758", "0759",
            "0760", "0762", "0763", "0766", "0768", "0769",
            "0770", "0771", "0772", "0773", "0774", "0775", "0776", "0777", "0778", "0779",
            "0790", "0791", "0792", "0793", "0794", "0795", "0796", "0797", "0798", "0799",
            "0801", "0812", "0813", "0816", "0817", "0818", "0819", "0825", "0826", "0827",
            "0830", "0831", "0832", "0833", "0834", "0835", "0836", "0837", "0838", "0839",
            "0840", "0845",
            "0851", "0852", "0853", "0854", "0855", "0856", "0857", "0858", "0859",
            "0870", "0871", "0872", "0873", "0874", "0875", "0876", "0877", "0878", "0879",
            "0883", "0886", "0887", "0888",
            "0890", "0891", "0892", "0893", "0894", "0895", "0896", "0898", "0899",
            "0901", "0902", "0903", "0906", "0908", "0909",
            "0911", "0912", "0913", "0914", "0915", "0916", "0917", "0919",
            "0931", "0932", "0933", "0934", "0935", "0936", "0937", "0938", "0939",
            "0941", "0943",
            "0951", "0952", "0953", "0954", "0955",
            "0970", "0971", "0972", "0973", "0974", "0975", "0976", "0977", "0978", "0979",
            "0990", "0991", "0992", "0993", "0994", "0995", "0996", "0997", "0998", "0999"
    ));
    private static final Map<String, String> SPECIAL_CHAR_MAP = new HashMap<>();
    private static final String URL_REGEX = "(?i)\\b((?:https?://)?(?:[a-zA-Z0-9\\-]+\\.)+[a-zA-Z]{2,6}(?::\\d{1,5})?(?:/[\\w\\-./?%&=]*)?)";
    private static final Pattern UNIT_REGEX = Pattern.compile("\\b\\d+(\\.\\d+)?\\s?(mg/L|μg/L|g/L|Mbps|Kbps|s|Gbps|ms|mm|kg|g|mL|L|kWh|km/h|m/s|Hz|MHz|GHz|dB|W|Pa|mol/L)\\b(?![a-zA-Z\\.])");

    private static final Pattern numberPattern = Pattern.compile("\\d{4,}");
    private static final Pattern mobilePattern = Pattern.compile(
            "(?<![\\da-zA-Z])" +  // 前缀不能是数字或字母
                    "(" +
                    // 1. 手机号（含 +86、空格或 - ）
                    "(?:\\+?86[-\\s]?)?1[3-9]\\d{1}(?:[-\\s]?\\d{4}){2}" +
                    "|" +
                    // 2. 座机（带区号）
                    "0\\d{2,3}[-\\s]?\\d{7,8}" +
                    "|" +
                    // 3. 特服号（95 / 96 开头）
                    "9[56]\\d{5,6}" +
                    "|" +
                    // 4. 400/800 热线
                    "[48]00[-\\s]?\\d{3}[-\\s]?\\d{4}" +
                    "|" +
                    // 5. 无区号座机（7~8位，加前后断词）
                    "(?<!\\d)\\d{7,8}(?!\\d)" +
                    ")" +
                    "(?![\\da-zA-Z\\.])"  // 后缀不能是数字、字母或点号
    );

    private static final Pattern LANDLINE_WITH_AREA_CODE_PATTERN = Pattern.compile("(0\\d{2,3})-?([1-9]\\d{6,7})");

    // 使用数组代替 HashSet 更高效
    private static final String[] THREE_DIGIT_AREA_CODES = {
            "010", "021", "022", "023", "024", "025", "027", "028", "029"
    };

    private static final Pattern yzmMobilePattern = Pattern.compile(
            "(?<![\\da-zA-Z])" +  // 前缀不能是数字或字母
                    "(" +
                    // 1. 手机号（11位，支持分隔符）
                    "(?:\\+?86[-\\s]?)?(1[3-9]\\d{1}[-\\s]?\\d{4}[-\\s]?\\d{4})" +
                    "|" +
                    // 2. 座机（带区号）
                    "(?:0\\d{2,3}[-\\s]?\\d{7,8})" +
//                    "|" +
                    // 3. 特服号码 95/96，5~8 位
//                    "(9[56]\\d{5,6})" +
                    "|" +
                    // 4. 400/800 热线
                    "([48]00[-\\s]?\\d{3}[-\\s]?\\d{4})" +
                    "|" +
                    ")" +
                    "(?![\\da-zA-Z\\.])"  // 后缀不能是数字、字母或点号
    );


    private static final Pattern relaxedUrlPattern = Pattern.compile(
            "(?i)((?:https?://)?[a-zA-Z0-9\\-]+(?:\\.[a-zA-Z0-9\\-]+)+(:\\d+)?(?:/[\\w\\-./?%&=+#@]*)?)"
    );

    // 标准邮箱正则（覆盖常见邮箱格式）
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+"
    );

    static {
        SPECIAL_CHAR_MAP.put("零", "0");
        SPECIAL_CHAR_MAP.put("〇", "0");
        SPECIAL_CHAR_MAP.put("一", "1");
        SPECIAL_CHAR_MAP.put("壹", "1");
        SPECIAL_CHAR_MAP.put("二", "2");
        SPECIAL_CHAR_MAP.put("贰", "2");
        SPECIAL_CHAR_MAP.put("三", "3");
        SPECIAL_CHAR_MAP.put("叁", "3");
        SPECIAL_CHAR_MAP.put("四", "4");
        SPECIAL_CHAR_MAP.put("肆", "4");
        SPECIAL_CHAR_MAP.put("五", "5");
        SPECIAL_CHAR_MAP.put("伍", "5");
        SPECIAL_CHAR_MAP.put("六", "6");
        SPECIAL_CHAR_MAP.put("陆", "6");
        SPECIAL_CHAR_MAP.put("七", "7");
        SPECIAL_CHAR_MAP.put("柒", "7");
        SPECIAL_CHAR_MAP.put("八", "8");
        SPECIAL_CHAR_MAP.put("捌", "8");
        SPECIAL_CHAR_MAP.put("九", "9");
        SPECIAL_CHAR_MAP.put("玖", "9");
        SPECIAL_CHAR_MAP.put("①", "1");
        SPECIAL_CHAR_MAP.put("②", "2");
        SPECIAL_CHAR_MAP.put("③", "3");
        SPECIAL_CHAR_MAP.put("④", "4");
        SPECIAL_CHAR_MAP.put("⑤", "5");
        SPECIAL_CHAR_MAP.put("⑥", "6");
        SPECIAL_CHAR_MAP.put("⑦", "7");
        SPECIAL_CHAR_MAP.put("⑧", "8");
        SPECIAL_CHAR_MAP.put("⑨", "9");
        SPECIAL_CHAR_MAP.put("⑩", "10");
//        SPECIAL_CHAR_MAP.put("zero", "0");
//        SPECIAL_CHAR_MAP.put("one", "1");
//        SPECIAL_CHAR_MAP.put("two", "2");
//        SPECIAL_CHAR_MAP.put("three", "3");
//        SPECIAL_CHAR_MAP.put("four", "4");
//        SPECIAL_CHAR_MAP.put("five", "5");
//        SPECIAL_CHAR_MAP.put("six", "6");
//        SPECIAL_CHAR_MAP.put("seven", "7");
//        SPECIAL_CHAR_MAP.put("eight", "8");
//        SPECIAL_CHAR_MAP.put("nine", "9");
        // “点号”替代字符
        SPECIAL_CHAR_MAP.put("丶", ".");
//        SPECIAL_CHAR_MAP.put("。", ".");
        SPECIAL_CHAR_MAP.put("．", ".");
        SPECIAL_CHAR_MAP.put("·", ".");
//        SPECIAL_CHAR_MAP.put("｡", ".");
        SPECIAL_CHAR_MAP.put("dot", ".");

    }

    public static String preprocess(String text) {
        if (text == null) return "";
        text = text.replaceAll("[\\u00A0\\u200B\\u200E\\u202F\\uFEFF\\s]", "");
        text = Normalizer.normalize(text, Normalizer.Form.NFKC);
        text = EMAIL_PATTERN.matcher(text).replaceAll("");
        // 替换特殊字符数字
        for (Map.Entry<String, String> entry : SPECIAL_CHAR_MAP.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text;
    }

    /**
     * 高效验证中国大陆固定电话号码
     */
    public static boolean isLandlineNumber(String number) {
        // 1. 提前检查长度：原始字符串至少需要10个数字字符
        int digitCount = 0;
        for (int i = 0; i < number.length(); i++) {
            if (Character.isDigit(number.charAt(i))) digitCount++;
        }
        if (digitCount < 10 || digitCount > 12) return false;

        // 2. 提取纯数字序列（避免创建新字符串）
        char[] digits = new char[digitCount];
        int index = 0;
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (Character.isDigit(c)) {
                digits[index++] = c;
            }
        }

        // 3. 快速失败检查
        if (digits[0] != '0') return false; // 首位必须为0

        // 4. 高效区号检查（避免使用substring）
        int areaCodeLength;
        boolean isThreeDigitArea = false;

        // 检查前三位是否匹配三位区号
        if (digits.length >= 3) {
            for (String code : THREE_DIGIT_AREA_CODES) {
                if (digits[0] == code.charAt(0) &&
                        digits[1] == code.charAt(1) &&
                        digits[2] == code.charAt(2)) {
                    isThreeDigitArea = true;
                    break;
                }
            }
        }

        if (isThreeDigitArea) {
            areaCodeLength = 3;
        } else {
            // 检查4位区号最小长度
            if (digits.length < 11) return false; // 4位区号+7位号码
            areaCodeLength = 4;
        }

        // 5. 本地号码长度检查
        int localNumberLength = digits.length - areaCodeLength;

        if (areaCodeLength == 3) {
            if (localNumberLength != 8) return false; // 必须8位
        } else {
            if (localNumberLength != 7 && localNumberLength != 8) return false;
        }

        // 6. 本地号首位检查（使用ASCII值比较）
        char firstLocalDigit = digits[areaCodeLength];
        return firstLocalDigit != '0' && firstLocalDigit != '1';
    }

    public static List<String> extractYzmPhoneNumbers(String text) {
        Set<String> numbers = new LinkedHashSet<>(); // 保证去重 & 保持顺序
        Matcher matcher = yzmMobilePattern.matcher(text);
        while (matcher.find()) {
            String matched = matcher.group();
            numbers.add(matched);
        }

        Matcher numberMatch = numberPattern.matcher(text);
        List<String> numberList = Lists.newArrayList();
        while (numberMatch.find()) {
            String matched = numberMatch.group().trim();
            if (StrUtil.isNotEmpty(matched)) {
                numberList.add(matched);
            }
        }

        if (numberList.size() < 2) {//如果数字只有1个，则为非引流号码
            numbers.clear();
        }

        return new ArrayList<>(numbers);
    }

    public static List<String> extractPhoneNumbers(String text, List<String> whiteKeywords, Pattern phonePattern) {
        Set<String> numbers = new LinkedHashSet<>(); // 保证去重 & 保持顺序
        Matcher matcher = phonePattern.matcher(text);
        while (matcher.find()) {
            String matched = matcher.group().replaceAll("[-\\s]", "");
            if (CollectionUtil.isNotEmpty(whiteKeywords)) {
                boolean exists = whiteKeywords.stream().anyMatch(text::contains);
                if (exists) {
                    continue;
                }
            }
            if (!matched.startsWith("400") && !matched.startsWith("800") &&
                    !matched.startsWith("95") && !matched.startsWith("96") &&
                    isTimeFormat(matched)) {//如果是时间格式则不认为是引流信息
                continue;
            }
            if (LANDLINE_WITH_AREA_CODE_PATTERN.matcher(matched).find()) {
                if (!isValidLandline(matched)) {
                    continue;
                }
            }
            numbers.add(matched);
        }



        if (CollectionUtil.isNotEmpty(numbers) && text.contains("验证码")) {//验证码特殊处理
            return extractYzmPhoneNumbers(text);
        }

        return new ArrayList<>(numbers);
    }

    /**
     * 校验是否为合法的中国大陆座机号码
     * - 区号必须在合法列表中
     * - 区号为3位时，固话号必须8位
     * - 区号为4位时，固话号为7或8位
     */
    public static boolean isValidLandline(String phone) {
        if (phone == null || phone.length() < 10) return false;

        // 去除非数字字符（只保留数字）——可选
        String normalized = phone.replaceAll("-", "");

        // 遍历所有合法区号，查找是否是以该区号开头
        for (String areaCode : VALID_AREA_CODES) {
            if (normalized.startsWith(areaCode)) {
                String landline = normalized.substring(areaCode.length());

                // 固话号必须是全数字、首位非0
                if (!landline.matches("[1-9]\\d{6,7}")) return false;

                // 长度校验：区号为3位 → 固话必须是8位，区号为4位 → 固话是7或8位
                if (areaCode.length() == 3 && landline.length() == 8) {
                    return true;
                }
                if (areaCode.length() == 4 && (landline.length() == 7 || landline.length() == 8)) {
                    return true;
                }
                return false;
            }
        }

        // 没有找到合法区号匹配
        return false;
    }

    private static boolean isTimeFormat(String matched) {
        if (StrUtil.isEmpty(matched)) {
            return false;
        }
        try {
            DateTime parse = DateUtil.parse(matched);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<String> extractLinks(String text, List<String> whiteKeywords, Pattern urlPattern) {
        List<String> links = new ArrayList<>();
        Matcher matcher = urlPattern.matcher(text);
        while (matcher.find()) {
            String url = matcher.group();
            if (url != null && !url.trim().isEmpty()) {
                if (CollectionUtil.isNotEmpty(whiteKeywords)) {
                    boolean exists = whiteKeywords.stream().anyMatch(text::contains);
                    if (exists) {
                        continue;
                    }
                }
                if (NumberUtil.isNumber(url)) {
                    continue;
                }
                boolean matchSuffix = UNIT_REGEX.matcher(url).find();
                if (matchSuffix) {
                    continue;
                }
                if (isTimeFormat(url)) {
                    continue;
                }
                links.add(url.trim());
            }
        }
        return links;
    }

    public static Map<Integer, List<String>> parseAllPossibleNumbersAndLinks(List<String> whiteKeywords, String content) {
        String processContent = preprocess(content);
        List<String> links = extractLinks(processContent, whiteKeywords, relaxedUrlPattern);
        if (CollectionUtil.isNotEmpty(links)) {
            for (String link : links) {
                processContent = processContent.replace(link, "");
            }
        }
        List<String> mobiles = extractPhoneNumbers(processContent, whiteKeywords, mobilePattern);
        return ImmutableMap.of(1, links, 2, mobiles);
    }


    public static void main(String[] args) throws Exception {

//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "尊敬的客户，人保车险管家为您服务。赣C196U3保险即将到期{终保日期}现为您报价：交强险855.00元，商业险2878.35元，途顺家安组合保险298元，代收车船税360.00元，总计4391.35元。其中车险险种包括：机动车损失保险保额136644.20元，保费1809.47元、机动车第三者责任保险保额300万元，保费755.05元、机动车车上人员责任保险（司机）保额40000.00元，保费72.43元、机动车车上人员责任保险（乘客）保额为160000.00元(40000/座*4)，保费183.78元、附加医保外医疗费用责任险（机动车第三者责任保险）保额为共享主险保额，保费57.62元、附加机动车增值服务特约条款（道路救援服务）(7次)保费0元。非车险险种包括：途顺家安组合保险保额3152000.00元，保费298.00元。客户经理袁林红，电话18178958181，祝您生活愉快！以上项目供您参考，最终以出单为准。"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "[待预约-SHGD0001757703，今日头条（字节跳动-ByteDance），4H-R_24H-C(国内L1)，请及时处理]"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "[您的客户出险：报案号RDAA2025620300N0005539，被保险人王刚，联系人王刚，联系电话13993581585，车牌号甘AS5P79，车名东风日产DFL6440VAL2多用途乘用车，出险地点甘肃省金昌市永昌县金昌市永昌县。请与客户联系并回复：客户满意，查勘不满意，定损不满意，修理不满意，需人伤咨询。]"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "[报案号后6位RDAA2025340800N0022573的查勘员为林健豪联系电话17755046400，请及时联系。]"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "报案号RDAA2025620300N0005539，联系人王刚，联系电话13993581585，车牌号甘AS5P79，报案已调度，车名东风日产DFL6440VAL2多用途乘用车，调度时间2025-07-10，查勘员任子欣(查勘电话19109450099)"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "尊敬的 黄跃跃, 上海迪士尼乐园酒店非常高兴确认您的预订：2025年7月11日入住，2025年7月12日退房，1间 奇梦童趣房 1晚，确认号：4596677。您可登陆网站，根据确认号查询订单详情及预订条款。 上海迪士尼度假区欢迎您的到来！"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "广电 | 星乐卡, JDV021902123035"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "事件攻击开始通知 攻击客户名称：泛美汽车零部件(宁波)有限公司 事件ID：M6014A244579 攻击目标IP：116.148.210.90 攻击流速：108.8Mbps 攻击类型：Malform TCP with port 0"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "推修福州市盛世新景汽车销售有限公司福峡路分公司，新承保推送，报案号：RDAA2025350100S0101217，闽AF80195，比亚迪BYD7152WT6HEVC1插电式混合动力轿车，2025-07-09 23:00:49在福建省莆田市荔城区荔城区 荔城区黄石镇凤山村卫生所 福建省莆田市荔城区道山街东150米出险，是单方事故]\n" +
//                "自动放行"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "山东圣阳电源股份有限公司,在2025-07-09 22:00 总排口:氨氮浓度为37.40mg/L标准值为30.0超标0.247倍;，请予以关注、及时分析处置。"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "推修邵武市中福汽车贸易有限公司，新集中推送，报案号：RDAA2025350700N0014833，闽H529AA，帝豪HQ7151D02轿车，2025-07-10 00:35:12在福建省南平市光泽县福建省南平市光泽县鸾凤乡出险"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "报案号RDAA2025520100N0073247，新承保推送，彭军婷，005N4M，特斯拉TSL7000BEVAR7纯电动轿车，贵州省贵阳市云岩区贵州省贵阳市云岩区北京路社区北京路207号，查勘人员汪芳煦18586918185。已经推荐到贵店（特斯拉贵阳），请尽快联系客户预约进店维修。并请回复短信1、2、或0 (注：1-客户进本店维修、2-不确定、0-客户不到本店维修)涉客户信息 泄露违法必究，请知悉！"));
//
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "测试签名0703】你好测试签名http://ysds.cn"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【666666】dfwerw大是大非12.23ms，200ms，1.1Gbps，qqqq@163.com"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "测试签名0703】你好测试签名www.ysds.cn还有https://wsga.gaj.cq.gov.cn"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "测试签名0703】你好测试签名www.ysds.cn/test?name=abc"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "测试签名0703】你好测试签名https://www.ysds.cn/test?name=abc"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "测试签名0703】你好测试签名https://www.ysds.cn/test?name=abc你好075522626984"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【二胎未啊】您派送的包裹尾号28786384显示已签收，包裹如有异常可反馈 3.cn/2-k89TsB。"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【美团金融】马丽花的逾期欠款已完成债务转接，132465@qq.com即将加大催收力度，在15号前将此笔款项催缴回来，一旦进入强催状态，将对您造成严重影响；如有主动归还意愿，请24小时内主动拨打：4006375036或回复“9折结清”，致电给你确认减免结清，完结债权。拒收请回复R\n"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【测试签名0703】这是测试模板40095558，010-52548956带有引流信息旦米，其他引流信息测试，拒收请回复R"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "您的网上开户申请已成功，客户号016099840056，05390521478一码通账号:180312789177;沪市A股账户:A565240981;深市A股账户:0908467862，"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "客户号: 016099462113"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【联通云】告警恢复提醒：上海一区高级告警：123.6s.com云数据库RDS(备机)-mysql_evtj(ID：8393491985093091328，IP：192.168.0.7(私网))于2025-08-05 00:27:05触发告警恢复，告警描述：主从延迟时间大于等于2000s，当前值：368.00s，资源所属账号：310NWY13706215。详情请访问云管平台。"));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【中粮期货】主机:SY-WL-JYS-01 BFD:to_cffex 状态异常问题已恢复，恢复时间:2025.08.05 00:10:46."));
//        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "提醒您:刚才我们通过96110给你打了预警电话，因我们在工作中发现，你可能接到了冒充客服称\n" +
//                "理赔以及刷单，贷款，投资理财，网络博彩等电话和信息，可能被诈骗，希望你不要上当受骗。牢\n" +
//                "记:不听、不信、不转账!如果你不幸被诈骗，请及时拨打110报警，我们的预警电话是02396110、\n" +
//                "02370686110，请及时接听!"));
        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【联通云】告警消息提醒：您的云服务器ECS(杭州一区)招采系统应用服务器(ID：8489869407598919680，IP：172.16.0.127(私网) 103.44.81.2(公网) )于2025-08-05 00:10:00触发告警，告警级别：高级，告警描述：vda磁盘IO等待时间大于100ms，当前值：587.14ms。详情请访问云监控服务。"));
        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【全美在线】考生您好，欢迎您参加贵州电网有限责任公司电力科学研究院部分专业技术岗位公开选聘考试，考试时间定于2025年8月27日上午09:00-10:30。准考证已开放打印，请使用电脑登录网站：https://t.exam-sp.com/index.html#/e/GZDWYJY/print，使用姓名及身份证号码登录查询并打印纸质准考证。推荐使用谷歌、IE10、IE11以及360极速浏览器。考试现场凭您有效身份证件及纸质准考证入场，缺一不可，请务必准备齐全，以免无法入场参加考试，收到短信请回复“姓名＋参加/放弃”，祝您考试顺利。"));
        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【上海迪士尼度假区】感谢您提交订单。您的订单确认号为： SHDR4010831639540309。稍后您将收到含有您的年卡卡号及激活密码的短信。凭此信息，中国内地游客可下载上海迪士尼度假区官方App: https://shdr.cn/app，使用年卡在线兑换功能提前兑换您的上海迪士尼乐园年卡；如您是中国香港、中国澳门、中国台湾地区游客或其他国家及地区游客，须在乐园现场兑换（“现场兑换”）您的年卡。您须前往乐园主入口售票亭出示指定的个人身份证件原件和购卡订单号，现场兑换年卡。中国香港、中国澳门地区游客，须提供《港澳居民来往内地通行证》、《中华人民共和国旅行证》或《中华人民共和国港澳居民居住证》；中国台湾地区游客，须提供《台湾居民来往大陆通行证》、《中华人民共和国旅行证》或《中华人民共和国台湾居民居住证》；其他国家及地区游客，须提供有效护照或《中华人民共和国外国人永久居留身份证》。 残障人士须另外携带本人有效残障证明原件，并在入园时进行证件验证。幻彩珍珠卡和奇梦翡翠卡持卡人每次入园前需提前在“可预约入园日”内进行预约，预约成功后方可入园。可预约的名额有限，额满为止。 如需了解更多信息，请点击查看上海迪士尼乐园年卡须知：https://www.shanghaidisneyresort.com/annual-pass-rules 。"));
        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【上海迪士尼度假区】您提交的确认号[GAL2398000323002309]订单相关取消申请正在处理中。为了更好的处理您的申请，需要您在24小时以内通过如下链接提交对应的证明材料。https://customer-service-portal.shanghaidisneyresort.com.cn/csp/submit/detail/190614。如有任何疑问，请联系上海迪士尼度假区官方微信客服。祝您度过神奇的一天！"));
        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【上海迪士尼度假区】cn/en/totcReminder: Disney Standby Pass and Disney Premier Access prior to the visitation date are now available on the App. Click to download: https://shdr.cn/en/app"));
        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【上海迪士尼度假区】提醒：我们正在进行一项有关乐园酒店的满意度调查, 以帮助我们为游客提供更好的体验。参与调查，请点击下面的链接。https://survey.shanghaidisneyresort.com/wix/p1036422.aspx?r=1830831&s=NFOSKRUD 。如已完成，请忽略。拒收请回复R"));
        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【上海迪士尼度假区】亲爱的上海迪士尼乐园年卡持卡人龚晓晨，希望您与上海迪士尼乐园年卡度过了一段奇妙的旅程！为了更好地为您提供奇妙的体验，我们诚挚地邀请您花5-10分钟参与调研。点击链接：https://survey.shanghaidisneyresort.com/wix/p1098445.aspx?r=2436493&s=BURHWVMR，链接五日内有效。祝您度过神奇的一天！拒收请回复R"));
        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【上海迪士尼度假区】亲爱的年卡持卡人周雨之，感谢您的陪伴，希望上海迪士尼乐园年卡为您带来了一段奇妙旅程。我们诚挚地邀请您参与针对到期年卡用户的体验调研，仅需5分钟，您的反馈对我们很重要。请点击链接告诉我们您的心声：https://survey.shanghaidisneyresort.com/wix/p1096420.aspx?r=454036&s=OQJGHFHA，链接四日内有效。祝您度过神奇的一天！拒收请回复R"));
        System.out.println(parseAllPossibleNumbersAndLinks(Lists.newArrayList(), "【上海迪士尼度假区】提醒：我们正在进行一项有关乐园酒店的满意度调查, 以帮助我们为游客提供更好的体验。参与调查，请点击下面的链接。https://survey.shanghaidisneyresort.com/wix/p1036422.aspx?r=1836669&s=RMIUSHFK 。如已完成，请忽略。拒收请回复R"));

//        List<String> strings = Files.readAllLines(Paths.get("C:\\Users\\47156\\Downloads\\20250702\\content.txt"));
//        List<String> result = Lists.newArrayList();
//        for (String string : strings) {
//            Map<Integer, List<String>> integerListMap = parseAllPossibleNumbersAndLinks(Lists.newArrayList(), string);
//            String res = String.join(",", integerListMap.getOrDefault(2, Lists.newArrayList()));
//            if (StrUtil.isNotEmpty(res)) {
//                result.add(string + "=>" + res);
//            }
//        }
//
//        FileUtil.writeLines(result, new File("C:\\Users\\47156\\Downloads\\20250702\\result.txt"), Charset.defaultCharset());
//        System.out.println(isTimeFormat("2025.08.08"));
//        System.out.println("0825133706000101040505_0825133706000101040571_0825133706000101040575_0825133706000101040578_0825133706000101040581_0825133706000101040620_0825133706000101040622_0825133706000101040624_0825133706000101040774".length());
    }

}
