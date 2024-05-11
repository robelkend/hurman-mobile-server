package com.rsoft.hurmanmobileapp.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("utilisateur", "mdp");
    }

//    @Bean
//    @Scope("prototype")
//    @ConditionalOnMissingBean
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
//    @Configuration
//    @ConditionalOnClass({JacksonDecoder.class, JacksonEncoder.class})
//    protected static class JacksonFeignConfiguration {
//
//        @Bean
//        @ConditionalOnMissingBean
//        public Decoder feignDecoder() {
//            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx1");
//            return new JacksonDecoder();
//        }
//
//        @Bean
//        @ConditionalOnMissingBean
//        public Encoder feignEncoder() {
//            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx2");
//            return new JacksonEncoder();
//        }
//    }
}
