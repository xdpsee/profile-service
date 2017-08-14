package com.jerry.demo.usercenter.xauth.controller.social;


import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;

public class QQOAuthServiceImpl extends OAuth20Service {

    public QQOAuthServiceImpl(DefaultApi20 api, OAuthConfig config) {
        super(api, config);
    }


    @Override
    public void signRequest(OAuth2AccessToken accessToken, OAuthRequest request) {
        request.addQuerystringParameter("dataType", "json");
    }



}