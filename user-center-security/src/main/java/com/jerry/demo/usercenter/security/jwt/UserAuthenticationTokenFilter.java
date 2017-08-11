package com.jerry.demo.usercenter.security.jwt;

import com.jerry.demo.usercenter.api.dto.UserAuthInfo;
import com.jerry.demo.usercenter.api.services.UserAuthInfoService;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class UserAuthenticationTokenFilter extends OncePerRequestFilter {

    // Http request header
    private static final String AUTHORIZATION = "Authorization";
    // Authorization value head
    private static final String BEARER = "Bearer ";

    @Setter
    private UserAuthInfoService userAuthInfoService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserAuthenticationToken authentication = getAuthenticationToken(request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    private UserAuthenticationToken getAuthenticationToken(final HttpServletRequest request) {

        final String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            return null;
        }

        final String jwtToken = authHeader.substring(BEARER.length() + 1); // The part after "Bearer "

        final Principal principal = UserTokenUtils.getPrincipal(jwtToken);
        final Date expireDate = UserTokenUtils.getExpiresDate(jwtToken);
        if (null == expireDate || null == principal) {
            throw new IllegalAuthTokenException("Token非法");
        }

        if (expireDate.before(new Date())) {
            throw new AuthTokenExpiresException("Token过期");
        }

        UserAuthenticationToken authenticationToken = null;
        UserAuthInfo authInfo = userAuthInfoService.getUserAuthInfo(principal.getAuthType(), principal.getIdentifier());
        if (authInfo != null) {
            authenticationToken = new UserAuthenticationToken(authInfo.getType()
                    , authInfo.getIdentifier()
                    , null
                    , authInfo.getAuthorities().stream()
                    .map(e -> new SimpleGrantedAuthority(e.getRole()))
                    .collect(Collectors.toList()));
        }

        return authenticationToken;
    }
}


