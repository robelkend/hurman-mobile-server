package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.AuthenticationReponse;
import com.rsoft.hurmanmobileapp.dto.AuthenticationRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.Date;

@RestController
public class AuthenticationController {
    @RequestMapping(value = "/Authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationReponse authenticate(AuthenticationRequest authenticationRequest) throws JAXBException {
        AuthenticationReponse reponse = new AuthenticationReponse();
        reponse.setCurrentAccessTime(new Date());
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR,13);
        reponse.setLastAccessTime(c.getTime());
        reponse.setEnterpriseName("Plastoum Titano");
        reponse.setFirstName("Jean Senile");
        reponse.setLastName("Pierre");
        reponse.setLanguage("fr");
        return new AuthenticationReponse();
    }
}