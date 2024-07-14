package com.rsoft.hurmanmobileapp.service;

import com.rsoft.hurmanmobileapp.dto.*;
import com.rsoft.hurmanmobileapp.proxy.DefaultProxy;
import com.rsoft.hurmanmobileapp.security.MyUserDetails;
import com.rsoft.lib.Action;
import com.rsoft.lib.RequestAttributes;
import com.rsoft.lib.dto.AuthenticationDto;
import com.rsoft.lib.model.FilterWrapper;
import com.rsoft.lib.model.UserProfile;
import com.rsoft.lib.model.XFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private DefaultProxy proxy;
    private Map<String, String> mapUsers = new HashMap<>();

    //Fake authentication, l authentification reelle se fait par le module security
    public void addUser(String username, String password) {
        mapUsers.put(username, password);
    }

    public void resetUser(String username) {
        mapUsers.remove(username);
    }

    public AuthenticationResponse loadUserInfos(AuthenticationRequest authenticationRequest) {
        AuthenticationResponse r = new AuthenticationResponse();
        r.setError("000");
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen("SUPER_SCREEN");
        requestAttributes.setAgent(authenticationRequest.getUserId());
        requestAttributes.setField1(authenticationRequest.getPassword());
        AuthenticationDto authenticationDto = proxy.authenticate(requestAttributes);
        if (!StringUtils.isEmpty(authenticationDto.getErrorCode())) {
            r.setError(authenticationDto.getErrorCode());
            r.setMessage(authenticationDto.getErrorMessage());
        } else {
            if (authenticationDto.getUserProfile() != null) {
                r.setCodeEmploye(authenticationDto.getCodeEmploye());
                r.setFirstName(authenticationDto.getUserProfile().getFirstName());
                r.setLastName(authenticationDto.getUserProfile().getLastName());
                r.setLanguage(authenticationDto.getUserProfile().getLanguage());
                r.setCodeEmploye(r.getCodeEmploye());
                r.setEnterpriseName(authenticationRequest.getEnterprise());
            } else {
                throw new IllegalArgumentException("No user found");
            }
        }
        return r;
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        ChangePasswordResponse r = new ChangePasswordResponse();
        r.setError("000");
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen("SUPER_SCREEN");
        requestAttributes.setAgent(changePasswordRequest.getUserId());
        FilterWrapper filterWrapper = new FilterWrapper();
        filterWrapper.addFilter(new XFilter("eq", "userId", "string", changePasswordRequest.getUserId()));
        UserProfile userProfile = proxy.loadUserProfiles(requestAttributes);
        if (userProfile != null) {
            requestAttributes.setFilterWrapper(null);
            requestAttributes.setAction(Action.MODIFYPASS.name());
            userProfile.setPassword(changePasswordRequest.getNewPassword());
            requestAttributes.setModels(new ArrayList<>(Arrays.asList(userProfile)));
            ArrayList<UserProfile> userProfiles = proxy.persistUserProfiles(requestAttributes);
            if (CollectionUtils.isEmpty(userProfiles)) {
                r.setError("0001");
                r.setMessage("No response from server");
            } else {
                UserProfile p = userProfiles.get(0);
                if (!org.apache.commons.lang.StringUtils.isBlank(p.getErrorCode())) {
                    r.setError(p.getErrorCode());
                    r.setMessage(p.getErrorMessage());
                }
            }
        } else {
            r.setError("0099");
            r.setMessage("User not found");
        }
        return r;
    }

    public LogoutReponse logout(LogoutRequest logoutRequest) {
        LogoutReponse r = new LogoutReponse();
        r.setError("000");
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen("SUPER_SCREEN");
        requestAttributes.setAgent(logoutRequest.getUserId());
        AuthenticationDto authenticationDto = proxy.logout(requestAttributes);
        if (!StringUtils.isEmpty(authenticationDto.getErrorCode())) {
            r.setError(authenticationDto.getErrorCode());
            r.setMessage(authenticationDto.getErrorMessage());
        }
        return r;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Fake authentication, l authentification reelle se fait par le module security
        RequestAttributes requestAttributes = new RequestAttributes();
        requestAttributes.setScreen("SUPER_SCREEN");
        requestAttributes.setAgent(username);
        FilterWrapper filterWrapper = new FilterWrapper();
        filterWrapper.addFilter(new XFilter("eq", "userId", "string", username));
        UserDetails userDetail = new MyUserDetails(username, mapUsers.get(username));
        return userDetail;
    }
}
