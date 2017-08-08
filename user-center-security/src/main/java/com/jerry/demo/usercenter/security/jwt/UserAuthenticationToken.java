package com.jerry.demo.usercenter.security.jwt;

import com.jerry.demo.usercenter.api.enums.AuthType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserAuthenticationToken extends AbstractAuthenticationToken {
    @Getter
    private AuthType type;
    @Getter
    private String identifier;
    @Getter
    private String credentials;
    @Getter @Setter
    private Long userId;

    public UserAuthenticationToken(AuthType type, String identifier, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);

        this.type = type;
        this.identifier = identifier;
        this.credentials = credentials;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return String.format("%s:%s", type, identifier);
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

}
