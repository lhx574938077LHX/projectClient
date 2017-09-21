package com.xiaocui.common;

import java.io.Serializable;

public class Pkg1001 implements Serializable {
    private static final long serialVersionUID = -459546485281101782L;

    private String realName;    //姓名
    private String idCard;        //身份证号

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
        if (this.idCard != null) {
            this.idCard = this.idCard.trim();
        }
    }
}
