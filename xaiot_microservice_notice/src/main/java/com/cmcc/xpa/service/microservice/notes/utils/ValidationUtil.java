package com.cmcc.xpa.service.microservice.notes.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述  java后台表单验证工具类
 *
 * @ClassName ValidationUtil
 * @Author wzf
 * @DATE 2018/10/27 15:21
 * @VerSion 1.0
 */
public class ValidationUtil {

    //手机号
    public static String mobile = "^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";

    //不允许为空
    public static String blank = ".*[^ ].*";

    //邮件
    public static String email = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([0-9a-z_\\-]*)(\\.(com|cn|inc|org|cc|edu|de)*){1,2}([a-z]{2})?$";

    //QQ，允许为空
    public static String tencentQQAllowNull = "((^$)|([1-9][0-9]{4,11}))";

    //QQ
    public static String tencentQQ = "[1-9][0-9]{4,11}";

    //网址，允许为空
    public static String urlAllowNull = "((^$)|(http|https)+://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?)";

    //网址
    public static String url = "(http|https)+://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    //微信，允许为空
    public static String weixinAllowNull = "((^$)|(^[A-Za-z0-9]\\w{3,60}+$))";

    //微信
    public static String weixin = "^[A-Za-z0-9]\\w{3,60}+$";

    //正整数
    public static String PositiveInteger = "^[0-9]*[1-9][0-9]*$";

    //年份正则表达式
    public static String YearReg = "([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})";

    //正整数或非负数
    public static String NonnegativeNumber = "^(\\+?[1-9][0-9]*$)|(([0-9]+\\.[0-9]*[0-9][0-9]*))";

    //不允许有任何空白
    public static String NoAnyEmpty = "^[\\S]{5,30}$";

    //日期
    public static String DateReg = "^$|^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";

    //字符串长度 1到200  ValidationUtil.validata("xxxxx",StringLength);
    public static String StringLength = "/^\\S{1,100}$/";
    /*
     * @Description 身份证号码验证
     *
     * @Author wzf
     * @Date 2018/10/31 10:59
     * @Param [text]
     * @return boolean
     * 示例:
     *  Map valiTitleMap = ValidationUtil.isIdCardNo(xxxxx.getIdCardNo());//对身份证进行验证
     *       if(!(boolean)valiTitleMap.get("isOK")){
     *           return valiTitleMap;
     *       }
     **/
    public  static Map isIdCardNo(String cardId){
        Map error=new HashMap();
        if (cardId.length() == 15 || cardId.length() == 18) {
            if (!ValidationUtil.cardCodeVerifySimple(cardId)) {
                error.put("msg", "15位或18位身份证号码不正确");
                error.put("code","500");
                error.put("isOK",false);
                return error;
            } else {
                if (cardId.length() == 18 && !ValidationUtil.cardCodeVerify(cardId)) {
                    error.put("msg", "18位身份证号码不符合国家规范");
                    error.put("code","500");
                    error.put("isOK",false);
                    return error;
                }
            }
        } else {
            error.put("msg", "身份证号码长度必须等于15或18位");
            error.put("code","500");
            error.put("isOK",false);
            return error;
        }
        error.put("isOK",true);
        return error;
    }

