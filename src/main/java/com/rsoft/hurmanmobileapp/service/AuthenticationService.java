package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.AuthenticationReponse;
import com.rsoft.hurmanmobileapp.dto.AuthenticationRequest;
import com.rsoft.hurmanmobileapp.dto.LogoutReponse;
import com.rsoft.hurmanmobileapp.dto.LogoutRequest;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.dto.AuthenticationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBException;

@Service
public class AuthenticationService {

    @Autowired
    private DefaultProxy proxy;

    public AuthenticationReponse authenticate(AuthenticationRequest authenticationRequest) throws JAXBException {
        AuthenticationReponse r = new AuthenticationReponse();
        r.setErrorCode("000");
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen("SUPER_SCREEN");
        requestAttributes.setAgent(authenticationRequest.getUserId());
        requestAttributes.setField1(authenticationRequest.getPassword());
        AuthenticationDto authenticationDto = proxy.authenticate(requestAttributes);
        if (!StringUtils.isEmpty(authenticationDto.getErrorCode())) {
            r.setErrorCode(authenticationDto.getErrorCode());
            r.setErrorMessage(authenticationDto.getErrorMessage());
        } else {
//r.setPhoto();
            r.setFirstName(authenticationDto.getUserProfile().getFirstName());
            r.setLastName(authenticationDto.getUserProfile().getLastName());
//r.setLastAccessTime();
            r.setLanguage(authenticationDto.getUserProfile().getLanguage());
//r.setCurrentAccessTime();
            r.setCodeEmploye(r.getCodeEmploye());
            r.setEnterpriseName(authenticationRequest.getEnterprise());

        }
        return r;
    }

    public LogoutReponse logout(LogoutRequest logoutRequest) throws JAXBException {
        LogoutReponse r = new LogoutReponse();
        r.setErrorCode("000");
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen("SUPER_SCREEN");
        requestAttributes.setAgent(logoutRequest.getUserId());
        AuthenticationDto authenticationDto = proxy.logout(requestAttributes);
        if (!StringUtils.isEmpty(authenticationDto.getErrorCode())) {
            r.setErrorCode(authenticationDto.getErrorCode());
            r.setErrorMessage(authenticationDto.getErrorMessage());
        }
        return r;
    }

}
