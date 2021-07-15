package com.mokhs.springplayground.IoC;

import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class UrlEncoder implements IEncoder {

    public String encode(String message) {
        return URLEncoder.encode(message, StandardCharsets.UTF_8);
    }
}
