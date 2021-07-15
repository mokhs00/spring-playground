package com.mokhs.springplayground;

import com.mokhs.springplayground.IoC.ApplicationContextProvider;
import com.mokhs.springplayground.IoC.Base64Encoder;
import com.mokhs.springplayground.IoC.Encoder;
import com.mokhs.springplayground.IoC.UrlEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlaygroundApplication.class, args);
        ApplicationContext context = ApplicationContextProvider.getContext();

        String message = "mokhs kk";

        // class type IoC

//        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
//        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
//
//        Encoder encoder = new Encoder(base64Encoder);
//
//        String encoded = encoder.encode(message);
//        System.out.println(encoded);
//
//        encoder.setiEncoder(urlEncoder);
//
//        encoded = encoder.encode(message);
//        System.out.println(encoded);

        // interface type IoC

//        Encoder encoder = context.getBean(Encoder.class);
//
//        String encoded = encoder.encode(message);
//
//        System.out.println(encoded);


        // get same interface bean
        // 매개변수 주의
        Encoder encoder = context.getBean("urlEncode", Encoder.class);

//        Encoder encoder = context.getBean("base64Encode", Encoder.class);

        String encode = encoder.encode(message);
        System.out.println(encode);
    }

}


@Configuration
class AppConfig {

    @Bean("base64Encode")
    public Encoder encoder(Base64Encoder base64Encoder) {
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }

}
