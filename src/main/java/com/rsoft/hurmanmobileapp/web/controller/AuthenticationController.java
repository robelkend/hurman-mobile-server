package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.AuthenticationReponse;
import com.rsoft.hurmanmobileapp.dto.AuthenticationRequest;
import com.rsoft.lib.Utilities;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping(value = "/v1/mobile")
public class AuthenticationController {
    @RequestMapping(value = "/v1/mobile/Authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationReponse authenticate(AuthenticationRequest authenticationRequest) throws JAXBException {
        int r = new Random().nextInt(12);
        AuthenticationReponse reponse = new AuthenticationReponse();
        if (r < 4) {
            reponse.setErrorCode("000");
            reponse.setCurrentAccessTime(new Date());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR, -1 * r);
            reponse.setLastAccessTime(c.getTime());
            reponse.setEnterpriseName("Tropical S.A");
            reponse.setFirstName("Jean Robelkend");
            reponse.setLastName("Charles");
            reponse.setLanguage("en");
        } else if (r < 6) {
            reponse.setErrorCode("000");
            reponse.setCurrentAccessTime(new Date());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR, -1 * r);
            reponse.setLastAccessTime(c.getTime());
            reponse.setEnterpriseName("Plastoum Titano");
            reponse.setFirstName("Mario");
            reponse.setLastName("Polito");
            reponse.setLanguage("fr");
        } else if (r < 8) {
            reponse.setErrorCode("000");
            reponse.setCurrentAccessTime(new Date());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR, -1 * r);
            reponse.setLastAccessTime(c.getTime());
            reponse.setEnterpriseName("RSoft System");
            reponse.setFirstName("Miralo");
            reponse.setLastName("Pierre");
            reponse.setLanguage("es");
        } else {
            reponse.setErrorCode("000");
            reponse.setCurrentAccessTime(new Date());
            Calendar c = Calendar.getInstance();
            c.add(Calendar.HOUR, -1 * r);
            reponse.setLastAccessTime(c.getTime());
            reponse.setEnterpriseName("Plastech Solution");
            reponse.setFirstName("Jean Senile");
            reponse.setLastName("Pierre");
            reponse.setLanguage("cr");
        }
        return reponse;
    }
}