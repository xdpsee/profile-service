package com.jerry.demo.usercenter.api.enums;

@SuppressWarnings("unused")
public enum AuthType {
    USERNAME(0, "用户名"),
    MOBILE(1, "手机"),
    EMAIL(2, "电子邮件"),
    QQ(3, "QQ帐号"),
    WECHAT(4, "微信帐号"),
    WEIBO(5, "微博帐号")
    ;

    public final int code;
    public final String comment;

    AuthType(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}
