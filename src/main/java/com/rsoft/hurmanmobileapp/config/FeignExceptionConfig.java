package com.rsoft.hurmanmobileapp.config;

import com.rsoft.hurmanmobileapp.exception.CustomErrorDecoder;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.form.FormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FeignExceptionConfig {

    public static final int TIMEOUT_SEGONDS = 600 * 1000;

    @Bean
    public CustomErrorDecoder mCustomErrorDecoder() {
        return new CustomErrorDecoder();
    }

    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(TIMEOUT_SEGONDS, TIMEOUT_SEGONDS);
    }

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder().encoder(new FormEncoder());
    }
}
