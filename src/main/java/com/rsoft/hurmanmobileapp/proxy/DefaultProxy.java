/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rsoft.hurmanmobileapp.proxy;

import com.rsoft.hurmanmobileapp.config.FeignSimpleEncoderConfig;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.dto.AuthenticationDto;
import com.rsoft.lib.model.*;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "api-gateway-server", configuration = FeignSimpleEncoderConfig.class, url = "${api.gateway.url}")
public abstract interface DefaultProxy {

    @RequestMapping(value = "/hurman-server/PersistMobileAlertMessages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
    public List<SalaireEmploye> getSalaireEmployes(RequestAttributes requestAttributes);

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

    @RequestMapping(value = "/hurman-server/GetPayrollsEmployee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public List<PayrollDt> getPayrollsEmployee(RequestAttributes requestAttributes);

    @RequestMapping(value = "/hurman-server/GetConges", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public List<Conge> getConges(RequestAttributes requestAttributes);

    @RequestMapping(value = "/hurman-server/GetHorairesEmploye", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    public ArrayList<HoraireDt> getHorairesEmploye(RequestAttributes requestAttributes);

    @RequestMapping(value = "/hurman-server/GetTravailNocturnes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public List<TravailNocturne> getTravailNocturnes(RequestAttributes requestAttributes);


    @RequestMapping(value = "/hurman-server/GetPresences", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    public List<Presence> getPresences(RequestAttributes requestAttributes);

    @RequestMapping(value = "/rsoft-security/Authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    public abstract AuthenticationDto authenticate(@RequestBody RequestAttributes requestAttribute);

    @RequestMapping(value = "/rsoft-security/LoadUserProfiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    public abstract UserProfile loadUserProfiles(@RequestBody RequestAttributes requestAttribute);


    @RequestMapping(value = "/rsoft-security/Logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    public abstract AuthenticationDto logout(@RequestBody RequestAttributes requestAttribute);

    @RequestMapping(value = "/rsoft-security/PersistUserProfiles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    public ArrayList<UserProfile> persistUserProfiles(RequestAttributes requestAttributes);

    @RequestMapping(value = "/hurman-server/GetEmployes", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    public ArrayList<Employe> getEmployes(@RequestBody(required = false) RequestAttributes<Employe> requestAttribute);

    @RequestMapping(value = "/hurman-server/GetTaches", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    public ArrayList<Taches> getTaches(RequestAttributes requestAttributes);
}
