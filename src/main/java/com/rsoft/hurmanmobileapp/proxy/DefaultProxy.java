/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.hurmanmobileapp.proxy;

import com.rsoft.hurmanmobileapp.config.FeignSimpleEncoderConfig;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.model.*;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "api-gateway-server", configuration = FeignSimpleEncoderConfig.class)
public abstract interface DefaultProxy {

    @RequestMapping(value = "/hurman-server/GetAlertMessages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public List<AlertMessage> getAlertMessages(RequestAttributes requestAttributes);
}
