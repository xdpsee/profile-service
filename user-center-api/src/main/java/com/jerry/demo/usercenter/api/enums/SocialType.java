package com.jerry.demo.usercenter.api.enums;

import java.util.Arrays;

public enum SocialType {
    QQ(1, "QQ帐号"),
    WECHAT(2, "微信帐号"),
    WEIBO(3, "微博帐号");

    public final int code;
    public final String comment;

    SocialType(int code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public static SocialType valueOf(int code) {
        return Arrays.stream(values())
                .filter(e -> e.code == code)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
