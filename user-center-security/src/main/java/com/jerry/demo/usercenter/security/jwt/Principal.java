package com.jerry.demo.usercenter.security.jwt;

import com.jerry.demo.usercenter.api.enums.AuthType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class Principal implements Serializable {

    @Getter
    private AuthType authType;
    @Getter
    private String identifier;

    @Override
    public String toString() {
        return String.format("%s:%s", authType, identifier);
    }
}



