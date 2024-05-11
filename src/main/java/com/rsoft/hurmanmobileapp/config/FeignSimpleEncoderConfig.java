/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.hurmanmobileapp.config;

import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author ROBELKEND
 */
public class FeignSimpleEncoderConfig {

    @Bean
    public Encoder encoder() {
        return new FeignSpringFormEncoder();
    }

    @Bean
    public Decoder decoder() {
        return new GsonDecoder();
    }
}
