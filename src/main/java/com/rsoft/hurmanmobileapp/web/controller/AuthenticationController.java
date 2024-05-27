package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.AuthenticationReponse;
import com.rsoft.hurmanmobileapp.dto.AuthenticationRequest;
import com.rsoft.hurmanmobileapp.dto.LogoutReponse;
import com.rsoft.hurmanmobileapp.dto.LogoutRequest;
import com.rsoft.hurmanmobileapp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/v1/mobile")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/Authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthenticationReponse authenticate(AuthenticationRequest authenticationRequest) throws JAXBException {
        return authenticationService.authenticate(authenticationRequest);
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LogoutReponse logout(LogoutRequest logoutRequest) throws JAXBException {
        return authenticationService.logout(logoutRequest);
    }
}