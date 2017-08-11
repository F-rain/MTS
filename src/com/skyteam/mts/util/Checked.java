package com.skyteam.mts.util;

import java.util.regex.Pattern;

/**
 * 检查各种字符串
 * Created by WWW on 2016/10/18.
 */
public class Checked {
    public static boolean checkAndroidID(String meid){
        return Pattern.matches("^[0-9A-F]{32}$", meid);
    }
    public static boolean checkTel(String tel){
        return Pattern.matches("^1[34578][0-9]{9}$", tel);
    }
    public static boolean checkGrade(String grade){
        return Pattern.matches("^[123]$", grade);
    }
    public static boolean checkNumber(String number){
        return Pattern.matches("^[+-]?[0-9]+$", number);
    }
}
