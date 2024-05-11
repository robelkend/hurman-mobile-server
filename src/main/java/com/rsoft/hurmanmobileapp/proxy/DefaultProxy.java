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

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "api-gateway-server", configuration = FeignSimpleEncoderConfig.class)
public abstract interface DefaultProxy {

    @RequestMapping(value = "/hurman-server/PersistAlertMessages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public ArrayList<AlertMessage> persistAlertMessages(RequestAttributes requestAttributes);


    @RequestMapping(value = "/hurman-server/GetNotificationAlerts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public List<AlertMessage> getNotificationAlerts(RequestAttributes requestAttributes);

    @RequestMapping(value = "/hurman-server/GetTypeCongeEmploye", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public TypeConge getTypeCongeEmploye(RequestAttributes requestAttributes);


    @RequestMapping(value = "/hurman-server/PersistConges", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public ArrayList<Conge> persistConges(RequestAttributes requestAttributes);



    @RequestMapping(value = "/hurman-server/PersistAbsences", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public ArrayList<Absence> persistAbsences(RequestAttributes requestAttributes);

    @RequestMapping(value = "/hurman-server/GetPosteEmployes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public List<PosteEmploye> getPosteEmployes(RequestAttributes requestAttributes);

    @RequestMapping(value = "/hurman-server/GetSalaireEmployes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    public ArrayList<SalaireEmploye> getSalaireEmployes(RequestAttributes requestAttributes);

    @RequestMapping(value = "/hurman-server/GetPostes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    public List<Poste> getPostes(RequestAttributes requestAttributes);

    @RequestMapping(value = "/hurman-server/GetPrets", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public List<Pret> getPrets(RequestAttributes requestAttributes);
}
