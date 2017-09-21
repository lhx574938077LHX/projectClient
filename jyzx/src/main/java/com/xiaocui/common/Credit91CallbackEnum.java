package com.xiaocui.common;

public enum Credit91CallbackEnum {
    EXCEPTION((short)0, "系统发生异常"),
    OK((short)1, "正常"),
    CONN_KEY_ERROR((short)2, "连接密钥错误"),
    NO_PORTAL_PERM((short)3, "没有开通此接口权限"),
    NO_COMPANY_INFO((short)4, "公司信息不可识别");

    private final Short value;

    private final String desc;
    

    Credit91CallbackEnum(Short value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Short getValue() {
        return value;
    }

    public static Credit91CallbackEnum parseServiceName(Short value) {
        Credit91CallbackEnum[] values = Credit91CallbackEnum.values();
        for (Credit91CallbackEnum credit91CallbackEnum : values){
            if (credit91CallbackEnum.getValue().equals(value)){
                return credit91CallbackEnum;
            }
        }
        return Credit91CallbackEnum.EXCEPTION;
    }
    
    public String getDescription()
    {
        return this.desc;
    }
	
	
}
