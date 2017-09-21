package com.xiaocui.platform.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import com.xiaocui.platform.common.StringUtils;

public class IDCardUtils {

    // 加权因字数
    private static final int[] WI = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 代码
    private static final char[] CODE = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    public static boolean isIdCard(String idCard) {
        if (StringUtils.isEmpty(idCard))
            return false;
        Pattern pattern = Pattern.compile("^(^\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$");
        return pattern.matcher(idCard).matches();
    }

    /**
     * 检查身份证是否合法
     *
     * @param card 身份证号码
     * @return 是/否
     */
    public static boolean verifi(String card) {
        if (StringUtils.isEmpty(card))
            return false;
        if (card.length() == 15 && Pattern.matches("^\\d{15}$", card)) {
            card = card15$18(card);
        }
        if (card.length() == 18 && isDate(card)) {
            card = card.toUpperCase();
            if (Pattern.matches("^\\d{17}[xX]|\\d{18}$", card)) {
                char[] chars = card.toCharArray();
                int si = 0;
                for (int i = 0; i < 17; i++) {
                    si += (chars[i] - '0') * WI[i];
                }
                return chars[17] == CODE[si % 11];
            }
            return false;
        }
        return false;
    }

    private static boolean isDate(String card) {
        if (StringUtils.isEmpty(card))
            return false;
        String y = card.substring(6, 10);
        String m = card.substring(10, 12);
        String d = card.substring(12, 14);
        String date = y + "-" + m + "-" + d;
        Pattern p = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        return p.matcher(date).matches();
    }

    /**
     * 身份证15位转18位
     *
     * @param $15 15位身份证号码
     * @return 18位身份证号码
     */
    public static String card15$18(String $15) {
        try {
            if ($15.length() == 15) {
                int si = 0;
                StringBuffer $18 = new StringBuffer();
                $18.append($15.substring(0, 6));
                $18.append("19");
                $18.append($15.substring(6, 15));
                for (int i = 0; i < 17; i++) {
                    si += ($18.charAt(i) - '0') * WI[i];
                }
                $18.append(CODE[si % 11]);
                return $18.toString();
            }
        } catch (Exception ex) {
            return null;
        }
        return $15;
    }

    //获取性别
    public static String getsex(String idCard) {

        String code = idCard.substring(idCard.length() - 2, idCard.length() - 1);
        int codeNum = Integer.parseInt(code);
        if (codeNum % 2 != 0) {
            return "男";
        }
        return "女";

    }

    // 获取出生日期
    public static String getBirthday(String idCard) {
        if (StringUtils.isEmpty(idCard))
            return "";
        String birthday = idCard.substring(6, 14);
        LocalDate localDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return localDate.toString();
    }

    public static int getAge(String idCard) {
        if (StringUtils.isEmpty(idCard))
            return 0;
        String birthday = idCard.substring(6, 14);
        LocalDate localDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyyMMdd"));
        return LocalDate.now().getYear() - localDate.getYear();
    }

    public static String removeSensitiveInfo(String idCard) {
        if (idCard == null || (idCard.trim().length() != 15 && idCard.trim().length() != 18)) {
            return idCard;
        }
        idCard = idCard.trim();
        return idCard.substring(0, 10) + "****" + idCard.substring(14);
    }
}