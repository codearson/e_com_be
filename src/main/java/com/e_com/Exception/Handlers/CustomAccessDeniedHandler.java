package com.e_com.Exception.Handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.e_com.Constants.ApplicationConstants;
import com.e_com.Constants.ApplicationMessageConstants;
import com.e_com.Dto.ResponseDto;
import com.e_com.Service.Utils.ServiceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    private ServiceUtil serviceUtil;


        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response,
                           AccessDeniedException accessDeniedException) throws IOException, ServletException {
            ResponseDto responseDto=null;
            ResponseDto error = serviceUtil.getExceptionServiceResponse(accessDeniedException);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(error));
        }
    }

