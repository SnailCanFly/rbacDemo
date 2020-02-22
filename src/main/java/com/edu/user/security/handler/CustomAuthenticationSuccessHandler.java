package com.edu.user.security.handler;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * Decode the value using Base64.
     * @param base64Value the Base64 String to decode
     * @return the Base64 decoded value
     * @since 1.2.2用于手动解码sessionId
     */
    private String base64Decode(String base64Value) {
        try {
            byte[] decodedCookieBytes = Base64.getDecoder().decode(base64Value);
            return new String(decodedCookieBytes);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功,{}", authentication);
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String sessionId= details.getSessionId();
        System.out.println(sessionId);
//       Session session = (Session) getSessionInformation.
        System.out.println(authentication);
        if("[ROLE_USER]".equals(authentication.getAuthorities().toString())) {
        	  response.sendRedirect("/");
        }else if("[ROLE_ADMIN]".equals(authentication.getAuthorities().toString())
        		|| "[ROLE_TEACHER]".equals(authentication.getAuthorities().toString())
        		){
        response.sendRedirect("/home");
        }else {
        	System.out.println("发现错误");
        }
    }
}
