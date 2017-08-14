package com.jerry.demo.usercenter.xauth.controller.social;


import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.OAuth2AccessTokenExtractor;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthConfig;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class QQApi extends DefaultApi20 {

    private static final String AUTHORIZE_URL = "https://graph.qq.com/oauth2.0/authorize?client_id=%s&redirect_uri=%s";

    protected QQApi() {
    }

    private static class InstanceHolder {
        private static final QQApi INSTANCE = new QQApi();
    }

    public static QQApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://graph.qq.com/oauth2.0/token";
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OAuth2AccessTokenExtractor.instance();
    }

    @Override
    public OAuth20Service createService(OAuthConfig config) {
        return new QQOAuthServiceImpl(this, config);
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://graph.qq.com/oauth2.0/authorize";
    }
}