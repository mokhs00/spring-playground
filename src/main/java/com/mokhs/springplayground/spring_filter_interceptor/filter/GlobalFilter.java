package com.mokhs.springplayground.spring_filter_interceptor.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/api/temp/*")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 전처리
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(httpServletRequest, httpServletResponse);


        String url = httpServletRequest.getRequestURI();
//
//        BufferedReader br = httpServletRequest.getReader();
//
//        br.lines().forEach(line -> {
//            log.info("url : {}, line : {}", url, line);
//        });


        // 후처리
        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        log.info("requsetUrl : {}, requestBody : {}", url, reqContent);


        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse();

        log.info("response status : {}, responseBody : {}", httpStatus, resContent);


    }
}
