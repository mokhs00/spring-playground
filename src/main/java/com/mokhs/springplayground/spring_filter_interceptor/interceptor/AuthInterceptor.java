package com.mokhs.springplayground.spring_filter_interceptor.interceptor;

import com.mokhs.springplayground.spring_filter_interceptor.annotation.Auth;
import com.mokhs.springplayground.spring_filter_interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // filter 에서 ContentCachingRequestWrapper request 를 넘겨주면 아래와 같이 형변환 가능 \
        // 작동 순서에 주의!
        // (ContentCachingRequestWrapper) request
        String url = request.getRequestURI();


        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        if (hasAnnotation) {
            String query = uri.getQuery();

            log.info("query : {} ", query);
            if ("name=mokhs".equals(query)) {
                return true;
            }

            throw new AuthException("mohks와 name이 일치하지 않음");
        }

        log.info("request url : {}", url);

        return false;
    }

    private boolean checkAnnotation(Object handler, Class clazz) {

        // resource js, html,
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)) {

            // Auth annotation check
            return true;
        }


        return false;
    }
}
