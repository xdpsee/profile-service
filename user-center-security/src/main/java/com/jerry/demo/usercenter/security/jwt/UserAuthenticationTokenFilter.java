package com.jerry.demo.usercenter.security.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("SpringJavaAutowiringInspection")
public abstract class UserAuthenticationTokenFilter extends OncePerRequestFilter {

    // Http request header
    private static final String AUTHORIZATION = "Authorization";
    // Authorization value head
    private static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
//        String authHeader = request.getHeader(AUTHORIZATION);
//        if (authHeader != null && authHeader.startsWith(BEARER)) {
//            final String authToken = authHeader.substring(BEARER.length()); // The part after "Bearer "
//            String principal = userTokenUtils.getPrincipalFromToken(authToken);
//
//            logger.info("Authentication checking principal: " + principal);
//
//            if (principal != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                final String cachedToken = jwtTokenCache.get(principal);
//                if (cachedToken != null && cachedToken.equals(authToken)) {
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                            principal, null, userTokenUtils.getAuthoritiesFromToken(cachedToken));
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    logger.info("Authentication principal: " + principal + " authenticated");
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        }
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UserAuthenticationToken authentication = getAuthenticationToken(request);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    protected abstract UserAuthenticationToken getAuthenticationToken(final HttpServletRequest request);
}

