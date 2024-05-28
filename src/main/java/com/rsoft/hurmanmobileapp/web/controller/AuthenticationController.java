package com.rsoft.hurmanmobileapp.web.controller;

import com.rsoft.hurmanmobileapp.dto.AuthenticationResponse;
import com.rsoft.hurmanmobileapp.dto.LogoutReponse;
import com.rsoft.hurmanmobileapp.dto.LogoutRequest;
import com.rsoft.hurmanmobileapp.dto.AuthenticationRequest;
import com.rsoft.hurmanmobileapp.security.JwtUtil;
import com.rsoft.hurmanmobileapp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping(value = "/v1/mobile")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    //Tout ce code va etre mis au niveau de security
    @RequestMapping(value = "/Authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserId(), authenticationRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.getUserId());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        response.setJwt(jwt);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/Logout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LogoutReponse logout(LogoutRequest logoutRequest) throws JAXBException {
        return authenticationService.logout(logoutRequest);
    }
}