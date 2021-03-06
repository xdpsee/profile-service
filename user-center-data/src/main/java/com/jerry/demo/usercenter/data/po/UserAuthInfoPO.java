package com.jerry.demo.usercenter.data.po;

import com.jerry.demo.usercenter.api.enums.AuthType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthInfoPO extends BasePO {
    /**
     * 认证类型
     */
    private AuthType type;
    /**
     * 用户的标识
     */
    private String identifier;
    /**
     * 凭据, 可能是密码hash,access_token
     */
    private String credential;
    /**
     * 绑定的用户ID
     */
    private Long userId = 0L;
    /**
     * 是否已验证
     */
    private boolean verified;

}