    //是否是Double数字类型
    public static boolean isDouble(String value) {
        if (value == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
        return pattern.matcher(value).matches();
    }

    //是否是Long类型
    public static boolean isValidLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    //是否是Int类型
    public static boolean isValidInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public static boolean mobile(String str) {
        return validata(str, mobile);
    }

    public static boolean blank(String str) {
        return validata(str, blank);
    }

    /****
     * 不为空，限制长度范围
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static boolean blankforlenth(String str, int start, int end) {
        String reg = "\\S{" + start + "," + end + "}";
        return validata(str, reg);
    }

    /***
     * 非负数
     * @param str
     * @return
     */
    public static boolean nonnegativeNumber(String str) {
        return validata(str, NonnegativeNumber);
    }

    /****
     * 不允许有任何空白
     * @param str
     * @return
     */
    public static boolean noAnyEmpty(String str) {
        return validata(str, NoAnyEmpty);
    }

    /***
     * 日期判断
     * @param str
     * @return
     */
    public static boolean isDateReg(String str) {
        return validata(str, DateReg);
    }

    public static boolean eimail(String str) {
        return validata(str, email);
    }

    public static boolean tencentQQAllNull(String str) {
        return validata(str, tencentQQAllowNull);
    }

    public static boolean tencentQQ(String str) {
        return validata(str, tencentQQ);
    }

    public static boolean webURLAllowNull(String str) {
        return validata(str, urlAllowNull);
    }

    public static boolean webURL(String str) {
        return validata(str, url);
    }

    public static boolean weixinAllowNull(String str) {
        return validata(str, weixinAllowNull);
    }

    public static boolean weixin(String str) {
        return validata(str, weixin);
    }

    public static boolean positiveInteger(String str) {
        return validata(str, PositiveInteger);
    }

    public static boolean isyear(String str) {
        return validata(str, YearReg);
    }

    public static boolean validata(String str, String type) {
        Pattern p = Pattern.compile(type);//将给定的正则表达式编译到模式中。

        Matcher m = p.matcher(str);//创建匹配给定输入与此模式的匹配器。
        return m.matches();//尝试将整个区域与模式匹配。
    }

    /*
     * @Description 对String 字符串长度进行验证并返回提示信息
     *
     * @Author wzf
     * @Date 2018/10/27 16:14
     * @Param
     * @return ValidationUtil.valiStrLength("xxxx",10000,"字段长度不能超过10000请核对后提交");
     * 示例:
     *    Map valiTitleMap = ValidationUtil.valiStrLength(xxxxx.getName(),30, "名称输入不能超过30位请核对后访问提交");
     *       if(!(boolean)valiTitleMap.get("isOK")){
     *           return valiTitleMap;
     *       }
     **/
    public static Map valiStrLength(String str, Integer strLength, String msg) {
        Map map = new HashMap();
        if (null != str && !"".equals(str)) {
            if (str.length() > strLength) {
                map.put("code", "500");
                map.put("msg", msg);
                map.put("isOK",false);
                return map;
            }
            map.put("isOK",true);
            return map;
        }
        map.put("isOK",true);
        return map;
    }
    /*
     * @Description 正则校验身份证是否符合第一代第二代标准
     *
     * @Author wzf
     * @Date 2018/10/31 11:47
     * @Param [cardcode]
     * @return boolean
     **/
    public static boolean cardCodeVerifySimple(String cardcode) {
        //第一代身份证正则表达式(15位)
        String isIDCard1 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        //第二代身份证正则表达式(18位)
        String isIDCard2 ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$";

        //验证身份证
        if (cardcode.matches(isIDCard1) || cardcode.matches(isIDCard2)) {
            return true;
        }
        return false;
    }
    /*
     * @Description  验证第二代身份证是否符合国家规范
     *
     * @Author wzf
     * @Date 2018/10/31 11:47
     * @Param [cardcode]
     * @return boolean
     **/
    public static boolean cardCodeVerify(String cardcode) {
        int i = 0;
        String r = "error";
        String lastnumber = "";

        i += Integer.parseInt(cardcode.substring(0, 1)) * 7;
        i += Integer.parseInt(cardcode.substring(1, 2)) * 9;
        i += Integer.parseInt(cardcode.substring(2, 3)) * 10;
        i += Integer.parseInt(cardcode.substring(3, 4)) * 5;
        i += Integer.parseInt(cardcode.substring(4, 5)) * 8;
        i += Integer.parseInt(cardcode.substring(5, 6)) * 4;
        i += Integer.parseInt(cardcode.substring(6, 7)) * 2;
        i += Integer.parseInt(cardcode.substring(7, 8)) * 1;
        i += Integer.parseInt(cardcode.substring(8, 9)) * 6;
        i += Integer.parseInt(cardcode.substring(9, 10)) * 3;
        i += Integer.parseInt(cardcode.substring(10,11)) * 7;
        i += Integer.parseInt(cardcode.substring(11,12)) * 9;
        i += Integer.parseInt(cardcode.substring(12,13)) * 10;
        i += Integer.parseInt(cardcode.substring(13,14)) * 5;
        i += Integer.parseInt(cardcode.substring(14,15)) * 8;
        i += Integer.parseInt(cardcode.substring(15,16)) * 4;
        i += Integer.parseInt(cardcode.substring(16,17)) * 2;
        i = i % 11;
        lastnumber =cardcode.substring(17,18);
        if (i == 0) {
            r = "1";
        }
        if (i == 1) {
            r = "0";
        }
        if (i == 2) {
            r = "x";
        }
        if (i == 3) {
            r = "9";
        }
        if (i == 4) {
            r = "8";
        }
        if (i == 5) {
            r = "7";
        }
        if (i == 6) {
            r = "6";
        }
        if (i == 7) {
            r = "5";
        }
        if (i == 8) {
            r = "4";
        }
        if (i == 9) {
            r = "3";
        }
        if (i == 10) {
            r = "2";
        }
        if (r.equals(lastnumber.toLowerCase())) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        String xxx = "xxxxx";
        System.out.println(blankforlenth(xxx,5, 10));
        String xq="1";
        boolean blank = blank(xq);
        System.out.println(blank);
    }
}