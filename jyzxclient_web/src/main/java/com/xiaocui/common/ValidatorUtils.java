package com.xiaocui.common;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidatorUtils {
	
	
	public static void main(String[] args) {
		System.out.println(isChinese("藻·本人的个人信息"));
	}
	
    /**
     * 判断传入参数是否为字母与数字的组合字符串,而不是单一的字母或者数字
     *
     * @param
     * @return
     */
    public static boolean isPassword(String pwd) {
        if (StringUtils.isEmpty(pwd))
            return false;
        if (isInteger(pwd)) {//是否全部为数字
            return false;//全部为数字
        } else {
            if (isLettersOnly(pwd)) {
                return false;//全部为字母
            } else {
                if (isAlphanumeric(pwd)) {
                    return true;//正常数据
                } else {
                    return false;//包含了非法数据
                }
            }
        }
    }

    /**
     * 判断字母、数字（适用于密码校验）.
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false capital
     */
    public static boolean isAlphanumeric(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 必须为字母.
     *
     * @param str 传入的字符串
     * @return true or false .
     */
    public static boolean isLettersOnly(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为浮点数，包括double和float
     *
     * @param str 传入的字符串
     * @return 是浮点数返回true, 否则返回false
     */
    public static boolean isDouble(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为有且只有小数点后面包含两位的数
     *
     * @param str 传入的字符串
     * @return 是浮点数返回true, 否则返回false
     */
    public static boolean isDoubleAnd2decimals(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))([.](\\d){1,2})?$");
        return pattern.matcher(str).matches();
    }

    /**
     * 验证一个字符串是否完全由纯数字组成的字符串，当字符串为空时也返回false.
     *
     * @param str 要判断的字符串 .
     * @return true or false .
     * @author WuShuicheng .
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        } else {
            return str.matches("\\d*");
        }
    }

    /**
     * 判断输入的字符串是否符合Email样式.
     *
     * @param str 传入的字符串
     * @return 是Email样式返回true, 否则返回false
     */
    public static boolean isEmail(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断输入的字符串是否为纯汉字
     *
     * @param str 传入的字符窜
     * @return 如果是纯汉字返回true, 否则返回false
     */
    public static boolean isChinese(String str) {
        if (StringUtils.isEmpty(str))
            return false;
        Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断是否为质数
     *
     * @param x
     * @return
     */
    public static boolean isPrime(int x) {
        if (x <= 7) {
            if (x == 2 || x == 3 || x == 5 || x == 7)
                return true;
        }
        int c = 7;
        if (x % 2 == 0)
            return false;
        if (x % 3 == 0)
            return false;
        if (x % 5 == 0)
            return false;
        int end = (int) Math.sqrt(x);
        while (c <= end) {
            if (x % c == 0) {
                return false;
            }
            c += 4;
            if (x % c == 0) {
                return false;
            }
            c += 2;
            if (x % c == 0) {
                return false;
            }
            c += 4;
            if (x % c == 0) {
                return false;
            }
            c += 2;
            if (x % c == 0) {
                return false;
            }
            c += 4;
            if (x % c == 0) {
                return false;
            }
            c += 6;
            if (x % c == 0) {
                return false;
            }
            c += 2;
            if (x % c == 0) {
                return false;
            }
            c += 6;
        }
        return true;
    }

    /**
     * 判断是不是合法手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile))
            return false;
        Pattern pattern = Pattern.compile("^1[3|4|5|7|8][0-9]{9}$");
        return pattern.matcher(mobile).matches();

    }

    /**
     * 是否为座机 (010-66571346)
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        if (StringUtils.isEmpty(phone))
            return false;
        Pattern pattern = Pattern.compile("^0[0-9]{2,3}[-|－][0-9]{7,8}([-|－][0-9]{1,4})?$");
        return pattern.matcher(phone).matches();
    }

    /**
     * 是否为邮编
     *
     * @param phone
     * @return
     */
    public static boolean isPostCode(String post) {
        if (StringUtils.isEmpty(post))
            return false;
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        return pattern.matcher(post).matches();
    }

    /**
     * 是否为日期格式：yyyy-MM-dd
     *
     * @return
     */
    public static boolean isDate(String dateStr) {
        if (StringUtils.isEmpty(dateStr))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return date != null;
    }

    /**
     * 是否为时间格式：hh:mm:ss
     *
     * @param timeStr
     * @return
     */
    public static boolean isTime(String timeStr) {
        if (StringUtils.isEmpty(timeStr))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        Date date = null;
        try {
            date = df.parse(timeStr);
        } catch (ParseException e) {
            return false;
        }
        return date != null;
    }

    /**
     * 是否为日期时间格式：yyyy-MM-dd hh:mm:ss or yyyy-MM-dd hh:mm
     *
     * @param dateTime
     * @return
     */
    public static boolean isDateTime(String dateTime) {
        if (StringUtils.isEmpty(dateTime))
            return false;
        int first = dateTime.indexOf(":");
        int last = dateTime.lastIndexOf(":");
        if (first == -1) {
            return false;
        }
        SimpleDateFormat df = null;
        if (first == last) {
            df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        } else {
            df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        }
        Date date = null;
        try {
            date = df.parse(dateTime);
        } catch (ParseException e) {
            return false;
        }
        return date == null;
    }

    /**
     * 判断参数是否非NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
     *
     * @param obj
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String && obj.toString().trim().length() == 0) {
            return true;
        }
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isUsername(String param) {
        if (StringUtils.isEmpty(param))
            return false;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{4,30}$");
        return pattern.matcher(param).matches();
    }

    public static boolean isOrderNum(String param) {
        if (StringUtils.isEmpty(param))
            return false;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{10,50}$");
        return pattern.matcher(param).matches();
    }
}
