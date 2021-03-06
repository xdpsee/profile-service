package com.jerry.demo.usercenter.api.enums;

import java.util.Arrays;

@SuppressWarnings("unused")
public enum AuthType {
    QQ(1, "QQ帐号"),
    WECHAT(2, "微信帐号"),
    WEIBO(3, "微博帐号"),
    USERNAME(4, "用户名"),
    MOBILE(5, "手机"),
    EMAIL(6, "电子邮件"),;

    public final int code;
    public final String comment;
    AuthType(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public static AuthType valueOf(int code) {
        return Arrays.stream(values())
                .filter(e -> e.code == code)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
